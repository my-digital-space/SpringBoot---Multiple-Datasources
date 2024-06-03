package com.demo.multipledb.postgresql.repo;

import com.demo.multipledb.postgresql.entities.Db1Postgresql;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Db1PostgresqlRepo extends JpaRepository<Db1Postgresql, Integer> {

}
