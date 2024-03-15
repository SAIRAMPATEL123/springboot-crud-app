package com.app.entity;

import com.app.util.Gender;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"personName","mobileNumber"}))
public class Person extends Audit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private UUID personId;
    private String personName;
    private Gender gender;

    private long mobileNumber;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_proof")
    @JsonBackReference
    private IdProof proof;
    @OneToMany(mappedBy = "person",orphanRemoval = true)
    private Set<Project> project;

    public Person(){
    }

    public Person(LocalDateTime createdAt, LocalDateTime updatedAt, int id, UUID personId, String personName, Gender gender, Long mobileNumber, IdProof proof, Set<Project> project) {
        super(createdAt, updatedAt);
        this.id = id;
        this.personId = personId;
        this.personName = personName;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.proof = proof;
        this.project = project;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UUID getPersonId() {
        return personId;
    }

    public void setPersonId(UUID personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public IdProof getProof() {
        return proof;
    }

    public void setProof(IdProof proof) {
        this.proof = proof;
    }

    public Set<Project> getProject() {
        return project;
    }

    public void setProject(Set<Project> project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", personId=" + personId +
                ", personName='" + personName + '\'' +
                ", gender=" + gender +
                ", mobileNumber=" + mobileNumber +
                ", proof=" + proof +
                ", project=" + project +
                '}';
    }
}
