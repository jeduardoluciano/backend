package br.com.appdosamba.backend.controller;

import static br.com.caelum.vraptor.view.Results.json;

import java.util.Collection;

import javax.inject.Inject;

import br.com.appdosamba.backend.model.User;
import br.com.appdosamba.backend.model.common.Profile;
import br.com.appdosamba.backend.repository.UserRepository;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;

@Controller
@Path("/users")
public class UsersController {
	private final Result result;
	private final UserRepository userBusiness;
	/**
	 * @deprecated CDI eyes only
	 */
	protected UsersController() {
		this(null, null);
	}
	
	@Inject
	public UsersController(Result result, UserRepository userBusiness) {
		this.result = result;		
		this.userBusiness = userBusiness;
	}
	
	@Get("/")
	public void list() {
		Collection<User> users = userBusiness.all();
		result.include("userList", users);
		result.include("profiles", Profile.values());
	}
	
	@Post("/")
	public void save(){
		
	}
	
	@Delete("/{user.id}")
	public void remove(User user) {
		
	}
	
	@Put("/{user.id}")
	public void callUpdate(User user) {
		User update = userBusiness.find(user.getId());
		result.use(json()).from(update, "user").include("profile").exclude("password").serialize();
	}
}
