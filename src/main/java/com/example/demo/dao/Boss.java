package com.example.demo.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "boss")
public class Boss {

    private int id;
    private String bossName;
    private String bossPicture;

    public Boss() {
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "bossname")
    public String getBossName() {
        return bossName;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
    }

    @Column(name = "bosspicture")
    public String getBossPicture() {
        return bossPicture;
    }

    public void setBossPicture(String bossPicture) {
        this.bossPicture = bossPicture;
    }
}
