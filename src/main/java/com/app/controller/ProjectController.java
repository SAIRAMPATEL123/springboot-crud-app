package com.app.controller;

import com.app.dto.ProjectDTO;
import com.app.exception.BaseExcepiton;
import com.app.exception.PersonNotFoundException;
import com.app.exception.ProjectNotFoundException;
import com.app.service.impl.ProjectServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private static Logger logger = LoggerFactory.getLogger(ProjectController.class);
    private ProjectServiceImpl projectService;

    public ProjectController(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }


    @PostMapping("/")
    public ResponseEntity<Integer> saveProject(@RequestBody() ProjectDTO projectDTO,@RequestParam(name = "personName") String personName, @RequestParam(name = "mobileNumber") long mobileNumber) throws PersonNotFoundException, BaseExcepiton {
        logger.info("Request received to save project {} ", projectDTO.getProjectName());
        return new ResponseEntity<>(projectService.saveProject(projectDTO,personName,mobileNumber), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProjectDTO>> getAllProjects() throws BaseExcepiton {
        logger.info("Request received to get all projects ");
        return new ResponseEntity<>(projectService.getAllProjects(), HttpStatus.OK);
    }

    @GetMapping("/person")
    public ResponseEntity<List<ProjectDTO>> getProjectByPersonNameAndMobileNumber(@RequestParam(name = "personName") String personName, @RequestParam(name = "mobileNumber") long mobileNumber) throws BaseExcepiton {
        logger.info("Request received to get Project made by the person {} ",personName);
        return new ResponseEntity<>(projectService.getProjectByPersonNameAndMobileNum(personName, mobileNumber), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateProjectByPerson(@PathVariable("id") int projectId, @RequestBody ProjectDTO projectDto) throws ProjectNotFoundException {
        logger.info("Request received to update the project");
        return new ResponseEntity<>(projectService.updateProject(projectId, projectDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/")
    public ResponseEntity<Void> deleteByPersonNameAndMobileNumber(@RequestParam(name = "personName") String personName, @RequestParam(name = "mobileNumber") long mobileNumber) throws BaseExcepiton {
        logger.info("Request received to delete all projects of person {} ", personName);
        projectService.deleteProjectByPersonNameAndMobileNumber(personName, mobileNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
