package com.trkdmrl.readingisgood.controller;

import com.trkdmrl.readingisgood.utils.common.ResponseMessage;
import com.trkdmrl.readingisgood.model.Customer;
import com.trkdmrl.readingisgood.model.Order;
import com.trkdmrl.readingisgood.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customer")
public class CustomerController {

    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseMessage> addCustomer(@RequestBody Customer customer) {
        long result = customerService.save(customer);
        ResponseMessage responseMessage = new ResponseMessage("Saved",result);
        return ResponseEntity.ok(responseMessage);
    }

    @GetMapping("/getAllOrders/{customerid}")
    public ResponseEntity<Page<Order>> allOrders(@PathVariable long customerid, Pageable page) {
        Page<Order> orders = customerService.allOrders(customerid, page);
        return ResponseEntity.ok(orders);
    }
}
