package com.rewards.service.impl;

import com.rewards.dto.CustomerDTO;
import com.rewards.dto.TransactionDTO;
import com.rewards.entity.Customer;
import com.rewards.entity.Transaction;
import com.rewards.exception.CustomerNotFoundException;
import com.rewards.repository.CustomerRepository;
import com.rewards.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CustomerDTO saveUpdateCustomer(CustomerDTO customerDTO) {
        log.info("saveUpdateCustomer method started Purchase value :: "+customerDTO.getName());
//        Customer savedCustomer = customerRepository.save(customer);
        Customer customer = modelMapper.map(customerDTO,Customer.class);
          Customer savedCustomer = customerRepository.saveAndFlush(customer);
          CustomerDTO savedCustomerDTO = modelMapper.map(savedCustomer,CustomerDTO.class);
        log.info("Customer Saved Successfully customerId :: "+savedCustomer.getCid());
        return savedCustomerDTO;
    }

    @Override
    public List<CustomerDTO> getCustomers() {
        log.info("getCustomer method started.");

        List<Customer> customers = (List<Customer>) customerRepository.findAll();
        List<CustomerDTO> customerDTOS = customers.stream().map(user -> modelMapper.map(user, CustomerDTO.class)).collect(Collectors.toList());
        log.info("getCustomer method completed total customers :: "+customers.size());
        return customerDTOS;
    }

    @Override
    public void removeCustomer(Long id) {
        log.info("removeCustomer method started transaction id ::"+id);
        customerRepository.deleteById(id);
        log.info("removeCustomer method ended");
    }

    @Override
    public CustomerDTO getCustomer(Long id) {
        log.info("getTransaction method started transaction id ::"+id);
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if(customerOptional.isEmpty()){
            log.info("getCustomer Customer not found with id ::"+id);
            throw new CustomerNotFoundException("Customer not found with id ::"+id);
        }
        Customer customer = customerRepository.findById(id).get();
        CustomerDTO customerDTO = modelMapper.map(customer,CustomerDTO.class);
        System.out.println("=====================================================================");
        System.out.println("customer.getTransactions() = " + customer.getTransactions());
        log.info("getTransaction method ended");
        return customerDTO;
    }


}
