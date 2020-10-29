package com.project.fintech.sunpay.dto;

import com.project.fintech.sunpay.model.User;

import java.util.List;
import java.util.Random;

public class RequestApiDepositOfReqFrom {
    private String tran_no;
    private String bank_tran_id;
    private String fintech_use_num;
    private String print_content;
    private String tran_amt;
    private String req_client_name;
    private String req_client_fintech_use_num;
    private String req_client_num;
    private String transfer_purpose;

    public RequestApiDepositOfReqFrom(User user, Random random, String fintech_use_num, int amount) {
        tran_no = "1";
        bank_tran_id = user.getUseCode() + "U" + random.nextInt(1000000000);
        this.fintech_use_num = fintech_use_num;
        print_content = "입금";
        tran_amt = String.valueOf(amount);
        req_client_name = user.getName();
        req_client_fintech_use_num = fintech_use_num;
        req_client_num = user.getName();
        transfer_purpose="ST";
    }
}
