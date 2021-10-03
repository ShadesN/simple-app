package com.shadesn.simpleapp.service;

import com.shadesn.simpleapp.model.Person;
import com.shadesn.simpleapp.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonDataService extends AbstractDataService<Person> {

    @Autowired
    PersonRepository repository;
    
	@Override
	public CrudRepository<Person, Long> getRepository() {
		return repository;
	}
    
}
