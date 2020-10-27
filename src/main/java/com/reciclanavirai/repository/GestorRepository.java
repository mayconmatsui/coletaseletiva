package com.reciclanavirai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reciclanavirai.domain.Gestor;

public interface GestorRepository extends JpaRepository<Gestor, Long>{
	
	Optional<Gestor> findByEmail(String email);
	boolean existsByEmail(String email);
}
