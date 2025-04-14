package com.azim.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {

    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updateDate;

    @PrePersist
    protected void onCreate(){
        this.createdDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updateDate = LocalDateTime.now();
    }

    public  LocalDateTime getCreatedDate(){ return createdDate;}
    public  LocalDateTime getUpdateDate() { return  updateDate;}
}
