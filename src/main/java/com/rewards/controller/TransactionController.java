package com.rewards.controller;

import com.rewards.entity.Transaction;
import com.rewards.reponse.ResponseHandler;
import com.rewards.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @GetMapping("transactions")
    public ResponseEntity<Object> getTransactions(){
        try {
            List<Transaction> transactions = transactionService.getTransactions();
            return ResponseHandler.generateResponse("", HttpStatus.OK, transactions);
        }catch (Exception e) {
            log.error("Exception occured in getTransactions ::"+ e.getMessage());
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY,null) ;
        }
    }

    @GetMapping("transactions/{id}")
    public ResponseEntity<Object> getTransaction(@PathVariable Long id){
        try {
            Transaction transaction = transactionService.getTransaction(id);
            return ResponseHandler.generateResponse("", HttpStatus.OK, transaction);
        }catch (Exception e) {
            log.error("Exception occured in getTransaction ::"+ e.getMessage());
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY,id) ;
        }
    }

    @PostMapping("transactions")
    public ResponseEntity<Object> addTransaction(@RequestBody Transaction transaction){
        try {
            Transaction savedTransaction = transactionService.saveUpdateTransaction(transaction);
            return ResponseHandler.generateResponse("Transaction Done Successfully", HttpStatus.OK, savedTransaction);
        }
     catch (Exception e) {
         log.error("Exception occured in addTransaction ::"+ e.getMessage());
        return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY,transaction) ;
    }
    }

    @PutMapping("transactions")
    public ResponseEntity<Object> updateTransaction(@RequestBody Transaction transaction){
        try {
            Transaction updateTransaction = transactionService.saveUpdateTransaction(transaction);
            return ResponseHandler.generateResponse("", HttpStatus.OK, updateTransaction);
        }catch (Exception e) {
            log.error("Exception occured in updateTransaction ::"+ e.getMessage());
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY,transaction) ;
        }
    }

    @DeleteMapping("transactions/{id}")
    public ResponseEntity<Object> deleteTransaction(@PathVariable Long id){
        try {
            transactionService.removeTransaction(id);
            return ResponseHandler.generateResponse("", HttpStatus.OK, null);
        }catch (Exception e) {
            log.error("Exception occured in deleteTransaction ::"+ e.getMessage());
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR,null) ;
        }
    }

}
