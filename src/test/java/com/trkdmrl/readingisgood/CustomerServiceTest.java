package com.trkdmrl.readingisgood;

import com.trkdmrl.readingisgood.model.Customer;
import com.trkdmrl.readingisgood.model.Order;
import com.trkdmrl.readingisgood.repository.CustomerRepository;
import com.trkdmrl.readingisgood.repository.OrderRepository;
import com.trkdmrl.readingisgood.service.OrderService;
import com.trkdmrl.readingisgood.service.impl.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    private CustomerServiceImpl service;

    @Mock
    private CustomerRepository repository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderService orderService;

    @Before
    public void setup() {
        service = new CustomerServiceImpl(repository,orderRepository,orderService);
    }

    @Test
    public void save() {
        Customer bean = createCustomer();
        when(repository.save(any())).thenReturn(bean);
        long id = service.save(createCustomer());
        assertThat(id).isNotNull();
    }

    @Test
    public void allOrders() {
        Customer bean = createCustomer();
        when(repository.save(any())).thenReturn(bean);
        service.save(createCustomer());
        assertThat(bean.getOrders()).isNotNull();
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

}
