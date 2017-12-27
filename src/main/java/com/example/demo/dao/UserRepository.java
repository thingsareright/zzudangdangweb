package com.example.demo.dao;

import com.example.demo.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,String> {


    User findByPhoneAndPassword(String phone, String password);

    @Query(value = "select password from user where phone = :phone",nativeQuery = true)
    String findPasswordByPhone(@Param("phone") String phone);
}
