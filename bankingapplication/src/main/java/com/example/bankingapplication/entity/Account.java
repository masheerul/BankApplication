package com.example.bankingapplication.entity;

import java.time.LocalDateTime;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
public class Account {
		
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String accountNumber;  
    private String accountType;
    private String holderName;     
    private String email;       
    private String password;
    private String phoneNumber;    
    private Double balance = 0.0; 
    private LocalDateTime createdAt;
    
    
    @OneToOne(mappedBy = "account")
    private AtmCard atmCard;
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transactions> transactions;
    
	
    public Account() {}
    
	public List<Transactions> getTransactions() {
		return transactions;
	}
	public void setTransactions() {
		
	}
	
	public Long getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getHolderName() {
		return holderName;
	}
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
    
    public Account(Long id, String accountNumber, String accountType, String holderName, String email,
			String phoneNumber, Double balance, LocalDateTime createdAt,List<Transactions> transactions,
			String password) {
		
		this.id = id;
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.holderName = holderName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.balance = balance;
		this.createdAt = createdAt;
		this.transactions = transactions;
		this.password=password;
	}
		
}
