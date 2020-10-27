package com.project.fintech.sunpay.service;

import com.project.fintech.sunpay.model.Request;
import com.project.fintech.sunpay.model.RequestState;
import com.project.fintech.sunpay.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;
    public void cancel(Long id) {
        Request request = requestRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        request.setRequestState(RequestState.CANCEL);
    }

    public void changeAmount(Long id, int amount) {
        Request request = requestRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        request.setAmount(amount);
    }

    public void refuse(Long id) {
        Request request = requestRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        request.setRequestState(RequestState.REFUSE);
    }
}
