package com.project.fintech.sunpay.repository;

import com.project.fintech.sunpay.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
