package com.reciclanavirai.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Telefone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long motorista_matricula;
	private String telefone;
	public Long getMotorista_matricula() {
		return motorista_matricula;
	}
	public void setMotorista_matricula(Long motorista_matricula) {
		this.motorista_matricula = motorista_matricula;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}


}
