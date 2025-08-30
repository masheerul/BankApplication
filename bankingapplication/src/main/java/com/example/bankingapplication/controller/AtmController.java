package com.example.bankingapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankingapplication.entity.AtmCard;
import com.example.bankingapplication.entity.CardStatus;
import com.example.bankingapplication.service.AtmService;

@CrossOrigin(origins = "http://127.0.0.1:5501") // or http://localhost:5501
@RestController
@RequestMapping("/atm")
public class AtmController {
	
		
		@Autowired
		private AtmService atmService;
		
		@PostMapping("/apply")
		public ResponseEntity<AtmCard>applyCard(@RequestParam String accountNumber ,@RequestParam String cardType){
			AtmCard atmCard=atmService.applyCard(accountNumber, cardType);
			return ResponseEntity.ok(atmCard);
		}
		
		//Check if an ATM card exists for the given account
		@GetMapping("/account")
		public ResponseEntity<AtmCard> getCardByAccount(@RequestParam String accountNumber) {
		    AtmCard atmCard = atmService.getCardByAccountNumber(accountNumber);
		    return ResponseEntity.ok(atmCard);
		}

		//status block or 
		@PutMapping("/status")
		public ResponseEntity<AtmCard> cardStatus(@RequestParam String cardNumber, @RequestParam CardStatus status){
		    AtmCard atmCard = atmService.updateAtmCard(cardNumber, status);
		    return ResponseEntity.ok(atmCard);
		}

}
