package com.rewards.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;
    private Long purchase;
    private Long rewardPoint;
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;
}
