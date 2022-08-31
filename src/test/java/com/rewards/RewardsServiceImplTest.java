package com.rewards;

import com.rewards.dto.CustomerDTO;
import com.rewards.dto.RewardsDto;
import com.rewards.dto.TransactionDTO;
import com.rewards.entity.Transaction;
import com.rewards.service.RewardsService;
import com.rewards.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rewards.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;

@SpringBootTest
@Slf4j
public class RewardsServiceImplTest {

	 @Autowired private CustomerService customerService;

	 @Autowired private TransactionService transactionService;

	 @Autowired private RewardsService rewardsService;
	 
	 @Test
	 public void getRewardMonthWise() {

		 TransactionDTO transactionDto = new TransactionDTO();
		 CustomerDTO  customerDto = new CustomerDTO();
		 customerDto.setName("test1");
		 CustomerDTO customerDTO= customerService.saveUpdateCustomer(customerDto);
		 transactionDto.setAmount(170l); transactionDto.setDate(LocalDate.of(2022, 01, 025));
		 transactionDto.setCustomerDTO(customerDTO);
		 TransactionDTO savedTransactionDTO = transactionService.saveUpdateTransaction(transactionDto);
		 RewardsDto rewardsDto = rewardsService.getRewardsMonthWise(customerDTO.getCid());
		 Assertions.assertNotNull(rewardsDto);
		 Assertions.assertTrue(rewardsDto.getMonths().size()>0);
		 Assertions.assertNotNull(rewardsDto.getMonths());

		
	 }
}
