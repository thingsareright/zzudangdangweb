package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/12/8 0008.
 */
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT * FROM book  where bookname like CONCAT('%',:keyName,'%') limit 10", nativeQuery = true)
    List<Book> mySearch(@Param("keyName")  String bookname);

    Book findById(int id);

    List<Book> findByBooknameLike(String bookname);
}
