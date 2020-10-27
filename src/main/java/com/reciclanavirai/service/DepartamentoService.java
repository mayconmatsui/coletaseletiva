package com.reciclanavirai.service;

import java.util.List;
import java.util.Optional;

import com.reciclanavirai.domain.Departamento;
import com.reciclanavirai.domain.dto.DepartamentoDTO;

public interface DepartamentoService {

	DepartamentoDTO salvar(Departamento d);
	DepartamentoDTO atualizar(Departamento d);
	void excluir(Long id);
	List<DepartamentoDTO> listarDepartamentos();
	Optional<DepartamentoDTO> listarDepartamentoPorId(Long id);
}
