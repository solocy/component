package com.example.securitydemo.common;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@Data
@MappedSuperclass
public class BaseEntity {

    @Column(name = "create_time")
    @CreationTimestamp
    private Instant createTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    private Instant updateTime;
}
