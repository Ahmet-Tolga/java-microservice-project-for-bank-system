package com.bank_account.app.service;

import java.util.List;
import java.util.UUID;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bank_account.app.dto.CreateBankDto;
import com.bank_account.app.dto.UpdateBankDto;
import com.bank_account.app.entity.Bank;
import com.bank_account.app.repository.BankRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BankService {
    private final BankRepository bankRepository;

    public Bank createBank(CreateBankDto createBankDto){
        Bank new_bank=Bank.builder().bank_name(createBankDto.getBank_name()).id(UUID.randomUUID().toString()).build();
        
        bankRepository.save(new_bank);

        return new_bank;
    }

    @Cacheable(value = "banks",key="#p0", condition="#p0!=null")
    public Bank findOneBank(String bank_id){
        System.out.println("comming from database!");
        return bankRepository.findById(bank_id).get();
    }

    @Cacheable(value = "banks",key="#p0", condition="#p0!=null")
    public List<Bank> findAllBanks(){
        System.out.println("comming from database!");
        return bankRepository.findAll();
    }

    public int deleteBank(String bank_id){
        try{
            bankRepository.deleteById(bank_id);
            return 0;
        }

        catch(Exception exp){
            return 1;
        }
    }

    public Bank updateBank(String bank_id,UpdateBankDto updateBank){
        Bank bank=bankRepository.findById(bank_id).get();

        if(bank!=null){
            if(updateBank.getBank_name()!=null){
                bank.setBank_name(updateBank.getBank_name().get());
                bankRepository.save(bank);
            }
        }

        return bank;
    }

    
}
