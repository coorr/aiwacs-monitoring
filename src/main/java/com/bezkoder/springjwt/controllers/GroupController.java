package com.bezkoder.springjwt.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.models.Group;
import com.bezkoder.springjwt.models.GroupEquipmentJoin;
import com.bezkoder.springjwt.service.GroupService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/manage")
@RequiredArgsConstructor
public class GroupController {

	private final GroupService groupService;

	@GetMapping("/group/getGroupEquipment")
	public List<Object> getGroupEquipment() throws IOException, ParseException  {
	    return groupService.getGroupEquipment();
	}
	
	@GetMapping("/group/unGroupEquipment")
	public Map<String, Object> unGroupEquipment() {
	    return groupService.unGroupEquipment();
	}
	
	@PostMapping("/group/deleteGroupEquipmentMapping/{name}/{id}")
	public void deleteGroupEquipmentMapping(@PathVariable("name") String name,@PathVariable("id") String groupId) {
	    groupService.deleteGroupEquipmentMapping(name,groupId);
	}
	
	@PostMapping("/group/insertGroupFirst")
    public void insertGroupFirst(@RequestBody Group group) {
        groupService.insertGroupFirst(group);
    }
	
	@PostMapping("/group/insertGroupEquipmentMapping")
    public void insertGroupEquipmentMapping(@RequestBody GroupEquipmentJoin groupEquipmentJoin ) {
        groupService.insertGroupEquipmentMapping(groupEquipmentJoin);
    }
	
	@PostMapping("/group/updateGroupName/{id}")
    public void updateGroupName(@PathVariable("id") Integer id, @RequestBody Group group) {
	    System.out.println(group.getTreeName());
	    System.out.println(id);
        groupService.updateGroupName(id,group);
    }
	
	@GetMapping("/group/getGroupName/{id}")
    public List<Group> getGroupName(@PathVariable("id") Integer id ) {
        return groupService.getGroupName(id);
    }
	
	@PostMapping("/group/insertGroupSecond")
    public void insertGroupSecond(@RequestBody Group group) {
        groupService.insertGroupSecond(group);
    }
	
	@GetMapping("/group/filterType/{equipType}/{equipCatagory}")
	public List<Object> searchFilterGroup(@PathVariable("equipType") String equipType, @PathVariable("equipCatagory") String equipCatagory) {
	    return groupService.searchFilterGroup(equipType,equipCatagory);
	}
	
	@PostMapping("/group/deleteGroupEquipByNo/{equipId}")
	public void deleteGroupEquipByNo(@PathVariable("equipId") String equipId) {
	        groupService.deleteGroupEquipByNo(equipId);
	}
      
	
	
	
	
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	