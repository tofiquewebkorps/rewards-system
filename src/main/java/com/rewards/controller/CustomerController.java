package com.rewards.controller;

import com.rewards.entity.Customer;
import com.rewards.reponse.ResponseHandler;
import com.rewards.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @GetMapping("customers")
    public ResponseEntity<Object> getCustomer(){
        try {
            List<Customer> customers = customerService.getCustomer();
            return ResponseHandler.generateResponse("", HttpStatus.OK, customers);
        }catch (Exception e) {
            log.error("Exception occured in getCustomer ::"+ e.getMessage());
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY,null) ;
        }
    }

    @GetMapping("customers/{id}")
    public ResponseEntity<Object> getCustomer(@PathVariable Long id){
        try {
            Customer customer = customerService.getCustomer(id);
            return ResponseHandler.generateResponse("", HttpStatus.OK, customer);
        }catch (Exception e) {
            log.error("Exception occured in getCustomer ::"+ e.getMessage());
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY,null) ;
        }
    }

    @PostMapping("customers")
    public ResponseEntity<Object> addCustomer(@RequestBody Customer customer){
        try {
            Customer savedCustomer = customerService.saveUpdateCustomer(customer);
            return ResponseHandler.generateResponse("", HttpStatus.OK, savedCustomer);
        }catch (Exception e) {
            log.error("Exception occured in addCustomer ::"+ e.getMessage());
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY,null) ;
        }
    }

    @PutMapping("customers")
    public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer){
        try {
            Customer updateCustomer = customerService.saveUpdateCustomer(customer);
            return ResponseHandler.generateResponse("", HttpStatus.OK, updateCustomer);
        }catch (Exception e) {
            log.error("Exception occured in updateCustomer ::"+ e.getMessage());
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY,null) ;
        }
    }

    @DeleteMapping("customers/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Long id){
        try {
            customerService.removeCustomer(id);
            return ResponseHandler.generateResponse("", HttpStatus.OK, null);
        }catch (Exception e) {
            log.error("Exception occured in deleteCustomer ::"+ e.getMessage());
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null) ;
        }
    }
}
