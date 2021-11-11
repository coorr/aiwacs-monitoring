package com.bezkoder.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.springjwt.models.StatNetwork;

@Repository
public interface StatNetworkRepository  extends JpaRepository<StatNetwork, Integer> {

   

}
