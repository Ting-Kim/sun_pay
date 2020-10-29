package com.project.fintech.sunpay;


import com.project.fintech.sunpay.model.Account;
import com.project.fintech.sunpay.model.Friend;
import com.project.fintech.sunpay.model.Request;
import com.project.fintech.sunpay.model.User;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;

@Component
@RequiredArgsConstructor
public class InitDate {
    private final InitService initService;
    @PostConstruct
    public void init() {
        initService.init();
    }
    @Component
    static class InitService {
        @PersistenceContext
        private EntityManager em;
        private String[] names ={"구태균", "김태호", "이은주", "김대철"};
        private String[] accountNumberList ={"21701956105804", "000000123456","0105323002009","01053230020"};
        private String[] accountAmountList ={"10000", "20000","30000","40000"};
        private int[] sendAmountList = {1000, 2000, 3000, 4000};
        private int sendAmountCount = 0;

        private String[] accountNameList ={"신협", "농협","국민","우체국"};

        @Transactional
        public void init() {
            User koo = user( 0);
            for (int i = 1; i < names.length; i++) {
                User user = user( i);
                friend(user, koo, sendAmountList[sendAmountCount]);
                friend(koo,user, sendAmountList[sendAmountCount++]/10);
            }

        }

        private void friend(User user1, User user2, int sendAmount) {
            Friend friend = Friend.builder()
                    .from(user1)
                    .to(user2)
                    .build();
            em.persist(friend);

            Request request = Request.builder()
                    .amount(sendAmount)
                    .from(user1)
                    .to(user2)
                    .build();
            em.persist(request);

        }


        private User user(int index) {
            User user = User.builder()
                    .account(account(index))
                    .password("pass")
                    .username("user" + (index + 1))
                    .name(names[index])
                    .build();
            em.persist(user);
            return user;

        }
        private Account account(int index){
            return Account.builder()
                    .owner(names[index])
                    .number(accountNumberList[index])
                    .max(new BigInteger("100000"))
                    .amount(new BigInteger(accountAmountList[index]))
                    .bank_name(accountNameList[index])
                    .build();
        }

    }
}