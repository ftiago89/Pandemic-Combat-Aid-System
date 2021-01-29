package com.felipemelo.pandemicsystem.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipemelo.pandemicsystem.domain.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long>{
	
	/*Para ser usado no serviço de cadastro de hospitais, para que
	 * um mesmo estado não seja inserido novamente no banco.*/
	public Estado findByNome(String Nome);

}
