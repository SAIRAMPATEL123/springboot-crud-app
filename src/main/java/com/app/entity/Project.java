package com.app.entity;

import com.app.util.ProjectType;
import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints =@UniqueConstraint(columnNames = {"projectName","projectType"}) )
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String projectName;
    @Column
    private String projectDescription;
    private ProjectType projectType;
    @ManyToOne
    @JoinColumn(name="person_id")
    private Person person;
    public Project(){

    }

    public Project(int id, String projectName, String projectDescription, ProjectType projectType, Person person) {
        this.id = id;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.projectType = projectType;
        this.person = person;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", person=" + person +
                ", ProjectType= "+projectType+
                '}';
    }
}
