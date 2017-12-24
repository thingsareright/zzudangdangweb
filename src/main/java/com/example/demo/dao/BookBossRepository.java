package com.example.demo.dao;

import com.example.demo.dao.entity.BookBoss;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/12/8 0008.
 */
public interface BookBossRepository extends JpaRepository<BookBoss, Long> {
    List<BookBoss> findAllByBookid(int id);

    BookBoss findByBookidAndBossid(int boodid, int bossid);
}
