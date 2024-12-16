package com.bank_account.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="banks")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Bank {
    @Id
    private String id;

    @Column(name = "bank_name",unique = true,nullable = false)
    private String bank_name;
}
