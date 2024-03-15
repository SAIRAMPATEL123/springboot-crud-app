package com.app.dto;

import com.app.util.ProjectType;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ProjectDTO {

    private String projectName;
    private String projectDescription;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    private ProjectType projectType;

    @JsonIgnore
    private PersonDTO person;

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }
}
