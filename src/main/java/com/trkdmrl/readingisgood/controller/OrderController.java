package com.trkdmrl.readingisgood.controller;

import com.trkdmrl.readingisgood.dto.OrderResponseDto;
import com.trkdmrl.readingisgood.utils.common.ResponseMessage;
import com.trkdmrl.readingisgood.dto.OrderRequestDto;
import com.trkdmrl.readingisgood.model.Order;
import com.trkdmrl.readingisgood.service.OrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseMessage> addOrder(@RequestBody OrderRequestDto order) {
        long result = orderService.save(order);
        ResponseMessage responseMessage = new ResponseMessage("Saved", result);
        return ResponseEntity.ok(responseMessage);
    }

    @GetMapping("/getbyid")
    public OrderResponseDto getById(@RequestParam long id) {
        OrderResponseDto order = orderService.queryById(id);
        return order;
    }

    @GetMapping("/listbydateinterval")
    public List<OrderResponseDto> listByDateInterval(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<OrderResponseDto> orders = orderService.listByDateInterval(startDate, endDate);
        return orders;
    }
}
