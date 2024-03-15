package com.app.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="id_proof")
public class IdProof extends Audit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private UUID proofId;
    @Column(nullable = false)
    private String proofName;

    @OneToOne(mappedBy = "proof", cascade = CascadeType.ALL,orphanRemoval = true)
    private Person person;

    public IdProof(){}

    public IdProof(LocalDateTime createdAt, LocalDateTime updatedAt, int id, UUID proofId, String proofName, Person person) {
        super(createdAt, updatedAt);
        this.id = id;
        this.proofId = proofId;
        this.proofName = proofName;
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UUID getProofId() {
        return proofId;
    }

    public void setProofId(UUID proofId) {
        this.proofId = proofId;
    }

    public String getProofName() {
        return proofName;
    }

    public void setProofName(String proofName) {
        this.proofName = proofName;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "IdProof{" +
                "id=" + id +
                ", proofId=" + proofId +
                ", proofName='" + proofName + '\'' +
                ", person=" + person +
                '}';
    }
}
