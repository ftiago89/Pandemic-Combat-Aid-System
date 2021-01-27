package com.felipemelo.pandemicsystem.domain.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Hospital {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cnpj;
	
	@OneToMany(mappedBy = "hospital")
	private List<Ocupacao> historicoOcupacoes = new ArrayList<>();
	
	@OneToMany(mappedBy = "id.hospital")
	private Set<RecursoInventario> recursos = new HashSet<>();
	
	public Hospital() {}

	public Hospital(Long id, String nome, String cnpj) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Set<RecursoInventario> getRecursos() {
		return recursos;
	}

	public void setRecursos(Set<RecursoInventario> recursos) {
		this.recursos = recursos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public List<Ocupacao> getHistoricoOcupacoes() {
		return historicoOcupacoes;
	}

	public void setHistoricoOcupacoes(List<Ocupacao> historicoOcupacoes) {
		this.historicoOcupacoes = historicoOcupacoes;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hospital other = (Hospital) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
