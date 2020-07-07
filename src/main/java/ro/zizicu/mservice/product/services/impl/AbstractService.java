package ro.zizicu.mservice.product.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import ro.zizicu.nwbase.exceptions.EntityNotFoundException;


public abstract class AbstractService<U, T extends CrudRepository<U, Integer>> {
	@Autowired
	private T repository;
	
	public U load(Integer id) {
		U u = repository.findById(id).orElse(null);
		if(u == null)
			throw new EntityNotFoundException();
		return u;
	}

	public Iterable<U> getAll() {
		return repository.findAll();
	}
	
	@Transactional
	public void delete(U u) {
		repository.delete(u);
	}

	@Transactional
	public U create(U u) {
		u = repository.save(u);
		return u;
		
	}
}
