package com.reciclanavirai.domain.dto;

import org.modelmapper.ModelMapper;

import com.reciclanavirai.domain.Motorista;

public class MotoristaDTO {

	private Long matricula;
	private String nome;
	private String CPF;
	private String RG;
	private String CNH;
	private String endereco;

	public static MotoristaDTO create(Motorista d) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(d, MotoristaDTO.class);
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getRG() {
		return RG;
	}

	public void setRG(String rG) {
		RG = rG;
	}

	public String getCNH() {
		return CNH;
	}

	public void setCNH(String cNH) {
		CNH = cNH;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	

}
