package com.bezkoder.springjwt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.jpa.repository.Modifying;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "topology_node")
@NoArgsConstructor
@Getter
@Setter
public class TopologyNode {
    
//        @Id  
//        @SequenceGenerator(initialValue = 1,name="topology_node_seqs", sequenceName="topology_node_seqs", allocationSize=1)
//        @GeneratedValue(generator="stat_network_seqs")
        @Id
        @Column(name = "node_Id")
        private String id;      
   
        private String equipment;  
        private String loc;
        private String settingIp;
        
//        @ManyToOne(fetch = FetchType.LAZY)
//        @JsonIgnore
        @JoinColumn(name = "diagram_id")
        private Integer diagramId;
}
