package com.example.bankingapplication.service;

import java.time.LocalDateTime;
//import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.hibernate.query.NativeQuery.ReturnableResultNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankingapplication.dto.RegisterDto;
import com.example.bankingapplication.entity.Account;
import com.example.bankingapplication.exception.ResourceNotFoundException;
import com.example.bankingapplication.repository.AccountRepository;

@Service
public class AccountService {

	
			@Autowired
			private AccountRepository repository; 
			
		private String generateAccountNumber() {	
			String accountNumber;
			Random random=new Random();
			do {
				accountNumber = String.valueOf(10000000000L + (Math.abs(random.nextLong()) % 90000000000L));
//		        accountNumber = String.valueOf(number);
			}
			while(repository.existsByAccountNumber(accountNumber));
			return accountNumber;
		}
		
		public Account createAccount(Account account) {
			
			String generatedAcoountNumber=generateAccountNumber();
			account.setAccountNumber(generatedAcoountNumber);
			account.setCreatedAt(LocalDateTime.now());
			account.setBalance(0.0);
			return repository.save(account);
		}
		
		public Double checkBalance(String accountNumber) {
		    Account account = repository.findByAccountNumber(accountNumber)
		            .orElseThrow(() -> new ResourceNotFoundException("Account not found for account number " + accountNumber));
		    
		    System.out.println("The account has a total balance of: " + account.getBalance());
		    return account.getBalance();
		}
		
		public Account login(String email, String password) {
			Account account=repository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("Account not found with email"+email));
			
			if(!account.getPassword().equals(password)) {
				throw new ResourceNotFoundException("Incorrect Pasword");
			}
			return account;
		}
		
		public boolean existByAcoountNumber(String accountNumber) {
			return repository.existsByAccountNumber(accountNumber);
		}
		
//		


}
