package com.bezkoder.springjwt.service;

import java.util.List;
import java.util.Map;

import com.bezkoder.springjwt.models.TopologyNode;

public interface TopologyNodeService {
    
    public Map<String, Object> getTopologyNode();
    public void insertTopologyNode(String topologyNode);

}
