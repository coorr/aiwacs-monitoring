package com.aiwacs.spring.payload.response;

import java.util.List;

import com.aiwacs.spring.models.Equipment;
import com.aiwacs.spring.models.Group;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupResponse {

	private Integer groupId;
	private String treeName;
//	private List<Equipment> equipments;
	
	public GroupResponse(Group group) {
		groupId=group.getId();
		treeName=group.getTreeName();
//		equipments=group.getEquipments();
	}
	
}
