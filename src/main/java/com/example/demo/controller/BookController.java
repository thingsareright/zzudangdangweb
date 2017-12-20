package com.example.demo.controller;

import com.example.demo.bean.BookToClientOfBookListForResult;
import com.example.demo.bean.BookToClientforSingleBook;
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

    @Autowired
    BossRepository bossRepository;

    /**
     * 这个方法是根据书名来模糊查询返回所有的商品的列表
     * @param name
     * @return
     */
    @RequestMapping(value = "/bookForResult")
    public List<BookToClientOfBookListForResult> bookToClientOfBookListForResults(@RequestParam(value = "name",defaultValue = "MySQL")String name,
                                                                                  @RequestParam(value = "begin",defaultValue = "0")int begin,
                                                                                  @RequestParam(value = "range", defaultValue = "10")int range){
        List<BookToClientOfBookListForResult> bookListForResults = new ArrayList<>();
        List<Book> bookList = new ArrayList<>();
        bookList = bookRepository.mySearch(name,begin, range);
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
                        BookBossPicture  book_boss_picture = bookBossPictureRepository.findTopByBookidAndBossid(book_id,boss_id);
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

    /**
     * 这个方法用来根据书籍id和商家id分别从各个表中获取数据并传送
     * @param bookid
     * @param bossid
     * @return
     */
    @RequestMapping(value = "/bookForSingle")
    public BookToClientforSingleBook bookToClientforSingleBook(@RequestParam("bookid") int bookid,
                                                               @RequestParam("bossid") int bossid){
        BookToClientforSingleBook bookToClientforSingleBook = new BookToClientforSingleBook();
        Book book = bookRepository.findById(bookid);
        Boss boss = bossRepository.findById(bossid);
        BookBoss bookBoss = bookBossRepository.findByBookidAndBossid(bookid, bossid);
        List<BookBossPicture> bookBossPictures = bookBossPictureRepository.findAllByBookidAndBossid(bookid, bossid);
        bookToClientforSingleBook.setBookid(bookid);
        bookToClientforSingleBook.setBossid(bossid);
        bookToClientforSingleBook.setBook_name(book.getBookname());
        bookToClientforSingleBook.setBoss_name(boss.getBossName());
        bookToClientforSingleBook.setPress(book.getPress());
        bookToClientforSingleBook.setPrice(bookBoss.getPrice());
        bookToClientforSingleBook.setWritter(book.getWritter());
        bookToClientforSingleBook.setBoss_image(boss.getBossPicture());
        List<String> pictureList = new ArrayList<>();
        if (bookBossPictures != null){
            for (BookBossPicture bookBossPicture : bookBossPictures){
                pictureList.add(bookBossPicture.getPictureurl());
            }
        }
        bookToClientforSingleBook.setImageAddressList(pictureList);

        return bookToClientforSingleBook;
    }
}
