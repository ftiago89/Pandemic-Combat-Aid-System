package com.felipemelo.pandemicsystem.api.model;

import java.util.HashSet;
import java.util.Set;

import com.felipemelo.pandemicsystem.domain.model.RecursoInventario;

public class HospitalDto {
	
	private Long id;
	private String nome;
	private String cnpj;
	
	private Double percentualOcupacao;
	
	private Set<RecursoInventario> recursos = new HashSet<>();
	
	private String logradouro;
	private Integer numero;
	private String complemento;
	private String bairro;
	private String cep;
	private Double latitude;
	private Double longitude;
	
	private Long cidadeId;
	
	public HospitalDto() {}
	
	

	public HospitalDto(Long id, String nome, String cnpj, Double percentualOcupacao, Set<RecursoInventario> recursos,
			String logradouro, Integer numero, String complemento, String bairro, String cep, Double latitude,
			Double longitude, Long cidadeId) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.percentualOcupacao = percentualOcupacao;
		this.recursos = recursos;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.latitude = latitude;
		this.longitude = longitude;
		this.cidadeId = cidadeId;
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

	public Double getPercentualOcupacao() {
		return percentualOcupacao;
	}

	public void setPercentualOcupacao(Double percentualOcupacao) {
		this.percentualOcupacao = percentualOcupacao;
	}

	public Set<RecursoInventario> getRecursos() {
		return recursos;
	}

	public void setRecursos(Set<RecursoInventario> recursos) {
		this.recursos = recursos;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Long getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Long cidadeId) {
		this.cidadeId = cidadeId;
	}

}
