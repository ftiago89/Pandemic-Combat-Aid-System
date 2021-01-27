package com.felipemelo.pandemicsystem.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.felipemelo.pandemicsystem.domain.model.enums.TipoRecurso;

@Entity
public class Recurso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer tipo;
	private Integer pontos;
	
	@OneToMany(mappedBy = "id.recurso")
	private Set<RecursoInventario> recursos = new HashSet<>();
	
	public Recurso() {}

	public Recurso(Long id, TipoRecurso tipo, Integer pontos) {
		super();
		this.id = id;
		this.tipo = tipo.getCod();
		this.pontos = pontos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoRecurso getTipo() {
		return TipoRecurso.toEnum(tipo);
	}

	public void setTipo(TipoRecurso tipo) {
		this.tipo = tipo.getCod();
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
		Recurso other = (Recurso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
