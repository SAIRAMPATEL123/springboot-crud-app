package com.app.service.impl;

import com.app.dao.IdProofRepository;
import com.app.dao.PersonRepository;
import com.app.dto.IdProofDTO;
import com.app.dto.PersonDTO;
import com.app.dto.ProjectDTO;
import com.app.entity.IdProof;
import com.app.entity.Person;
import com.app.entity.Project;
import com.app.exception.BaseExcepiton;
import com.app.exception.IdProofNotFoundException;
import com.app.exception.PersonNotFoundException;
import com.app.service.IdProofService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.UnexpectedException;
import java.util.*;

@Service
public class IdProofServiceImpl implements IdProofService {
    private IdProofRepository idProofRepository;

    @Autowired
    private PersonRepository personRepository;

    public IdProofServiceImpl(IdProofRepository idProofRepository) {
        this.idProofRepository = idProofRepository;
    }

    private static final Logger log = LoggerFactory.getLogger(IdProofServiceImpl.class);

    @Override
    public int saveIdProof(IdProofDTO idProofDTO) throws UnexpectedException, BaseExcepiton, PersonNotFoundException {
        IdProof savedIdProof = null;
        try {
            log.info("Saving Id proof with name {} ", idProofDTO.getProofName());
            if (idProofDTO.getPerson() != null) {
                UUID proofId = UUID.randomUUID();
                IdProof idProof = new IdProof();
                Person person = new Person();
                person.setPersonId(UUID.randomUUID());
                person.setPersonName(idProofDTO.getPerson().getPersonName());
                person.setGender(idProofDTO.getPerson().getGender());
                person.setMobileNumber(idProofDTO.getPerson().getMobileNumber());
                person.setProof(idProof);
                idProof.setPerson(person);
                idProof.setProofId(proofId);
                idProof.setProofName(idProofDTO.getProofName());
                savedIdProof = idProofRepository.save(idProof);
            } else {
                log.error("IdProof without person is invalid");
                throw new PersonNotFoundException("Please provide person details. IdProof without person is Invalid");
            }
        } catch (PersonNotFoundException e) {
            throw e;
        } catch (Exception exception) {
            log.error("Exception occurred while saving IdProof with name {} ", idProofDTO.getProofName());
            throw new BaseExcepiton("Exception occurred while saving the IdProof with name " + idProofDTO.getProofName() + " : " + exception.getMessage());
        }
        return savedIdProof.getId();
    }

    @Override
    public List<IdProofDTO> getIdProofByName(String proofName) throws BaseExcepiton {
        List<IdProofDTO> idProofDTOList = new ArrayList<>();
        try {
            log.info("Fetching the IdProof with name {}", proofName);
            List<IdProof> idProofList = idProofRepository.findByProofName(proofName);
            if (!idProofList.isEmpty()) {
                for (IdProof idProof : idProofList) {
                    IdProofDTO idProofDTO = new IdProofDTO();
                    idProofDTO.setProofId(idProof.getProofId());
                    idProofDTO.setProofName(idProof.getProofName());
                    PersonDTO personDTO = new PersonDTO();
                    personDTO.setPersonId(idProof.getPerson().getPersonId());
                    personDTO.setPersonName(idProof.getPerson().getPersonName());
                    personDTO.setGender(idProof.getPerson().getGender());
                    personDTO.setMobileNumber(idProof.getPerson().getMobileNumber());
                    personDTO.setProject(mapToDTO(idProof.getPerson().getProject()));
                    idProofDTO.setPerson(personDTO);
                    idProofDTOList.add(idProofDTO);
                    idProofDTOList.add(idProofDTO);
                }
            } else {
                log.error("Exception occurred while fetching IdProof with name {} ", proofName);
                throw new IdProofNotFoundException("IdProof not found with name " + proofName);
            }

        } catch (IdProofNotFoundException e) {
            throw new BaseExcepiton("Unable to get IdProof with name " + proofName);
        }
        return idProofDTOList;
    }

    private Set<ProjectDTO> mapToDTO(Set<Project> projects){
        Set<ProjectDTO> projectDTOS=new HashSet<>();
        for(Project project:projects){
            ProjectDTO projectDTO=new ProjectDTO();
            projectDTO.setProjectName(project.getProjectName());
            projectDTO.setProjectDescription(project.getProjectDescription());
            projectDTO.setProjectType(project.getProjectType());
            projectDTOS.add(projectDTO);
        }
        return projectDTOS;
    }

//    private Set<Project> mapToEntity(Set<ProjectDTO> projectDTOs){
//        Set<Project> projects=new HashSet<>();
//        for(ProjectDTO dto:projectDTOs){
//            Project project=new Project();
//            project.setProjectName(dto.getProjectName());
//            project.setProjectDescription(dto.getProjectDescription());
//            project.setProjectType(dto.getProjectType());
//            projects.add(project);
//        }
//        return projects;
//    }

