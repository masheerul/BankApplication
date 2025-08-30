package com.example.bankingapplication.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankingapplication.dto.LoginRequest;
import com.example.bankingapplication.entity.Account;
import com.example.bankingapplication.entity.Transactions;
import com.example.bankingapplication.exception.ResourceNotFoundException;
import com.example.bankingapplication.service.AccountService;
import com.example.bankingapplication.service.TransactionsService;

@CrossOrigin(origins = "http://127.0.0.1:5501") // or http://localhost:5500
@RestController
@RequestMapping("/bank")
public class AccountController {

		@Autowired
		private AccountService service;
		
		@Autowired
		private TransactionsService transactionsService;
		
		@PostMapping("/register")
		public ResponseEntity<?> registerAccounct(@RequestBody Account account){
			 System.out.println("Received request to create account"); 
			Account created=service.createAccount(account);
			return ResponseEntity.ok(created);
		}
		
		@PostMapping("/login")
		public ResponseEntity<?>login(@RequestBody LoginRequest loginRequest){
			try {
				Account loggedAccount=service.login(loginRequest.getEmail(), loginRequest.getPassword());
				return ResponseEntity.ok(loggedAccount);
			}
			catch (ResourceNotFoundException ex) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	        } catch (IllegalArgumentException ex) {
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
	        } catch (Exception ex) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
	        }
	    
		}
		
		 @PostMapping("/deposit/{accountNumber}")
		    public ResponseEntity<String> deposit(@PathVariable String accountNumber, @RequestParam Double amount) {
		        String updatedAccount = transactionsService.deposit(accountNumber, amount);
		        return ResponseEntity.ok(updatedAccount);
		    }
		 
		 @PostMapping("/withdraw/{accountNumber}")
		 public ResponseEntity<Account>withdraw(@RequestParam Double amount, @PathVariable String accountNumber){
			 Account account=transactionsService.withdraw(accountNumber, amount);
			 return ResponseEntity.ok(account);
		 }
		 @GetMapping("/transaction/{accountNumber}")
		 public  ResponseEntity<List<Transactions>>getLast5Transaction(@PathVariable String accountNumber){
			 List<Transactions>transactions=transactionsService.getLast5Transaction(accountNumber);
			 return ResponseEntity.ok(transactions);
		 }
		 
		 @GetMapping("/checkBalance/{accountNumber}")
		public ResponseEntity<Double>checkBalance(@PathVariable String accountNumber){
			 Double balance=service.checkBalance(accountNumber);
			 return ResponseEntity.ok(balance);
		 }
		 
		 @GetMapping("/test")
		 public String test() {
		     return "Backend is working";
		 }
		 
		 
		 @GetMapping("/transactionsBetween")
		 public ResponseEntity<List<Transactions>>getTransactionsBetween(
		 @RequestParam String accountNumber,
		 @RequestParam String startDate,
		 @RequestParam String endDate){
			 
			 List<Transactions>transactions=transactionsService.getTransactionsBetweenDates(accountNumber, startDate, endDate);
			 return ResponseEntity.ok(transactions);
		 }
		 
		 @GetMapping("/check-account")
		 public ResponseEntity<Boolean>checkIfAccountExist(@RequestParam String accountNumber){
			 boolean exist=service.existByAcoountNumber(accountNumber);
			 return ResponseEntity.ok(exist);
			 
		 }
		 
}
