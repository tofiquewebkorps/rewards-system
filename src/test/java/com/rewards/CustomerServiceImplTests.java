package com.rewards;

import com.rewards.dto.CustomerDTO;
import com.rewards.dto.TransactionDTO;
import com.rewards.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
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
    public void getCustomersIfNotAvaiableTest() {
        log.info("getAllTransaction() has been started");
        List<CustomerDTO> customers = customerService.getCustomers();
        Assertions.assertTrue(customers.size()==0);
        log.info("getAllTransaction() has been ended");
    }

    @Test
    public void getCustomersIfAvaiableTest() {
        log.info("getAllTransaction() has been started");
        customerDto.setName("Test1");
        CustomerDTO savedCustomer = customerService.saveUpdateCustomer(customerDto);
        List<CustomerDTO> customers = customerService.getCustomers();
        Assertions.assertEquals(customers.get(0).getName(),"Test1");
        log.info("getAllTransaction() has been ended");
    }

    //for this API @PostMapping("customers") - 3
    @Test
    public void saveCustomerSuccessTest() {
        log.info("saveCustomerTransaction()-> has been started");
        customerDto.setName("test1");
        List<TransactionDTO> transactionList = new ArrayList<TransactionDTO>();
        transactionList.add(new TransactionDTO(120l,0l, LocalDate.of(2022, 01, 02)));
        transactionList.add(new TransactionDTO(125l,0l,LocalDate.of(2022, 01, 03)));
        customerDto.setTransactions(transactionList);
        CustomerDTO cust = customerService.saveUpdateCustomer(customerDto);
        Assertions.assertNotNull(cust);
        Assertions.assertNotNull(cust.getCid());
        Assertions.assertEquals(cust.getName(),"test1");
        log.info("saveCustomerTransaction()-> Customer Object :- "+cust.toString());
        log.info("saveCustomerTransaction()-> has been ended");
    }

    @Test
    public void saveCustomerFailerTest() {
        log.info("saveCustomerTransaction()-> has been started");
        customerDto.setName("test1");
        List<TransactionDTO> transactionList = new ArrayList<TransactionDTO>();
        transactionList.add(new TransactionDTO(120l,0l, LocalDate.of(2022, 01, 02)));
        transactionList.add(new TransactionDTO(125l,0l,LocalDate.of(2022, 01, 03)));
        customerDto.setTransactions(transactionList);
        Assertions.assertThrows(NullPointerException.class, ()->customerService.saveUpdateCustomer(null)) ;
        log.info("saveCustomerTransaction()-> has been ended");
    }


    //for this API @PutMapping("customers") - 4
    @Test
    public void updatedCustomerTransaction(){
        log.info("updatedCustomerTransaction()-> has been started");
        customerDto.setName("test1");
        List<TransactionDTO> transactionList = new ArrayList<TransactionDTO>();
        transactionList.add(new TransactionDTO(120l,0l,LocalDate.of(2022, 01, 02)));
        transactionList.add(new TransactionDTO(125l,0l,LocalDate.of(2022, 01, 03)));
        customerDto.setTransactions(transactionList);
        CustomerDTO cust = customerService.saveUpdateCustomer(customerDto);
        cust.setName("Test2");
        CustomerDTO updateCustomer = customerService.saveUpdateCustomer(cust);
        Assertions.assertEquals(updateCustomer.getName(),"Test2");
        Assertions.assertNotNull(updateCustomer);
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
