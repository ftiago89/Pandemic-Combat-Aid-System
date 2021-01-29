package com.felipemelo.pandemicsystem.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipemelo.pandemicsystem.domain.model.Recurso;

public interface RecursoRepository extends JpaRepository<Recurso, Long>{
	
	/*Para ser usado no serviços de cadastro e negociação, para que
	 * um mesmo tipo de recurso não seja inserido novamente no banco.*/
	public Recurso findByTipo(Integer tipo);

}
