package com.bank_account.app.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Account {
    @Id
    private String id;

    @Field
    private Long total_money;

    @Field
    private Date created_at;

    @Field
    private Date expiration_at;

    @Field
    private String customerId;

    @Field
    private String bankId;
}
