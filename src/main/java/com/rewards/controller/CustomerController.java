package com.rewards.controller;

import com.rewards.entity.Customer;
import com.rewards.reponse.ResponseHandler;
import com.rewards.service.CustomerService;
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
    public ResponseEntity<Object> getCustomer(){
        List<Customer> customers = customerService.getCustomer();
        return ResponseHandler.generateResponse("",HttpStatus.OK,customers) ;
    }

    @GetMapping("customers/{id}")
    public ResponseEntity<Object> getCustomer(@PathVariable Long id){
        Customer customer = customerService.getCustomer(id);
        return ResponseHandler.generateResponse("",HttpStatus.OK,customer) ;
    }

    @PostMapping("customers")
    public ResponseEntity<Object> addCustomer(@RequestBody Customer customer){
        Customer savedCustomer = customerService.saveUpdateCustomer(customer);
        return ResponseHandler.generateResponse("",HttpStatus.OK,savedCustomer) ;
    }

    @PutMapping("customers")
    public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer){
        Customer updateCustomer = customerService.saveUpdateCustomer(customer);
        return ResponseHandler.generateResponse("",HttpStatus.OK,updateCustomer) ;
    }

    @DeleteMapping("customers/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long id){
        customerService.removeCustomer(id);
        return ResponseHandler.generateResponse("",HttpStatus.OK,null) ;
    }
}
