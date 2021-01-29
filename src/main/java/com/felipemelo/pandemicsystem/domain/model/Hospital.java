package com.felipemelo.pandemicsystem.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.felipemelo.pandemicsystem.domain.model.enums.TipoRecurso;
import com.felipemelo.pandemicsystem.domain.model.exception.NegociacaoInvalidaException;

@Entity
public class Hospital {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cnpj;
	
	@OneToOne
	@JoinColumn(name = "ocupacao_id")
	private Ocupacao ocupacao;
	
	@OneToMany(mappedBy = "id.hospital")
	private List<RecursoInventario> recursos = new ArrayList<>();
	
	@OneToOne
	private Endereco endereco;
	
	public Hospital() {}

	public Hospital(Long id, String nome, String cnpj, Ocupacao ocupacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.ocupacao = ocupacao;
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

	public List<RecursoInventario> getRecursos() {
		return recursos;
	}

	public void setRecursos(List<RecursoInventario> recursos) {
		this.recursos = recursos;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public Ocupacao getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(Ocupacao percentualOcupacao) {
		this.ocupacao = percentualOcupacao;
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
	
	public void retiraRecurso(TipoRecurso tipo, Integer quantidade) {
		for (RecursoInventario ri: this.recursos) {
			//System.out.println(ri.getRecurso().getTipo());
			if (ri.getRecurso().getTipo().equals(tipo) && (ri.getQuantidade() >= quantidade)) {
				ri.setQuantidade(ri.getQuantidade() - quantidade);
				ri.calculaPontos();
				return;
			}
		}
		throw new NegociacaoInvalidaException("Hospital de id: " + this.id + " não tem o recurso: " 
				+ tipo + " disponível!");
	}
	
	public boolean adicionaRecurso(TipoRecurso tipo, Integer quantidade) {
		boolean achou = false;
		for (RecursoInventario ri: this.recursos) {
			if (ri.getRecurso().getTipo().equals(tipo)) {
				ri.setQuantidade(ri.getQuantidade() + quantidade);
				ri.calculaPontos();
				achou = true;
				break;
			}	
		}
		return achou;
	}

}
