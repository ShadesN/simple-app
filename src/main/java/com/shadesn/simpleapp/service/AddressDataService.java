package com.shadesn.simpleapp.service;

import com.shadesn.simpleapp.model.Address;
import com.shadesn.simpleapp.repository.AddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressDataService extends AbstractDataService<Address> {

    @Autowired
    AddressRepository repository;

    @Override
    public CrudRepository<Address, Long> getRepository() {
        return repository;
    }
    
}
