package com.example.demo.dao;

import com.example.demo.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,String> {


    User findByPhoneAndPassword(String phone, String password);

    @Query(value = "select password from user where phone = :phone",nativeQuery = true)
    String findPasswordByPhone(@Param("phone") String phone);

    User findTopByPhone(String phone);

    @Modifying
    @Query(value = "update user set password = :password where phone= :phone",nativeQuery = true)
    void changePassword(@Param("phone")String phone, @Param("password")String password);
}
