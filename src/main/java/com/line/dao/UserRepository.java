package com.line.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.line.entity.User;

/**
 * Created by admin on 2019/1/2.
 */
@Component
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);

    User findByUserNameOrEmail(String username, String email);
}
