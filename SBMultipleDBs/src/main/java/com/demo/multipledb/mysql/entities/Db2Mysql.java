package com.demo.multipledb.mysql.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "db_2_mysql")
public class Db2Mysql {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer db2id;

    private String db2firstname;

    private String db2lastname;

    public Integer getDb2id() {
        return db2id;
    }

    public void setDb2id(Integer db2id) {
        this.db2id = db2id;
    }

    public String getDb2firstname() {
        return db2firstname;
    }

    public void setDb2firstname(String db2firstname) {
        this.db2firstname = db2firstname;
    }

    public String getDb2lastname() {
        return db2lastname;
    }

    public void setDb2lastname(String db2lastname) {
        this.db2lastname = db2lastname;
    }
}
