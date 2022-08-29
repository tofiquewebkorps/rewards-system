package com.rewards.controller;

import com.rewards.dto.Response;
import com.rewards.entity.Transaction;
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
    public ResponseEntity<List<Transaction>> getTransactions(){
        List<Transaction> transactions = transactionService.getTransactions();
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("transactions/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable Long id){
        Transaction transaction = transactionService.getTransaction(id);
        return new ResponseEntity<>(transaction,HttpStatus.OK);
    }

    @PostMapping("transactions")
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction){
        Transaction savedTransaction = transactionService.saveUpdateTransaction(transaction);
        return new ResponseEntity<>(savedTransaction,HttpStatus.OK);
    }

    @PutMapping("transactions")
    public ResponseEntity<Transaction> updateTransaction(@RequestBody Transaction transaction){
        Transaction savedTransaction = transactionService.saveUpdateTransaction(transaction);
        return new ResponseEntity<>(savedTransaction,HttpStatus.OK);
    }

    @DeleteMapping("transactions/{id}")
    public ResponseEntity deleteTransaction(@PathVariable Long id){
       transactionService.removeTransaction(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/transaction")
    public ResponseEntity<Response> saveCustomerTransaction(@RequestBody Transaction transaction) {
        Response response = new Response();
        try {
            if(transaction != null) {
                transaction = transactionService.saveTransaction(transaction);
                response.setObject(transaction);
                response.setStatus(true);
                response.setStatusMessage("Transaction saved successfully");
            } else {
                response.setObject(transaction);
                response.setStatus(false);
                response.setStatusMessage("Transaction not saved successfully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }

}
