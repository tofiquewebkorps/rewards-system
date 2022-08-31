package com.rewards.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class CustomerDTO {


    private Long cid;

    private String name;

    private List<TransactionDTO> transactions;

    private Long totalRewardPoints = 0l;

}
