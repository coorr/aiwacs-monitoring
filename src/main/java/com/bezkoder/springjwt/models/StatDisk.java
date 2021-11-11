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
@Table(name="stat_disk")
public class StatDisk {
    
    @Id
    @SequenceGenerator(name="stat_disk_seq", sequenceName="stat_disk_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="stat_disk_seq")
    @Column(name="id", unique=true, nullable=false)
    private Integer id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="generate_time")
    private Date generateTime;
    
    @Column(name="io_queue_depth")
    private BigDecimal ioQueueDepth;
    
    @Column(name="io_time_percentage")
    private BigDecimal ioTimePercentage;
    
    @Column(name="io_total_bps")
    private BigDecimal ioTotalBps;
    
    @Column(name="io_total_cnt")
    private BigDecimal ioTotalCnt;
    
    @Column(name="partition_label")
    private String partitionLabel;
    
    @Column(name="total_bytes")
    private BigDecimal totalBytes;
    
    @Column(name="used_bytes")
    private BigDecimal usedBytes;
    
    @Column(name="used_percentage")
    private BigDecimal usedPercentage;

    @Column(name="device_id")
    private Integer deviceId;
    
    @Column(name="device_name")
    private String deviceName;


}
