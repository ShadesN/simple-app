package com.shadesn.simpleapp.service;

import com.shadesn.simpleapp.repository.PersonRepository;

import org.beryx.textio.TextIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;
    
	public void listAll() {
		System.out.println("Here's the list of all persons:");
		repository.findAll().forEach(d -> System.out.println(d));
	}
    
}
