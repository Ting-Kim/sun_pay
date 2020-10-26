package com.project.fintech.sunpay.controller;

import com.project.fintech.sunpay.model.User;
import com.project.fintech.sunpay.repository.UserRepository;
import com.project.fintech.sunpay.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import netscape.security.UserTarget;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class SignController {
    private final UserService userService;
    private final UserRepository userRepository;
    @GetMapping("sign_in_form")
    public String sign_in_form(Model model){
        model.addAttribute("user", new User());
        return "sign_in_form";
    }

    @PostMapping("sign_in")
    public String sign_in(User user, HttpSession session){
        User findUser = userRepository
                .findByUsernameAndPassword(user.getUsername(), user.getPassword()).orElseThrow(IllegalArgumentException::new);
        session.setAttribute("user", findUser);
        return "redirect:/main";
    }
    @GetMapping("main")
    public String main(){
        return "main";
    }

}
