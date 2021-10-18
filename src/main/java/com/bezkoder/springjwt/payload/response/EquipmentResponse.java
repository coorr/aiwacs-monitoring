package com.bezkoder.springjwt.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipmentResponse {


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
	
	
	

}
