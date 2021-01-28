package com.felipemelo.pandemicsystem.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.felipemelo.pandemicsystem.api.model.RelatorioDto;
import com.felipemelo.pandemicsystem.domain.service.RelatoriosService;

@RestController
@RequestMapping("/relatorios")
public class RelatoriosController {
	
	@Autowired
	private RelatoriosService relatoriosService;
	
	@GetMapping("/maior-que-noventa")
	public ResponseEntity<RelatorioDto> hospitaisMaiorQue90(){
		RelatorioDto relatorioDto = new RelatorioDto();
		relatorioDto.getMensagens().add(relatoriosService.hospitaisMaiorQue90());
		return ResponseEntity.ok().body(relatorioDto);
	}
	
	@GetMapping("/menor-que-noventa")
	public ResponseEntity<RelatorioDto> hospitaisMenorQue90(){
		RelatorioDto relatorioDto = new RelatorioDto();
		relatorioDto.getMensagens().add(relatoriosService.hospitaisMenorQue90());
		return ResponseEntity.ok().body(relatorioDto);
	}
	
	@GetMapping("/media-recursos")
	public ResponseEntity<RelatorioDto> recursosMedia(){
		RelatorioDto relatorioDto = new RelatorioDto();
		relatorioDto.getMensagens().addAll(relatoriosService.recursosMedia());
		return ResponseEntity.ok().body(relatorioDto);
	}
	
	@GetMapping("/pior-situacao")
	public ResponseEntity<RelatorioDto> hospitalMaisCritico(){
		RelatorioDto relatorioDto = new RelatorioDto();
		relatorioDto.getMensagens().add(relatoriosService.hospitalMaisCritico());
		return ResponseEntity.ok().body(relatorioDto);
	}
	
	@GetMapping("/melhor-situacao")
	public ResponseEntity<RelatorioDto> hospitalMenosCritico(){
		RelatorioDto relatorioDto = new RelatorioDto();
		relatorioDto.getMensagens().add(relatoriosService.hospitalMenosCritico());
		return ResponseEntity.ok().body(relatorioDto);
	}

}
