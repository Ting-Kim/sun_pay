package com.project.fintech.sunpay;


import com.project.fintech.sunpay.model.User;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Profile("local")
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
            user();
        }

        private void user() {
            User user = User.builder()
                    .username("username")
                    .password("password")
                    .build();
            em.persist(user);
        }
    }
}