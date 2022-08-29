package com.rewards.service;

import com.rewards.entity.Transaction;


import java.util.List;


public interface TransactionService {

    Transaction saveUpdateTransaction(Transaction transaction);

    List<Transaction> getTransactions();

    void removeTransaction(Long id);

    Transaction getTransaction(Long id);

   Transaction saveTransaction(Transaction transaction);
}
