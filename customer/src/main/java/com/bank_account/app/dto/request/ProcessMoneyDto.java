package com.bank_account.app.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProcessMoneyDto {
    private String account_id;
    private String customer_id;
    private Long money;
}
