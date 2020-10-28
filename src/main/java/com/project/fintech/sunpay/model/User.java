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
    private String clientId;
    private String clientSecret;
    private String code;

    @Builder
    public User(String username, String password, String clientId, String clientSecret, String code) {
        this.username = username;
        this.password = password;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.code = code;
    }
}
