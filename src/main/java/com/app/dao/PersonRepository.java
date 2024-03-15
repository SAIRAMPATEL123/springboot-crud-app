package com.app.dao;

import com.app.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {

    Optional<Person> findByPersonNameAndMobileNumber(String personName,long mobileNumber);

    Person findByPersonId(UUID personId);
}
