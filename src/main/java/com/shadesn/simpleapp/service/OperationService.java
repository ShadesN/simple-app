package com.shadesn.simpleapp.service;

import java.util.Optional;

import com.shadesn.simpleapp.model.Address;
import com.shadesn.simpleapp.model.Person;
import com.shadesn.simpleapp.repository.AddressRepository;
import com.shadesn.simpleapp.repository.PersonRepository;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class OperationService  {
    
	@Autowired
	PersonRepository personRepository;

	@Autowired
	AddressRepository addressRepository;

    public Integer operationSelect() {

		TextIO in = TextIoFactory.getTextIO();
        Integer userInput = in.newIntInputReader().withMinVal(1).withMaxVal(9).read("Operation");
		System.out.println();
		switch (userInput) {
			case 1:
				addPerson(in);
				break;
			case 2:
				editPerson(in);
				break;
			case 3:
				deleteItem(in, Person.class, personRepository); // delete Person
				break;
			case 4:
				addAddressToPerson(in);
				break;
			case 5:
				editAddress(in);
				break;
			case 6:
				deleteItem(in, Address.class, addressRepository); // delete Address
				break;
			case 7:
				countPersons();
				break;
			case 8:
				listPersons(in);
				break;
			default:
				break;
		}

        return userInput;
	}

    private void editPerson(TextIO in) {

		Person person = findItem(in, Person.class, personRepository);
		if(person!=null){
			fillInPersonDetail(in, person);
			personRepository.save(person);
		}
	}

	private <T> T findItem(TextIO in, Class<?> itemType, CrudRepository<T, Long> repository){
		while(true){
			System.out.println("Please insert the id of the "+ itemType.getSimpleName() +" or type \"0\" to quit out of the command");
			try{
				Long id = in.newLongInputReader().read("Id");
				if(id == 0L)
					return null;
				Optional<T> optional = repository.findById(id);
				if(optional.isPresent()){
					System.out.println(itemType.getSimpleName()+" found:");
					System.out.println(optional.get());
					return optional.get();
				}
			} catch(IllegalArgumentException ex){
				System.out.println("Wrong input");
			}
		}
	}

	private <T> void deleteItem(TextIO in, Class<?> itemType, CrudRepository<T, Long> repository){
		while(true){
			System.out.println("Please insert the id of the "+ itemType.getSimpleName() +" or type \"0\" to quit out of the command");
			try{
				Long id = in.newLongInputReader().read("Id");
				if(id == 0L)
					return;
				Optional<T> optional = repository.findById(id);
				if(optional.isPresent()){
					System.out.println(itemType.getSimpleName()+" found:");
					System.out.println(optional.get());
					System.out.println("Are you sure you wanna delete this "+itemType.getSimpleName()+"?");
					if(in.newBooleanInputReader().withFalseInput("n").withTrueInput("y").read("y/n")){
						repository.deleteById(id);
						System.out.println(itemType.getSimpleName()+" deleted.");
						return;
					}
				}else
					System.out.println("No "+itemType.getSimpleName() + " found...");
			} catch(IllegalArgumentException ex){
				System.out.println("Wrong input");
			}
		}
	}

	private void addAddressToPerson(TextIO in) {
		Person person = findItem(in, Person.class, personRepository);
		if(person != null){
			System.out.println("Here's all the adresses associated to this person: ");
			showPersonAddresses(person);
			while(in.newBooleanInputReader().withFalseInput("n").withTrueInput("y").read("Do you want to add a new Address to this person? ")){
				Address address = new Address();
				address.setPersonId(person);
				fillInAddressDetails(in, address);
				addressRepository.save(address);
			}
		}
	}

	private void showPersonAddresses(Person person) {
		person.getAddresses().forEach(d -> System.out.println(d));
	}

	private void editAddress(TextIO in) {
		Address address = findItem(in, Address.class, addressRepository);
		if(address!=null){
			if(in.newBooleanInputReader().withFalseInput("n").withTrueInput("y").read("Do you want to edit this address? ")){
				fillInAddressDetails(in, address);
				addressRepository.save(address);
			}
		}
	}

	private void listPersons(TextIO in) {
		System.out.println("Here's the list of all persons:");
		personRepository.findAll().forEach(d -> System.out.println(d));
	}

    public void addPerson(TextIO in){
        Person person = new Person();
		fillInPersonDetail(in, person);
        personRepository.save(person);
    }
	
	public void countPersons(){
		System.out.println(String.format("Number of persons: %d ", personRepository.count()));
	}

	private void fillInPersonDetail(TextIO in, Person person) {
		System.out.println("Please fill in the details (FirstNname,Last name)");
		String firstName = in.newStringInputReader().read("First Name");
		String lastName = in.newStringInputReader().read("Last Name");
		person.setLastName(lastName);
		person.setFirstName(firstName);
	}

	private void fillInAddressDetails(TextIO in, Address address) {
		System.out.println("Please fill in the details (Country, Street, City, Administrative Area (State / Province / County), Postal code)");
		String country = in.newStringInputReader().read("Country");
		String street = in.newStringInputReader().read("Street");
		String city = in.newStringInputReader().read("City");
		String administrativeArea = in.newStringInputReader().read("Administrative Area (State / Province / County)");
		String postalCode = in.newStringInputReader().read("Postal Code");

		address.setCountry(country);
		address.setStreet(street);
		address.setCity(city);
		address.setAdministrativeArea(administrativeArea);
		address.setPostalCode(postalCode);
	}

}
