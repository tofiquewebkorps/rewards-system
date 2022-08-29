package com.rewards.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;
    private Long amount;
    private Long rewardPoints;
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
    private LocalDate date;
}
