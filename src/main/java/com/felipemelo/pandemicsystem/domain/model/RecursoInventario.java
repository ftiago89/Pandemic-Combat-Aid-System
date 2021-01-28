package com.felipemelo.pandemicsystem.domain.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*Entidade da classe de associação entre Hospital e Recurso, representa itens do inventario do hospital, pois
 * um hospital pode ter quantidades diferentes de cada tipo de recurso*/

@Entity
public class RecursoInventario {
	
	@JsonIgnore
	@EmbeddedId
	private RecursoInventarioPK id = new RecursoInventarioPK();
	private Integer quantidade;
	private Integer pontos;
	
	public RecursoInventario() {}

	public RecursoInventario(Hospital hospital, Recurso recurso, Integer quantidade) {
		super();
		this.id.setHospital(hospital);
		this.id.setRecurso(recurso);
		this.quantidade = quantidade;
		calculaPontos();
	}

	@JsonIgnore
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
	
	public void calculaPontos() {
		int pontos = 0;
		
		switch (this.getRecurso().getTipo()) {
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
		this.pontos = pontos;
	}

}
