package com.rewards.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rewards.entity.Customer;
import com.rewards.entity.Transaction;
import com.rewards.exception.CustomerNotFoundException;
import com.rewards.repository.CustomerRepository;
import com.rewards.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired private CustomerRepository customerRepository;
    @Autowired private TransactionServiceImpl transactionServiceImpl;
    
    @Override
    public Customer saveUpdateCustomer(Customer customer) {
        log.info("saveUpdateCustomer method started Purchase value :: "+customer.getName());
        Customer savedCustomer = null;
        if(customer.getTransactions() !=null) {
           Set<Transaction> transactions = customer.getTransactions();
           Set<Transaction> transaction = new HashSet<Transaction>();
           for(Transaction tr : transactions) {
        	   tr.setRewardPoints(transactionServiceImpl.calculateRewardsPoint(tr.getAmount()));
        	   transaction.add(tr);
           }
           transactions = transaction;
           customer.setTransactions(transactions);
           savedCustomer =  customerRepository.saveAndFlush(customer);	
        }
        log.info("Customer Saved Successfully customerId :: "+savedCustomer.getId());
        return savedCustomer;
    }

    @Override
    public List<Customer> getCustomers() {
        log.info("getCustomer method started.");

        List<Customer> customers = (List<Customer>) customerRepository.findAll();
        log.info("getCustomer method completed total customers :: "+customers.size());
        return customers;
    }

    @Override
    public void removeCustomer(Long id) {
        log.info("removeCustomer method started transaction id ::"+id);
        customerRepository.deleteById(id);
        log.info("removeCustomer method ended");
    }

    @Override
    public Customer getCustomer(Long id) {
        log.info("getTransaction method started transaction id ::"+id);
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if(customerOptional.isEmpty()){
            log.info("getCustomer Customer not found with id ::"+id);
            throw new CustomerNotFoundException("Customer not found with id ::"+id);
        }
        Customer customer = customerRepository.findById(id).get();
        log.info("getTransaction method ended");
        return customer;
    }
}
