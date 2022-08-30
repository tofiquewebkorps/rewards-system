package com.rewards.service.impl;

import com.rewards.dto.RewardsDto;
import com.rewards.entity.Customer;
import com.rewards.entity.Transaction;
import com.rewards.exception.CustomerNotFoundException;
import com.rewards.service.CustomerService;
import com.rewards.service.RewardsService;
import com.rewards.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
public class RewardsServiceImpl implements RewardsService {
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CustomerService customerService;

    @Override
    public RewardsDto getRewardsMonthWise(Long id) {
        HashMap<String,Long> map= new HashMap<>();
        Customer customer = customerService.getCustomer(id);
        RewardsDto rewardsDto = new RewardsDto();
        rewardsDto.setCustomerName(customer.getName());
        rewardsDto.setTotalRewardsPoints(customer.getTotalRewardPoints());
        for(Month month:Month.values()) {
            List<Transaction> transactions = transactionService.getTransactionsByMonths(month);
            Long monthPoint = transactionService.totalRewardsPointInMonth(transactions);
            if(monthPoint!=0){
                map.put(month.name(),monthPoint);
            }
        }
        rewardsDto.setMonths(map);
        return rewardsDto;
    }
}
