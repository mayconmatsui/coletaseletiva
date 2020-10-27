package com.reciclanavirai.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reciclanavirai.domain.Departamento;
import com.reciclanavirai.domain.dto.DepartamentoDTO;
import com.reciclanavirai.service.DepartamentoService;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {
	
	@Autowired
	public DepartamentoService service;

	@GetMapping()
	public ResponseEntity<List<DepartamentoDTO>> listarDepartamentos() {
		return ResponseEntity.ok(service.listarDepartamentos());
	}

	@GetMapping("/{id}")
	public ResponseEntity listarDepartamentoPorId(@PathVariable("id") Long id) {
		Optional<DepartamentoDTO> departamento = service.listarDepartamentoPorId(id);
		if (!departamento.isPresent()) {
			return ResponseEntity.badRequest().body("O departamento para o ID informado não foi localizado");
		} else {
			return ResponseEntity.ok(departamento);
		}
	}

	@PostMapping
	public ResponseEntity salvar(@RequestBody Departamento departamento) {
		try {
			DepartamentoDTO dto = service.salvar(departamento);
			return new ResponseEntity(dto, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody Departamento departamento) {
		return service.listarDepartamentoPorId(id).map(entidade -> {
			try {
				departamento.setId(entidade.getId());
				service.atualizar(departamento);
				return ResponseEntity.ok(departamento);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}).orElseGet(() -> new ResponseEntity("Departamento não encontrado", HttpStatus.BAD_REQUEST));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity excluir(@PathVariable("id") Long id) {
		return service.listarDepartamentoPorId(id).map(entidade -> {
			service.excluir(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}).orElseGet(() -> new ResponseEntity("Departamento não encontrado", HttpStatus.BAD_REQUEST));
	}

}
