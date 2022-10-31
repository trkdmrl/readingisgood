package com.trkdmrl.readingisgood.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
}
