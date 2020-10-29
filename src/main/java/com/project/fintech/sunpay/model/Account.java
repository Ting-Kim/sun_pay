package com.project.fintech.sunpay.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigInteger;

@Entity @NoArgsConstructor
@Getter
public class Account {
    @Id @GeneratedValue
    @Column(name = "account_id")
    private Long id;
    // 계좌 은행이름
    private String bank_name;
    // 계좌 소유자
    private String owner;
    // 계좌 번호
    private String number;
    // 잔액
    private BigInteger amount;
    // 한도
    private BigInteger max;

    @Builder
    public Account(String bank_name, String owner, String number, BigInteger amount, BigInteger max) {
        this.bank_name = bank_name;
        this.owner = owner;
        this.number = number;
        this.amount = amount;
        this.max = max;
    }
}
