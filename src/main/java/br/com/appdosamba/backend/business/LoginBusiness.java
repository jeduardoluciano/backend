	package br.com.appdosamba.backend.business;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.appdosamba.backend.model.User;
import br.com.appdosamba.backend.repository.LoginRepository;

public class LoginBusiness implements LoginRepository{
	
	@Inject 
	private EntityManager manager;

	public User authenticate(String mail, String password) {
		try {
			Query query = manager.createQuery("from " + User.class.getName() + " where login = :login and password = :password");
			query.setParameter("login", mail);
			query.setParameter("password", password);
			return (User) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
