package br.com.appdosamba.backend.amazon.s3;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;

public class S3FileStorage implements FileStorage {
	private final AmazonS3Client amazonS3Client;
	private String bucket;

	public S3FileStorage(AmazonS3Client amazonS3Client, String bucket) {
		this.amazonS3Client = amazonS3Client;
		this.bucket = bucket;
	}

	public URL store(File file, String key) {
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, key, file)
				.withCannedAcl(CannedAccessControlList.PublicRead);
		amazonS3Client.putObject(putObjectRequest);
		return urlFor(bucket, key);
	}

	public URL store(InputStream is, String path, String contentType) {
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(contentType);
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, path, is, metadata)
				.withCannedAcl(CannedAccessControlList.PublicRead);
		amazonS3Client.putObject(putObjectRequest);
		return urlFor(bucket, path);
	}

	public void newBucket(String bucket) {
		amazonS3Client.createBucket(bucket);
	}

	public URL urlFor(String bucket, String key) {
		try {
			return new URL("http://" + bucket + ".s3.amazonaws.com/" + key);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public URL store(File file, String rootName, Collection list, String key) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JodaModule());
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);

		file.getParentFile().mkdirs();

		try {
			mapper.writer().withRootName(rootName).writeValue(file, list);
			store(file, key);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return urlFor(null, file.getPath());
	}

	@Override
	public void remove(String key) {
		try {
			amazonS3Client.deleteObject(new DeleteObjectRequest(bucket, key));
		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException.");
			System.out.println("Error Message: " + ace.getMessage());
		}
	}
}
