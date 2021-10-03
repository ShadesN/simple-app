##### by Maurizio Coletta
# Simple Application

**Ask:**

* _Create a simple (console) application with database access_

**Input:**

* _Based on an input from the user provide the following functionality:_

**Output:**

* _Provide a console application to do the following:_
* 1. _Add Person (id, firstName, lastName)_
* 2. _Edit Person (firstName, lastName)_
* 3. _Delete Person (id)_
* 4. _Add Address to person [multiple required] (id, street, city, state, postalCode)_
* 5. _Edit Address (street, city, state, postalCode)_
* 6. _Delete Address (id)_
* 7. _Count Number of Persons_
* 8. _List Persons_

**Software Stack / Libraries**

* Java 1.8
* Spring-Boot v2.5.5
* [Lombok](https://github.com/projectlombok/lombok) (for boilerplate model code)
* [H2 Database](https://github.com/h2database/h2database)
* [Text IO](https://github.com/beryx/text-io) (For handling console input)

### Local Setup and Launching the application
- [Have Java 8+ installed](https://www.java.com/)
- Clone the repository (git clone https://github.com/ShadesN/simple-app.git) or download and extract the zip output from GitHub itself
- Open a cmd/Powershell prompt and run  `.\mwnw spring-boot:run`  or `mwnw spring-boot:run` from a unix shell 
    
**Assumptions:**

As the storing of addresses in a database is strictly related to the specific needs of the application...some assumptions have to be made as there are different considerations to be made for addresses needed for postal delivery or simply to store residence information for a person.

Taking into consideration the requirements I have deemed the following address fields as non nullable:
- Street
- City
- State (Administrative Area for State / Province / County)
- Postal code

And also the addition of a "Country" field.
    
**Drawbacks:**

Assuming the possibility of storing addresses from all over the world, the aforementioned structure may pose some problems as it forces users to insert data which they may not be able to use:
- UK Addresses do not require/have something to be inserted in the "State" field. The closest thing is the "County" such information is, however, not needed for postal delivery. 
- Country, should be validated against proper ISO codes...
- Some (?) Addresses in Ireland do not have postal codes

(Considering all these issues...oftentimes the best way to store an address is simply an "Address" line, as text data.)

---