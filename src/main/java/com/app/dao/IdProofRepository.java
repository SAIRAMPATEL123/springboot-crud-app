package com.app.dao;

import com.app.entity.IdProof;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface IdProofRepository extends JpaRepository<IdProof,Integer> {

    List<IdProof> findByProofName(String name);
    Optional<IdProof> findByProofId(UUID uuid);
    void deleteByProofId(UUID uuid);
}
