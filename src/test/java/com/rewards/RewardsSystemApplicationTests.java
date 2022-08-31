package com.rewards;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rewards.dto.CustomerDTO;
import com.rewards.dto.TransactionDTO;
import com.rewards.entity.Customer;
import com.rewards.entity.Transaction;
import com.rewards.service.CustomerService;
import com.rewards.service.TransactionService;
import com.rewards.service.impl.CustomerServiceImpl;

import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@Slf4j
class RewardsSystemApplicationTests {

	public static final CustomerDTO customerDto = new CustomerDTO();
	public static final Customer customer = new Customer();
	public static final TransactionDTO transactionDto = new TransactionDTO();
	public static final Transaction transaction = new Transaction();
	
	@Autowired private CustomerServiceImpl customerServiceImpl;
	
	@Autowired private TransactionService transactionService;
	
	@Autowired private CustomerService customerService;
	
	//for this API @GetMapping("customers") -1
	@Test
	public void getAllCustomerTransaction() {
		log.info("getAllTransaction() has been started");
		List<CustomerDTO> customers = customerService.getCustomers();
	    log.info("getAllTransaction() has been ended");
	}
	 
	//for this API @PostMapping("customers") - 3
	@Test
	public void saveCustomerTransaction() {
		log.info("saveCustomerTransaction()-> has been started");
		customerDto.setName("test1"); customerDto.setTotalRewardPoints(0l);		
		List<TransactionDTO> transactionList = new ArrayList<TransactionDTO>();
		transactionList.add(new TransactionDTO(120l,0l,LocalDate.of(2022, 01, 02)));
		transactionList.add(new TransactionDTO(125l,0l,LocalDate.of(2022, 01, 03)));
		customerDto.setTransactions(transactionList);
		CustomerDTO cust = customerServiceImpl.saveUpdateCustomer(customerDto);
		log.info("saveCustomerTransaction()-> Customer Object :- "+cust.toString());	
	    log.info("saveCustomerTransaction()-> has been ended");
	}
	
	//for this API @PutMapping("customers") - 4
	@Test
	public void updatedCustomerTransaction(){
		log.info("updatedCustomerTransaction()-> has been started");
		customerDto.setName("test1"); customerDto.setTotalRewardPoints(0l);		
		List<TransactionDTO> transactionList = new ArrayList<TransactionDTO>();
		transactionList.add(new TransactionDTO(120l,0l,LocalDate.of(2022, 01, 02)));
		transactionList.add(new TransactionDTO(125l,0l,LocalDate.of(2022, 01, 03)));
		customerDto.setTransactions(transactionList);
		CustomerDTO cust = customerServiceImpl.saveUpdateCustomer(customerDto);
		log.info("saveCustomerTransaction()-> Customer Object :- "+cust.toString());	
	    log.info("saveCustomerTransaction()-> has been ended");
	}
	
	// @GetMapping("customers/{id}")  -2 
	 @Test
		public void getCustomerById() {
			log.info("getCustomer(id) has been started");			
			long id = 4; //id should be present in db
			CustomerDTO customer = customerService.getCustomer(id);
			log.info("getCustomer(id) has been ended:-" +customer.toString());
		}
		 
	//for this API @DeleteMapping("customers/{id}") - 5
	@Test
	public void deleteCustomerTransaction() {
		long id = 4; //id should be present in db
		log.info("deleteCustomerTransaction() has been started");
        customerServiceImpl.removeCustomer(id);
	    log.info("deleteCustomerTransaction() has been ended");
	}


	
	// test case for this API @GetMapping("transactions") - 1 
	@Test
	public void getAllTransaction() {
		log.info("getAllTransaction() has been started");
		List<TransactionDTO> transactions = transactionService.getTransactions();	
	    log.info("getAllTransaction() has been ended");
	}

   //@GetMapping("transactions/{id}") - 2
	 @Test
	public void getTransactionById() {
		log.info("getCustomer(id) has been started");			
		long id = 11; //id should be present in db
		TransactionDTO transaction = transactionService.getTransaction(id);
		log.info("getCustomer(id) has been ended");
	}
	 
	//@DeleteMapping("transactions/{id}")
	@Test
	public void deleteTransaction() {
		long id = 8; //id should be present in db
		log.info("deleteTransaction() has been started");
		transactionService.removeTransaction(id);
	    log.info("deleteTransaction() has been ended");
	}
}
