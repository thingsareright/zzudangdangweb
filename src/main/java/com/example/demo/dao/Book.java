package com.example.demo.dao;



import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/12/8 0008.
 * 映射数据库中的book表
 */
@Entity
@Table(name = "book")
public class Book implements Serializable{

    private int id;
    private String bookname;
    private String writter;
    private String press;
    private int version;
    private String ISBN;
    private int format;
    private int paper;
    private int bookpackage;
    private int bookkind;
    private int sellernumber;
    private int score;
    private int presstime;



    public Book() {
    }

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getWritter() {
        return writter;
    }

    public void setWritter(String writter) {
        this.writter = writter;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getFormat() {
        return format;
    }

    public void setFormat(int format) {
        this.format = format;
    }

    public int getPaper() {
        return paper;
    }

    public void setPaper(int paper) {
        this.paper = paper;
    }

    

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public int getBookpackage() {
        return bookpackage;
    }

    public void setBookpackage(int bookpackage) {
        this.bookpackage = bookpackage;
    }

    public int getBookkind() {
        return bookkind;
    }

    public void setBookkind(int bookkind) {
        this.bookkind = bookkind;
    }

    public int getSellernumber() {
        return sellernumber;
    }

    public void setSellernumber(int sellernumber) {
        this.sellernumber = sellernumber;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPresstime() {
        return presstime;
    }

    public void setPresstime(int presstime) {
        this.presstime = presstime;
    }

    
}
