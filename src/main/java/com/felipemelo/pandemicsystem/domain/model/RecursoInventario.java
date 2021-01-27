package com.felipemelo.pandemicsystem.domain.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class RecursoInventario {
	
	@EmbeddedId
	private RecursoInventarioPK id = new RecursoInventarioPK();
	private Integer quantidade;
	private Integer pontos;
	
	public RecursoInventario() {}

	public RecursoInventario(Hospital hospital, Recurso recurso, Integer quantidade, Integer pontos) {
		super();
		this.id.setHospital(hospital);
		this.id.setRecurso(recurso);
		this.quantidade = quantidade;
		this.pontos = pontos;
	}

	public Hospital getHospital() {
		return id.getHospital();
	}
	
	public void setHospital(Hospital hospital) {
		id.setHospital(hospital);
	}
	
	public Recurso getRecurso() {
		return id.getRecurso();
	}
	
	public void setRecurso(Recurso recurso) {
		id.setRecurso(recurso);
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getPontos() {
		return pontos;
	}

	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecursoInventario other = (RecursoInventario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
