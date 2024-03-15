package com.app.service;

import com.app.dto.IdProofDTO;
import com.app.exception.BaseExcepiton;
import com.app.exception.IdProofNotFoundException;
import com.app.exception.PersonNotFoundException;

import java.rmi.UnexpectedException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface IdProofService {

    int saveIdProof(IdProofDTO idProofDTO) throws UnexpectedException, BaseExcepiton, PersonNotFoundException;
    List<IdProofDTO> getIdProofByName(String proofName) throws BaseExcepiton;
    Set<IdProofDTO> getAllIdProofs() throws BaseExcepiton;
    int updateIdProof(IdProofDTO idProofDTO) throws PersonNotFoundException, BaseExcepiton, IdProofNotFoundException;
    void deleteIdProofByProofId(UUID proofId) throws IdProofNotFoundException, BaseExcepiton;

}
