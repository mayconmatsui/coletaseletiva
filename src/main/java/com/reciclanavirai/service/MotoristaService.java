package com.reciclanavirai.service;

import java.util.List;
import java.util.Optional;

import com.reciclanavirai.domain.Motorista;
import com.reciclanavirai.domain.dto.MotoristaDTO;

public interface MotoristaService {

	MotoristaDTO salvar(Motorista d);
	MotoristaDTO atualizar(Motorista d);
	void excluir(Long matricula);
	List<MotoristaDTO> listarMotoristas();
	Optional<MotoristaDTO> listarMotoristaPorMatricula(Long matricula);
}
