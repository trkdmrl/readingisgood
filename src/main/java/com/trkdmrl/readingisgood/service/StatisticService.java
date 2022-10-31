package com.trkdmrl.readingisgood.service;

import com.trkdmrl.readingisgood.dto.MonthlyStatisticRequestDto;
import com.trkdmrl.readingisgood.dto.MonthlyStatisticResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface StatisticService {

    MonthlyStatisticResponseDto getMonthlyStatistic(MonthlyStatisticRequestDto monthlyStatisticRequestDto);
}
