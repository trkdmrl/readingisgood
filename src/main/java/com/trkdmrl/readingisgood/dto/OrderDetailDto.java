package com.trkdmrl.readingisgood.dto;

import com.trkdmrl.readingisgood.model.Book;
import com.trkdmrl.readingisgood.model.Order;
import lombok.Data;

import javax.persistence.*;

@Data
public class OrderDetailDto {

    private long id;

    private long bookId;

    private long orderId;

}
