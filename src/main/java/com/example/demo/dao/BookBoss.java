package com.example.demo.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.apache.catalina.LifecycleState;
import org.dom4j.tree.AbstractEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/12/8 0008.
 * 映射bookboss表
 */
@Entity
@IdClass(PrimaryKey.class)
public class BookBoss implements Serializable{

    private int bookid;
    private int bossid;
    private float price;
    private int sellnumber;
    private String text;


    public BookBoss() {
    }



    @Id
    @Column(insertable=false,updatable=false)
    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }


    @Id
    @Column(insertable=false,updatable=false)
    public int getBossid() {
        return bossid;
    }

    public void setBossid(int bossid) {
        this.bossid = bossid;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public int getSellnumber() {
        return sellnumber;
    }

    public void setSellnumber(int sellnumber) {
        this.sellnumber = sellnumber;
    }




}

class PrimaryKey implements Serializable {

    private int bookid;
    private int bossid;


    public PrimaryKey() {
    }

    public PrimaryKey(int bookid, int bossid) {
        this.bookid = bookid;
        this.bossid = bossid;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public int getBossid() {
        return bossid;
    }

    public void setBossid(int bossid) {
        this.bossid = bossid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrimaryKey that = (PrimaryKey) o;

        if (bookid != that.bookid) return false;
        return bossid == that.bossid;
    }

    @Override
    public int hashCode() {
        int result = bookid;
        result = 31 * result + bossid;
        return result;
    }
}
