package com.project.fintech.sunpay.controller;

import com.project.fintech.sunpay.model.Request;
import com.project.fintech.sunpay.model.User;

import com.project.fintech.sunpay.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RequestedController {
    private final RequestRepository requestRepository;

    @GetMapping("requested")
    public String requested(HttpSession session, Model model){
        User to = (User) session.getAttribute("user");
        if (to == null)return "redirect:/sign_in";
        List<Request> byTo = requestRepository.findByTo(to);
        model.addAttribute("request_list", byTo);
        return "requested_list";
    }

}
