package com.rewards.repository;

import com.rewards.entity.Customer;
import com.rewards.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction,Long> {

    List<Transaction> findAllByCustomerAndDateBetween(Customer customer, LocalDate startDate,
                                                      LocalDate endDate);
}
