package com.example.demo.dao;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "boss")
public class Boss {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "bossname")
    private String bossName;
    @Column(name = "bosspicture")
    private String bossPicture;

    public Boss() {
    }


    public int getId() {
        return id;
    }

    public void setId(int i) {
        this.id = id;
    }

    public String getBossName() {
        return bossName;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
    }

    public String getBossPicture() {
        return bossPicture;
    }

    public void setBossPicture(String bossPicture) {
        this.bossPicture = bossPicture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Boss boss = (Boss) o;
        return id == boss.id &&
                Objects.equals(bossName, boss.bossName) &&
                Objects.equals(bossPicture, boss.bossPicture);
    }


    @Override
    public int hashCode() {

        return Objects.hash(id, bossName, bossPicture);
    }
}
