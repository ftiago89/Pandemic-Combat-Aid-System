package com.felipemelo.pandemicsystem.domain.service;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipemelo.pandemicsystem.api.model.InputNegociacaoDto;
import com.felipemelo.pandemicsystem.api.model.RecursoInventarioDto;
import com.felipemelo.pandemicsystem.domain.model.Hospital;
import com.felipemelo.pandemicsystem.domain.model.Negociacao;
import com.felipemelo.pandemicsystem.domain.model.Recurso;
import com.felipemelo.pandemicsystem.domain.model.RecursoInventario;
import com.felipemelo.pandemicsystem.domain.model.exception.NegociacaoInvalidaException;
import com.felipemelo.pandemicsystem.domain.model.exception.ObjectNotFoundException;
import com.felipemelo.pandemicsystem.domain.repository.HospitalRepository;
import com.felipemelo.pandemicsystem.domain.repository.NegociacaoRepository;
import com.felipemelo.pandemicsystem.domain.repository.RecursoInventarioRepository;
import com.felipemelo.pandemicsystem.domain.repository.RecursoRepository;

/*Valida uma negociação entre hospitais
 * 
 * Decidi importar os objetos do modelo de representação para cá, com
 * a finalidade de não ter tantos objetos do dominio na camada da api*/

@Service
public class NegociacaoService {
	
	@Autowired
	private HospitalRepository hospitalRepository;
	
	@Autowired
	private NegociacaoRepository negociacaoRepository;
	
	@Autowired
	private RecursoInventarioRepository recursoInventarioRepository;
	
	@Autowired
	private RecursoRepository recursoRepository;
	
	
	public void validaNegociacao(InputNegociacaoDto input) {
		
		if (!hospitalRepository.existsById(input.getIdHospital1())) {
			throw new ObjectNotFoundException("Primeiro Hospital não cadastrado no sistema!");
		}
		
		if (!hospitalRepository.existsById(input.getIdHospital2())) {
			throw new ObjectNotFoundException("Segundo Hospital não cadastrado no sistema!");
		}
		
		Optional<Hospital> opth1 = hospitalRepository.findById(input.getIdHospital1());
		Optional<Hospital> opth2 = hospitalRepository.findById(input.getIdHospital2());
		
		if ((opth1.isEmpty() || opth2.isEmpty())) throw new ObjectNotFoundException("Hospitais não encontrados.");
		
		Hospital hospital1 = opth1.get();
		Hospital hospital2 = opth2.get();
		
		/*Para tratar a restrição da necessidade de os hospitais ofertarem a mesma quantidade de pontos. Caso um dos
		 * hospitais apresente ocupação maior que 90%, então a negociação é liberada.*/
		if ((input.calculaPontos1() != input.calculaPontos2()) && !(((hospital1.getOcupacao().getPercentual() > 90.0))
				|| ((hospital2.getOcupacao().getPercentual() > 90.0)))) {
			throw new NegociacaoInvalidaException("Negociação inválida! Os hospitais precisam oferecer a mesma"
					+ " quantidade de pontos.");
		}
		
		for (RecursoInventarioDto rnd: input.getRecursosHospital1()) {
			hospital1.retiraRecurso(rnd.getTipo(), rnd.getQuantidade());
		}
		
		for (RecursoInventarioDto rnd: input.getRecursosHospital2()) {
			hospital2.retiraRecurso(rnd.getTipo(), rnd.getQuantidade());
		}
		
		/*Pois se o adicionaRecurso retorna falso, então o hospital não tinha esse
		 * tipo de recurso no seu inventário antes, então ele é adicionado também
		 * no registro do hospital.*/
		for (RecursoInventarioDto rnd: input.getRecursosHospital2()) {
			if (!hospital1.adicionaRecurso(rnd.getTipo(), rnd.getQuantidade())) {
				Recurso recurso = recursoRepository.findByTipo(rnd.getTipo().getCod());
				recursoRepository.save(recurso);
				RecursoInventario recursoInventario = new RecursoInventario(hospital1, recurso, rnd.getQuantidade());
				recursoInventarioRepository.save(recursoInventario);
				hospital1.getRecursos().add(recursoInventario);
			}
		}
		
		for (RecursoInventarioDto rnd: input.getRecursosHospital1()) {
			if (!hospital2.adicionaRecurso(rnd.getTipo(), rnd.getQuantidade())) {
				Recurso recurso = recursoRepository.findByTipo(rnd.getTipo().getCod());
				RecursoInventario recursoInventario = new RecursoInventario(hospital2, recurso, rnd.getQuantidade());
				recursoInventarioRepository.save(recursoInventario);
				hospital2.getRecursos().add(recursoInventario);
			}
		}
		
		/*Optei por salvar as informações da negociação como duas strings,
		 * representando os recursos oferecidos por cada hospital.*/
		String info1 = "Hospital ID: " + hospital1.getId() + " Ofereceu: ";
		String info2 = "Hospital ID: " + hospital2.getId() + " Ofereceu: ";
		
		for (RecursoInventarioDto rnd: input.getRecursosHospital1()) {
			info1 += (rnd.getTipo() + " x" + rnd.getQuantidade() + ". ");
			System.out.println("passou");
		}
		
		for (RecursoInventarioDto rnd: input.getRecursosHospital2()) {
			info2 += (rnd.getTipo() + " x" + rnd.getQuantidade() + ". ");
		}
		
		Negociacao negociacao = new Negociacao(null, hospital1.getId(), hospital2.getId(), OffsetDateTime.now(), info1, info2);
		
		negociacaoRepository.save(negociacao);
		
	}

}
