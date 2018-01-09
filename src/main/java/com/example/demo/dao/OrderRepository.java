package com.example.demo.dao;

import com.example.demo.dao.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByPhoneOrderByBossid(String phone);

    List<Order> findAllByPhoneAndBossid(String phone, int bossid);

    List<Order> findAllByPhoneAndBookidAndBossid(String phone, int bookid, int bossid);
}
