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

import com.reciclanavirai.domain.Motorista;
import com.reciclanavirai.domain.dto.MotoristaDTO;
import com.reciclanavirai.service.MotoristaService;

@RestController
@RequestMapping("/api/motoristas")
public class MotoristaController {
	
	@Autowired
	public MotoristaService service;

	@GetMapping()
	public ResponseEntity<List<MotoristaDTO>> listarMotoristas() {
		return ResponseEntity.ok(service.listarMotoristas());
	}

	@GetMapping("/{matricula}")
	public ResponseEntity listarMotoristaPorMatricula(@PathVariable("matricula") Long matricula) {
		Optional<MotoristaDTO> motorista = service.listarMotoristaPorMatricula(matricula);
		if (!motorista.isPresent()) {
			return ResponseEntity.badRequest().body("O motorista para o ID informado não foi localizado");
		} else {
			return ResponseEntity.ok(motorista);
		}
	}

	@PostMapping
	public ResponseEntity salvar(@RequestBody Motorista motorista) {
		try {
			MotoristaDTO dto = service.salvar(motorista);
			return new ResponseEntity(dto, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{matricula}")
	public ResponseEntity atualizar(@PathVariable("matricula") Long matricula, @RequestBody Motorista motorista) {
		return service.listarMotoristaPorMatricula(matricula).map(entmatriculaade -> {
			try {
				motorista.setMatricula(entmatriculaade.getMatricula());
				service.atualizar(motorista);
				return ResponseEntity.ok(motorista);
			} catch (Exception e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}).orElseGet(() -> new ResponseEntity("Motorista não encontrado", HttpStatus.BAD_REQUEST));
	}

	@DeleteMapping("/{matricula}")
	public ResponseEntity excluir(@PathVariable("matricula") Long matricula) {
		return service.listarMotoristaPorMatricula(matricula).map(entmatriculaade -> {
			service.excluir(matricula);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}).orElseGet(() -> new ResponseEntity("Motorista não encontrado", HttpStatus.BAD_REQUEST));
	}

}
