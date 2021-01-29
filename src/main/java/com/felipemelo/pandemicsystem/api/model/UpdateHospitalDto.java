package com.felipemelo.pandemicsystem.api.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateHospitalDto {
	
private Long id;
	
	@NotEmpty(message = "Preenchimento de nome obrigatório!")
	@NotNull(message = "Preenchimento de nome obrigatório!")
	@Size(min = 2, max = 120, message = "O nome precisa ter entre 2 e 120 letras!")
	private String nome;
	private String cnpj;
	
	private String logradouro;
	private Integer numero;
	private String complemento;
	private String bairro;
	private String cep;
	private Double latitude;
	private Double longitude;
	
	private String Cidade;
	private String Estado;
	
	public UpdateHospitalDto() {}

	public UpdateHospitalDto(Long id,
			String nome, String cnpj, String logradouro, Integer numero, String complemento, String bairro, String cep,
			Double latitude, Double longitude, String cidade, String estado) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.latitude = latitude;
		this.longitude = longitude;
		Cidade = cidade;
		Estado = estado;
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

	public String getCidade() {
		return Cidade;
	}

	public void setCidade(String cidade) {
		Cidade = cidade;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

}
