package br.com.appdosamba.backend.amazon.sqs;

import java.util.List;

import com.amazonaws.services.sqs.model.Message;

public interface Queue {
	List<Message> receive();
	
	void deleteMessage(Message message);
}
