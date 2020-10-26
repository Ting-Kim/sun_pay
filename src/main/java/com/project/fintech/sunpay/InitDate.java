package com.project.fintech.sunpay;


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
            User user1 = user("name1");
            User user2 = user("name2");
//            Friend friend = friend(user1, user2);


        }

       /* private Friend friend(User user1, User user2) {
            Friend friend = Friend.builder()
                    .to(user1)
                    .from(user2)
                    .build();
            em.persist(friend);
            return friend;
        }*/

        private User user(String username) {
            User user = User.builder()
                    .username(username)
                    .password("password")
                    .build();
            em.persist(user);
            return user;
        }
    }
}