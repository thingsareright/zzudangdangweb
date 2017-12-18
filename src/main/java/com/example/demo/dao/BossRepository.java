package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface BossRepository  extends JpaRepository<Boss,Long> {
    Boss findById(int id);

    @Query(value = "insert into boss(bossname,bosspicture) VALUES (?1,?2)", nativeQuery = true)
    @Modifying
    @Transactional
    void insertSingleBook( String bossname, String bosspicture);
}
