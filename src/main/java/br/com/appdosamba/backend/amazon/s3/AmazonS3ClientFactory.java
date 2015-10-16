package br.com.appdosamba.backend.amazon.s3;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3Client;

@ApplicationScoped
public class AmazonS3ClientFactory {

	private AmazonS3Client amazonS3Client;

	@Produces
	public AmazonS3Client getInstance() {
		return amazonS3Client;
	}

	@PostConstruct
	public void create() {
		try {

			amazonS3Client = new AmazonS3Client(new EnvironmentVariableCredentialsProvider());

		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Could not instantiate amazon S3 client", e);
		}
	}

}