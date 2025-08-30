package com.example.bankingapplication.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankingapplication.entity.Account;
import com.example.bankingapplication.entity.Transactions;
import com.example.bankingapplication.exception.ResourceNotFoundException;
import com.example.bankingapplication.repository.AccountRepository;
import com.example.bankingapplication.repository.TransactionRepository;

@Service
public class TransactionsService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
		public String deposit(String accountNumber , double amount) {
			Optional<Account>accountOpt=accountRepository.findByAccountNumber(accountNumber);
			if(accountOpt.isPresent()) {
				Account account=accountOpt.get();
				account.setBalance(account.getBalance()+amount);
				
				Transactions transactions=new Transactions(null, amount, "deposit", LocalDateTime.now() , account);
				transactionRepository.save(transactions);
				return amount+" amount has been deposited succesfully to account "+accountNumber+".";
			}
			return accountNumber;
		}
		
		public Account withdraw(String accountNumber, double amount) {
			Optional<Account>optional=accountRepository.findByAccountNumber(accountNumber);
			if(optional.isPresent()) {
				Account account=optional.get();
				if(account.getBalance() >=amount) {
					account.setBalance(account.getBalance()- amount);
					
					Transactions transactions=new Transactions(null, amount, "Withdraw", LocalDateTime.now(), account);
					transactionRepository.save(transactions);
					
					accountRepository.save(account);
					return account;
				}
				else {
					throw new ResourceNotFoundException("Insufficient Money");					
				}
			}
			else {
				throw new ResourceNotFoundException("Account not found");
			}
			
		}
		
		public List<Transactions>getLast5Transaction(String accountNumber){
			return transactionRepository.findTop5ByAccount_AccountNumberOrderByTransactionTimeDesc(accountNumber);
		}
		
		public List<Transactions> getTransactionsBetweenDates(String accountNumber, String startDate, String endDate) {
	       
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        LocalDateTime start = LocalDate.parse(startDate, formatter).atStartOfDay();
	        LocalDateTime end = LocalDate.parse(endDate, formatter).atTime(LocalTime.MAX);

			return transactionRepository.findTransactionsBetweenDates(accountNumber, start, end);
		}
		
}
