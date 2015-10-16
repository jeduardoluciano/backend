package br.com.appdosamba.backend.repository.common;

import java.util.Collection;

import br.com.appdosamba.backend.exception.CommonException;
import br.com.appdosamba.backend.model.common.AbstractEntity;

public interface GenericRepository<T extends AbstractEntity> {

	Collection<T> all();

	T find(Long id);

	void remove(T entity);

	T save(T entity) throws CommonException;
	
}
