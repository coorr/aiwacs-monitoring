package com.aiwacs.spring.payload.request;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@JsonAutoDetect
public class Test {
	private Integer[] groupId;
	
	public Test() {}

	public Test(Integer[] groupId) {
		this.groupId = groupId;
	}

	

	
	
}
