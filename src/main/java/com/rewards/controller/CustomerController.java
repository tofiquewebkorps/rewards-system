package com.rewards.controller;

import com.rewards.dto.CustomerDTO;
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
            List<CustomerDTO> customers = customerService.getCustomers();
            return ResponseHandler.generateResponse("", HttpStatus.OK, customers);
        }catch (Exception e) {
            log.error("Exception occured in getCustomer ::"+ e.getMessage());
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY,null) ;
        }
    }

    @GetMapping("customers/{id}")
    public ResponseEntity<Object> getCustomer(@PathVariable Long id){
        try {
            CustomerDTO customerDTO = customerService.getCustomer(id);
            return ResponseHandler.generateResponse("", HttpStatus.OK, customerDTO);
        }catch (Exception e) {
            log.error("Exception occured in getCustomer ::"+ e.getMessage());
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY,null) ;
        }
    }

    @PostMapping("customers")
    public ResponseEntity<Object> addCustomer(@RequestBody CustomerDTO customerDTO){
        try {
            CustomerDTO savedCustomerDTO = customerService.saveUpdateCustomer(customerDTO);
            return ResponseHandler.generateResponse("", HttpStatus.OK, savedCustomerDTO);
        }catch (Exception e) {
            log.error("Exception occured in addCustomer ::"+ e.getMessage());
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY,null) ;
        }
    }

    @PutMapping("customers")
    public ResponseEntity<Object> updateCustomer(@RequestBody CustomerDTO customerDTO){
        try {
            CustomerDTO updateCustomer = customerService.saveUpdateCustomer(customerDTO);
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
