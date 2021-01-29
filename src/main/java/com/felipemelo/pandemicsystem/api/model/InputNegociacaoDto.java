package com.felipemelo.pandemicsystem.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/*Representation model de entrada para uma negociação*/

public class InputNegociacaoDto {
	
	@NotNull(message = "Preenchimento de id obrigatório!")
	private Long idHospital1;
	@NotNull(message = "Preenchimento de id obrigatório!")
	private Long idHospital2;
	
	@NotEmpty(message = "Preenchimento de recursos obrigatório!")
	@NotNull(message = "Preenchimento de recursos obrigatório!")
	private List<RecursoInventarioDto> recursosHospital1 = new ArrayList<>();
	
	@NotEmpty(message = "Preenchimento de recursos obrigatório!")
	@NotNull(message = "Preenchimento de recursos obrigatório!")
	private List<RecursoInventarioDto> recursosHospital2 = new ArrayList<>();
	
	public InputNegociacaoDto() {}

	public Long getIdHospital1() {
		return idHospital1;
	}

	public void setIdHospital1(Long idHospital1) {
		this.idHospital1 = idHospital1;
	}

	public Long getIdHospital2() {
		return idHospital2;
	}

	public void setIdHospital2(Long idHospital2) {
		this.idHospital2 = idHospital2;
	}

	public List<RecursoInventarioDto> getRecursosHospital1() {
		return recursosHospital1;
	}

	public void setRecursosHospital1(List<RecursoInventarioDto> recursos) {
		this.recursosHospital1 = recursos;
	}

	public List<RecursoInventarioDto> getRecursosHospital2() {
		return recursosHospital2;
	}

	public void setRecursosHospital2(List<RecursoInventarioDto> recursosHospital2) {
		this.recursosHospital2 = recursosHospital2;
	}
	
	//Para calcular a pontuacao total dos recursos propostos para negociacao
	
	public Integer calculaPontos1() {
		int pontos = 0;
		for (RecursoInventarioDto rnd: this.recursosHospital1) {
			pontos += rnd.calculaPontos();
		}
		return pontos;
	}
	
	public Integer calculaPontos2() {
		int pontos = 0;
		for (RecursoInventarioDto rnd: this.recursosHospital2) {
			pontos += rnd.calculaPontos();
		}
		return pontos;
	}

}
