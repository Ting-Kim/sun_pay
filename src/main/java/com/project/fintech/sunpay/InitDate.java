package com.project.fintech.sunpay;


import com.project.fintech.sunpay.model.Friend;
import com.project.fintech.sunpay.model.User;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

        @Transactional
        public void init() {
            User user = user("name1", 1);
            for (int i = 2; i < 12; i++) {
                User second = user("name" + i, i);
                friend(user, second);
            }
        }

        private void friend(User user1, User user2) {
            em.persist(Friend.builder()
                    .to(user1)
                    .from(user2)
                    .build());
            em.persist(Friend.builder()
                    .to(user2)
                    .from(user1)
                    .build());
        }

        private User user(String username, int price) {
            User user = User.builder()
                    .username(username)
                    .password("password")
                    .amount(price * 1000)
                    .build();
            em.persist(user);
            return user;
        }
    }
}