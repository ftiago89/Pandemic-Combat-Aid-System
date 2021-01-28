package com.felipemelo.pandemicsystem.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Negociacao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long hospital1Id;
	private Long hospital2Id;
	
	private String infoItensHospital1;
	private String infoItensHospital2;
	
	private OffsetDateTime data;
	
	
	/*@ManyToMany
	private List<RecursoInventario> recursosTrocados1 = new ArrayList<>();
	
	@ManyToMany
	private List<RecursoInventario> recursosTrocados2 = new ArrayList<>();
	*/
	
	public Negociacao() {}

	public Negociacao(Long id, Long hospital1, Long hospital2, OffsetDateTime data, String info1, String info2) {
		super();
		this.id = id;
		this.hospital1Id = hospital1;
		this.hospital2Id = hospital2;
		this.data = data;
		this.infoItensHospital1 = info1;
		this.infoItensHospital2 = info2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHospital1Id() {
		return hospital1Id;
	}

	public void setHospital1(Long hospital1) {
		this.hospital1Id = hospital1;
	}

	public Long getHospital2Id() {
		return hospital2Id;
	}

	public void setHospital2(Long hospital2) {
		this.hospital2Id = hospital2;
	}

	public OffsetDateTime getData() {
		return data;
	}

	public void setData(OffsetDateTime data) {
		this.data = data;
	}

	public void setHospital1Id(Long hospital1Id) {
		this.hospital1Id = hospital1Id;
	}

	public void setHospital2Id(Long hospital2Id) {
		this.hospital2Id = hospital2Id;
	}

	public String getInfoItensHospital1() {
		return infoItensHospital1;
	}

	public void setInfoItensHospital1(String infoItensHospital1) {
		this.infoItensHospital1 = infoItensHospital1;
	}

	public String getInfoItensHospital2() {
		return infoItensHospital2;
	}

	public void setInfoItensHospital2(String infoItensHospital2) {
		this.infoItensHospital2 = infoItensHospital2;
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
		Negociacao other = (Negociacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
