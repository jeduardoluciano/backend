package br.com.appdosamba.backend.amazon.sqs;

import javax.enterprise.context.ApplicationScoped;

import com.amazonaws.services.sqs.AmazonSQSClient;

@ApplicationScoped
public class QueueFactory {
	private static final String BUCKET_KEY = "br.com.backendapp.amazons3.bucket";
	
	private Queue sqs;
	
		
	public Queue getQueue(String queue) {
		 
		AmazonSQSClientFactory factory = new AmazonSQSClientFactory();
		factory.create();
		AmazonSQSClient client = factory.getInstance();
	
		
	
		if(queue.equals("places"))
			return new QueuePlaces(client, "https://sqs.us-east-1.amazonaws.com/309276238607/places");
		return this.sqs;
	}

}
