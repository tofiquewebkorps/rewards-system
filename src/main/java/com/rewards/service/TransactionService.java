package com.rewards.service;

import com.rewards.entity.Transaction;


import java.time.LocalDate;
import java.time.Month;
import java.util.List;


public interface TransactionService {

    Transaction saveUpdateTransaction(Transaction transaction);

    List<Transaction> getTransactions();

    void removeTransaction(Long id);

    Transaction getTransaction(Long id);

    List<Transaction> getTransactionsByMonths(Month month);

    Long totalRewardsPointInMonth(List<Transaction> transactions);
}
