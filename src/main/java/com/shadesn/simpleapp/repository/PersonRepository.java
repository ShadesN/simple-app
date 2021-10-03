package com.shadesn.simpleapp.repository;

import com.shadesn.simpleapp.model.Person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
