package com.bank_account.app.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bank_account.app.dto.CreateAccountDto;
import com.bank_account.app.dto.UpdateAccountDto;
import com.bank_account.app.dto.ValidateAccountDto;
import com.bank_account.app.dto.response.ResponseAccountDto;
import com.bank_account.app.entity.Account;
import com.bank_account.app.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    private final RestTemplate restTemplate;

    public Account createAccount(CreateAccountDto createAccountDto) {

    Date createdAt = new Date();

    Calendar calendar = Calendar.getInstance();
    calendar.setTime(createdAt);
    calendar.add(Calendar.YEAR, 5);
    Date expirationAt = calendar.getTime();

    Account newAccount = Account.builder()
            .total_money(0L)
            .created_at(createdAt)
            .expiration_at(expirationAt)
            .customerId(createAccountDto.getCustomer_id())
            .bankId(createAccountDto.getBank_id())
            .build();

    accountRepository.save(newAccount);
    return newAccount;
}
    @Cacheable(value = "accounts",key="#p0", condition="#p0!=null")
    public Account findOneAccountById(String id){
        return accountRepository.findById(id).get();
    }

    public ResponseAccountDto findOneWithRelations(String id){
        Account account=accountRepository.findById(id).get();

        Object bank=restTemplate.getForObject("http://BANKSERVICE/api/v1/bank/findById/"+account.getBankId(),Object.class);

        Object customer=restTemplate.getForObject("http://CUSTOMERSERVICE/api/v1/customer/findOneById/"+account.getCustomerId(), Object.class);

        return ResponseAccountDto.builder().id(account.getId()).created_at(account.getCreated_at()).expiration_at(account.getExpiration_at()).bank(bank).customer(customer).build();
    }

    public List<Account> findAllAccountsByCustomerId(String customer_id){
        return accountRepository.findByCustomerId(customer_id);
    }

    public List<Account> findAllAccountsByBankId(String bank_id){
        return accountRepository.findByBankId(bank_id);
    }
    
    public List<Account> findAllAccounts(){
        return accountRepository.findAll();
    }

    public Integer deleteAccount(String id){
        try{
            accountRepository.deleteById(id);

            return 0;
        }
        catch(Exception exp){
            return 1;
        }
    }

    public Integer updateAccount(String id,UpdateAccountDto updateAccountDto){
        try{
            Account updatedAccount=accountRepository.findById(id).get();

            if(updateAccountDto.getTotal_money()!=null){
                updatedAccount.setTotal_money(updateAccountDto.getTotal_money().get());
            }

            if(updateAccountDto.getExpiration_at()!=null){
                updateAccountDto.setExpiration_at(null);
            }

            accountRepository.save(updatedAccount);
            return 0;

        }
        catch(Exception exp){
            return 1;
        }
    }

    public Object validateAccount(ValidateAccountDto validateAccountDto){
        Account account=accountRepository.findById(validateAccountDto.getAccount_id()).get();

        if(account==null || account.getTotal_money()<validateAccountDto.getMoney() ||
        !account.getCustomerId().equals(validateAccountDto.getCustomer_id())
        || account.getExpiration_at().before(new Date())){
            Map<String, Object> response = new HashMap<>();
            response.put("validation", false);
            return response;
        }

         Map<String, Object> response = new HashMap<>();
        response.put("validation", true);
        response.put("total_money", account.getTotal_money());
        return response;
    }
}
