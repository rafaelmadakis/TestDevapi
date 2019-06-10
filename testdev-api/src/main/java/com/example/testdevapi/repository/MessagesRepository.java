package com.example.testdevapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.testdevapi.model.Messages;
import com.example.testdevapi.repository.lancamento.LancamentoRepositoryQuery;

public interface MessagesRepository  extends JpaRepository<Messages, Long>, LancamentoRepositoryQuery {

}
