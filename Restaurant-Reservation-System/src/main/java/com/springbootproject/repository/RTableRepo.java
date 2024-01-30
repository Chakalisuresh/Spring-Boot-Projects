package com.springbootproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springbootproject.model.RTable;




@Repository
public interface RTableRepo extends JpaRepository<RTable, Long> {

}
