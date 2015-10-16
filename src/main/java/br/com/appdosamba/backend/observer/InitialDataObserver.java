package br.com.appdosamba.backend.observer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import br.com.appdosamba.backend.infra.DefaultAdminCreator;
import br.com.appdosamba.backend.infra.scheduler.StartJobs;
import br.com.caelum.vraptor.events.VRaptorInitialized;

@ApplicationScoped
public class InitialDataObserver {
	
	@Inject private DefaultAdminCreator adminCreator;
	@Inject private StartJobs startJobs;
	
	public void execute(@Observes VRaptorInitialized event) {		
		adminCreator.createDefaultAdmin();
		
		
		startJobs.initialize();
	}
}