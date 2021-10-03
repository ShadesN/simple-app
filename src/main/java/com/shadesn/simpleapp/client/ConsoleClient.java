package com.shadesn.simpleapp.client;

import com.shadesn.simpleapp.model.Address;
import com.shadesn.simpleapp.model.Person;
import com.shadesn.simpleapp.service.AddressDataService;
import com.shadesn.simpleapp.service.IDataService;
import com.shadesn.simpleapp.service.PersonDataService;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsoleClient  {
    
	private static final int STR_FIELDS_MAX_LENGTH = 250;

	@Autowired
	PersonDataService personService;

	@Autowired
	AddressDataService addressService;

    public Integer operationSelect() {

		TextIO in = TextIoFactory.getTextIO();
        Integer userInput = in.newIntInputReader().withMinVal(0).withMaxVal(11).read("Operation");
		System.out.println();
		switch (userInput) {
			case 1:
				addPerson(in);
				break;
			case 2:
				editPerson(in);
				break;
			case 3:
				deleteItem(in, Person.class, personService); // delete Person
				break;
			case 4:
				addAddressToPerson(in);
				break;
			case 5:
				editAddress(in);
				break;
			case 6:
				deleteItem(in, Address.class, addressService); // delete Address
				break;
			case 7:
				countPersons();
				break;
			case 8:
				listPersons(false);
				break;
			case 9:
				listPersons(true);
				break;
			case 10:
				listAddresses(false);
				break;
			case 11:
				listAddresses(true);
				break;
			default:
				break;
		}

        return userInput;
	}

	public void printPersonAddresses(Person person) {
		person.getAddresses().forEach(d -> System.out.println(d));
		System.out.println();
	}

    private void editPerson(TextIO in) {
		Person person = findItem(in, Person.class, personService);
		if(person!=null){
			fillInPersonDetail(in, person);
			personService.save(person);
		}
	}

	private <T> T findItem(TextIO in, Class<?> itemType, IDataService<T> dataService){
		while(true){
			System.out.println("Please insert the id of the "+ itemType.getSimpleName() +" or type \"0\" to quit out of the command");
			try{
				Long id = in.newLongInputReader().read("Id");
				if(id == 0L)
					return null;
				return dataService.findItem(id, itemType);
			} catch(IllegalArgumentException ex){
				System.out.println("Wrong input");
			}
		}
	}

	private <T> void deleteItem(TextIO in, Class<?> itemType, IDataService<T> dataService){
		T item = findItem(in, itemType, dataService);
		if(item != null){
			if(in.newBooleanInputReader().withFalseInput("n").withTrueInput("y").read("Are you sure you wanna delete this "+itemType.getSimpleName()+"?")){
				dataService.deleteItem(item);
				System.out.println(itemType.getSimpleName()+" deleted.");
				return;
			}
		}
	}

	private void addAddressToPerson(TextIO in) {
		Person person = findItem(in, Person.class, personService);
		if(person != null){
			System.out.println("Here's all the adresses associated to this person: ");
			printPersonAddresses(person);
			while(in.newBooleanInputReader().withFalseInput("n").withTrueInput("y").read("Do you want to add a new Address to this person? ")){
				Address address = new Address();
				address.setPersonId(person);
				fillInAddressDetails(in, address);
				addressService.save(address);
				System.out.println("Address saved.");
			}
		}
	}
	
	private void editAddress(TextIO in) {
		Address address = findItem(in, Address.class, addressService);
		if(address!=null){
			if(in.newBooleanInputReader().withFalseInput("n").withTrueInput("y").read("Do you want to edit this address? ")){
				fillInAddressDetails(in, address);
				addressService.save(address);
			}
		}
	}

	private void listPersons(boolean showAddresses) {
		if(showAddresses)
			System.out.println("Here's the list of all persons and their addresses:");
		else
			System.out.println("Here's the list of all persons:");

		for (Person person : personService.findAll()) {
			System.out.println(person);
			if(showAddresses)
				printPersonAddresses(person);
		}
	}

	private void listAddresses(boolean showPerson) {
		if(showPerson)
			System.out.println("Here's the list of all the addresses and the person thei are associated to:");
		else
			System.out.println("Here's the list of all the addresses:");

		for (Address address : addressService.findAll()) {
			System.out.println(address);
			if(showPerson){
				System.out.println(address.getPersonId());
				System.out.println();
			}
		}
		
	}

    private void addPerson(TextIO in){
        Person person = new Person();
		fillInPersonDetail(in, person);
		personService.save(person);
    }
	
	public void countPersons(){
		System.out.println(String.format("Number of persons: %d ", personService.count()));
	}

	public void fillInPersonDetail(TextIO in, Person person) {
		System.out.println("Please fill in the details (First Name, Last name)");
		String firstName = in.newStringInputReader().withMaxLength(STR_FIELDS_MAX_LENGTH).read("First Name");
		String lastName = in.newStringInputReader().withMaxLength(STR_FIELDS_MAX_LENGTH).read("Last Name");
		person.setLastName(lastName);
		person.setFirstName(firstName);
	}

	private void fillInAddressDetails(TextIO in, Address address) {
		System.out.println("Please fill in the details (Country, Street, City, Administrative Area (State / Province / County), Postal code)");
		String country = in.newStringInputReader().withMaxLength(2).read("Country"); // This should be properly validated against ISO codes...
		String street = in.newStringInputReader().withMaxLength(STR_FIELDS_MAX_LENGTH).read("Street");
		String city = in.newStringInputReader().withMaxLength(STR_FIELDS_MAX_LENGTH).read("City");
		String administrativeArea = in.newStringInputReader().withMaxLength(STR_FIELDS_MAX_LENGTH).read("Administrative Area (State / Province / County)");
		String postalCode = in.newStringInputReader().withMaxLength(STR_FIELDS_MAX_LENGTH).read("Postal Code");

		address.setCountry(country);
		address.setStreet(street);
		address.setCity(city);
		address.setAdministrativeArea(administrativeArea);
		address.setPostalCode(postalCode);
	}

}
