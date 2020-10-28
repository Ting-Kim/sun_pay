package com.project.fintech.sunpay;


import com.project.fintech.sunpay.model.Friend;
import com.project.fintech.sunpay.model.Request;
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
            User koo = makeUser("구태균", "pass"
                    , "d6NED6UBY37jD74AldxE3efJ5a0lDQXWr6y7lAVI"
                    , "9jhDrk9ZQpO3SvaB0ixr96DA4qBQNdUDwwbkKXvB"
                    , "i6aLFhiLELl35T8BA2g74nxu5A3n2z");

            User lee = makeUser("이은주", "pass"
                    , "mkTKJ5HyW99zSPXcwnR65K2UT6ZUBAVyQxiZaJkd "
                    , "UNTXWPsLaEzDKz5vPvyLHVVudgeAju1Ot9Lje0Bf"
                    , "g5HBcK1XQVeSTtYUeH7RZBNKJOB02B");
        }

        private void friend(User user1, User user2, int amount) {
            em.persist(Friend.builder()
                    .to(user1)
                    .from(user2)
                    .build());
            em.persist(Friend.builder()
                    .to(user2)
                    .from(user1)
                    .build());

            em.persist(Request.builder().to(user1).from(user2).amount(amount).build());
            em.persist(Request.builder().to(user2).from(user1).amount(amount).build());
        }

        public User makeUser(String username, String password, String clientId, String clientSecret, String code) {
            User user = User.builder()
                    .username(username)
                    .password("password")
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .code(code)
                    .build();
            em.persist(user);
            return user;
        }
    }
}