package br.com.appdosamba.backend.infra.scheduler.jobs;

import java.io.IOException;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.sqs.model.Message;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.appdosamba.backend.amazon.sqs.Queue;
import br.com.appdosamba.backend.amazon.sqs.QueueFactory;
import br.com.appdosamba.backend.exception.CommonException;
import br.com.appdosamba.backend.model.CommercialPlace;

@ApplicationScoped
public class SQSJob implements Job {
	Logger logger = LoggerFactory.getLogger(SQSJob.class);
	private static EntityManagerFactory emf;
	private static EntityManager em;
	private EntityTransaction etx;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			logger.debug("Executando JOB SQS");
			
			emf = Persistence.createEntityManagerFactory("default");
			em = emf.createEntityManager();
			etx = em.getTransaction();
			etx.begin();

			QueueFactory queueFactory = new QueueFactory();
			Queue queue = queueFactory.getQueue("places");

			List<Message> messagePlaces = queue.receive();
			logger.debug("Quantidade de Places " + String.valueOf(messagePlaces.size()));
			
			for (Message message : messagePlaces) {				
				ObjectMapper mapper = new ObjectMapper();
				try {
					CommercialPlace place = mapper.readValue(message.getBody(), CommercialPlace.class);
					em.persist(place);
					logger.debug("Entity persistida " + place.toString());
					queue.deleteMessage(message);					

				} catch (JsonParseException e) {
					throw new CommonException("Ocorreu erro no parse do JSON", e);
				} catch (JsonMappingException e) {
					throw new CommonException(e);
				} catch (IOException e) {
					throw new CommonException(e);
				}
			}
			
			logger.debug("Finalizando a importação JOB SQS");

		} catch (Throwable t) {
			t.printStackTrace();
			close(false);
		} finally {
			close(true);
		}	
	}

	private void close(boolean isCommit) {
		if (isCommit) {
			try {
				etx.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				etx.rollback();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		emf = null;
		em = null;
		etx = null;
	}

}
