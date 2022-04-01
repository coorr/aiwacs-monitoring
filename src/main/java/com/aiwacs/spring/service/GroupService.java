package com.aiwacs.spring.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.aiwacs.spring.models.Group;
import com.aiwacs.spring.models.GroupEquipmentJoin;

public interface GroupService {

    public List<Object> getGroupEquipment() throws IOException;
    public Map<String, Object> unGroupEquipment();
    public void deleteGroupEquipmentMapping(String name,String groupId);
    public void insertGroupFirst(Group group);
    public void insertGroupEquipmentMapping(GroupEquipmentJoin groupEquipmentJoin);
    public void updateGroupName(Integer id,Group group);
    public List<Group> getGroupName(Integer id);
    public void insertGroupSecond(Group group);
    public List<Object> searchFilterGroup(String equipType,String equipCatagory);
    public void deleteGroupEquipByNo(String equipId);

}
