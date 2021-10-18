package com.bezkoder.springjwt.payload.response;

import java.util.List;

import com.bezkoder.springjwt.models.Equipment;
import com.bezkoder.springjwt.models.Group;

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
