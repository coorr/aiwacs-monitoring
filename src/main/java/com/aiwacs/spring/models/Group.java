package com.aiwacs.spring.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "group_equipment")
@NoArgsConstructor
@Getter
@Setter
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "group_id")
	private Integer id;

	private String treeName;  
	
    private Integer parent;
    private Integer depth;
    private Integer rootId;
		
//	@JsonIgnore
//	@OneToMany(mappedBy = "group" , cascade = CascadeType.ALL)
//	private List<Equipment> equipments = new ArrayList<>();

	
	
	


}



















































































































