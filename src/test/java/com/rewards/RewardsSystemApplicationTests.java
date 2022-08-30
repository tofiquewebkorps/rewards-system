package com.rewards;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rewards.entity.Customer;
import com.rewards.entity.Transaction;
import com.rewards.service.impl.CustomerServiceImpl;

import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@Slf4j
class RewardsSystemApplicationTests {

	public final Customer customer = new Customer();
	@Autowired private CustomerServiceImpl customerServiceImpl;
	
	//for this API @PostMapping("customers")
	@Test
	public void saveTransaction() {
		log.info("saveTfansaction has been started");
		customer.setName("test1"); customer.setTotalRewardPoints(0l);		
		Set<Transaction> transactionList = Set.of(new Transaction(120l,0l,"2022-01-02"),new Transaction(125l,0l,"2022-01-03"));
		customer.setTransactions(transactionList);
		Customer cust = customerServiceImpl.saveUpdateCustomer(customer);
		log.info("Customer Object :- "+cust.toString());
  		Customer expectedCustomer = new Customer(cust.getId(),cust.getName(),transactionList,cust.getTotalRewardPoints());
  		log.info("expectedCustomer object :- "+expectedCustomer.toString());
	    assertEquals(expectedCustomer, cust);	
	    log.info("saveTfansaction has been ended");
	}
	
	//for this API @PutMapping("customers")
	@Test
	public void updatedTransaction() {
		log.info("updatedTransaction() has been started");
		customer.setName("test1"); customer.setTotalRewardPoints(0l);		
		Set<Transaction> transactionList = Set.of(new Transaction(120l,0l,"2022-01-02"),new Transaction(125l,0l,"2022-01-03"));
		customer.setTransactions(transactionList);
		Customer cust = customerServiceImpl.saveUpdateCustomer(customer);
		log.info("updatedTransaction() Customer Object :- "+cust.toString());
  		Customer expectedCustomer = new Customer(cust.getId(),cust.getName(),transactionList,cust.getTotalRewardPoints());
  		log.info(" updatedTransaction() expectedCustomer object :- "+expectedCustomer.toString());
	    assertEquals(expectedCustomer, cust);	
	    log.info("updatedTransaction() has been ended");
	}
	
	//for this API @DeleteMapping("customers/{id}")
	@Test
	public void deleteTransaction() {
		long id = 10;
		log.info("deleteTransaction() has been started");
        customerServiceImpl.removeCustomer(id);
	    log.info("deleteTransaction() has been ended");
	}
		
	//for this API @GetMapping("customers/{id}")
//	@Test
//	public void getCustomerById() {
//		
//		log.info("getCustomer(id) has been started");
//		customer.setName("test1"); customer.setTotalRewardPoints(0l);		
//		Set<Transaction> transactionList = Set.of(new Transaction(120l,90l,"2022-01-02"),new Transaction(125l,100l,"2022-01-03"));
//		customer.setTransactions(transactionList);
//		
//		long id = 4;
//		Customer cust = customerServiceImpl.getCustomer(id);
//		
//		log.info(" getCustomer(id) Customer Object :- "+cust.toString());
//  		Customer expectedCustomer = new Customer(cust.getId(),cust.getName(),transactionList,cust.getTotalRewardPoints());
//  		log.info("getCustomer(id) expectedCustomer object :- "+expectedCustomer.toString());
//	    assertEquals(expectedCustomer, cust);	
//	    log.info("getCustomer(id) has been ended");
//	}
}
