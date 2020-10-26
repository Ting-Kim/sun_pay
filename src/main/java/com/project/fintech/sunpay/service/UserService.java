package com.project.fintech.sunpay.service;

import com.project.fintech.sunpay.model.User;
import com.project.fintech.sunpay.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

}
