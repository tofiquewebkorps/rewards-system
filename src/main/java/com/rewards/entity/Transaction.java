package com.rewards.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tid;
    private Long amount;
    private Long rewardPoints;
    @ManyToOne(targetEntity = Customer.class,cascade = CascadeType.MERGE)
    @JoinColumn(name = "CUSTOMER_ID",referencedColumnName = "CID")
    private Customer customer;
    private LocalDate date;
}
