package com.example.demo.dao;

import com.example.demo.dao.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {

    List<Cart> findAllByPhoneOrderByBossid(String phone);

    List<Cart> findAllByPhoneAndBossid(String phone, int bossid);

    List<Cart> findAllByPhoneAndBookidAndBossid(String phone, int bookid, int bossid);

}
