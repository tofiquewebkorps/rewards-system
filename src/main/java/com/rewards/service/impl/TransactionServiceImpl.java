package com.rewards.service.impl;

import com.rewards.entity.Customer;
import com.rewards.entity.Transaction;
import com.rewards.exception.CustomerNotFoundException;
import com.rewards.repository.CustomerRepository;
import com.rewards.repository.TransactionRepository;
import com.rewards.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Transaction saveUpdateTransaction(Transaction transaction) {
        log.info("saveUpdateTransaction method started Purchase value :: "+transaction.getAmount());

        Customer customer = transaction.getCustomer();
        if(customer==null){
            throw new CustomerNotFoundException("Without Customer Transaction would not Happen");
        }
        Optional<Customer> existCustomerOptional = customerRepository.findById(customer.getId());
        if(existCustomerOptional.isEmpty()){
            throw new CustomerNotFoundException("Customer Not Exist");
        }
        transaction.setRewardPoints(calculateRewardsPoint(transaction.getAmount()));
        Customer existCustomer = existCustomerOptional.get();
        existCustomer.setTotalRewardPoints(existCustomer.getTotalRewardPoints()+transaction.getRewardPoints());
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

    private static Long calculateRewardsPoint(Long amount){
        Long rewardsPoint = 0l;
        amount = amount-50;
        if(amount>0){
            if(amount/50<1) {
                rewardsPoint = amount;
            }else{
                rewardsPoint = 50 + ((amount-50)*2);
            }
        }
        return rewardsPoint;
    }
}
