package com.trkdmrl.readingisgood.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int stockNumber;

    private BigDecimal price;

    @OneToMany(mappedBy = "book")
    private List<OrderDetail> orderDetail;

}
