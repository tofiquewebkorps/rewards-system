package com.rewards.service.impl;

import com.rewards.dto.CustomerDTO;
import com.rewards.dto.TransactionDTO;
import com.rewards.entity.Customer;
import com.rewards.entity.Transaction;
import com.rewards.exception.CustomerNotFoundException;
import com.rewards.repository.CustomerRepository;
import com.rewards.repository.TransactionRepository;
import com.rewards.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TransactionDTO saveUpdateTransaction(TransactionDTO transactionDTO){
        log.info("saveUpdateTransaction method started Purchase value :: "+transactionDTO.getAmount());

        CustomerDTO customerDTO = transactionDTO.getCustomerDTO();
        if(customerDTO==null){
            throw new CustomerNotFoundException("Without Customer Transaction would not Happen");
        }
        Optional<Customer> existCustomerOptional = customerRepository.findById(customerDTO.getCid());
        if(existCustomerOptional.isEmpty()){
            throw new CustomerNotFoundException("Customer Not Exist");
        }
        transactionDTO.setRewardPoints(calculateRewardsPoint(transactionDTO.getAmount()));
        Customer existCustomer = existCustomerOptional.get();
        existCustomer.setTotalRewardPoints(existCustomer.getTotalRewardPoints()+transactionDTO.getRewardPoints());
        CustomerDTO existCustomerDto = modelMapper.map(existCustomer,CustomerDTO.class);
        transactionDTO.setCustomerDTO(existCustomerDto);
        Transaction transaction= modelMapper.map(transactionDTO,Transaction.class);
        Transaction savedTransaction = transactionRepository.save(transaction);
        TransactionDTO savedTransactionDto= modelMapper.map(savedTransaction,TransactionDTO.class);
        log.info("Transaction Saved Successfully transactionId :: "+savedTransaction.getTid());
        return savedTransactionDto;
    }

    @Override
    public List<TransactionDTO> getTransactions() {
        log.info("getTransactions method started.");
        List<Transaction> transactions = (List<Transaction>) transactionRepository.findAll();
        List<TransactionDTO> transactionDTOS = transactions.stream().map(user -> modelMapper.map(user, TransactionDTO.class)).collect(Collectors.toList());
        log.info("getTransactions method completed total tranactions :: "+transactions.size());
        return transactionDTOS;
    }

    @Override
    public void removeTransaction(Long id) {
        log.info("removeTransaction method started transaction id ::"+id);
        transactionRepository.deleteById(id);
        log.info("removeTransaction method ended");
    }

    @Override
    public TransactionDTO getTransaction(Long id) {
        log.info("getTransaction method started transaction id ::"+id);
        Transaction transaction = transactionRepository.findById(id).get();
        TransactionDTO transactionDTO = modelMapper.map(transaction,TransactionDTO.class);
        log.info("getTransaction method ended");
        return transactionDTO;
    }



    public Long totalRewardsPointInMonth(List<TransactionDTO> transactionDTOS){
        log.info("totalRewardsPointInMonth method started total transactions :: "+transactionDTOS.size());
        Long totalReward = 0l;
        for(TransactionDTO transactionDTO : transactionDTOS){
            totalReward = totalReward + transactionDTO.getRewardPoints();
        }
        log.info("totalRewardsPointInMonth method ended total Reward :: "+totalReward);
        return totalReward;
    }

    private static Long calculateRewardsPoint(Long amount){
        log.info("calculateRewardsPoint method started purchase amount ::"+amount);
        Long rewardsPoint = 0l;
        amount = amount-50;
        if(amount>0){
            if(amount/50<1) {
                rewardsPoint = amount;
            }else{
                rewardsPoint = 50 + ((amount-50)*2);
            }
        }
        log.info("calculateRewardsPoint method ended reward point ::"+rewardsPoint);
        return rewardsPoint;
    }

    @Override
    public List<TransactionDTO> getTransactionsByCustomerAndMonths(CustomerDTO customerDTO, Month month) {
        log.info("getTransactionsByMonths method started MONTH ::"+month);
        LocalDate startDate = LocalDate.of(2022, month,1);
        LocalDate endDate = LocalDate.of(2022,month, startDate.withDayOfMonth(
                startDate.getMonth().length(startDate.isLeapYear())).getDayOfMonth());
        Customer customer = modelMapper.map(customerDTO,Customer.class);
        List<Transaction> transactions = (List<Transaction>) transactionRepository.findAllByCustomerAndDateBetween(customer,startDate,endDate);
        List<TransactionDTO> transactionDTOS = transactions.stream().map(user -> modelMapper.map(user, TransactionDTO.class)).collect(Collectors.toList());
        log.info("getTransactionsByMonths method completed total transactions :: "+transactions.size());
        return transactionDTOS;

    }
}
