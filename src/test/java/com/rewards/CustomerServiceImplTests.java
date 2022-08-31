package com.rewards;

import com.rewards.dto.CustomerDTO;
import com.rewards.dto.TransactionDTO;
import com.rewards.exception.CustomerNotFoundException;
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
public class CustomerServiceImplTests {

    @Autowired
    private CustomerService customerService;


    //for this API @GetMapping("customers") -1
    @Test
    public void getCustomersIfNotAvaiableTest() {
        List<CustomerDTO> customers = customerService.getCustomers();
        Assertions.assertTrue(customers.size()==0);
    }

    @Test
    public void getCustomersIfAvaiableTest() {
        CustomerDTO customerDto = new CustomerDTO();
        customerDto.setName("Test1");
        CustomerDTO savedCustomer = customerService.saveUpdateCustomer(customerDto);
        List<CustomerDTO> customers = customerService.getCustomers();
        Assertions.assertEquals("Test1",customers.get(0).getName());
    }

    //for this API @PostMapping("customers") - 3
    @Test
    public void saveCustomerSuccessTest() {
        CustomerDTO customerDto = new CustomerDTO();
        customerDto.setName("test1");
        List<TransactionDTO> transactionList = new ArrayList<TransactionDTO>();
        transactionList.add(new TransactionDTO(120l,0l, LocalDate.of(2022, 01, 02)));
        transactionList.add(new TransactionDTO(125l,0l,LocalDate.of(2022, 01, 03)));
        customerDto.setTransactions(transactionList);
        CustomerDTO cust = customerService.saveUpdateCustomer(customerDto);
        Assertions.assertNotNull(cust);
        Assertions.assertNotNull(cust.getCid());
        Assertions.assertEquals("test1",cust.getName());
    }

    @Test
    public void saveCustomerFailerTest() {
        CustomerDTO customerDto = new CustomerDTO();
        customerDto.setName("test1");
        List<TransactionDTO> transactionList = new ArrayList<TransactionDTO>();
        transactionList.add(new TransactionDTO(120l,0l, LocalDate.of(2022, 01, 02)));
        transactionList.add(new TransactionDTO(125l,0l,LocalDate.of(2022, 01, 03)));
        customerDto.setTransactions(transactionList);
        Assertions.assertThrows(NullPointerException.class, ()->customerService.saveUpdateCustomer(null)) ;
    }


    //for this API @PutMapping("customers") - 4
    @Test
    public void updatedCustomerTransaction(){
        CustomerDTO customerDto = new CustomerDTO();
        customerDto.setName("test1");
        List<TransactionDTO> transactionList = new ArrayList<TransactionDTO>();
        transactionList.add(new TransactionDTO(120l,0l,LocalDate.of(2022, 01, 02)));
        transactionList.add(new TransactionDTO(125l,0l,LocalDate.of(2022, 01, 03)));
        customerDto.setTransactions(transactionList);
        CustomerDTO cust = customerService.saveUpdateCustomer(customerDto);
        cust.setName("Test2");
        CustomerDTO updateCustomer = customerService.saveUpdateCustomer(cust);
        Assertions.assertEquals("Test2",updateCustomer.getName());
        Assertions.assertNotNull(updateCustomer);
    }


    // @GetMapping("customers/{id}")  -2
    @Test
    public void getCustomerByIdSuccessTest() {

        CustomerDTO customerDto = new CustomerDTO();
        CustomerDTO cust = customerService.saveUpdateCustomer(customerDto);
        CustomerDTO customer = customerService.getCustomer(cust.getCid());
        Assertions.assertEquals(cust.getCid(),customer.getCid());
        Assertions.assertNotNull(customer);

    }

    @Test
    public void getCustomerByIdFailerTest() {

        CustomerDTO customerDto = new CustomerDTO();
        Assertions.assertThrows(CustomerNotFoundException.class, ()->customerService.getCustomer(0l)) ;
    }

    //for this API @DeleteMapping("customers/{id}") - 5
    @Test
    public void removeCustomerTest() {
        CustomerDTO customerDto = new CustomerDTO();
        customerDto.setName("test1");
        CustomerDTO cust = customerService.saveUpdateCustomer(customerDto);

        customerService.removeCustomer(cust.getCid());
        Assertions.assertThrows(CustomerNotFoundException.class, ()->customerService.getCustomer(cust.getCid())) ;
    }

}
