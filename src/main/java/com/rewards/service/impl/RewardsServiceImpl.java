package com.rewards.service.impl;

import com.rewards.dto.CustomerDTO;
import com.rewards.dto.RewardsDto;
import com.rewards.dto.TransactionDTO;
import com.rewards.service.CustomerService;
import com.rewards.service.RewardsService;
import com.rewards.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.HashMap;
import java.util.List;

@Service
public class RewardsServiceImpl implements RewardsService {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private CustomerService customerService;

    @Override
    public RewardsDto getRewardsMonthWise(Long id) {
        HashMap<String,Long> map= new HashMap<>();
        CustomerDTO customerDTO = customerService.getCustomer(id);
        RewardsDto rewardsDto = new RewardsDto();
        rewardsDto.setCustomerName(customerDTO.getName());
        rewardsDto.setTotalRewardsPoints(customerDTO.getTotalRewardPoints());


        for(Month month:Month.values()) {
            List<TransactionDTO> transactionDTOS = customerDTO.getTransactions();
            Long monthPoint = transactionService.totalRewardsPointInMonth(transactionService.getTransactionsByCustomerAndMonths(customerDTO,month));
            if(monthPoint>0){
                map.put(month.name(),monthPoint);
            }
        }
        rewardsDto.setMonths(map);
        return rewardsDto;
    }
}
