package com.shadesn.simpleapp;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import com.shadesn.simpleapp.client.ConsoleClient;
import com.shadesn.simpleapp.service.AddressDataService;
import com.shadesn.simpleapp.service.PersonDataService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// @SpringBootTest
// These annotations are needed otherwise the application would wait indefinitely for user input...
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SimpleAppApplication.class, initializers = ConfigDataApplicationContextInitializer.class)
class SimpleAppApplicationTests {
	
	@Autowired
	PersonDataService personService;

	@Autowired
	AddressDataService addressService;

	@Autowired
	ConsoleClient client;

	@Test
	public void usersLoadedInDatabase(){
		assertTrue(personService.count() > 0);
	}

	@Test
	public void addressesLoadedInDatabase(){
		assertTrue(addressService.count() > 0);
	}

	@Test
	public void getAllUsers(){
		assertTrue(personService.findAll().size() > 0);
	}

	@Test
	public void getAllAddresses(){
		assertTrue(addressService.findAll().size() > 0);
	}

	@Test
	public void test_printPersonAddresses(){
		client.printPersonAddresses(personService.findById(1L).get());
	}

	@Test
	public void test_ConsoleClient_printPersonAddresses_wrongInput() throws IllegalArgumentException{
		assertThrows(IllegalArgumentException.class, () -> {
			client.printPersonAddresses(personService.findById(-1L).get());
		});
	}
	
	@Test
	public void test_ConsoleClient_printPersonAddresses_nonExistentUser() throws NoSuchElementException{
		assertThrows(NoSuchElementException.class, () -> {
			client.printPersonAddresses(personService.findById(200L).get());
		});	
	}

}
