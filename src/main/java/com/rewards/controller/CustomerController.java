package com.rewards.controller;

import com.rewards.entity.Customer;
import com.rewards.entity.Transaction;
import com.rewards.service.CustomerService;
import com.rewards.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @GetMapping("customers")
    public ResponseEntity<List<Customer>> getCustomer(){
        List<Customer> customer = customerService.getCustomer();
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id){
        Customer transaction = customerService.getCustomer(id);
        return new ResponseEntity<>(transaction,HttpStatus.OK);
    }

    @PostMapping("customers")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){
        Customer savedTransaction = customerService.saveUpdateCustomer(customer);
        return new ResponseEntity<>(savedTransaction,HttpStatus.OK);
    }

    @PutMapping("customers")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
        Customer savedTransaction = customerService.saveUpdateCustomer(customer);
        return new ResponseEntity<>(savedTransaction,HttpStatus.OK);
    }

    @DeleteMapping("customers/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id){
        customerService.removeCustomer(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
