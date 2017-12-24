package com.example.demo.dao;

import com.example.demo.dao.entity.User;
import com.example.demo.dao.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTokenRepository extends JpaRepository<UserToken,Long> {

    UserToken findTopByPhone(String phone);

}
