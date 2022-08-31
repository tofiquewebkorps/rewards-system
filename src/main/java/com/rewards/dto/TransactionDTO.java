package com.rewards.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
