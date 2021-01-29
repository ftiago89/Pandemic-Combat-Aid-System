package com.felipemelo.pandemicsystem.api.model;

import javax.validation.constraints.NotBlank;

public class OcupacaoInput {
	
	@NotBlank
	private Double percentualAtualizado;
	
	public OcupacaoInput() {}


	public Double getPercentualAtualizado() {
		return percentualAtualizado;
	}

	public void setPercentualAtualizado(Double percentualAtualizado) {
		this.percentualAtualizado = percentualAtualizado;
	}

}
