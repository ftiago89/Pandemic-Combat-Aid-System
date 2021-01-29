package com.felipemelo.pandemicsystem.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*Representation model de entrada para um hospital*/

public class InputHospitalDto {
	
	private Long id;
	
	@NotEmpty(message = "Preenchimento de nome obrigatório!")
	@NotNull(message = "Preenchimento de nome obrigatório!")
	@Size(min = 2, max = 120, message = "O nome precisa ter entre 2 e 120 letras!")
	private String nome;
	private String cnpj;
	
	@NotNull(message = "Preenchimento de percentual obrigatório!")
	private Double percentualOcupacao;
	
	@NotNull(message = "Preenchimento de recursos obrigatório!")
	private List<RecursoInventarioDto> recursos = new ArrayList<>();
	
	private String logradouro;
	private Integer numero;
	private String complemento;
	private String bairro;
	private String cep;
	private Double latitude;
	private Double longitude;
	
	private String Cidade;
	private String Estado;
	
	public InputHospitalDto() {}
	
	

	public InputHospitalDto(Long id, String nome, String cnpj, Double percentualOcupacao, List<RecursoInventarioDto> recursos,
			String logradouro, Integer numero, String complemento, String bairro, String cep, Double latitude,
			Double longitude, String cidade, String estado) {
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
		this.Cidade = cidade;
		this.Estado = estado;
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

	public List<RecursoInventarioDto> getRecursos() {
		return recursos;
	}

	public void setRecursos(List<RecursoInventarioDto> recursos) {
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

	public String getCidade() {
		return Cidade;
	}

	public void setCidade(String cidade) {
		this.Cidade = cidade;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

}
