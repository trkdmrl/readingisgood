package com.trkdmrl.readingisgood.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MonthlyStatisticResponseDto {

    private String month;

    private int totalOrderCount;

    private int totalBookCount;

    private BigDecimal totalPurchasedCount;
}
