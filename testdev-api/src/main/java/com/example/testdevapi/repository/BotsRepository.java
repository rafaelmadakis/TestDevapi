package com.example.testdevapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.testdevapi.model.Bots;

public interface BotsRepository extends JpaRepository<Bots, Long> {

}
