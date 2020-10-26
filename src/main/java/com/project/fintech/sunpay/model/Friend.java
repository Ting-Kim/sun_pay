package com.project.fintech.sunpay.model;

import lombok.Builder;

import javax.persistence.*;

@Entity
public class Friend {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_id")
    private User to;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id")
    private User from;

    @Builder
    public Friend(User to, User from) {
        this.to = to;
        this.from = from;
    }
}
