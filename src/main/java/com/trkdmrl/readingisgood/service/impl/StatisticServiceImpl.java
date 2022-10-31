package com.trkdmrl.readingisgood.service.impl;

import com.trkdmrl.readingisgood.dto.MonthlyStatisticRequestDto;
import com.trkdmrl.readingisgood.dto.MonthlyStatisticResponseDto;
import com.trkdmrl.readingisgood.service.OrderService;
import com.trkdmrl.readingisgood.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticServiceImpl implements StatisticService {

    OrderService orderService;

    public StatisticServiceImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public MonthlyStatisticResponseDto getMonthlyStatistic(MonthlyStatisticRequestDto monthlyStatisticRequestDto) {
        MonthlyStatisticResponseDto monthlyStatisticResponseDto
                         = orderService.getMonthlyData(monthlyStatisticRequestDto.getCustomerId(), monthlyStatisticRequestDto.getMonth());
        return monthlyStatisticResponseDto;
    }
}
