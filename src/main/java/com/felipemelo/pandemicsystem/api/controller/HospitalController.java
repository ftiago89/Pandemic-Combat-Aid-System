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
import com.felipemelo.pandemicsystem.domain.model.Hospital;
import com.felipemelo.pandemicsystem.domain.service.HospitalService;

@RestController
@RequestMapping("/hospitais")
public class HospitalController {
	
	@Autowired
	private HospitalService hospitalService;
	
	@GetMapping
	public List<HospitalDto> findAll(){
		return hospitalService.toDtoCollection(hospitalService.findAll());
	}
	
	@GetMapping("/{idHospital}")
	public ResponseEntity<HospitalDto> find(@PathVariable Long idHospital) {
		Hospital hospital = hospitalService.find(idHospital);
		HospitalDto hospitalDto = hospitalService.toDto(hospital);
		return ResponseEntity.ok().body(hospitalDto);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public HospitalDto insertHospital(@RequestBody HospitalDto hospitalDto) {
		
		Hospital hospital = hospitalService.fromDto(hospitalDto);
	
		return hospitalService.toDto((hospitalService.insertHospital(hospital)));
	}
	
	@PutMapping("/{idHospital}/ocupacao")
	public ResponseEntity<HospitalDto> updateOcupacao(@PathVariable Long idHospital , @RequestBody OcupacaoInput novaOcupacao){
		
		if (!hospitalService.existsById(idHospital)) {
			return ResponseEntity.notFound().build();
		}
		
		HospitalDto hospitalDto = hospitalService.toDto(hospitalService.updateOcupacao(idHospital, novaOcupacao));
		return ResponseEntity.ok(hospitalDto);
	}
	
}
