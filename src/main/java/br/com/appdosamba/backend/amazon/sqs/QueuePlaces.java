package br.com.appdosamba.backend.amazon.sqs;

import java.util.List;

import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;

public class QueuePlaces implements Queue{
	private final AmazonSQSClient amazonSQSClient;
	private String queueURL;

	public QueuePlaces(AmazonSQSClient amazonSQSClient, String queueURL) {
		this.amazonSQSClient = amazonSQSClient;
		this.queueURL = queueURL;
	}

	@Override
	public List<Message> receive() {
		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueURL);
	       List<Message> messages = amazonSQSClient.receiveMessage(receiveMessageRequest.withMaxNumberOfMessages(10)).getMessages();
	       return messages;
		
	}

	@Override
	public void deleteMessage(Message message) {
		String messageReceiptHandle =message.getReceiptHandle();
		amazonSQSClient.deleteMessage(new DeleteMessageRequest()
		 .withQueueUrl(queueURL)
		 .withReceiptHandle(messageReceiptHandle));
		
	}
}
