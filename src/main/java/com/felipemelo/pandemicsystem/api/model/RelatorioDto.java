package com.felipemelo.pandemicsystem.api.model;

import java.util.ArrayList;
import java.util.List;

public class RelatorioDto {
	
	private List<String> mensagens = new ArrayList<>();
	
	public RelatorioDto() {}

	public List<String> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<String> mensagens) {
		this.mensagens = mensagens;
	}

}
