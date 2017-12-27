package com.example.demo.dao;

import com.example.demo.dao.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2017/12/8 0008.
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT * FROM book WHERE id > (SELECT id FROM book WHERE bookname like CONCAT('%',?1,'%') LIMIT ?2,1) LIMIT ?3", nativeQuery = true)
    List<Book> mySearch( String bookname,int begin, int range);

    Book findById(int id);

    List<Book> findByBooknameLike(String bookname);
}
