package com.rewards.service.impl;

import com.rewards.entity.Customer;
import com.rewards.repository.CustomerRepository;
import com.rewards.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer saveUpdateCustomer(Customer customer) {
        log.info("saveUpdateCustomer method started Purchase value :: "+customer.getName());
        Customer savedCustomer = customerRepository.save(customer);
        log.info("Customer Saved Successfully customerId :: "+savedCustomer.getId());
        return savedCustomer;
    }

    @Override
    public List<Customer> getCustomer() {
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
        Customer customer = customerRepository.findById(id).get();
        log.info("getTransaction method ended");
        return customer;
    }
}
