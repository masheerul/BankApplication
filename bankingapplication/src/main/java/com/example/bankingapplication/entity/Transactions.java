package com.example.bankingapplication.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transactions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Double amount;
	private String transactionType;
	private LocalDateTime  transactionTime;
	
	@ManyToOne
	@JsonIgnore
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

	public Transactions() {}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public LocalDateTime getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(LocalDateTime transactionTime) {
		this.transactionTime = transactionTime;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Transactions(Long id, Double amount, String transactionType, LocalDateTime transactionTime,
			Account account) {
		super();
		this.id = id;
		this.amount = amount;
		this.transactionType = transactionType;
		this.transactionTime = transactionTime;
		this.account = account;
	}
}
