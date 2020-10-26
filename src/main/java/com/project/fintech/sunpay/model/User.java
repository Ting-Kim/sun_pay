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

    @Builder
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
