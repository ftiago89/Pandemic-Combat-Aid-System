package com.felipemelo.pandemicsystem.domain.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipemelo.pandemicsystem.domain.model.Cidade;
import com.felipemelo.pandemicsystem.domain.model.Estado;
import com.felipemelo.pandemicsystem.domain.repository.CidadeRepository;
import com.felipemelo.pandemicsystem.domain.repository.EstadoRepository;

@Service
public class DBService {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public void instantiateTestDB() {
		
		Estado est1 = new Estado(null, "Paraíba");
		Cidade cid1 = new Cidade(null, "João pessoa", est1);
		Cidade cid2 = new Cidade(null, "Guarabira", est1);
		
		est1.getCidades().addAll(Arrays.asList(cid1, cid2));
		
		estadoRepository.save(est1);
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2));
	}

}
