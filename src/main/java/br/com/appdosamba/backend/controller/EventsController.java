package br.com.appdosamba.backend.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import br.com.appdosamba.backend.model.Event;
import br.com.appdosamba.backend.repository.EventRepository;
import br.com.appdosamba.backend.repository.PlaceRepository;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;

@Controller
@Path("/events")
public class EventsController {
	private final Result result;
	private final EventRepository eventBusiness;
	private final PlaceRepository placeBusiness;

	/**
	 * @deprecated CDI eyes only
	 */
	protected EventsController() {
		this(null, null, null);
	}

	@Inject
	public EventsController(Result result, EventRepository eventBusiness, PlaceRepository placeBusiness) {
		this.result = result;
		this.eventBusiness = eventBusiness;
		this.placeBusiness = placeBusiness;
	}

	@Get("/")
	public void list() {
		Collection<Event> events = eventBusiness.all();
		
		Collection<Event> newEventList = new ArrayList<Event>();
		
		for (Event event : events) {
			event.getPlace().getAddress();
			newEventList.add(event);
		}
		result.include(newEventList);
	
	}
		
	
}
