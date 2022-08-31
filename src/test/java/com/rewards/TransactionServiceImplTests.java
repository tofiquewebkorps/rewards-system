package com.rewards;

import com.rewards.dto.TransactionDTO;
import com.rewards.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
@Slf4j
public class TransactionServiceImplTests {

    public static final TransactionDTO transactionDto = new TransactionDTO();

    @Autowired
    private TransactionService transactionService;


    // test case for this API @GetMapping("transactions") - 1
    @Test
    public void getAllTransactionTest() {
        log.info("getAllTransaction() has been started");
        List<TransactionDTO> transactions = transactionService.getTransactions();
        log.info("getAllTransaction() has been ended");
    }

    //@GetMapping("transactions/{id}") - 2
    @Test
    public void getTransactionByIdTest() {
        log.info("getCustomer(id) has been started");
        long id = 11; //id should be present in db
        TransactionDTO transaction = transactionService.getTransaction(id);
        log.info("getCustomer(id) has been ended");
    }

    //@DeleteMapping("transactions/{id}")
    @Test
    public void deleteTransactionTest() {
        long id = 8; //id should be present in db
        log.info("deleteTransaction() has been started");
        transactionService.removeTransaction(id);
        log.info("deleteTransaction() has been ended");
    }


}
