package com.trkdmrl.readingisgood.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customer;

    private BigDecimal price;

    private LocalDateTime orderedDate;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetail;

}
