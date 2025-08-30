package com.example.bankingapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bankingapplication.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	
	boolean existsByAccountNumber(String accountNumber);
	 Optional<Account> findByAccountNumber(String accountNumber);
	 Optional<Account> findByEmail(String email);
}
