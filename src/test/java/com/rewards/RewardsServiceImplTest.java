package com.rewards;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.rewards.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class RewardsServiceImplTest {

	 @Autowired private CustomerService customerService;
	 
	 @Test
	 public void getRewardMonthWise() {
		
	 }
}
