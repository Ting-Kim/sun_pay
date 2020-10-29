package com.project.fintech.sunpay.dto;

import com.project.fintech.sunpay.model.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RequestApiDepositFrom {
    private String cntr_account_type;
    private String cntr_account_num;
    private String wd_pass_phrase;
    private String wd_print_content;
    private String name_check_option;
    private String tran_dtime;
    private String req_cnt;
    private List<RequestApiDepositOfReqFrom> req_list = new ArrayList<>();

    public RequestApiDepositFrom(User user, String fintech_use_num, Random random, int amount) {
        cntr_account_type = "N";
        cntr_account_num = user.getInputAccountNumber();
        wd_pass_phrase = "NONE";
        wd_print_content = "입금";
        name_check_option = "on";
        tran_dtime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        req_cnt = "1";
        req_list.add(new RequestApiDepositOfReqFrom(user, random, fintech_use_num, amount));
    }
}
