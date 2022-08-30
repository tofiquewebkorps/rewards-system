package com.rewards.service;

import com.rewards.entity.Customer;

import java.util.List;

public interface CustomerService{
    Customer saveUpdateCustomer(Customer customer);

    List<Customer> getCustomers();

    void removeCustomer(Long id);

    Customer getCustomer(Long id);
}
