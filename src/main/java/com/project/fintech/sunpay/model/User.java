package com.project.fintech.sunpay.model;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class User {
    @Id @GeneratedValue
    private Long id;

    private String username;
    private String password;


    @Builder
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
