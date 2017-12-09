package com.example.demo.controller;

import com.example.demo.bean.BookToClientOfBookListForResult;
import com.example.demo.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/8 0008.
 * 用于返回book类数据查询的请求
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    BookBossRepository bookBossRepository;

    @Autowired
    BookBossPictureRepository bookBossPictureRepository;

    @RequestMapping(value = "/bookForResult")
    public List<BookToClientOfBookListForResult> bookToClientOfBookListForResults(@RequestParam("name")String name){
        List<BookToClientOfBookListForResult> bookListForResults = new ArrayList<>();
        List<Book> bookList = new ArrayList<>();
        bookList = bookRepository.mySearch(name);
        if (bookList != null){
            for(Book book : bookList){
                BookToClientOfBookListForResult listForResult = new BookToClientOfBookListForResult();
                listForResult.setBook_kind(book.getBookkind());
                listForResult.setBook_name(book.getBookname());
                listForResult.setBook_package(book.getBookpackage());
                listForResult.setWritter(book.getWritter());
                listForResult.setPaper(book.getPaper());
                listForResult.setId(book.getId());
                listForResult.setPress(book.getPress());
                listForResult.setVersion(book.getVersion());
                listForResult.setFormat(book.getFormat());
                listForResult.setISBN(book.getISBN());
                List<BookBoss> book_bossList = bookBossRepository.findAllByBookid(listForResult.getId());
                if (book_bossList != null){
                    for (BookBoss book_boss : book_bossList){
                        int book_id = listForResult.getId();
                        int boss_id = book_boss.getBossid();
                        BookBossPicture  book_boss_picture = bookBossPictureRepository.findTopByBookidAndAndBossid(book_id,boss_id);
                        if (book_boss_picture != null){
                             listForResult.setBoss_id(book_boss.getBossid());
                             listForResult.setBook_price(book_boss.getPrice());
                             listForResult.setBook_picture(book_boss_picture.getPictureurl());
                             bookListForResults.add(listForResult);
                        }
                    }
                }
            }
        }
        return bookListForResults;
    }

    
}
