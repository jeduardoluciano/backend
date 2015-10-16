package br.com.appdosamba.backend.business;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.appdosamba.backend.business.common.GenericBusiness;
import br.com.appdosamba.backend.model.CommercialPlace;
import br.com.appdosamba.backend.repository.PlaceRepository;

public class PlaceBusiness extends GenericBusiness<CommercialPlace>implements PlaceRepository {

	@Inject
	protected PlaceBusiness(EntityManager manager) {
		super(manager);
	}

	@Override
	public Collection<CommercialPlace> listPlacesApproved() {
		Query query = manager
				.createQuery("from CommercialPlace c where c.approved =:approved ORDER BY c.name", CommercialPlace.class)
				.setParameter("approved", true);

		@SuppressWarnings("unchecked")
		Collection<CommercialPlace> list = query.getResultList();

		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<CommercialPlace> all() {
		Query query = manager
				.createQuery("from CommercialPlace c ORDER BY c.name", CommercialPlace.class);						
		return query.getResultList();
	}


}
