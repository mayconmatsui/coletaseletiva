package com.reciclanavirai.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reciclanavirai.domain.Gestor;
import com.reciclanavirai.domain.dto.GestorDTO;
import com.reciclanavirai.exception.ErroAutenticacao;
import com.reciclanavirai.exception.RegraNegocioException;
import com.reciclanavirai.repository.GestorRepository;

@Service
@Transactional(readOnly = false)
public class GestorServiceImpl implements GestorService {

	@Autowired
	private GestorRepository repository;

	@Override
	public GestorDTO salvar(Gestor g) {
		validarEmail(g.getEmail());
		return GestorDTO.create(repository.save(g));
	}

	@Override
	public GestorDTO atualizar(Gestor g) {
		validarEmail(g.getEmail());
		return GestorDTO.create(repository.save(g));
	}

	@Override
	public void excluir(Long id) {
		repository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<GestorDTO> listarGestores() {
		return repository.findAll().stream().map(GestorDTO::create).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<GestorDTO> listarGestorPorId(Long id) {
		return repository.findById(id).map(GestorDTO::create);
	}

	// Regras de negocio
	@Override
	public GestorDTO autenticar(String email, String senha) {
		Optional<Gestor> gestor = repository.findByEmail(email);
		if (!gestor.isPresent()) {
			throw new ErroAutenticacao("O email do usuário não foi localizado.");
		}
		if(!gestor.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("A senha está incorreta.");
		}
		return GestorDTO.create(gestor.get());
	}

	@Override
	public void validarEmail(String email) {
		boolean existe = repository.existsByEmail(email);
		if(existe) {
			throw new RegraNegocioException("Já existe um gestor cadastrado com este email.");
		}

	}

}
