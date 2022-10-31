package com.trkdmrl.readingisgood.service.impl;

import com.trkdmrl.readingisgood.dto.*;
import com.trkdmrl.readingisgood.exception.GenericException;
import com.trkdmrl.readingisgood.model.Book;
import com.trkdmrl.readingisgood.model.Customer;
import com.trkdmrl.readingisgood.model.Order;
import com.trkdmrl.readingisgood.model.OrderDetail;
import com.trkdmrl.readingisgood.repository.BookRepository;
import com.trkdmrl.readingisgood.repository.CustomerRepository;
import com.trkdmrl.readingisgood.repository.OrderDetailRepository;
import com.trkdmrl.readingisgood.repository.OrderRepository;
import com.trkdmrl.readingisgood.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository;

    BookRepository bookRepository;

    CustomerRepository customerRepository;

    OrderDetailRepository orderDetailRepository;

    public OrderServiceImpl(OrderRepository orderRepository, BookRepository bookRepository, CustomerRepository customerRepository, OrderDetailRepository orderDetailRepository) {
        this.orderRepository = orderRepository;
        this.bookRepository  = bookRepository;
        this.customerRepository = customerRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    @Transactional
    @Override
    public long save(OrderRequestDto orderRequestDto) {
        if(orderRequestDto == null || orderRequestDto.getCustomerId() == 0) {
            throw new GenericException("Request Dto is wrong");
        }
       Optional<Customer> customer = customerRepository.findById(orderRequestDto.getCustomerId());
       if(customer == null || !customer.isPresent()) {
           throw new GenericException("Customer not found");
       }
       Order order = new Order();
       BigDecimal totalPrice = new BigDecimal(0);
       List<OrderDetail> orderDetails = new ArrayList<>();
       order.setCustomer(customer.get());
       order.setOrderedDate(LocalDateTime.now());
       for(BookRequestDto bookRequestDto : orderRequestDto.getBooks()) {
           Book book = bookRepository.findById(bookRequestDto.getBookId()).orElseThrow(RuntimeException::new);
           checkStock(book, bookRequestDto.getQuantity());
           updateStock(book, bookRequestDto.getQuantity());
           totalPrice = totalPrice.add(book.getPrice());
           OrderDetail orderDetail = OrderDetail.builder().book(book).order(order).build();
           orderDetails.add(orderDetail);
       }
       order.setPrice(totalPrice);
       order.setOrderDetail(orderDetails);

       Order savedOrder = orderRepository.save(order);
        orderDetailRepository.saveAll(orderDetails);
       return savedOrder.getId();
    }

    @Override
    public OrderResponseDto queryById(long id) {
        Order order = orderRepository.findById(id).orElseThrow(RuntimeException::new);
        OrderResponseDto orderResponseDto = orderCustomer(order);
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrder_Id(order.getId());
        List<BookDto> books = orderBooks(orderDetails);
        orderResponseDto.setBooks(books);
        return orderResponseDto;
    }

    @Override
    public List<OrderResponseDto> listByDateInterval(LocalDateTime startDate, LocalDateTime endDate) {

        List<Order> orders = orderRepository.findByOrderedDateBetween(startDate, endDate);
        List<OrderResponseDto> orderDtos = new ArrayList<>();
        for(Order order : orders) {
            OrderResponseDto orderResponseDto = orderCustomer(order);
            List<BookDto> books = orderBooks(order.getOrderDetail());
            orderResponseDto.setBooks(books);
            orderDtos.add(orderResponseDto);
        }
        return orderDtos;
    }

    @Override
    public MonthlyStatisticResponseDto getMonthlyData(long customerId, String month) {
        MonthlyStatisticResponseDto monthlyStatisticResponseDto = new MonthlyStatisticResponseDto();
        List<Order> orders = getAllOrders(customerId);
        int totatBookCount = 0;
        BigDecimal totalPurchasedCount = new BigDecimal(0);
        List<Order> filteredOrders =  filterOrdersByMonth(orders, month);
        monthlyStatisticResponseDto.setTotalOrderCount(filteredOrders.size());
        for(Order order : filteredOrders) {
            totatBookCount += order.getOrderDetail().size();
            totalPurchasedCount = totalPurchasedCount.add(order.getPrice());
        }
        monthlyStatisticResponseDto.setTotalBookCount(totatBookCount);
        monthlyStatisticResponseDto.setTotalPurchasedCount(totalPurchasedCount);
        monthlyStatisticResponseDto.setMonth(month);

        return monthlyStatisticResponseDto;
    }

    @Override
    public Page<Order> getOrdersByCustomerId(long customerId, Pageable pageable) {
        Page<Order> pageOrders = orderRepository.findByCustomerId(customerId, pageable);
        return pageOrders;
    }

    public List<Order> getAllOrders(long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer == null || !customer.isPresent()) {
            throw new GenericException("Customer Not Found");
        }
        List<Order> orders = customer.get().getOrders();
        return orders;
    }

    public List<Order> filterOrdersByMonth(List<Order> orders, String month) {
        return orders
                .stream()
                .filter(x -> x.getOrderedDate().getMonth().toString().contains(month.toUpperCase()))
                .collect(Collectors.toList());
    }

    public void checkStock(Book book, int quantity) {
        if(quantity <= 0) {
            throw new GenericException("Quantity is less than zero or equal to zero");
        }
        if(book.getStockNumber() < quantity) {
            throw new GenericException("Out Of Stock");
        }
    }

    public void updateStock(Book book, int quantity) {
        int oldStockNumber = book.getStockNumber();
        int newStockNumber = oldStockNumber - quantity;
        book.setStockNumber(newStockNumber);
        bookRepository.save(book);
    }

    public List<BookDto> orderBooks(List<OrderDetail> orderDetails) {
        List<BookDto> books = new ArrayList<>();
        for(OrderDetail orderDetail : orderDetails) {
            Book book = bookRepository.findById(orderDetail.getBook().getId()).orElseThrow(RuntimeException::new);
            BookDto bookDto = new BookDto();
            bookDto.setId(book.getId());
            bookDto.setPrice(book.getPrice());
            bookDto.setStockNumber(book.getStockNumber());
            books.add(bookDto);
        }
        return books;
    }

    public OrderResponseDto orderCustomer(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setOrderedDate(order.getOrderedDate());

        orderResponseDto.setPrice(order.getPrice());
        Optional<Customer> customer = customerRepository.findById(order.getCustomer().getId());
        if(!customer.isPresent()) {
            throw new GenericException("Customer not found");
        }
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.get().getId());
        customerDto.setUsername(customer.get().getUsername());
        customerDto.setEmail(customer.get().getEmail());
        orderResponseDto.setCustomer(customerDto);
        return orderResponseDto;
    }


}
