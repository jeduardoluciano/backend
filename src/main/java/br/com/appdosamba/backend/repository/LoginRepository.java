package br.com.appdosamba.backend.repository;

import br.com.appdosamba.backend.model.User;

public interface LoginRepository {
	
	User authenticate(String mail, String password);

}
