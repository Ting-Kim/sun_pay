package com.project.fintech.sunpay.controller;

import com.project.fintech.sunpay.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SignController {
    private final UserService userService;

    @GetMapping("sign_in_form")
    public String sign_in_form(){
        return "sign_in_form";
    }

    @PostMapping("sign_in")
    public String sign_in(){

        return "";
    }

}
