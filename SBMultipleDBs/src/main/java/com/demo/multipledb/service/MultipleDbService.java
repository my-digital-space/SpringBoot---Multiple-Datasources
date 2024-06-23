package com.demo.multipledb.service;

import com.demo.multipledb.mysql.entities.Db2Mysql;
import com.demo.multipledb.mysql.repo.Db2MysqlRepo;
import com.demo.multipledb.postgresql.entities.Db1Postgresql;
import com.demo.multipledb.postgresql.repo.Db1PostgresqlRepo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MultipleDbService {

    private final Db1PostgresqlRepo db1PostgresqlRepo;
    private final Db2MysqlRepo db2MysqlRepo;

    public MultipleDbService(Db1PostgresqlRepo db1PostgresqlRepo, Db2MysqlRepo db2MysqlRepo) {
        this.db1PostgresqlRepo = db1PostgresqlRepo;
        this.db2MysqlRepo = db2MysqlRepo;
    }

    public void saveInDB1PostgreSql() {
        Db1Postgresql db1Postgresql = getDb1Entity();
        transactionOnDb1(db1Postgresql);
    }

    public void saveInDB2MySql() {
        Db2Mysql db2Mysql = getDb2Entity();
        transactionOnDb2(db2Mysql);
    }

    // Note: The below qualifier name is optional
    @Transactional("postgreSqlTransactionManagerBean")
    public void transactionOnDb1(Db1Postgresql db1Postgresql) {
        db1PostgresqlRepo.save(db1Postgresql);
    }

    // Note: The below qualifier name is optional
    @Transactional("mySqlTransactionManagerBean")
    public void transactionOnDb2(Db2Mysql db2Mysql) {
        db2MysqlRepo.save(db2Mysql);
    }

    public Db1Postgresql getDb1Entity() {
        Db1Postgresql db1Postgresql = new Db1Postgresql();
        db1Postgresql.setDb1firstname("Postgresql first name");
        db1Postgresql.setDb1lastname("Postgresql last name");
        return db1Postgresql;
    }

    public Db2Mysql getDb2Entity() {
        Db2Mysql db2Mysql = new Db2Mysql();
        db2Mysql.setDb2firstname("MySQL first name");
        db2Mysql.setDb2lastname("MySQL last name");
        return db2Mysql;
    }


}
