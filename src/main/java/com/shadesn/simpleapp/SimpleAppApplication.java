package com.shadesn.simpleapp;

import com.shadesn.simpleapp.service.OperationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SimpleAppApplication.class, args).close();
	}

	@Autowired
	OperationService operationService;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Simple application.");

		Integer userInput = 0;
		while(userInput != 9){
			greet();
			userInput = operationService.operationSelect();
		}
	}

	private void greet() throws InterruptedException{
		Thread.sleep(500);
		System.out.println();
		System.out.println();
		System.out.println("Here's a list of available operations, to select one, simply input the number to perform an operation:");
		System.out.println("1: Add a Person");
		System.out.println("2: Edit a Person");
		System.out.println("3: Delete a Person");
		System.out.println("4: Add an Address to a Person");
		System.out.println("5: Edit the address of a Person");
		System.out.println("6: Delete an address");
		System.out.println("7: Get the number of persons");
		System.out.println("8: List of persons");
		System.out.println("9: Exit the application");
		System.out.println();
		System.out.println();
	
	}

}
