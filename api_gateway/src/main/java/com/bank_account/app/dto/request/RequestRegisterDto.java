package com.bank_account.app.dto.request;

import lombok.Data;

@Data
public class RequestRegisterDto {
    private String username;

    private String email;

    private String password;

    private String role;
}
