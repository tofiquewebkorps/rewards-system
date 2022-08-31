package com.rewards.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter

public class TransactionDTO {

    private Long tid;
    private Long amount;
    private Long rewardPoints;
    private CustomerDTO customerDTO;
    private LocalDate date;
}
