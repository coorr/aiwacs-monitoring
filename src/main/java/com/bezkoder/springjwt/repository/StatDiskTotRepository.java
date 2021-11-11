package com.bezkoder.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.StatDiskTot;

@Repository
public interface StatDiskTotRepository  extends JpaRepository<StatDiskTot, Integer> {

   

}
