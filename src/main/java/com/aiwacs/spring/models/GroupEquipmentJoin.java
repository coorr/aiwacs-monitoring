package com.aiwacs.spring.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table (name = "group_equipment_join")
@Getter
@Setter
@NoArgsConstructor
public class GroupEquipmentJoin {
    @Id
    private Integer equipment_id;
    private Integer group_id;
}


