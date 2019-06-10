package com.example.testdevapi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.testdevapi.event.RecursoCriadoEvent;
import com.example.testdevapi.model.Messages;
import com.example.testdevapi.repository.MessagesRepository;
import com.example.testdevapi.service.MessageService;

@RestController
@RequestMapping("/messages")
public class MessagesResource {
	
	@Autowired
	private MessagesRepository messagesRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private MessageService messageService;
	
	
	@GetMapping
	public List<Messages> pesquisar() {
		
		return messagesRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Messages> criar(@Valid @RequestBody Messages messages, HttpServletResponse response) {

		Messages messagesSalva = messageService.salvar(messages);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, messagesSalva.getId()));
		
		// Devolve o recuso messages criado
		return ResponseEntity.status(HttpStatus.CREATED).body(messagesSalva);

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Messages> buscarPeloId(@PathVariable Long id) {

		Messages messages = messagesRepository.findOne(id);
		return messages != null ? ResponseEntity.ok(messages) : ResponseEntity.notFound().build();

	}
	
	
}
