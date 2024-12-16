package com.bank_account.app.dto.request;

import lombok.Data;

@Data
public class RequestLoginDto {
    private String email;

    private String password;
}
