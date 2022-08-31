package com.rewards;

import com.rewards.dto.CustomerDTO;
import com.rewards.dto.TransactionDTO;
import com.rewards.exception.TransactionNotFoundException;
import com.rewards.service.TransactionService;
import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;


@SpringBootTest
@Slf4j
public class TransactionServiceImplTests {

    
    @Autowired  private TransactionService transactionService;

    @Test
    public void getAllTransactionTest() {
        log.info("getAllTransaction() has been started");
        List<TransactionDTO> transactions = transactionService.getTransactions();
        Assertions.assertNotNull(transactions);
        Assertions.assertTrue(!transactions.isEmpty());
        log.info("getAllTransaction() has been ended");
    }

    @Test
    public void getTransactionByIdTest() {
        TransactionDTO transactionDto = new TransactionDTO();
        CustomerDTO  customerDto = new CustomerDTO();
        customerDto.setCid(7l);
        transactionDto.setAmount(170l); transactionDto.setDate(LocalDate.of(2022, 01, 025));
        transactionDto.setCustomerDTO(customerDto);
        TransactionDTO savedTransactionDTO = transactionService.saveUpdateTransaction(transactionDto);
        TransactionDTO transaction = transactionService.getTransaction(savedTransactionDTO.getTid());
        Assertions.assertNotNull(transaction);
        Assertions.assertEquals(savedTransactionDTO.getTid(), transaction.getTid());
    }

    @Test
    public void saveTransactionsTest(){
    	TransactionDTO transactionDto = new TransactionDTO();
    	CustomerDTO  customerDto = new CustomerDTO();
        customerDto.setCid(7l);
        transactionDto.setAmount(170l); transactionDto.setDate(LocalDate.of(2022, 01, 025));
        transactionDto.setCustomerDTO(customerDto);
        TransactionDTO savedTransactionDTO = transactionService.saveUpdateTransaction(transactionDto);
        Assertions.assertNotNull(savedTransactionDTO);
        Assertions.assertEquals(LocalDate.of(2022, 01, 025), savedTransactionDTO.getDate());
        Assertions.assertNotNull(savedTransactionDTO.getTid());
    }
    
    @Test
    public void updateTransactionTest(){
    	TransactionDTO transactionDto = new TransactionDTO();
    	CustomerDTO  customerDto = new CustomerDTO();
        customerDto.setCid(7l);
        transactionDto.setAmount(170l); transactionDto.setDate(LocalDate.of(2022, 01, 025));
        transactionDto.setCustomerDTO(customerDto);
        TransactionDTO savedTransactionDTO = transactionService.saveUpdateTransaction(transactionDto);
        Assertions.assertNotNull(savedTransactionDTO);
        Assertions.assertEquals(LocalDate.of(2022, 01, 025), savedTransactionDTO.getDate());
        Assertions.assertNotNull(savedTransactionDTO.getTid());
    }
    
    @Test
    public void deleteTransactionTest() {
    	TransactionDTO transactionDto = new TransactionDTO();
    	CustomerDTO  customerDto = new CustomerDTO();
        customerDto.setCid(7l);
        transactionDto.setAmount(170l); transactionDto.setDate(LocalDate.of(2022, 01, 025));
        transactionDto.setCustomerDTO(customerDto);
        TransactionDTO savedTransactionDTO = transactionService.saveUpdateTransaction(transactionDto);
        transactionService.removeTransaction(savedTransactionDTO.getTid());
        Assertions.assertThrows(TransactionNotFoundException.class,()->transactionService.getTransaction(savedTransactionDTO.getTid()));
    }


}
