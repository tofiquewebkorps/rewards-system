package com.rewards;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rewards.entity.Customer;
import com.rewards.entity.Transaction;
import com.rewards.service.impl.CustomerServiceImpl;
import com.rewards.service.impl.TransactionServiceImpl;


@SpringBootTest
class RewardsSystemApplicationTests {

	@Autowired private TransactionServiceImpl transactionService;
	@Autowired private CustomerServiceImpl customerServiceImpl;
	
	@Test
	public void saveTfansaction() {
		Customer customer = new Customer();
		
		customer.setName("test1"); customer.setTotalRewardPoints(0l);		
		Set<Transaction> transactionList = Set.of(new Transaction(120l,0l,"2022-01-02"),new Transaction(125l,0l,"2022-01-03"));
		customer.setTransactions(transactionList);
		Customer cust = customerServiceImpl.saveUpdateCustomer(customer);
		System.out.println("Customer:- "+cust.toString());
  		Customer expectedCustomer = new Customer(cust.getId(),cust.getName(),transactionList,cust.getTotalRewardPoints());
  		System.out.println("expectedCustomer:- "+expectedCustomer.toString());
  		boolean flag = cust.equals(expectedCustomer);
  		System.out.println("flag:- "+flag);
	    assertEquals(expectedCustomer, cust);	
	}
}
