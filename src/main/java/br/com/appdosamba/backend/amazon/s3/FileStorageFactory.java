package br.com.appdosamba.backend.amazon.s3;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3Client;

import br.com.caelum.vraptor.environment.Environment;

@ApplicationScoped
public class FileStorageFactory {
	
	private static final String BUCKET_KEY = "br.com.backendapp.amazons3.bucket";
	private final Environment env;
	private FileStorage fileStorage;
	private ServletContext context;

	@Deprecated
	FileStorageFactory() {
		this(null, null);
	}

	@Inject
	public FileStorageFactory(Environment env, ServletContext context) {
		this.env = env;
		this.context = context;
	}
	
	@PostConstruct
	public void init() {
		AWSCredentials credentialsFile = new EnvironmentVariableCredentialsProvider().getCredentials();
		
		if (credentialsFile == null) {
			this.fileStorage = new LocalFileStorage(env, context);
		} else {
			AmazonS3ClientFactory factory = new AmazonS3ClientFactory();
			factory.create();
			AmazonS3Client client = factory.getInstance();
			String bucket = env.get(BUCKET_KEY);
			this.fileStorage = new S3FileStorage(client, bucket);
		}
	}
	
	@Produces
	public FileStorage getFileStorage() {
		return this.fileStorage;
	}
}
