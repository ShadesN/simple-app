package com.shadesn.simpleapp.service;

import java.util.List;
import java.util.Optional;

public abstract class AbstractDataService<T> implements IDataService<T> {

    @Override
    public final T findItem(Long id, Class<?> itemType){
        if(id <= 0)
            throw new IllegalArgumentException();
        Optional<T> optional = getRepository().findById(id);
        if(optional.isPresent()){
            System.out.println(itemType.getSimpleName()+" found:");
            System.out.println(optional.get());
            return optional.get();
        }
        return null;
    }

    @Override
	public final void deleteById(Long id) {
        if(id <= 0)
            throw new IllegalArgumentException();
		getRepository().deleteById(id);		
	}

    @Override
	public final void deleteItem(T item) {
        if(item == null)
            throw new IllegalArgumentException();
		getRepository().delete(item);		
	}

    @Override
    public final Optional<T> findById(long id){
        if(id <= 0)
            throw new IllegalArgumentException();
        return getRepository().findById(id); 
    }

	@Override
	public List<T> findAll() {
		return (List<T>) getRepository().findAll();
	}
    
	@Override
	public final long count() {
		return getRepository().count();
	}

	@Override
    public void save(T entity) {
        getRepository().save(entity);        
    }
    
}
