package com.trkdmrl.readingisgood.model;

import com.trkdmrl.readingisgood.utils.UniqueEmail;
import com.trkdmrl.readingisgood.utils.UniqueUsername;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@ToString(callSuper = true)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;

    private String password;

    private String email;

    @OneToMany(mappedBy="customer")
    private List<Order> orders;

    private String token;

}
