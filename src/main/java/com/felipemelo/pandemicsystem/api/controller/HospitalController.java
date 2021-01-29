package com.felipemelo.pandemicsystem.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipemelo.pandemicsystem.api.model.InputHospitalDto;
import com.felipemelo.pandemicsystem.api.model.OcupacaoInput;
import com.felipemelo.pandemicsystem.api.model.OutputHospitalDto;
import com.felipemelo.pandemicsystem.domain.service.HospitalService;

/*Controlador REST com a função de:
 * - Buscar todos os hospitais cadastrados.
 * - Buscar um hospital por id.
 * - Cadastrar um novo hospital.
 * - Fazer update no percentual de ocupação de um hospital.*/

@RestController
@RequestMapping("/hospitais")
public class HospitalController {
	
	@Autowired
	private HospitalService hospitalService;
	
	@GetMapping
	public List<OutputHospitalDto> findAll(){
		return hospitalService.findAll();
	}
	
	@GetMapping("/{idHospital}")
	public ResponseEntity<OutputHospitalDto> find(@PathVariable Long idHospital) {
		OutputHospitalDto outputHospitalDto = hospitalService.toDto(hospitalService.find(idHospital));
		return ResponseEntity.ok().body(outputHospitalDto);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OutputHospitalDto insertHospital(@Valid @RequestBody InputHospitalDto hospitalDto) {
		return (hospitalService.insertHospital(hospitalDto));
	}
	
	@PutMapping("/{idHospital}/ocupacao")
	public ResponseEntity<OutputHospitalDto> updateOcupacao(@Valid @PathVariable Long idHospital , @RequestBody OcupacaoInput novaOcupacao){
		if (!hospitalService.existsById(idHospital)) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(hospitalService.updateOcupacao(idHospital, novaOcupacao));
	}
	
}
