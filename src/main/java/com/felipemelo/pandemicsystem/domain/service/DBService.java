package com.felipemelo.pandemicsystem.domain.service;

import java.time.OffsetDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipemelo.pandemicsystem.domain.model.Cidade;
import com.felipemelo.pandemicsystem.domain.model.Endereco;
import com.felipemelo.pandemicsystem.domain.model.Estado;
import com.felipemelo.pandemicsystem.domain.model.Hospital;
import com.felipemelo.pandemicsystem.domain.model.Ocupacao;
import com.felipemelo.pandemicsystem.domain.model.Recurso;
import com.felipemelo.pandemicsystem.domain.model.RecursoInventario;
import com.felipemelo.pandemicsystem.domain.model.enums.TipoRecurso;
import com.felipemelo.pandemicsystem.domain.repository.CidadeRepository;
import com.felipemelo.pandemicsystem.domain.repository.EnderecoRepository;
import com.felipemelo.pandemicsystem.domain.repository.EstadoRepository;
import com.felipemelo.pandemicsystem.domain.repository.HospitalRepository;
import com.felipemelo.pandemicsystem.domain.repository.OcupacaoRepository;
import com.felipemelo.pandemicsystem.domain.repository.RecursoInventarioRepository;
import com.felipemelo.pandemicsystem.domain.repository.RecursoRepository;

@Service
public class DBService {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private OcupacaoRepository ocupacaoRepository;
	
	@Autowired
	private RecursoRepository recursoRepository;
	
	@Autowired
	private RecursoInventarioRepository recursoInventarioRepository;
	
	@Autowired
	private HospitalRepository hospitalRepository;
	
	public void instantiateTestDB() {
		
		Estado est1 = new Estado(null, "Paraíba");
		Cidade c1 = new Cidade(null, "João pessoa", est1);
		Cidade c2 = new Cidade(null, "Guarabira", est1);
		
		est1.getCidades().addAll(Arrays.asList(c1, c2));
		
		estadoRepository.save(est1);
		cidadeRepository.saveAll(Arrays.asList(c1, c2));
		
		Endereco e1 = new Endereco(null, "Rua Sabiniano Maia", 500, "Hospital", "Bairro Novo", "58200-000", c2, 5.45, 8.45);
		Endereco e2 = new Endereco(null, "Rua Beira Rio", 642, "Hospital", "Torre", "58406-053", c1, 5.55, 8.9654);
		
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		Ocupacao oc1 = new Ocupacao(null, 58.0, OffsetDateTime.now());
		Ocupacao oc2 = new Ocupacao(null, 92.0, OffsetDateTime.now());
		
		Hospital h2 = new Hospital(null, "Santa Clara", "56464536415834", oc1);
		Hospital h1 = new Hospital(null, "Unimed", "456432734156", oc2);
		
		h1.setOcupacao(oc2);
		h2.setOcupacao(oc1);
		h1.setEndereco(e2);
		h2.setEndereco(e1);
		
		ocupacaoRepository.saveAll(Arrays.asList(oc1, oc2));
		hospitalRepository.saveAll(Arrays.asList(h1, h2));

		oc1.setHospital(h2);
		System.out.println(oc1.getHospital().getId());
		oc2.setHospital(h1);
		
		Recurso rc1 = new Recurso(null, TipoRecurso.MEDICO);
		Recurso rc2 = new Recurso(null, TipoRecurso.ENFERMEIRO);
		Recurso rc3 = new Recurso(null, TipoRecurso.AMBULANCIA);
		
		recursoRepository.saveAll(Arrays.asList(rc1, rc2, rc3));
		
		RecursoInventario ri1 = new RecursoInventario(h2, rc1, 3);
		RecursoInventario ri2 = new RecursoInventario(h2, rc2, 1);
		RecursoInventario ri3 = new RecursoInventario(h1, rc3, 2);
		RecursoInventario ri4 = new RecursoInventario(h1, rc2, 2);
		
		h1.getRecursos().addAll(Arrays.asList(ri3, ri4));
		h2.getRecursos().addAll(Arrays.asList(ri1, ri2));
		
		recursoInventarioRepository.saveAll(Arrays.asList(ri1, ri2, ri3, ri4));
		
		
	}

}
