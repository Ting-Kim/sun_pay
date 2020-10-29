package com.project.fintech.sunpay.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity @NoArgsConstructor
public class Pay {
    @Id @GeneratedValue
    @Column(name = "pay_id")
    private Long id;

    private String senderName;
    private int senderAmount;
    private String senderBankName;
    private String senderBankNumber;

    private String receiveName;
    private int receiveAmount;
    private String receiveBankName;
    private String receiveBankNumber;

    @Builder
    public Pay(String senderName, int senderAmount, String senderBankName, String senderBankNumber, String receiveName, int receiveAmount, String receiveBankName, String receiveBankNumber) {
        this.senderName = senderName;
        this.senderAmount = senderAmount;
        this.senderBankName = senderBankName;
        this.senderBankNumber = senderBankNumber;
        this.receiveName = receiveName;
        this.receiveAmount = receiveAmount;
        this.receiveBankName = receiveBankName;
        this.receiveBankNumber = receiveBankNumber;
    }
}
