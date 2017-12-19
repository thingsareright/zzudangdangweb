package com.example.demo.dao;

import org.dom4j.tree.AbstractEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Administrator on 2017/12/8 0008.
 *  映射book_boss_picture表
 */
@Entity
@IdClass(PrimaryKeyForBookBossPicture.class)
public class BookBossPicture {


    private int bookid;

    private int bossid;

    private String pictureurl;


    public BookBossPicture() {
    }

    @Id
    @Column(insertable=false,updatable=false)
    public int getBookid() {
        return bookid;
    }

    public void setBookid(int book_id) {
        this.bookid = book_id;
    }

    @Id
    @Column(insertable=false,updatable=false)
    public int getBossid() {
        return bossid;
    }

    public void setBossid(int boss_id) {
        this.bossid = boss_id;
    }

    @Id
    @Column(insertable=false,updatable=false)
    public String getPictureurl() {
        return pictureurl;
    }

    public void setPictureurl(String picture_url) {
        this.pictureurl = picture_url;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookBossPicture that = (BookBossPicture) o;
        return bookid == that.bookid &&
                bossid == that.bossid &&
                Objects.equals(pictureurl, that.pictureurl);
    }

    @Override
    public int hashCode() {

        return Objects.hash(bookid, bossid, pictureurl);
    }
}

class PrimaryKeyForBookBossPicture implements Serializable {


    private int bookid;

    private int bossid;

    private String pictureurl;


    public PrimaryKeyForBookBossPicture() {
    }

    public PrimaryKeyForBookBossPicture(int bookid, int bossid, String pictureurl) {
        this.bookid = bookid;
        this.bossid = bossid;
        this.pictureurl = pictureurl;
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

    public String getPictureurl() {
        return pictureurl;
    }

    public void setPictureurl(String pictureurl) {
        this.pictureurl = pictureurl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrimaryKeyForBookBossPicture that = (PrimaryKeyForBookBossPicture) o;

        if (bookid != that.bookid) return false;
        if (bossid != that.bossid) return false;
        return pictureurl != null ? pictureurl.equals(that.pictureurl) : that.pictureurl == null;
    }

    @Override
    public int hashCode() {
        int result = bookid;
        result = 31 * result + bossid;
        result = 31 * result + (pictureurl != null ? pictureurl.hashCode() : 0);
        return result;
    }
}
