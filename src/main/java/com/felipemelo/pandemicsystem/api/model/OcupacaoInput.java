package com.felipemelo.pandemicsystem.api.model;

import javax.validation.constraints.NotNull;

public class OcupacaoInput {
	
	@NotNull
	private Double percentualAtualizado;
	
	public OcupacaoInput() {}


	public Double getPercentualAtualizado() {
		return percentualAtualizado;
	}

	public void setPercentualAtualizado(Double percentualAtualizado) {
		this.percentualAtualizado = percentualAtualizado;
	}

}
