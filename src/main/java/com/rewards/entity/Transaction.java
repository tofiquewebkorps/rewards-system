package com.rewards.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;
    private Long purchase;


}
