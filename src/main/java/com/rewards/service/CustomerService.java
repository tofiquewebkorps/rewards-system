package com.rewards.service;

import com.rewards.dto.CustomerDTO;
import com.rewards.dto.TransactionDTO;
import com.rewards.entity.Customer;

import java.time.Month;
import java.util.List;

public interface CustomerService{
    CustomerDTO saveUpdateCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getCustomers();

    void removeCustomer(Long id);

    CustomerDTO getCustomer(Long id);


}
