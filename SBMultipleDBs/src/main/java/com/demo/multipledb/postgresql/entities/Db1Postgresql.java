package com.demo.multipledb.postgresql.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "db_1_postgresql")
public class Db1Postgresql {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer db1id;

    private String db1firstname;

    private String db1lastname;

    public Integer getDb1id() {
        return db1id;
    }

    public void setDb1id(Integer db1id) {
        this.db1id = db1id;
    }

    public String getDb1firstname() {
        return db1firstname;
    }

    public void setDb1firstname(String db1firstname) {
        this.db1firstname = db1firstname;
    }

    public String getDb1lastname() {
        return db1lastname;
    }

    public void setDb1lastname(String db1lastname) {
        this.db1lastname = db1lastname;
    }
}
