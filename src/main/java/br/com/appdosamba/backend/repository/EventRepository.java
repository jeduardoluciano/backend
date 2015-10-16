package br.com.appdosamba.backend.repository;

import java.util.Collection;

import br.com.appdosamba.backend.model.Event;
import br.com.appdosamba.backend.repository.common.GenericRepository;

public interface EventRepository extends GenericRepository<Event>{
	Collection<Event> listGroupByDateJson();
	Collection<Event> listEventsBy300();
}

	
