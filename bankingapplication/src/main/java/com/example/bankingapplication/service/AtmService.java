package com.example.bankingapplication.service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankingapplication.entity.Account;
import com.example.bankingapplication.entity.AtmCard;
import com.example.bankingapplication.entity.CardStatus;
import com.example.bankingapplication.exception.ResourceNotFoundException;
import com.example.bankingapplication.repository.AccountRepository;
import com.example.bankingapplication.repository.AtmRepository;

@Service
public class AtmService {
		
		@Autowired
		private AccountRepository accountRepository;
		
		@Autowired
		private AtmRepository  atmRepository;
		
		public String generateCardNumber() {
			Random random=new Random();
			String cardNumber;
			do {
				cardNumber=String.valueOf(300000000000L +(Math.abs(random.nextLong()) % 100000000000L));	
			}
			while(atmRepository.existsByCardNumber(cardNumber));
				return cardNumber;
			
		}
		
		public String generateCvv() {
		    SecureRandom random = new SecureRandom();
		    int cvv = random.nextInt(900) + 100;
		    return String.valueOf(cvv);
		}
		
		
		public AtmCard applyCard(String accountNumber, String cardType) {
			
			Account account=accountRepository.findByAccountNumber(accountNumber).
					orElseThrow( ()-> new ResourceNotFoundException("Account not found"));
			AtmCard card=new AtmCard();
			card.setCardNumber(generateCardNumber());
			card.setCardHolderName(account.getHolderName());
			card.setCvv(generateCvv());
			card.setCardType(cardType);
			card.setExpiryDate(LocalDate.now().plusYears(5));
			card.setStatus(CardStatus.ACTIVE);
			card.setAccount(account);
			
			
			return atmRepository.save(card);
			
		}
		
		//check account has card or no
		public AtmCard getCardByAccountNumber(String accountNumber) {
		    return atmRepository.findByAccount_AccountNumber(accountNumber)
		            .orElseThrow(() -> new ResourceNotFoundException("ATM card not found for account: " + accountNumber));
		}
		

		//atm card status
		public AtmCard updateAtmCard(String cardNumber, CardStatus status) {
			AtmCard card=atmRepository.findByCardNumber(cardNumber).
					orElseThrow(()-> new ResourceNotFoundException("Atm Card not found"));
			
			card.setStatus(status);
			return atmRepository.save(card);
		}
		
		
}
