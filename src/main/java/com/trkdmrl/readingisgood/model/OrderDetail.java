package com.trkdmrl.readingisgood.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="book_id", nullable=false)
    private Book book;

    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private Order order;

}
