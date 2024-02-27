package com.rsvp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "is_active")
    protected boolean active;

    @Column(name = "created_by", length = 50)
    protected String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "created_date")
    protected LocalDateTime createdDate;

    @Column(name = "updated_by", length = 50)
    protected String updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "updated_date")
    protected LocalDateTime updatedDate;

    @PrePersist
    public void prePersist() {
        this.active = true;
        this.setCreatedBy("System");
        this.setCreatedDate(LocalDateTime.now());
        this.setUpdatedBy("System");
        this.setUpdatedDate(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate() {
        this.setUpdatedBy("System");
        this.setUpdatedDate(LocalDateTime.now());
    }
}
