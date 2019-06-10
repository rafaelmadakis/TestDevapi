package com.example.testdevapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.testdevapi.model.Bots;
import com.example.testdevapi.repository.BotsRepository;

@Service
public class BotService {
	
	@Autowired
	private BotsRepository botsRepository;
	
	public Bots atualizar(Long id, Bots bots) {
		
		Bots botsSalvo = botsRepository.findOne(id);
		
		if (botsSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(bots, botsSalvo, "id");
		 return botsRepository.save(botsSalvo);
		
	}

}
