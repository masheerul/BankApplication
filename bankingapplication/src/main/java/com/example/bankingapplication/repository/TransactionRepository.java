package com.example.bankingapplication.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.bankingapplication.entity.Transactions;

public interface TransactionRepository extends JpaRepository<Transactions, Long>{
	
	List<Transactions> findTop5ByAccount_AccountNumberOrderByTransactionTimeDesc(String accountNumber);
	
	@Query("SELECT t FROM Transactions t WHERE t.account.accountNumber = :accountNumber AND  t.transactionTime BETWEEN :start AND :end ")
	List<Transactions>findTransactionsBetweenDates(String accountNumber, LocalDateTime start, LocalDateTime end);
	
}
