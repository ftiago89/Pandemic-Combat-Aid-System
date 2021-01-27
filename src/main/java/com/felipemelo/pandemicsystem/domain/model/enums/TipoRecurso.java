package com.felipemelo.pandemicsystem.domain.model.enums;

public enum TipoRecurso {
	
	MEDICO(1, "Médico"),
	ENFERMEIRO(2, "Enfermeiro"),
	RESPIRADOR(3, "Respirador"),
	TOMOGRAFO(4, "Tomógrafo"),
	AMBULANCIA(5, "Ambulância");
	
	private int cod;
	private String descricao;
	
	private TipoRecurso(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoRecurso toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for (TipoRecurso x: TipoRecurso.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
