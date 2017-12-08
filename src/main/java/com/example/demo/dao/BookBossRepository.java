package com.example.demo.dao;

import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/12/8 0008.
 */
public interface BookBossRepository extends JpaRepository<BookBoss, Long> {
    List<BookBoss> findAllByBookid(int id);
}
