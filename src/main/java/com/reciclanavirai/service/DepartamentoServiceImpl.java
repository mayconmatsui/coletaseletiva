package com.reciclanavirai.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reciclanavirai.domain.Departamento;
import com.reciclanavirai.domain.dto.DepartamentoDTO;
import com.reciclanavirai.repository.DepartamentoRepository;

@Service
@Transactional(readOnly = false)
public class DepartamentoServiceImpl implements DepartamentoService {

	@Autowired
	private DepartamentoRepository repository;

	@Override
	public DepartamentoDTO salvar(Departamento d) {
		return DepartamentoDTO.create(repository.save(d));
	}

	@Override
	public DepartamentoDTO atualizar(Departamento d) {
		return DepartamentoDTO.create(repository.save(d));
	}

	@Override
	public void excluir(Long id) {
		repository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<DepartamentoDTO> listarDepartamentos() {
		return repository.findAll().stream().map(DepartamentoDTO::create).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<DepartamentoDTO> listarDepartamentoPorId(Long id) {
		return repository.findById(id).map(DepartamentoDTO::create);
	}

}
