package com.trkdmrl.readingisgood.controller;

import com.trkdmrl.readingisgood.dto.MonthlyStatisticRequestDto;
import com.trkdmrl.readingisgood.dto.MonthlyStatisticResponseDto;
import com.trkdmrl.readingisgood.service.StatisticService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/statistic")
public class StatisticController {

    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/getcustomersmonthlystatistic")
    public ResponseEntity<MonthlyStatisticResponseDto> getMonthlyStatistic(@RequestBody MonthlyStatisticRequestDto monthlyStatisticRequestDto) {
         MonthlyStatisticResponseDto monthlyStatisticResponseDto = statisticService.getMonthlyStatistic(monthlyStatisticRequestDto);
        return ResponseEntity.ok(monthlyStatisticResponseDto);
    }
}
