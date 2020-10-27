package com.project.fintech.sunpay.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String username;
    private String password;
    private int amount;

    @Builder
    public User(String username, String password, int amount) {
        this.username = username;
        this.password = password;
        this.amount = amount;
    }
}
