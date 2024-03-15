package com.app.dto;

import com.app.util.Gender;

import java.util.Set;
import java.util.UUID;

public class PersonDTO {

    public UUID personId;
    public String personName;
    public Gender gender;
    public long mobileNumber;
    public Set<ProjectDTO> project;

    public String getPersonName() {
        return personName;
    }

    public UUID getPersonId() {
        return personId;
    }

    public void setPersonId(UUID personId) {
        this.personId = personId;
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

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Set<ProjectDTO> getProject() {
        return project;
    }


    public void setProject(Set<ProjectDTO> project) {
        this.project = project;
    }
}
