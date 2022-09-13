package com.rewards.service;

import com.rewards.dto.CustomerDTO;
import java.util.List;

public interface   CustomerService{
    CustomerDTO saveUpdateCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getCustomers();

    void removeCustomerDetailsById(Long id);

    CustomerDTO getCustomerDetailsById(Long id);


}
