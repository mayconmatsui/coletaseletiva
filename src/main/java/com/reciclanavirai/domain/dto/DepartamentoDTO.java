package com.reciclanavirai.domain.dto;

import org.modelmapper.ModelMapper;

import com.reciclanavirai.domain.Departamento;

public class DepartamentoDTO {

	private Long id;
	private String nome;

	public static DepartamentoDTO create(Departamento d) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(d, DepartamentoDTO.class);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
