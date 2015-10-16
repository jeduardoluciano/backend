package br.com.appdosamba.backend.business;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.appdosamba.backend.business.common.GenericBusiness;
import br.com.appdosamba.backend.model.Event;
import br.com.appdosamba.backend.repository.EventRepository;

public class EventBusiness extends GenericBusiness<Event> implements EventRepository{

	@Inject
	protected EventBusiness(EntityManager manager) {
		super(manager);
	}

	@Override
	public Collection<Event> listGroupByDateJson() {
		
		Query query = manager.createQuery("from Event" );

		@SuppressWarnings("unchecked")
		Collection<Event> list = query.getResultList();

		return list;
	}
	
	@Override
	public Collection<Event> listEventsBy300() {
		
		Query query = manager.createQuery("from Event" );

		@SuppressWarnings("unchecked")
		Collection<Event> list = query.getResultList();

		return list;
	}
	
	
	
	
}
