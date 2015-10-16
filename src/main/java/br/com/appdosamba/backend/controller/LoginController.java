package br.com.appdosamba.backend.controller;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import br.com.appdosamba.backend.annotation.Public;
import br.com.appdosamba.backend.component.UserSession;
import br.com.appdosamba.backend.model.User;
import br.com.appdosamba.backend.repository.LoginRepository;
import br.com.appdosamba.backend.repository.UserRepository;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;

@Controller
public class LoginController {
	
	private static Logger logger = Logger.getLogger(LoginController.class);
	
	private final Result result;
	private final LoginRepository loginBusiness;
	private final UserRepository userBusiness;
	
	private final UserSession userSession;
	/**
	 * @deprecated CDI eyes only
	 */
	public LoginController() {
		this(null, null, null, null);
	}
	
	@Inject
	public LoginController(Result result, LoginRepository loginBusiness, UserSession userSession, UserRepository userBusiness){
		this.result = result;
		this.loginBusiness = loginBusiness;
		this.userSession = userSession;
		this.userBusiness= userBusiness;
	}

	@Public
	@Post("/autenticar")
	public void authenticate(User entity) {
		
	}
	
	@Public
	@Get("/login")
	public void login(){}
	
	
	@Get("/logout")
	public	void  logout()
	{
		userSession.logout();
		logger.info("logout realizado");
		result.redirectTo(IndexController.class).index();
	}
}
