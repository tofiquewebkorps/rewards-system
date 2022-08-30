package com.rewards.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(targetEntity = Transaction.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Set<Transaction> transactions;

    private Long totalRewardPoints = 0l;

	public Customer(String name, Long totalRewardPoints) {
		super();
		this.name = name;
		this.totalRewardPoints = totalRewardPoints;
	}

	public Customer(String name, Set<Transaction> transactions, Long totalRewardPoints) {
		super();
		this.name = name;
		this.transactions = transactions;
		this.totalRewardPoints = totalRewardPoints;
	}

	@Override  
	public boolean equals(Object obj)   
	{  
	if (obj == null)   
	return false;  
	if (obj == this)  
	return true;  
	return this.getId() == ((Customer) obj).getId();  
	}
    
}
