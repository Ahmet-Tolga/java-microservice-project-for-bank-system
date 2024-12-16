package com.bank_account.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bank_account.app.entity.Account;
import java.util.List;


@Repository
public interface AccountRepository extends MongoRepository<Account,String> {
    List<Account> findByCustomerId(String customer_id);
    List<Account> findByBankId(String bank_id);
}
