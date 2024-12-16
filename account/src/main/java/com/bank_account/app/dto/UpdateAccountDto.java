package com.bank_account.app.dto;

import java.util.Optional;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAccountDto {
    private Optional<Long> total_money;

    private Optional<Date> expiration_at;
}
