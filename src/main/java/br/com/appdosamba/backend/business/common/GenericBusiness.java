package br.com.appdosamba.backend.business.common;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.appdosamba.backend.exception.CommonException;
import br.com.appdosamba.backend.model.common.AbstractEntity;
import br.com.appdosamba.backend.repository.common.GenericRepository;

public abstract class GenericBusiness<T extends AbstractEntity> implements GenericRepository<T> {
	protected final EntityManager manager;
	private final Class<T> clazz;
	
	protected GenericBusiness(EntityManager manager) {
		this.manager = manager;

		@SuppressWarnings("unchecked")
		Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

		this.clazz = clazz;
	}
	
	public Collection<T> all() {
		Query query = manager.createQuery("from " + clazz.getName());

		@SuppressWarnings("unchecked")
		Collection<T> list = query.getResultList();

		return list;
	}

	public T find(Long id) {
		return manager.find(clazz, id);
	}

	public void remove(T entity) {
		manager.remove(manager.getReference(clazz, entity.getId()));
	}

	public T save(T entity) throws CommonException {
		return manager.merge(entity);
	}

}
