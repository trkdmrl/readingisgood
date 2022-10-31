package com.trkdmrl.readingisgood.dto;

import lombok.Data;

@Data
public class MonthlyStatisticRequestDto {

    private String month;

    private long customerId;
}
