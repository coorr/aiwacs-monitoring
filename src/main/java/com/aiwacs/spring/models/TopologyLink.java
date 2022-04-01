package com.aiwacs.spring.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "topology_Link")
@NoArgsConstructor
@Getter
@Setter
public class TopologyLink {
    
        @Id
//        @SequenceGenerator(name="topology_Link_seq", sequenceName="topology_Link_seq", allocationSize=1)
//        @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="topology_Link_seq")
        @Column(name = "Link_id")
        private Integer id;
        
        private String froms;  
        private String tos;
        private Integer borderColor;
        
        @JoinColumn(name = "diagram_id")
        private Integer diagramId;   
        
        
}