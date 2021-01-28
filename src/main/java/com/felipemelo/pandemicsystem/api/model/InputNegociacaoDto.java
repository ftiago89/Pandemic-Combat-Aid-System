package com.felipemelo.pandemicsystem.api.model;

import java.util.ArrayList;
import java.util.List;

public class InputNegociacaoDto {
	
	private Long idHospital1;
	private Long idHospital2;
	
	private List<RecursoNegociacaoDto> recursosHospital1 = new ArrayList<>();
	
	private List<RecursoNegociacaoDto> recursosHospital2 = new ArrayList<>();
	
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

	public List<RecursoNegociacaoDto> getRecursosHospital1() {
		return recursosHospital1;
	}

	public void setRecursosHospital1(List<RecursoNegociacaoDto> recursos) {
		this.recursosHospital1 = recursos;
	}

	public List<RecursoNegociacaoDto> getRecursosHospital2() {
		return recursosHospital2;
	}

	public void setRecursosHospital2(List<RecursoNegociacaoDto> recursosHospital2) {
		this.recursosHospital2 = recursosHospital2;
	}
	
	//Para calcular a pontuacao total dos recursos propostos para negociacao
	
	public Integer calculaPontos1() {
		int pontos = 0;
		for (RecursoNegociacaoDto rnd: this.recursosHospital1) {
			pontos += rnd.calculaPontos();
		}
		return pontos;
	}
	
	public Integer calculaPontos2() {
		int pontos = 0;
		for (RecursoNegociacaoDto rnd: this.recursosHospital2) {
			pontos += rnd.calculaPontos();
		}
		return pontos;
	}

}
