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
    @ManyToOne(targetEntity = Customer.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private Customer customer;
    private String date;
	public Transaction(Long amount, Long rewardPoints, String string) {
		super();
		this.amount = amount;
		this.rewardPoints = rewardPoints;
		this.date = string;
	}
    
    
}
