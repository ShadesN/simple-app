package com.shadesn.simpleapp;

import com.shadesn.simpleapp.client.ConsoleClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleAppApplication implements CommandLineRunner {

	private static final int QUIT_COMMAND = 0;

	public static void main(String[] args) {
		SpringApplication.run(SimpleAppApplication.class, args).close();
	}

	@Autowired
	ConsoleClient operationService;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Simple application.");

		Integer userInput = -1;
		while(userInput != QUIT_COMMAND){
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
		System.out.println("5: Edit an Address");
		System.out.println("6: Delete an address");
		System.out.println("7: Get the number of persons");
		System.out.println("8: List of persons");
		System.out.println("9: List of persons (with addresses)");
		System.out.println("10: List of Addresses");
		System.out.println("11: List of Addresses with person");
		System.out.println("0: Exit the application");
		System.out.println();
		System.out.println();
	
	}

}
