package com.example.testdevapi.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.testdevapi.model.Bots;
import com.example.testdevapi.repository.BotsRepository;
import com.example.testdevapi.service.BotService;

@RestController
@RequestMapping("/bots")
public class BotsResource {

	@Autowired
	private BotsRepository botsRepository;
	
	@Autowired
	private BotService botService;
	
	

	@GetMapping
	public List<Bots> listar() {

		return botsRepository.findAll();

	}

	@PostMapping
	public ResponseEntity<Bots> criar(@Valid @RequestBody Bots bots, HttpServletResponse response) {

		Bots botsSalvo = botsRepository.save(bots);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(botsSalvo.getId())
				.toUri();
		response.setHeader("Location", uri.toASCIIString());

		// Devolve o recuso bot criado
		return ResponseEntity.created(uri).body(botsSalvo);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Bots> buscarPeloId(@PathVariable Long id) {

		Bots bots = botsRepository.findOne(id);
		return bots != null ? ResponseEntity.ok(bots) : ResponseEntity.notFound().build();

	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		
		botsRepository.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Bots> atualizar(@PathVariable Long id, @Valid @RequestBody Bots bots) {
		
		Bots botsSalvo = botService.atualizar(id, bots);
		
		return ResponseEntity.ok(botsSalvo);
	}
}
