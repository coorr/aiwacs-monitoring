package com.bezkoder.springjwt.service;

import java.util.List;

import com.bezkoder.springjwt.models.TopologyNode;

public interface TopologyNodeService {
    
    public List<TopologyNode> getTopologyNode();
    public void insertTopologyNode(String topologyNode);

}
