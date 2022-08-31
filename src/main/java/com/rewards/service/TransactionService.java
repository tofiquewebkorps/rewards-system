package com.rewards.service;

import com.rewards.dto.CustomerDTO;
import com.rewards.dto.TransactionDTO;

import java.time.Month;
import java.util.List;


public interface TransactionService {

    TransactionDTO saveUpdateTransaction(TransactionDTO transactionDTO);

    List<TransactionDTO> getTransactions();

    void removeTransaction(Long id);

    TransactionDTO getTransaction(Long id);


    Long totalRewardsPointInMonth(List<TransactionDTO> transactionDTOS);

    List<TransactionDTO> getTransactionsByCustomerAndMonths(CustomerDTO customerDTO, Month month);
}
