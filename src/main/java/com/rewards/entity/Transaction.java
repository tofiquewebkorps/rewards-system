package com.rewards.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;
    private Long amount;
    private Long rewardPoints;
    @ManyToOne(targetEntity = Customer.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "customerId", referencedColumnName = "id")
    @JsonIgnore
    private Customer customer;
    private String date;
	public Transaction(Long amount, Long rewardPoints, String string) {
		super();
		this.amount = amount;
		this.rewardPoints = rewardPoints;
		this.date = string;
	}
    
    
}
