package com.bank_account.app.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank_account.app.dto.CreateBankDto;
import com.bank_account.app.dto.UpdateBankDto;
import com.bank_account.app.entity.Bank;
import com.bank_account.app.service.BankService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/bank")
@RequiredArgsConstructor
public class BankController {
    private final BankService bankService;

    @PostMapping("/create")
    private ResponseEntity<Bank> createBank(@RequestBody CreateBankDto bankDto){
        return ResponseEntity.ok(bankService.createBank(bankDto));
    }

    @GetMapping(path = "/findById/{id}")
    private ResponseEntity<Bank> findById(@PathVariable("id") String id){
        return ResponseEntity.ok(bankService.findOneBank(id));
    }

    @GetMapping("/findAll")
    private ResponseEntity<List<Bank>> findAllBanks(){
        return ResponseEntity.ok(bankService.findAllBanks());
    }

    @DeleteMapping("/deleteById/{id}")
    private ResponseEntity<Integer> deleteBank(@PathVariable("id") String id){
        return ResponseEntity.ok(bankService.deleteBank(id));
    }

    @PutMapping("/updateBank/{id}")
    private ResponseEntity<Bank> updateBank(@PathVariable("id") String id,@RequestBody UpdateBankDto updateBankDto){
        return ResponseEntity.ok(bankService.updateBank(id, updateBankDto));
    }


}
