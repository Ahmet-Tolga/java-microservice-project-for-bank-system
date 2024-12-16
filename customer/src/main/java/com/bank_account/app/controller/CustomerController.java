package com.bank_account.app.controller;

import java.util.HashMap;
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

import com.bank_account.app.dto.request.CreateAccountDto;
import com.bank_account.app.dto.request.CreateCustomerDto;
import com.bank_account.app.dto.request.ProcessMoneyDto;
import com.bank_account.app.dto.request.UpdateCustomerDto;
import com.bank_account.app.dto.response.ResponseCustomerDto;
import com.bank_account.app.entity.Customer;
import com.bank_account.app.service.CustomerService;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/create")
    private ResponseEntity<Customer> createCustomer(@RequestBody CreateCustomerDto createCustomerDto){
        return ResponseEntity.ok(customerService.createCustomer(createCustomerDto));
    }

    @GetMapping("/findOneById/{id}")
    private ResponseEntity<Customer> findCustomerById(@PathVariable("id") String id){
        return ResponseEntity.ok(customerService.findOneById(id));
    }

    @GetMapping("/findOneByIdWithRelations/{id}")
    private ResponseEntity<ResponseCustomerDto> findOneCustomerWithoutRelation(@PathVariable("id") String id){
        return ResponseEntity.ok(customerService.findOneCustomerByRelation(id));
    }

    @GetMapping("/findAll")
    private ResponseEntity<List<Customer>> findAllCustomers(){
        return ResponseEntity.ok(customerService.findAllCustomers());
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Integer> deleteCustomerById(@PathVariable("id") String id){
        return ResponseEntity.ok(customerService.deleteCustomer(id));
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<Integer> updateCustomerById(@PathVariable("id") String id,@RequestBody UpdateCustomerDto updateCustomerDto){
        return ResponseEntity.ok(customerService.updateCustomer(id, updateCustomerDto));
    }

    @PostMapping("/createAccount")
    private ResponseEntity<Object> createAccount(@RequestBody CreateAccountDto createAccountDto){
        return ResponseEntity.ok(customerService.createAccount(createAccountDto));
    }

    @PostMapping("/withDrawMoney")
    private ResponseEntity<Object> withDrawMoney(@RequestBody ProcessMoneyDto processMoneyDto){
        Integer statu=customerService.withDrawMoney(processMoneyDto);

        HashMap map=new HashMap<>();

        if(statu==0){
            map.put("success", true);
            map.put("message", "withdraw money successfully!");
            return ResponseEntity.ok(map);
        }
        map.put("success", false);
        map.put("message", "There is an error while withdrawing money from account!");

        return ResponseEntity.ok(map);
    }

    @PostMapping("/depositMoney")
    private ResponseEntity<Object> depositMoney(@RequestBody ProcessMoneyDto processMoneyDto){
        Integer statu=customerService.depositMoney(processMoneyDto);

        HashMap map=new HashMap<>();

        if(statu==0){
            map.put("success", true);
            map.put("message", "deposit money successfully!");
            return ResponseEntity.ok(map);
        }
        map.put("success", false);
        map.put("message", "There is an error while depositing money!");

        return ResponseEntity.ok(map);
    }

    @DeleteMapping("/deleteAccount/{id}")
    private ResponseEntity<Integer> deleteAccount(@PathVariable("id") String id){
        return ResponseEntity.ok(customerService.deleteAccount(id));
    }
}
