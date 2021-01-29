package com.felipemelo.pandemicsystem.api.model;

import javax.validation.constraints.NotNull;

/*Representation model de entrada para a atualização de uma ocupação*/

public class OcupacaoDto {
	
	@NotNull(message = "Preenchimento de percentual obrigatório!")
	private Double percentualAtualizado;
	
	public OcupacaoDto() {}


	public Double getPercentualAtualizado() {
		return percentualAtualizado;
	}

	public void setPercentualAtualizado(Double percentualAtualizado) {
		this.percentualAtualizado = percentualAtualizado;
	}

}
