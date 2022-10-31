package com.trkdmrl.readingisgood.service;

import com.trkdmrl.readingisgood.dto.MonthlyStatisticResponseDto;
import com.trkdmrl.readingisgood.dto.OrderRequestDto;
import com.trkdmrl.readingisgood.dto.OrderResponseDto;
import com.trkdmrl.readingisgood.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {

    long save(OrderRequestDto order);

    OrderResponseDto queryById(long id);

    List<OrderResponseDto> listByDateInterval(LocalDateTime startDate, LocalDateTime endDate);

    MonthlyStatisticResponseDto getMonthlyData(long customerId, String month);

    Page<Order> getOrdersByCustomerId(long customerId, Pageable pageable);

}
