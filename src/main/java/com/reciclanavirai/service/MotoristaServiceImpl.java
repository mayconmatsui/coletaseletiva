package com.reciclanavirai.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reciclanavirai.domain.Motorista;
import com.reciclanavirai.domain.dto.MotoristaDTO;
import com.reciclanavirai.repository.MotoristaRepository;

@Service
@Transactional(readOnly = false)
public class MotoristaServiceImpl implements MotoristaService {

	@Autowired
	private MotoristaRepository repository;

	@Override
	public MotoristaDTO salvar(Motorista d) {
		return MotoristaDTO.create(repository.save(d));
	}

	@Override
	public MotoristaDTO atualizar(Motorista d) {
		return MotoristaDTO.create(repository.save(d));
	}

	@Override
	public void excluir(Long matricula) {
		repository.deleteById(matricula);
	}

	@Override
	@Transactional(readOnly = true)
	public List<MotoristaDTO> listarMotoristas() {
		return repository.findAll().stream().map(MotoristaDTO::create).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<MotoristaDTO> listarMotoristaPorMatricula(Long matricula) {
		return repository.findById(matricula).map(MotoristaDTO::create);
	}

}
