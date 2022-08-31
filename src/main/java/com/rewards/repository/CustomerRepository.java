package com.rewards.repository;

import com.rewards.entity.Customer;
import com.rewards.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {


}
