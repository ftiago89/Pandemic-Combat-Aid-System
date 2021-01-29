package com.felipemelo.pandemicsystem.api.controller;

import java.util.List;

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

import com.felipemelo.pandemicsystem.api.model.HospitalDto;
import com.felipemelo.pandemicsystem.api.model.OcupacaoInput;
import com.felipemelo.pandemicsystem.domain.service.HospitalService;

@RestController
@RequestMapping("/hospitais")
public class HospitalController {
	
	@Autowired
	private HospitalService hospitalService;
	
	@GetMapping
	public List<HospitalDto> findAll(){
		return hospitalService.findAll();
	}
	
	@GetMapping("/{idHospital}")
	public ResponseEntity<HospitalDto> find(@PathVariable Long idHospital) {
		return ResponseEntity.ok().body(hospitalService.find(idHospital));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public HospitalDto insertHospital(@RequestBody HospitalDto hospitalDto) {
		return (hospitalService.insertHospital(hospitalDto));
	}
	
	@PutMapping("/{idHospital}/ocupacao")
	public ResponseEntity<HospitalDto> updateOcupacao(@PathVariable Long idHospital , @RequestBody OcupacaoInput novaOcupacao){
		
		if (!hospitalService.existsById(idHospital)) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(hospitalService.updateOcupacao(idHospital, novaOcupacao));
	}
	
}
