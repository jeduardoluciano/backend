package br.com.appdosamba.backend.component;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.appdosamba.backend.model.User;

@SessionScoped
@Named("userSession")
public class UserSession implements Serializable{

	private static final long serialVersionUID = 8113472081570152045L;

	private User user;
	private String language;

	public boolean isLogged() {
		return user != null;
	}

	public void logout() {
		user = null;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
