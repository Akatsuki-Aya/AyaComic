package com.example.aya.demo.dao.impl;

import com.example.aya.demo.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserImpl extends JpaRepository<User,Long> {
    User findByUserName(String userName);

    User findByUserNameOrIdCard(String username, String idCard);

}
