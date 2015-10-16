package br.com.appdosamba.backend.business;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.appdosamba.backend.business.common.GenericBusiness;
import br.com.appdosamba.backend.model.User;
import br.com.appdosamba.backend.repository.UserRepository;

public class UserBusiness extends GenericBusiness<User>implements UserRepository {

	@Inject
	protected UserBusiness(EntityManager manager) {
		super(manager);
	}
}
