package com.trkdmrl.readingisgood.service.impl;

import com.trkdmrl.readingisgood.model.Customer;
import com.trkdmrl.readingisgood.model.Order;
import com.trkdmrl.readingisgood.repository.CustomerRepository;
import com.trkdmrl.readingisgood.repository.OrderRepository;
import com.trkdmrl.readingisgood.service.CustomerService;
import com.trkdmrl.readingisgood.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;

    OrderRepository orderRepository;

    OrderService orderService;

    public CustomerServiceImpl(CustomerRepository customerRepository, OrderRepository orderRepository, OrderService orderService) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.orderService = orderService;
    }

    @Override
    public long save(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        return savedCustomer.getId();
    }

    @Override
    public Page<Order> allOrders(long id, Pageable page) {
         return orderService.getOrdersByCustomerId(id,page);
    }

}