    @Override
    public Set<IdProofDTO> getAllIdProofs() throws BaseExcepiton {
        Set<IdProofDTO> idProofDTOS = new HashSet<>();
        try {
            log.info("Fetching all IdProofs");
            List<IdProof> idProofList = idProofRepository.findAll();
            if (!idProofList.isEmpty()) {
                idProofDTOS = new HashSet<>();
                for (IdProof idProof : idProofList) {
                    IdProofDTO idProofDTO = new IdProofDTO();
                    idProofDTO.setProofId(idProof.getProofId());
                    idProofDTO.setProofName(idProof.getProofName());
                    PersonDTO personDTO = new PersonDTO();
                    personDTO.setPersonId(idProof.getPerson().getPersonId());
                    personDTO.setPersonName(idProof.getPerson().getPersonName());
                    personDTO.setGender(idProof.getPerson().getGender());
                    personDTO.setMobileNumber(idProof.getPerson().getMobileNumber());
                    personDTO.setProject(mapToDTO(idProof.getPerson().getProject()));
                    idProofDTO.setPerson(personDTO);
                    idProofDTOS.add(idProofDTO);
                }
            }
        } catch (Exception exception) {
            log.error("Exception occurred while fetching IdProofs ");
            throw new BaseExcepiton("Unable to get IdProofs");
        }
        return idProofDTOS;
    }

    @Override
    public int updateIdProof(IdProofDTO idProofDTO) throws PersonNotFoundException, BaseExcepiton, IdProofNotFoundException {
        int updatedIdProofDTOId = 0;
        try {
            Optional<IdProof> savedIdProof = idProofRepository.findByProofId(idProofDTO.getProofId());
            if (savedIdProof.isPresent()) {
                if (idProofDTO.getPerson() != null) {
                    IdProof idProof = savedIdProof.get();
                    Person person =personRepository.findByPersonId(idProof.getPerson().getPersonId());
                    idProof.setProofName(idProofDTO.getProofName());
                    idProof.setProofId(idProofDTO.getProofId());
                    person.setGender(idProofDTO.getPerson().getGender());
                    person.setPersonName(idProofDTO.getPerson().getPersonName());
                    person.setMobileNumber(idProofDTO.getPerson().getMobileNumber());
                    person.setProof (idProof);
                  //  person.setProject(mapToEntity(idProofDTO.getPerson().getProject()));
                    idProof.setPerson(person);
                    log.info("Before saving");
                    updatedIdProofDTOId = idProofRepository.save(idProof).getId();
                } else {
                    log.error("IdProof without person is invalid");
                    throw new PersonNotFoundException("Please provide person details. IdProof without person is Invalid");
                }
            } else {
                log.error(" No IdProof found with proofId {} ", idProofDTO.getProofId());
                throw new IdProofNotFoundException("No IdProof found with proofId " + idProofDTO.getProofId());
            }
        } catch (PersonNotFoundException personNotFoundException) {
            throw personNotFoundException;
        } catch (IdProofNotFoundException idProofNotFoundException) {
            throw idProofNotFoundException;
        } catch (Exception e) {
            log.error("Unable to update idProof \n ERROR ::: {}",e.getMessage());
            throw new BaseExcepiton("Unable to update IdProof with name " + idProofDTO.getProofName());

        }
        return updatedIdProofDTOId;
    }

    @Override
    public void deleteIdProofByProofId(UUID proofId) throws IdProofNotFoundException, BaseExcepiton {
        try {
            Optional<IdProof> idProof = idProofRepository.findByProofId(proofId);
            if (idProof.isPresent()) {

                idProofRepository.deleteByProofId(proofId);
            } else {
                log.info("Proof with proofId not found {} ", proofId);
                throw new IdProofNotFoundException("IdProof not found ");
            }
        } catch (IdProofNotFoundException idProofNotFoundException) {
            log.error("Unable to delete IdProof with ProofId {}", proofId);
            throw idProofNotFoundException;
        } catch (Exception e) {
            log.error("Unable to delete IdProof with ProofId {}", proofId);
            throw new BaseExcepiton("Unable to delete IdProof " + proofId +e.getMessage());
        }
    }
}
