package com.bank_account.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank_account.app.entity.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank,String> {
    
}
