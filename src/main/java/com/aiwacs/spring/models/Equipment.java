package com.aiwacs.spring.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "equipment")
@NoArgsConstructor
@Getter
@Setter
public class Equipment {
	
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "equipment_id")
	private Integer id;

	private String equipment;
	private String nickname;
	private String settingType;
	private String settingTemplate;
	private String settingIp;	
	private String settingCatagory;
	private String settingOs;
	private String settingPerson;
	private String settingProxy;
	private boolean settingActive;
	private Integer hwCpu;
	private Integer hwDisk;
	private Integer hwNic;
	private Integer hwSensor;
	private boolean deletedFlag;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "group_equipment_join", 
    joinColumns = @JoinColumn(name = "equipment_id"),  
    inverseJoinColumns = @JoinColumn(name = "group_id"))
	private Group group;
	
	
	
	
	// 연관 관계 메소드
//	public void setGroup(Group group) {
//		this.group=group;
//		group.getEquipments().add(this);
//	}

	public static Equipment creatEquipment(String equipment, String nickname, String settingType, String settingTemplate, String settingIp,
			String settingCatagory, String settingOs, String settingPerson, String settingProxy, boolean settingActive,
			Integer hwCpu, Integer hwDisk, Integer hwNic, Integer hwSensor, boolean deletedFlag) {
		Equipment equipments = new Equipment();
		
		equipments.setEquipment(equipment);
		equipments.setNickname(nickname);
		equipments.setSettingType(settingType);
		equipments.setSettingTemplate(settingTemplate);
		equipments.setSettingIp(settingIp);
		equipments.setSettingCatagory(settingCatagory);
		equipments.setSettingOs(settingOs);
		equipments.setSettingPerson(settingPerson);
		equipments.setSettingProxy(settingProxy);
		equipments.setSettingActive(settingActive);
		equipments.setHwCpu(hwCpu);
		equipments.setHwDisk(hwDisk);
		equipments.setHwNic(hwNic);
		equipments.setHwSensor(hwSensor);
		equipments.setDeletedFlag(deletedFlag);
	
		return equipments;
	}


	
	
	
	


	

	
	

}



	
	
	
	

	
	
	

	





	
	
	

	






	
	
	
	
	
	
	

	





	
	
	

	






	
	
	
	
	
	
	

	




