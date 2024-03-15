package com.app.dto;

import java.util.UUID;

public class IdProofDTO {

    private UUID proofId;
    private String proofName;
    private PersonDTO person;

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

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }
}
