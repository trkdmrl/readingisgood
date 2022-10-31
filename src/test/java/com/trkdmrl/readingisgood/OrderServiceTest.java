package com.trkdmrl.readingisgood;

import com.trkdmrl.readingisgood.dto.*;
import com.trkdmrl.readingisgood.model.Book;
import com.trkdmrl.readingisgood.model.Customer;
import com.trkdmrl.readingisgood.model.Order;
import com.trkdmrl.readingisgood.model.OrderDetail;
import com.trkdmrl.readingisgood.repository.BookRepository;
import com.trkdmrl.readingisgood.repository.CustomerRepository;
import com.trkdmrl.readingisgood.repository.OrderDetailRepository;
import com.trkdmrl.readingisgood.repository.OrderRepository;
import com.trkdmrl.readingisgood.service.impl.OrderServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    OrderServiceImpl service;

    @Mock
    OrderRepository orderRepository;

    @Mock
    BookRepository bookRepository;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    OrderDetailRepository orderDetailRepository;

    @Before
    public void setup() {
        service = new OrderServiceImpl(orderRepository,bookRepository,customerRepository,orderDetailRepository);
    }

    @Test
    public void save() {
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        List<BookRequestDto> books = new ArrayList<>();
        books.add(new BookRequestDto());
        orderRequestDto.setCustomerId(createCustomer().getId());
        orderRequestDto.setBooks(books);
        createCustomer();
        Order order = orderRepository.save(createOrder());
        verify(orderRepository, atLeastOnce()).save(any());
    }

    @Test
    public void queryById() {
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrder_Id(createOrder().getId());
        List<BookDto> books = service.orderBooks(orderDetails);
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(1);
        customerDto.setUsername("rig");
        assertThat(customerDto).isNotNull();
        orderResponseDto.setCustomer(customerDto);
        orderResponseDto.setBooks(books);
        assertThat(orderResponseDto).isNotNull();
    }

    @Test
    public void getMonthlyData() {
        List<Order> orders = new ArrayList<>();
        orders.add(createOrder());
        List<Order> filteredOrders = service.filterOrdersByMonth(orders, "OCTOBER");
        assertThat(filteredOrders).isNotNull();
    }

    @Test
    public void checkStock() {
        Book book = new Book();
        book.setId(1);
        book.setStockNumber(3);
        service.checkStock(book,2);
        assertThat(book.getStockNumber()).isNotNull();

    }

    @Test
    public void updateStock() {
        Book book = new Book();
        book.setId(1);
        book.setStockNumber(3);
        service.updateStock(book,2);
        bookRepository.save(book);
        assertThat(book.getStockNumber()).isNotNull();
    }

    private static Customer createCustomer() {
        Customer customer = new Customer();
        List<Order> orders = new ArrayList<>();
        customer.setId(1);
        customer.setEmail("abc@gmail.com");
        customer.setPassword("1234");
        customer.setOrders(orders);
        return customer;
    }

    private static Order createOrder() {
         Order order = new Order();
         order.setId(1);
         order.setPrice(new BigDecimal("10"));
         order.setCustomer(createCustomer());
         order.setOrderedDate(LocalDateTime.now());
         List<OrderDetail> orderDetails = new ArrayList<>();
         orderDetails.add(new OrderDetail(1,new Book(),new Order()));
         order.setOrderDetail(orderDetails);
         return order;
    }


}
