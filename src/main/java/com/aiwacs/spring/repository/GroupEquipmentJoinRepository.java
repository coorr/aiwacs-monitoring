package com.aiwacs.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aiwacs.spring.models.GroupEquipmentJoin;

@Repository
public interface GroupEquipmentJoinRepository extends JpaRepository<GroupEquipmentJoin, Integer> {

}
