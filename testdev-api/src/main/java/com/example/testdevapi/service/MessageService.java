package com.example.testdevapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.testdevapi.model.Messages;
import com.example.testdevapi.repository.MessagesRepository;

@Service
public class MessageService {
	
	@Autowired
	private MessagesRepository messagesRepository;
	
	public Messages salvar (Messages messages) {
		
		messages = messagesRepository.findOne(messages.getId());
		
		return messagesRepository.save(messages);
		
	}

}
