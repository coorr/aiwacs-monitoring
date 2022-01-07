package com.bezkoder.springjwt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "topology_node")
@NoArgsConstructor
@Getter
@Setter
public class TopologyNode {
    
        @Id
        @SequenceGenerator(name="topology_node_seq", sequenceName="topology_node_seq", allocationSize=1)
        @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="stat_network_seq")
        @Column(name = "node_id")
        private Integer id;

        private String equipment;  
        private String loc;
        private String settingIp;
}
