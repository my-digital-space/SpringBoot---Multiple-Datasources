package com.demo.multipledb.mysql.repo;

import com.demo.multipledb.mysql.entities.Db2Mysql;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Db2MysqlRepo extends JpaRepository<Db2Mysql, Integer> {

}
