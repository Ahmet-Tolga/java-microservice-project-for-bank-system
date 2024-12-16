package com.bank_account.app.dto.response;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCustomerDto {
    private String id;
    private String customer_name;
    private Date created_at;
    private List<Object> accounts;
}
