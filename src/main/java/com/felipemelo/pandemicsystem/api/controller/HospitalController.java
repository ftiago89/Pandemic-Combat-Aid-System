package com.felipemelo.pandemicsystem.api.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipemelo.pandemicsystem.api.model.InputHospitalDto;
import com.felipemelo.pandemicsystem.api.model.OcupacaoDto;
import com.felipemelo.pandemicsystem.api.model.OutputHospitalDto;
import com.felipemelo.pandemicsystem.api.model.UpdateHospitalDto;
import com.felipemelo.pandemicsystem.domain.service.HospitalService;

/*Controlador REST com a função de:
 * - Buscar todos os hospitais cadastrados.
 * - Buscar um hospital por id.
 * - Cadastrar um novo hospital.
 * - Fazer update de um hospital.
 * --- devido a utilização da classe UpdateHospitalDto, ao atualizar um hospital,
 * --- não se consegue adicionar ou remover recursos do mesmo.
 * - Fazer update no percentual de ocupação de um hospital.
 * - Deletar um hospital
 * - */

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
	
	@PutMapping("/{idHospital}")
	public ResponseEntity<OutputHospitalDto> updateHospital(@PathVariable Long idHospital,
		 	@RequestBody UpdateHospitalDto hospitalDto){
		return ResponseEntity.ok().body(hospitalService.updateHospital(idHospital, hospitalDto));
	}
	
	@PutMapping("/{idHospital}/ocupacao")
	public ResponseEntity<OutputHospitalDto> updateOcupacao(@Valid @PathVariable Long idHospital , @RequestBody OcupacaoDto novaOcupacao){
		if (!hospitalService.existsById(idHospital)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(hospitalService.updateOcupacao(idHospital, novaOcupacao));
	}
	
	//tentar consertar, não está surtindo efeito nenhum com a requisição.
	@DeleteMapping("/{idHospital}")
	public ResponseEntity<Void> deleteHospital(@PathVariable Long idHospital){
		hospitalService.deleteHospital(idHospital);
		return ResponseEntity.noContent().build();
	}
	
}
