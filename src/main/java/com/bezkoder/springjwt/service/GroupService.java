package com.bezkoder.springjwt.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ResponseBody;

import com.bezkoder.springjwt.models.Equipment;
import com.bezkoder.springjwt.models.Group;
import com.bezkoder.springjwt.models.GroupEquipmentJoin;
import com.bezkoder.springjwt.payload.response.GroupResponse;

public interface GroupService {

    public List<Object> getGroupEquipment();
    public Map<String, Object> unGroupEquipment();
    public void deleteGroupEquipmentMapping(String name,String groupId);
    public void insertGroupFirst(Group group);
    public void insertGroupEquipmentMapping(GroupEquipmentJoin groupEquipmentJoin);
    public void updateGroupName(Integer id,Group group);
    public List<Group> getGroupName(Integer id);
    public void insertGroupSecond(Group group);
}
