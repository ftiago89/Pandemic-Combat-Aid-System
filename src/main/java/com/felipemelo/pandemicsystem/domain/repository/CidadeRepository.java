package com.felipemelo.pandemicsystem.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipemelo.pandemicsystem.domain.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{
	
	/*Para ser usado no serviço de cadastro de hospitais, para que
	 * uma mesma cidade não seja inserida novamente no banco.*/
	public Cidade findByNome(String nome);

}
