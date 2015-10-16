package br.com.appdosamba.backend.repository;

import java.util.Collection;

import br.com.appdosamba.backend.model.CommercialPlace;
import br.com.appdosamba.backend.repository.common.GenericRepository;

public interface PlaceRepository extends GenericRepository<CommercialPlace>{
	Collection<CommercialPlace> listPlacesApproved();		
}

	
