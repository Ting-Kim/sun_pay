package com.project.fintech.sunpay.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @NoArgsConstructor
@Getter
public class Request {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_id")
    private User to;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id")
    private User from;

    private int amount;
    private RequestState requestState;

    @Builder
    public Request(User to, User from, int amount) {
        this.to = to;
        this.from = from;
        this.amount = amount;
        requestState = RequestState.READ;
    }
}