DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS person;

CREATE TABLE person (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL
);

CREATE TABLE address (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  country VARCHAR(2) NOT NULL, -- ISO code ie: US, IT, ES, DE...
  street VARCHAR(250) NOT NULL,
  city VARCHAR(250) NOT NULL, -- Locality, non ISO code
  administrative_area VARCHAR(250) NULL, -- State / Province / County, this field may not be  
  postal_code VARCHAR(250) NULL,  -- Depending on the region, the postal code may not be used locally
  person_id INT NOT NULL,
  FOREIGN KEY (person_id) REFERENCES person(id) ON DELETE CASCADE
);

INSERT INTO person (first_name, last_name) VALUES
  ('John', 'Doe'),
  ('Jane', 'Doe'),
  ('Georgia', 'Stephens'),
  ('Joshua', 'Connolly'),
  ('Stefano', 'Accorsi'),
  ('Rodrigo', 'De Lucia'),
  ('David', 'Blau'),
  ('Michael', 'Caine');


INSERT INTO address (country, street, city, administrative_area, postal_code, person_id) VALUES
  ('US','2622 Delaware Avenue', 'Brisbane', 'California', '94005', 1),
  ('US','2622 Delaware Avenue', 'Brisbane', 'California', '94005', 2),
  ('US','409 Harper Street', 'Bowling Green', 'Kentucky', '42101', 3),
  ('UK','45 Pendwyallt Road', 'Collwall Green', 'Herefordshire', 'WR13 2EH', 4),
  ('IT','Via Longhena 162', 'Morlupo', 'Roma', '00067', 5),
  ('ES','Rosa de los Vientos 76', 'Terrinches', 'Ciudad Real', '13341', 6),
  ('DE','Leipziger Strasse 79', 'Gießen', 'Hessen', '35398', 7);
  