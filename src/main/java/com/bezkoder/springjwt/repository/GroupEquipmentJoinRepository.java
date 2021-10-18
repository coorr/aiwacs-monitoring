package com.bezkoder.springjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bezkoder.springjwt.models.GroupEquipmentJoin;

@Repository
public interface GroupEquipmentJoinRepository extends JpaRepository<GroupEquipmentJoin, Integer> {

}
