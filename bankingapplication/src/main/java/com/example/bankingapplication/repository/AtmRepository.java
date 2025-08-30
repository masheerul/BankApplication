package com.example.bankingapplication.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bankingapplication.entity.AtmCard;

@Repository
public interface AtmRepository extends JpaRepository<AtmCard, Long> {
	boolean existsByCardNumber(String cardNumber); // ✅ For unique generation

    Optional<AtmCard> findByAccount_AccountNumber(String accountNumber); 
    
    Optional<AtmCard> findByCardNumber(String cardNumber); //status
}
