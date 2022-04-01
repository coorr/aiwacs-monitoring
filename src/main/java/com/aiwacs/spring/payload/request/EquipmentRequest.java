package com.aiwacs.spring.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.aiwacs.spring.models.Equipment;
import com.aiwacs.spring.models.Group;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipmentRequest {
    private Integer id;

	private String equipment;
	private String nickname;
	private String settingType;
	private String settingTemplate;
	@NotBlank
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
	
    public EquipmentRequest(String equipment, String nickname) {
        this.equipment = equipment;
        this.nickname = nickname;
    }
 
    
    
	
	
}