package com.felipemelo.pandemicsystem.api.controller;

import javax.validation.Valid;

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

/*Controlador REST com a função de:
 * - Realizar uma negociação entre hospitais.
 * 
 * Eu tive dúvidas em como modelar essa negociação, optei pela inserção, em uma mesma requisição, dos Ids dos hospitais
 * participantes e das listas de recursos que ambos oferecem para negociação.*/

@RestController
@RequestMapping("/negociacoes")
public class NegociacaoController {
	
	@Autowired
	private NegociacaoService negociacaoService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OutputNegociacaoDto negociacao(@Valid @RequestBody InputNegociacaoDto input){
		negociacaoService.validaNegociacao(input);
		OutputNegociacaoDto output = new OutputNegociacaoDto(input);
		return output;
	}

}
