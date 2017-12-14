package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BossRepository  extends JpaRepository<Boss,Long> {
    Boss findById(int id);
}
