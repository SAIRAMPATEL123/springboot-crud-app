package com.app.service.impl;

import com.app.dao.PersonRepository;
import com.app.dao.ProjectRepository;
import com.app.dto.PersonDTO;
import com.app.dto.ProjectDTO;
import com.app.entity.Person;
import com.app.entity.Project;
import com.app.exception.BaseExcepiton;
import com.app.exception.PersonNotFoundException;
import com.app.exception.ProjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl {

    private ProjectRepository projectRepository;
    private PersonRepository personRepository;

    private static Logger log= LoggerFactory.getLogger(ProjectServiceImpl.class);

    public ProjectServiceImpl(ProjectRepository projectRepository,PersonRepository personRepository){
        this.projectRepository=projectRepository;
        this.personRepository=personRepository;
    }

    public int saveProject(ProjectDTO projectDTO,String personName,long mobileNumber) throws PersonNotFoundException, BaseExcepiton {
        Project project = new Project();
        try {
            log.info("Checking if the person is available to make project {} ",projectDTO.getProjectName());
            Optional<Person> optionalPerson=personRepository.findByPersonNameAndMobileNumber(personName,mobileNumber);
            if(optionalPerson.isPresent()){
                log.info("Person is available to make project");
                project.setProjectName(projectDTO.getProjectName());
                project.setProjectType(projectDTO.getProjectType());
                project.setProjectDescription(projectDTO.getProjectDescription());
                log.info("Saving the project {} made by the person {} ",projectDTO.getProjectName(),personName);
                project.setProjectDescription(project.getProjectDescription());
                project.setPerson(optionalPerson.get());
                project=projectRepository.save(project);
                }
            else {
                log.warn(" Person is not available to make project");
                throw new PersonNotFoundException("Person is not found. Please register the person prior making project");
            }
        }
        catch (PersonNotFoundException personNotFoundException){
                log.error("Exception occurred while saving the project");
                throw personNotFoundException;
        }
        catch (Exception exception){
            log.error("Exception occurred while saving the project");
            throw new BaseExcepiton("Unable to save the project "+projectDTO.getProjectName());
        }
        return project.getId();
    }

    public List<ProjectDTO> getAllProjects() throws BaseExcepiton {
        try {
            List<Project> projects=projectRepository.findAll();
            List<ProjectDTO> projectDTOS=new ArrayList<>();
            for(Project project:projects){
                ProjectDTO projectDTO=new ProjectDTO();
                projectDTO.setProjectName(project.getProjectName());
                projectDTO.setProjectDescription(project.getProjectDescription());
                projectDTO.setProjectType(project.getProjectType());
                PersonDTO personDTO=new PersonDTO();
                personDTO.setPersonId(project.getPerson().getPersonId());
                personDTO.setGender(project.getPerson().getGender());
                personDTO.setPersonName(project.getPerson().getPersonName());
                personDTO.setMobileNumber(project.getPerson().getMobileNumber());
                projectDTO.setPerson(personDTO);
                projectDTOS.add(projectDTO);
            }
            return projectDTOS;
        }
        catch (Exception exception){
            log.error("Exception Occurred while fetching all projects ");
            throw new BaseExcepiton("Unable to fetch projects");
        }
    }


    public List<ProjectDTO> getProjectByPersonNameAndMobileNum(String personName,long mobileNumber) throws BaseExcepiton {
        try {
            List<Project> projects = projectRepository.findProjectByPersonNameAndMobileNumber(personName, mobileNumber);
            if (!projects.isEmpty()) {
                List<ProjectDTO> projectDTOS = new ArrayList<>();
                for (Project project : projects) {
                    ProjectDTO projectDTO = new ProjectDTO();
                    projectDTO.setProjectType(project.getProjectType());
                    projectDTO.setProjectDescription(project.getProjectDescription());
                    projectDTO.setProjectName(project.getProjectName());
                    projectDTOS.add(projectDTO);
                }
                return projectDTOS;
            }
            else{
                log.warn(personName+" haven't made any project.");
                throw new BaseExcepiton("No projects found");
            }
        }

        catch (Exception e){
            throw new BaseExcepiton(personName+" haven't made any project.");
        }

    }

    public int updateProject(int id,ProjectDTO projectDTO) throws ProjectNotFoundException {
        try {
            Optional<Project> optionalProject=projectRepository.findById(id);
            if(optionalProject.isPresent()){
              Project project=optionalProject.get();
              project.setProjectDescription(projectDTO.getProjectDescription());
              project.setProjectType(projectDTO.getProjectType());
              project.setProjectName(projectDTO.getProjectName());
              return projectRepository.save(project).getId();
            }
            else {
                log.warn("No project found with id "+id);
                throw new ProjectNotFoundException("No project found with id "+id);
            }
        }
        catch (ProjectNotFoundException projectNotFoundException){
            throw projectNotFoundException;
        }
    }

    public void deleteProjectByPersonNameAndMobileNumber(String personName,long mobileNumber) throws BaseExcepiton {
        try {
            List<Project> projects = projectRepository.findProjectByPersonNameAndMobileNumber(personName, mobileNumber);
            log.info("Deleting {} projects made by the person {} ",projects.size(),personName);
            if (!projects.isEmpty()) {
                for(Project project:projects) {
                    projectRepository.delete(project);
                }
            }
            else{
                log.warn(personName+" haven't made any project.");
                throw new BaseExcepiton("No projects found for deleting");
            }
        }
           catch (BaseExcepiton e) {
            throw e;
        }
    }
}
