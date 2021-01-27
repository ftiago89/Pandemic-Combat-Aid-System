package com.felipemelo.pandemicsystem.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felipemelo.pandemicsystem.domain.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long>{

}
