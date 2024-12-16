package com.bank_account.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bank_account.app.dto.request.CreateAccountDto;
import com.bank_account.app.dto.request.CreateCustomerDto;
import com.bank_account.app.dto.request.ProcessMoneyDto;
import com.bank_account.app.dto.request.UpdateCustomerDto;
import com.bank_account.app.dto.response.ResponseCustomerDto;
import com.bank_account.app.dto.response.ResponseDepositDto;
import com.bank_account.app.dto.response.ResponseWithdrawMoneyDto;
import com.bank_account.app.entity.Customer;
import com.bank_account.app.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    private final RestTemplate restTemplate;

    public Customer createCustomer(CreateCustomerDto createCustomerDto){

        Customer new_customer=Customer.builder().id(UUID.randomUUID().toString()).customer_name(createCustomerDto.getCustomer_name())
        .created_at(new Date())
        .build();

        customerRepository.save(new_customer);

        return new_customer;
    }


    public ResponseCustomerDto findOneCustomerByRelation(String id){
        Customer customer=customerRepository.findById(id).get();

        List<Object> customer_accounts=restTemplate.getForObject("http://ACCOUNTSERVICE/api/v1/account/findAllByCustomerId/"+customer.getId(), List.class);

        return ResponseCustomerDto.builder().accounts(customer_accounts!=null ? customer_accounts : new ArrayList<>()).created_at(customer.getCreated_at())
        .id(customer.getId()).customer_name(customer.getCustomer_name()).build();
    }

    @Cacheable(value = "customers",key="#p0", condition="#p0!=null")
    public Customer findOneById(String id){
        System.out.println("comming from database!");
        return customerRepository.findById(id).get();
    }

    @Cacheable(value = "customers",key="#p0", condition="#p0!=null")
    public List<Customer> findAllCustomers(){
        return customerRepository.findAll();
    }

    public Integer deleteCustomer(String id){
        try{
            customerRepository.deleteById(id);
            return 0;
        }

        catch(Exception exp){
            return 1;
        }
    }

    public Integer updateCustomer(String id,UpdateCustomerDto updateCustomerDto){
        try{
            Customer customer_to_update=customerRepository.findById(id).get();

            if(updateCustomerDto.getCustomer_name()!=null){
                customer_to_update.setCustomer_name(updateCustomerDto.getCustomer_name().get());
            }

            customerRepository.save(customer_to_update);
            return 0;
        }
        catch(Exception exp){
            return 1;
        }
    }


    public Object createAccount(CreateAccountDto createAccountDto){
            Customer customer=customerRepository.findById(createAccountDto.getCustomer_id()).get();
            Object bank=restTemplate.getForObject("http://BANKSERVICE/api/v1/bank/findById/"+createAccountDto.getBank_id(), Object.class);

            if(customer!=null && bank!=null){
                Object createdAccount=restTemplate.postForObject("http://ACCOUNTSERVICE/api/v1/account/create", createAccountDto, Object.class);

                return createdAccount;
            }

            return new Exception("Bank or customer couldn't found");
    }

    public Integer withDrawMoney(ProcessMoneyDto withDrawMoneyDto){
        ResponseWithdrawMoneyDto account_validation=restTemplate.postForObject("http://ACCOUNTSERVICE/api/v1/account/validateAccount", withDrawMoneyDto,ResponseWithdrawMoneyDto.class);

        if (account_validation != null && account_validation.getValidation()) {
    
        Map<String, Object> updateData = new HashMap<>();
        updateData.put("total_money", account_validation.getTotal_money() - withDrawMoneyDto.getMoney());


        restTemplate.put(
            "http://ACCOUNTSERVICE/api/v1/account/update/" + withDrawMoneyDto.getAccount_id(),
            updateData
        );

        return 0;
    }
    return 1;
}

public Integer depositMoney(ProcessMoneyDto processMoneyDto){
    ResponseDepositDto account=restTemplate.getForObject("http://ACCOUNTSERVICE/api/v1/account/findOneById/"+processMoneyDto.getAccount_id(), ResponseDepositDto.class);

    if(account!=null){
        Map<String,Object> map=new HashMap<>();

        map.put("total_money", account.getTotal_money()+processMoneyDto.getMoney());
        restTemplate.put("http://ACCOUNTSERVICE/api/v1/account/update/"+processMoneyDto.getAccount_id(), map);

        return 0;

    }

    return 1;
}

public Integer deleteAccount(String id){
    try{

        restTemplate.delete("http://ACCOUNTSERVICE/api/v1/account/delete/"+id);

        return 0;
    }
    catch(Exception exp){
        return 1;
    }
}

    
}
