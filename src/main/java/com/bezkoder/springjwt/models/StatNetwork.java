package com.bezkoder.springjwt.models;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name="stat_network")
public class StatNetwork {
    
    @Id
    @SequenceGenerator(name="stat_network_seq", sequenceName="stat_network_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="stat_network_seq")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="generate_time")
    private Date generateTime;
    
    @Column(name="in_bytes_per_sec")
    private BigDecimal inBytesPerSec;
    
    @Column(name="in_discard_pkts")
    private BigDecimal inDiscardPkts;
    
    @Column(name="in_error_pkts")
    private BigDecimal inErrorPkts;
    
    @Column(name="in_pkts_per_sec")
    private BigDecimal inPktsPerSec;
    
    @Column(name="network_usage")
    private BigDecimal networkUsage;
    
    @Column(name="out_bytes_per_sec")
    private BigDecimal outBytesPerSec;
    
    @Column(name="out_discard_pkts")
    private BigDecimal outDiscardPkts;
    
    @Column(name="out_error_pkts")
    private BigDecimal outErrorPkts;
    
    @Column(name="out_pkts_per_sec")
    private BigDecimal outPktsPerSec;
    
    @Column(name="device_id")
    private Integer deviceId;
    
    @Column(name="device_name")
    private String deviceName;

}
