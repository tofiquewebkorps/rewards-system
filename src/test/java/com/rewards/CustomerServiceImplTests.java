package com.rewards;

import com.rewards.dto.CustomerDTO;
import com.rewards.dto.TransactionDTO;
import com.rewards.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
public class CustomerServiceImplTests {

    public static final CustomerDTO customerDto = new CustomerDTO();
    @Autowired
    private CustomerService customerService;


    //for this API @GetMapping("customers") -1
    @Test
    public void getCustomersTest() {
        log.info("getAllTransaction() has been started");
        List<CustomerDTO> customers = customerService.getCustomers();
        log.info("getAllTransaction() has been ended");
    }

    //for this API @PostMapping("customers") - 3
    @Test
    public void saveCustomerTest() {
        log.info("saveCustomerTransaction()-> has been started");
        customerDto.setName("test1"); customerDto.setTotalRewardPoints(0l);
        List<TransactionDTO> transactionList = new ArrayList<TransactionDTO>();
        transactionList.add(new TransactionDTO(120l,0l, LocalDate.of(2022, 01, 02)));
        transactionList.add(new TransactionDTO(125l,0l,LocalDate.of(2022, 01, 03)));
        customerDto.setTransactions(transactionList);
        CustomerDTO cust = customerService.saveUpdateCustomer(customerDto);
        log.info("saveCustomerTransaction()-> Customer Object :- "+cust.toString());
        log.info("saveCustomerTransaction()-> has been ended");
    }


    //for this API @PutMapping("customers") - 4
    @Test
    public void updatedCustomerTransaction(){
        log.info("updatedCustomerTransaction()-> has been started");
        customerDto.setName("test1"); customerDto.setTotalRewardPoints(0l);
        List<TransactionDTO> transactionList = new ArrayList<TransactionDTO>();
        transactionList.add(new TransactionDTO(120l,0l,LocalDate.of(2022, 01, 02)));
        transactionList.add(new TransactionDTO(125l,0l,LocalDate.of(2022, 01, 03)));
        customerDto.setTransactions(transactionList);
        CustomerDTO cust = customerService.saveUpdateCustomer(customerDto);
        log.info("saveCustomerTransaction()-> Customer Object :- "+cust.toString());
        log.info("saveCustomerTransaction()-> has been ended");
    }


    // @GetMapping("customers/{id}")  -2
    @Test
    public void getCustomerByIdTest() {
        log.info("getCustomer(id) has been started");
        long id = 4; //id should be present in db
        CustomerDTO customer = customerService.getCustomer(id);
        log.info("getCustomer(id) has been ended:-" +customer.toString());
    }

    //for this API @DeleteMapping("customers/{id}") - 5
    @Test
    public void removeCustomerTest() {
        long id = 4; //id should be present in db
        log.info("deleteCustomerTransaction() has been started");
        customerService.removeCustomer(id);
        log.info("deleteCustomerTransaction() has been ended");
    }

}
