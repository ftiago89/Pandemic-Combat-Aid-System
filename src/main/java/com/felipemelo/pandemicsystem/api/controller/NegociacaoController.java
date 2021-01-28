package com.felipemelo.pandemicsystem.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.felipemelo.pandemicsystem.api.model.InputNegociacaoDto;
import com.felipemelo.pandemicsystem.api.model.OutputNegociacaoDto;
import com.felipemelo.pandemicsystem.domain.service.NegociacaoService;

@RestController
@RequestMapping("/negociacoes")
public class NegociacaoController {
	
	@Autowired
	private NegociacaoService negociacaoService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OutputNegociacaoDto negociacao(@RequestBody InputNegociacaoDto input){
		negociacaoService.validaNegociacao(input);
		OutputNegociacaoDto output = new OutputNegociacaoDto(input);
		return output;
	}

}
