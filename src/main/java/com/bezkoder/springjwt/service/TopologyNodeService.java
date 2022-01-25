package com.bezkoder.springjwt.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.springjwt.models.DiagramGroup;
import com.bezkoder.springjwt.models.TopologyNode;

public interface TopologyNodeService {
    
    public Map<String, Object> getTopologyNode(Integer diagramId);
    public void insertTopologyNode(Integer diagramId, String topologyNode);
    public void diagramInsertImage(Integer diagramId, MultipartFile multipartFile);
    public List<DiagramGroup> insertDiagramGroup(String diagramGroup);
    public List<DiagramGroup> getDiagramGroup();
    public List<DiagramGroup> updateDiagramGroup(String diagramGroup);
    public List<DiagramGroup> deleteDiagramGroup(String groupId);

}
