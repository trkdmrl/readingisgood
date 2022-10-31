package com.trkdmrl.readingisgood.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookDto {

    private long id;

    private int stockNumber;

    private BigDecimal price;
}
