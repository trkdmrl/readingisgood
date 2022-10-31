package com.trkdmrl.readingisgood.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {

    private long customerId;

    private List<BookRequestDto> books;

}
