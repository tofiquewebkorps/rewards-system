package com.rewards.service;

import com.rewards.entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {

    Transaction saveUpdateTransaction(Transaction transaction);

    List<Transaction> getTransactions();

    boolean removeTransaction(Long id);

    Transaction getTransaction(Long id;

}
