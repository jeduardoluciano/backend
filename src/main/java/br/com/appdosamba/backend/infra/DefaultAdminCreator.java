package br.com.appdosamba.backend.infra;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;

import br.com.appdosamba.backend.model.User;
import br.com.appdosamba.backend.model.common.Profile;

@ApplicationScoped
public class DefaultAdminCreator {
	
	@Inject
	private EntityManagerFactory factory;
	
	private static Logger LOG = Logger.getLogger(DefaultAdminCreator.class);
	
	
	public void createDefaultAdmin() {
		
		EntityManager manager;
		
		manager = factory.createEntityManager();
		manager.getTransaction().begin();
		
		Long result = (Long) manager.createQuery("select count(*) from User").getSingleResult();
		
		if (result == 0) {
			String login = "admin@appadosamba.com.br";
			String password = "123456";
			
			User defaultUser = new User();
			defaultUser.setLogin(login);
			defaultUser.setPassword(MD5.crypt(password));
			defaultUser.setFirstName("Super");
			defaultUser.setLastName("Admininstrator");
			defaultUser.setProfile(Profile.SUPERADMINISTRATOR);
			defaultUser.setPhoto("images/Jellyfish.jpg");
			defaultUser.setApproved(true);
			
			manager.persist(defaultUser);
			
			LOG.info("=========================");
			LOG.info("New admin user created!");
			LOG.info("email: " + login);
			
			LOG.info("=========================");
		}
		
		manager.getTransaction().commit();
	}
}
