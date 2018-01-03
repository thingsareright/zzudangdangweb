package com.example.demo.dao.entity;



import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "cart")
@IdClass(PrimaryKeyForOrder.class)
public class Cart {

    private int bookid;
    private int bossid;
    private String phone;
    private int number;
    private float price;
    private String bookname;
    private String bossname;
    private String bookpicture;
    private String bosspicture;

    public Cart() {
    }

    public Cart(int bookid, int bossid, String phone, int number, float price, String bookname, String bossname, String bookpicture, String bosspicture) {
        this.bookid = bookid;
        this.bossid = bossid;
        this.phone = phone;
        this.number = number;
        this.price = price;
        this.bookname = bookname;
        this.bossname = bossname;
        this.bookpicture = bookpicture;
        this.bosspicture = bosspicture;
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

    @Id
    @Column(insertable=false,updatable=false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBossname() {
        return bossname;
    }

    public void setBossname(String bossname) {
        this.bossname = bossname;
    }

    public String getBookpicture() {
        return bookpicture;
    }

    public void setBookpicture(String bookpicture) {
        this.bookpicture = bookpicture;
    }

    public String getBosspicture() {
        return bosspicture;
    }

    public void setBosspicture(String bosspicture) {
        this.bosspicture = bosspicture;
    }




}

class PrimaryKeyForOrder implements Serializable {

    private int bookid;
    private int bossid;
    private String phone;


    public PrimaryKeyForOrder() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrimaryKeyForOrder that = (PrimaryKeyForOrder) o;
        return bookid == that.bookid &&
                bossid == that.bossid &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(bookid, bossid, phone);
    }
}
