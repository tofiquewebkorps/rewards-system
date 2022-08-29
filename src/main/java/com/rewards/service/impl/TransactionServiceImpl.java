package com.rewards.service.impl;

import com.rewards.entity.Transaction;
import com.rewards.repository.TransactionRepository;
import com.rewards.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction saveUpdateTransaction(Transaction transaction) {
        log.info("saveUpdateTransaction method started Purchase value :: "+transaction.getPurchase());
        Transaction savedTransaction = transactionRepository.save(transaction);
        log.info("Transaction Saved Successfully transactionId :: "+savedTransaction.getId());
        return savedTransaction;
    }

    @Override
    public List<Transaction> getTransactions() {
        log.info("getTransactions method started.");
        List<Transaction> transactions = (List<Transaction>) transactionRepository.findAll();
        log.info("getTransactions method completed total tranactions :: "+transactions.size());
        return transactions;
    }

    @Override
    public void removeTransaction(Long id) {
        log.info("removeTransaction method started transaction id ::"+id);
        transactionRepository.deleteById(id);
        log.info("removeTransaction method ended");
    }

    @Override
    public Transaction getTransaction(Long id) {
        log.info("getTransaction method started transaction id ::"+id);
        Transaction transaction = transactionRepository.findById(id).get();
        log.info("getTransaction method ended");
        return transaction;
    }
}
