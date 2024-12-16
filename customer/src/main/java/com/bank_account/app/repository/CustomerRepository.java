package com.bank_account.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank_account.app.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {
    
}
