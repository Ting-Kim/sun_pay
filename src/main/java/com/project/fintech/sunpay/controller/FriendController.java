package com.project.fintech.sunpay.controller;

import com.project.fintech.sunpay.model.Friend;
import com.project.fintech.sunpay.model.User;
import com.project.fintech.sunpay.repository.FriendRepository;
import com.project.fintech.sunpay.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FriendController {
    private final FriendRepository friendRepository;
    private final UserRepository userRepository;
    @GetMapping("request")
    public String request(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        if (user == null)return "redirect:/sign_in";
        List<Friend> byTo = friendRepository.findByTo(user);
        model.addAttribute("friend_list", byTo);
        return "request_list";
    }
    @GetMapping("request/{id:.+}")
    public String request_id(@PathVariable Long id, HttpSession session, Model model){
        User to = (User) session.getAttribute("user");
        if (to == null)return "redirect:/sign_in";
        User from
                = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        if (friendRepository.existsByToAndFrom(to,from)
                && friendRepository.existsByToAndFrom(from,to)){
            model.addAttribute("to", to);
            model.addAttribute("from", from);
        }else return "redirect:/request";
        return "request";
    }
}
