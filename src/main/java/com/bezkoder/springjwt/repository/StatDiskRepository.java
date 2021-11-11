package com.bezkoder.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.StatDisk;

@Repository
public interface StatDiskRepository  extends JpaRepository<StatDisk, Integer> {

   

}
