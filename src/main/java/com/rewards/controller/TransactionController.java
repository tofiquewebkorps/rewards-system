package com.rewards.controller;

import com.rewards.dto.Response;
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
        List<Transaction> transactions = transactionService.getTransactions();
        return ResponseHandler.generateResponse("",HttpStatus.OK,transactions) ;
    }

    @GetMapping("transactions/{id}")
    public ResponseEntity<Object> getTransaction(@PathVariable Long id){
        Transaction transaction = transactionService.getTransaction(id);
        return ResponseHandler.generateResponse("",HttpStatus.OK,transaction) ;
    }

    @PostMapping("transactions")
    public ResponseEntity<Object> addTransaction(@RequestBody Transaction transaction){
        try {
            Transaction savedTransaction = transactionService.saveUpdateTransaction(transaction);
            return ResponseHandler.generateResponse("", HttpStatus.OK, savedTransaction);
        }
     catch (Exception e) {
        e.printStackTrace();
        return ResponseHandler.generateResponse("Transaction not saved successfully",HttpStatus.UNPROCESSABLE_ENTITY,transaction) ;
    }
    }

    @PutMapping("transactions")
    public ResponseEntity<Object> updateTransaction(@RequestBody Transaction transaction){
        Transaction updateTransaction = transactionService.saveUpdateTransaction(transaction);
        return ResponseHandler.generateResponse("",HttpStatus.OK,updateTransaction) ;
    }

    @DeleteMapping("transactions/{id}")
    public ResponseEntity<Object> deleteTransaction(@PathVariable Long id){
       transactionService.removeTransaction(id);
        return ResponseHandler.generateResponse("",HttpStatus.OK,null) ;
    }

    @PostMapping("/transaction")
    public ResponseEntity<Object> saveCustomerTransaction(@RequestBody Transaction transaction) {

        try {

                transaction = transactionService.saveTransaction(transaction);

                return ResponseHandler.generateResponse("Transaction saved successfully",HttpStatus.OK,transaction) ;

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateResponse("Transaction not saved successfully",HttpStatus.UNPROCESSABLE_ENTITY,transaction) ;
        }

    }

}
