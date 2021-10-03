package com.shadesn.simpleapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shadesn.simpleapp.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
    
}
