package com.project.fintech.sunpay.controller;

import com.project.fintech.sunpay.dto.*;
import com.project.fintech.sunpay.dto.withdraw.RequestApiWithdrawFrom;
import com.project.fintech.sunpay.dto.withdraw.ResponseApiWithdrawFrom;
import com.project.fintech.sunpay.model.User;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Controller
public class ApiController {
    private Random random = new Random();
    private WebClient webClient = WebClient.create();

    @GetMapping("me")
    public String me(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/sign_in";
        Mono<ApiMeForm> apiMeForm = webClient
                .mutate()
                .baseUrl("https://testapi.openbanking.or.kr").build()
                .get()
                .uri(it ->
                        it.path("/v2.0/user/me")
                                .queryParam("user_seq_no", user.getSeqNum()).build())
                .header("Authorization", "bearer " + user.getAccessToken())
                .retrieve()
                .bodyToMono(ApiMeForm.class);
        apiMeForm.subscribe(deposit -> {
            System.out.println(deposit.toString());
            model.addAttribute("me", deposit);
        });

        return "main";
    }

    @GetMapping("balance")
    public String balance(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/sign_in";
        Mono<ApiBalanceForm> apiBalanceFormMono = webClient.mutate()
                .baseUrl("https://testapi.openbanking.or.kr").build()
                .get()
                .uri(it ->
                        it
                                .path("/v2.0/account/balance/fin_num")
                                .queryParam("tran_dtime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))
                                .queryParam("fintech_use_num", "199166629057887693863446")
                                .queryParam("bank_tran_id", user.getUseCode() + "U" + random.nextInt(1000000000))
                                .build())
                .header("Authorization", "Bearer " + user.getAccessToken())
                .retrieve()
                .bodyToMono(ApiBalanceForm.class);
        apiBalanceFormMono.subscribe(apiBalanceForm -> {
            System.out.println(apiBalanceForm.toString());
            model.addAttribute("balance", apiBalanceForm);
        });
        return "main";
    }

    @GetMapping("withdraw")
    public String withdraw(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/sign_in";
        Mono<ResponseApiWithdrawFrom> responseApiWithdrawFromMono = webClient.mutate()
                .baseUrl("https://testapi.openbanking.or.kr").build()
                .post()
                .uri("/v2.0/transfer/withdraw/fin_num")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + user.getAccessToken())
                .bodyValue(new RequestApiWithdrawFrom(user, "199166629057887693863446", random, 10000))
                .retrieve()
                .bodyToMono(ResponseApiWithdrawFrom.class);
        responseApiWithdrawFromMono.subscribe(response -> {
            System.out.println(response.toString());
            model.addAttribute("withdraw", response);
        });
        return "main";
    }

    @GetMapping("deposit")
    public String deposit(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) return "redirect:/sign_in";
        Mono<String> responseApiWithdrawFromMono = webClient.mutate()
                .baseUrl("https://testapi.openbanking.or.kr").build()
                .post()
                .uri("/v2.0/transfer/deposit/fin_num")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + user.getAccessToken())
                .bodyValue(new RequestApiDepositFrom(user, "199166629057887693863446", random, 100))
                .retrieve()
                .bodyToMono(String.class);
        responseApiWithdrawFromMono.subscribe(response ->{
            model.addAttribute("deposit",response);
            System.out.println(response);
        });
        return "main";
    }


}
