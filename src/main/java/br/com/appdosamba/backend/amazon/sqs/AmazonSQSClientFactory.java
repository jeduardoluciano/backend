package br.com.appdosamba.backend.amazon.sqs;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.sqs.AmazonSQSClient;

@ApplicationScoped
public class AmazonSQSClientFactory {

	private AmazonSQSClient amazonSQSClient;

	@Produces
	public AmazonSQSClient getInstance() {
		return amazonSQSClient;
	}

	@PostConstruct
	public void create() {

		AWSCredentials credentials = new EnvironmentVariableCredentialsProvider().getCredentials();

		if (credentials == null) {
			throw new IllegalStateException("Could not found your credentials resource, please "
					+ "place it at a source folder with name AwsCredentials.properties or set its path with "
					+ " in your environment file.");
		}
		amazonSQSClient = new AmazonSQSClient(new EnvironmentVariableCredentialsProvider());
	}
}
