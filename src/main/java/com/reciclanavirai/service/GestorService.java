package com.reciclanavirai.service;

import java.util.List;
import java.util.Optional;

import com.reciclanavirai.domain.Gestor;
import com.reciclanavirai.domain.dto.GestorDTO;

public interface GestorService {
	
	GestorDTO salvar(Gestor g);
	GestorDTO atualizar(Gestor g);
	void excluir(Long id);
	List<GestorDTO> listarGestores();
	Optional<GestorDTO> listarGestorPorId(Long id);
	
	//Regras de negocio
	GestorDTO autenticar(String email, String senha);
	void validarEmail(String email);

}
