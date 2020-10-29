package com.project.fintech.sunpay.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity @NoArgsConstructor
@Getter @Setter
public class Request {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_id")
    private User to;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "form_id")
    private User from;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pay_id")
    private Pay pay;

    private int amount;

    // TODO 1주일안에 요청이 진행되지않으면 마감되는 기능추가
    // 반환하는 날과 마감용 등록날 속성 추가
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;
    private LocalDateTime returnDay;

    @Enumerated(EnumType.STRING)
    private RequestState requestState;

    @Builder
    public Request(User to, User from, int amount) {
        this.to = to;
        this.from = from;
        this.amount = amount;
        requestState = RequestState.READ;
    }
}