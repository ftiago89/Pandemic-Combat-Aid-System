package com.felipemelo.pandemicsystem.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipemelo.pandemicsystem.domain.model.Negociacao;

public interface NegociacaoRepository extends JpaRepository<Negociacao, Long>{

}
