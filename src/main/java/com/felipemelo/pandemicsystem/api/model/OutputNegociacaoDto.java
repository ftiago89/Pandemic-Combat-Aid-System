package com.felipemelo.pandemicsystem.api.model;

import java.util.ArrayList;
import java.util.List;

public class OutputNegociacaoDto {
	
	private Long idHospital1;
	private Integer pontuacaoHospital1;
	private Long idHospital2;
	private Integer pontuacaoHospital2;
	
	private List<RecursoNegociacaoDto> recursosHospital1 = new ArrayList<>();
	
	private List<RecursoNegociacaoDto> recursosHospital2 = new ArrayList<>();
	


	
	public OutputNegociacaoDto() {}

	public OutputNegociacaoDto(InputNegociacaoDto input) {
		super();
		this.idHospital1 = input.getIdHospital1();
		this.idHospital2 = input.getIdHospital2();
		this.pontuacaoHospital1 = input.calculaPontos1();
		this.pontuacaoHospital2 = input.calculaPontos2();
		this.recursosHospital1 = input.getRecursosHospital1();
		this.recursosHospital2 = input.getRecursosHospital2();
	}

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

	public void setRecursosHospital1(List<RecursoNegociacaoDto> recursosHospital1) {
		this.recursosHospital1 = recursosHospital1;
	}

	public List<RecursoNegociacaoDto> getRecursosHospital2() {
		return recursosHospital2;
	}

	public void setRecursosHospital2(List<RecursoNegociacaoDto> recursosHospital2) {
		this.recursosHospital2 = recursosHospital2;
	}

	public Integer getPontuacaoHospital1() {
		return pontuacaoHospital1;
	}

	public void setPontuacaoHospital1(Integer pontuacao) {
		this.pontuacaoHospital1 = pontuacao;
	}

	public Integer getPontuacaoHospital2() {
		return pontuacaoHospital2;
	}

	public void setPontuacaoHospital2(Integer pontuacaoHospital2) {
		this.pontuacaoHospital2 = pontuacaoHospital2;
	}

}
