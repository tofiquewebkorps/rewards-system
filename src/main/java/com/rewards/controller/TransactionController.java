package com.rewards.controller;

import com.rewards.dto.TransactionDTO;
import com.rewards.entity.Customer;
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
            List<TransactionDTO> transactionDTOS= transactionService.getTransactions();
            return ResponseHandler.generateResponse("", HttpStatus.OK, transactionDTOS);
        }catch (Exception e) {
            log.error("Exception occured in getTransactions ::"+ e.getMessage());
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY,null) ;
        }
    }

    @GetMapping("transactions/{id}")
    public ResponseEntity<Object> getTransaction(@PathVariable Long id){
        try {
            TransactionDTO transactionDTO = transactionService.getTransaction(id);
            return ResponseHandler.generateResponse("", HttpStatus.OK, transactionDTO);
        }catch (Exception e) {
            log.error("Exception occured in getTransaction ::"+ e.getMessage());
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY,id) ;
        }
    }

    @PostMapping("transactions")
    public ResponseEntity<Object> addTransaction(@RequestBody TransactionDTO transactionDTO){
        try {
            TransactionDTO savedTransactionDTO = transactionService.saveUpdateTransaction(transactionDTO);
            return ResponseHandler.generateResponse("Transaction Done Successfully", HttpStatus.OK, savedTransactionDTO);
        }
     catch (Exception e) {
         log.error("Exception occured in addTransaction ::"+ e.getMessage());
        return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY,transactionDTO) ;
    }
    }

    @PutMapping("transactions")
    public ResponseEntity<Object> updateTransaction(@RequestBody TransactionDTO transactionDTO){
        try {
            TransactionDTO savedTransactionDTO = transactionService.saveUpdateTransaction(transactionDTO);
            return ResponseHandler.generateResponse("", HttpStatus.OK, savedTransactionDTO);
        }catch (Exception e) {
            log.error("Exception occured in updateTransaction ::"+ e.getMessage());
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY,transactionDTO) ;
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
