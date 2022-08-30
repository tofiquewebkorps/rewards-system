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

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
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
        Transaction savedTransaction = transactionRepository.save(transaction);
        Customer existCustomer = existCustomerOptional.get();
        existCustomer.setTotalRewardPoints(existCustomer.getTotalRewardPoints()+transaction.getRewardPoints());
        Customer updateCustomer = customerRepository.save(existCustomer);
        savedTransaction.setCustomer(updateCustomer);
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

    @Override
    public List<Transaction> getTransactionsByMonths(Month month) {
        log.info("getTransactionsByMonths method started MONTH ::"+month);
        LocalDate startDate = LocalDate.of(2022, month,1);
        LocalDate endDate = LocalDate.of(2022,month, startDate.withDayOfMonth(
                startDate.getMonth().length(startDate.isLeapYear())).getDayOfMonth());
        List<Transaction> transactions = (List<Transaction>) transactionRepository.findAllByDateBetween(startDate,endDate);
        log.info("getTransactionsByMonths method completed total transactions :: "+transactions.size());
        return transactions;
    }

    public Long totalRewardsPointInMonth(List<Transaction> transactions){
        log.info("totalRewardsPointInMonth method started total transactions :: "+transactions.size());
        Long totalReward = 0l;
        for(Transaction transaction : transactions){
            totalReward = totalReward + transaction.getRewardPoints();
        }
        log.info("totalRewardsPointInMonth method ended total Reward :: "+totalReward);
        return totalReward;
    }

    protected  Long calculateRewardsPoint(Long amount){
        log.info("calculateRewardsPoint method started purchase amount ::"+amount);
        Long rewardsPoint = 0l;
        amount = amount-50;
        if(amount>0){
            if(amount/50<1) {
                rewardsPoint = amount;
            }else{
                rewardsPoint = 50 + ((amount-50)*2);
            }
        }
        log.info("calculateRewardsPoint method ended reward point ::"+rewardsPoint);
        return rewardsPoint;
    }
}
