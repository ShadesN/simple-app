package com.shadesn.simpleapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface IDataService<T> {

    List<T> findAll();
    Optional<T> findById(long id);
    void deleteById(Long id);
    void save(T entity);
    long count();
    
    CrudRepository<T, Long> getRepository();
    
    T findItem(Long id, Class<?> itemType); 
    void deleteItem(T item);
}
