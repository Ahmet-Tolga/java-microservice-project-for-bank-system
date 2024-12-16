package com.bank_account.app.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseAuthDto {
    private String token;
}