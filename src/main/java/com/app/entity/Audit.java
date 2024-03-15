package com.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@JsonIgnoreProperties(value = {"createdAt","updatedAt"})
public class Audit implements Serializable {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at",
    nullable = false,
    updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="updated_at",
    nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Audit() {
    }

    public Audit(LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
