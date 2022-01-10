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
//        @SequenceGenerator(initialValue = 1,name="topology_node_seqs", sequenceName="topology_node_seqs", allocationSize=1)
//        @GeneratedValue(generator="stat_network_seqs")
        @Column(name = "node_id")
        private Integer id;

        private String equipment;  
        private String loc;
        private String settingIp;
}
