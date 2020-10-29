package com.project.fintech.sunpay.model;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    // 계정 이름
    private String username;

    // 계정 이름
    private String name;

    private String password;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    @Builder
    public User(String username, String name, String password, Account account) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.account = account;
    }
}
