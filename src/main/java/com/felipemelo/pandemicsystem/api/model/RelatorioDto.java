package com.felipemelo.pandemicsystem.api.model;

import java.util.ArrayList;
import java.util.List;

/*Fiz essa classe para aninhar para a saída de relatórios.*/

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
