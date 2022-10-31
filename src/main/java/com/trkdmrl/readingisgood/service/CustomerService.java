package com.trkdmrl.readingisgood.service;

import com.trkdmrl.readingisgood.model.Customer;
import com.trkdmrl.readingisgood.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    long save(Customer customer);

    Page<Order> allOrders(long id, Pageable page);
}
