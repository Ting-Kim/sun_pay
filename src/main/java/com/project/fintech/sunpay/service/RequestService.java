package com.project.fintech.sunpay.service;

import com.project.fintech.sunpay.model.Pay;
import com.project.fintech.sunpay.model.Request;
import com.project.fintech.sunpay.model.RequestState;
import com.project.fintech.sunpay.model.User;
import com.project.fintech.sunpay.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service @Transactional
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;
    public void cancel(Long id) {
        Request request = requestRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        request.setRequestState(RequestState.CANCEL);
    }

    public void change(Long id, int amount, String msg, LocalDate returnDay) {
        Request request = requestRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        request.setRequestMsg(msg);
        request.setReturnDay(returnDay);
        request.setAmount(amount);
    }

    public void refuse(Long id) {
        Request request = requestRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        request.setRequestState(RequestState.REFUSE);
    }

    public void pay(Long id) {
        Request request = requestRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        request.setPay(Pay.builder()
                .senderName(request.getTo().getName())
                .receiveName(request.getFrom().getName())
                .price(request.getAmount())
                .returnDay(request.getReturnDay())
                .build());
        request.setRequestState(RequestState.PAYED);
        request.getFrom().setPoint(request.getFrom().getPoint() - request.getAmount());
        request.getTo().setPoint(request.getTo().getPoint() + request.getAmount());

    }
}
