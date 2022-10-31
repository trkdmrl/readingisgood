package com.trkdmrl.readingisgood;


import com.trkdmrl.readingisgood.dto.MonthlyStatisticRequestDto;
import com.trkdmrl.readingisgood.service.OrderService;
import com.trkdmrl.readingisgood.service.impl.StatisticServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StatisticServiceTest {

    StatisticServiceImpl service;

    @Mock
    OrderService orderService;

    @Before
    public void setup() {
        service = new StatisticServiceImpl(orderService);
    }

    @Test
    public void getMonthlyStatistic() {
        MonthlyStatisticRequestDto monthlyStatisticRequestDto = new MonthlyStatisticRequestDto();
        monthlyStatisticRequestDto.setMonth("OCTOBER");
        monthlyStatisticRequestDto.setCustomerId(1);
        orderService.getMonthlyData(monthlyStatisticRequestDto.getCustomerId(), monthlyStatisticRequestDto.getMonth());
        verify(orderService, atLeastOnce()).getMonthlyData(monthlyStatisticRequestDto.getCustomerId(), monthlyStatisticRequestDto.getMonth());
    }
}
