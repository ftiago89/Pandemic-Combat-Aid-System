package com.felipemelo.pandemicsystem.api.model;

import com.felipemelo.pandemicsystem.domain.model.enums.TipoRecurso;

public class RecursoNegociacaoDto {
	
	private TipoRecurso tipo;
	private Integer quantidade;
	
	public RecursoNegociacaoDto() {}

	public RecursoNegociacaoDto(TipoRecurso tipo, Integer quantidade) {
		super();
		this.tipo = tipo;
		this.quantidade = quantidade;
	}

	public TipoRecurso getTipo() {
		return tipo;
	}

	public void setTipo(TipoRecurso tipo) {
		this.tipo = tipo;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public Integer calculaPontos() {
		int pontos = 0;
		
		switch (this.tipo) {
		case MEDICO:
			pontos =  3 * this.quantidade;
			break;
		case ENFERMEIRO:
			pontos =  3 * this.quantidade;
			break;
		case RESPIRADOR:
			pontos =  5 * this.quantidade;
			break;
		case TOMOGRAFO:
			pontos =  12 * this.quantidade;
			break;
		case AMBULANCIA:
			pontos =  10 * this.quantidade;
			break;
		}
		return pontos;
	}

}
