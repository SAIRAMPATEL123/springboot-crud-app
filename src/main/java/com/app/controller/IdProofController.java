package com.app.controller;

import com.app.dto.IdProofDTO;
import com.app.exception.BaseExcepiton;
import com.app.exception.IdProofNotFoundException;
import com.app.exception.PersonNotFoundException;
import com.app.service.IdProofService;
import com.app.service.impl.IdProofServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.UnexpectedException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/idProof")
public class IdProofController {

    private IdProofService idProofService;
    public IdProofController(IdProofServiceImpl idProofService){
        this.idProofService=idProofService;
    }
    Logger logger= LoggerFactory.getLogger(IdProofController.class);

    @GetMapping("/")
    public ResponseEntity<Set<IdProofDTO>> getAllIdProofs() throws BaseExcepiton {
        logger.info("Request received to get all IdProofs");
        Set<IdProofDTO> idProofDTOSet=idProofService.getAllIdProofs();
        return new ResponseEntity<>(idProofDTOSet, HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<Integer> saveIDProof(@RequestBody() IdProofDTO idProofDTO) throws UnexpectedException, BaseExcepiton, PersonNotFoundException {
        logger.info("Request received to save Idproofs");
        int idProofId =idProofService.saveIdProof(idProofDTO);
        return new ResponseEntity<>(idProofId,HttpStatus.CREATED);
    }
    @PutMapping("/")
    public ResponseEntity<Integer> updateIdProof(@RequestBody() IdProofDTO idProofDTO) throws PersonNotFoundException, BaseExcepiton, IdProofNotFoundException {
        logger.info("Request received to update the IdProof");
        int idProofId=idProofService.updateIdProof(idProofDTO);
        return new ResponseEntity<>(idProofId,HttpStatus.OK);
    }
    @GetMapping("/proofName/{name}")
    public ResponseEntity<List<IdProofDTO>> getIdProofByName(@PathVariable("name") String name) throws BaseExcepiton {
        logger.info("Request received to get IdProof with name {} ",name);
        List<IdProofDTO> idProofDTO= idProofService.getIdProofByName(name);
        return new ResponseEntity<>(idProofDTO,HttpStatus.OK);
    }
    @DeleteMapping("/{proofId}")
    public ResponseEntity<Void> deleteByProofId(@PathVariable("proofId")UUID proofId) throws IdProofNotFoundException, BaseExcepiton {
        logger.info("Request received to delete the IdProof");
        idProofService.deleteIdProofByProofId(proofId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
