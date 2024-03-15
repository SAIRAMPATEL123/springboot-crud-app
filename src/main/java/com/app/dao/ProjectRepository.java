package com.app.dao;

import com.app.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Integer> {
    @Query(value = "SELECT pr FROM Project pr WHERE pr.person.id IN (SELECT p.id FROM Person p WHERE p.personName = :personName AND p.mobileNumber = :mobileNumber)")
    List<Project> findProjectByPersonNameAndMobileNumber(@Param("personName") String personName, @Param("mobileNumber") long mobileNumber);

}
