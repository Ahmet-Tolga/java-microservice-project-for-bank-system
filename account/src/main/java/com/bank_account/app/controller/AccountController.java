package com.bank_account.app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank_account.app.dto.CreateAccountDto;
import com.bank_account.app.dto.UpdateAccountDto;
import com.bank_account.app.dto.ValidateAccountDto;
import com.bank_account.app.dto.response.ResponseAccountDto;
import com.bank_account.app.entity.Account;
import com.bank_account.app.service.AccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/create")
    private ResponseEntity<Account> createAccount(@RequestBody CreateAccountDto createAccountDto){
        return ResponseEntity.ok(accountService.createAccount(createAccountDto));
    }

    @GetMapping("/findOneById/{id}")
    private ResponseEntity<Account> findOneAccount(@PathVariable("id") String id){
        return ResponseEntity.ok(accountService.findOneAccountById(id));
    }

    @GetMapping("/findByIdWithRelation/{id}")
    private ResponseEntity<ResponseAccountDto> findOneByIdWithRelations(@PathVariable("id") String id){
        return ResponseEntity.ok(accountService.findOneWithRelations(id));
    }

    @GetMapping("/findAllByCustomerId/{id}")
    private ResponseEntity<List<Account>> findAllByCustomerId(@PathVariable("id") String id){
        return ResponseEntity.ok(accountService.findAllAccountsByCustomerId(id));
    }

    @GetMapping("/findAllByBankId/{id}")
    private ResponseEntity<List<Account>> findAllByBankId(@PathVariable("id") String id){
        return ResponseEntity.ok(accountService.findAllAccountsByBankId(id));
    }

    @GetMapping("/findAll")
    private ResponseEntity<List<Account>> findAllAcounts(){
        return ResponseEntity.ok(accountService.findAllAccounts());
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Integer> deleteAccount(@PathVariable("id") String id){
        return ResponseEntity.ok(accountService.deleteAccount(id));
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<Integer> updateAccount(@PathVariable("id") String id,@RequestBody UpdateAccountDto updateAccountDto){
        return ResponseEntity.ok(accountService.updateAccount(id, updateAccountDto));
    }

    @PostMapping("/validateAccount")
    private Object validateAccount(@RequestBody ValidateAccountDto validateAccountDto){
        return accountService.validateAccount(validateAccountDto);
    }
}
