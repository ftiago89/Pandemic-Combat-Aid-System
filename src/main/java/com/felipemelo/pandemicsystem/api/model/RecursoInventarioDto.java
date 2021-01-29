package com.felipemelo.pandemicsystem.api.model;

import com.felipemelo.pandemicsystem.domain.model.enums.TipoRecurso;

public class RecursoInventarioDto {
	
	private TipoRecurso tipo;
	private Integer quantidade;
	
	public RecursoInventarioDto() {}

	public RecursoInventarioDto(TipoRecurso tipo, Integer quantidade) {
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

}
