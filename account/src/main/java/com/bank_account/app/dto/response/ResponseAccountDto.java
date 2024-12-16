package com.bank_account.app.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAccountDto {
    private String id;

    private Long total_money;

    private Date created_at;

    private Date expiration_at;

    private Object bank;

    private Object customer;
}
