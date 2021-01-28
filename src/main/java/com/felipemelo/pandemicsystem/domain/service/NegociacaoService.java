package com.felipemelo.pandemicsystem.domain.service;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipemelo.pandemicsystem.api.model.InputNegociacaoDto;
import com.felipemelo.pandemicsystem.api.model.RecursoNegociacaoDto;
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
		
		/*Caso os pontos oferecidos pelos hospitais não sejam equivalentes e nenhum dos hospitais tenha uma
		 * ocupação maior que 90, então a negociacão não é realizada.
		 */
		if ((input.calculaPontos1() != input.calculaPontos2()) && (!((hospital1.getOcupacao().getPercentual() > 90.0))
				|| ((hospital2.getOcupacao().getPercentual() > 90.0)))) {
			throw new NegociacaoInvalidaException("Negociação inválida! Os hospitais precisam oferecer a mesma"
					+ "quantidade de pontos.");
		}
		
		for (RecursoNegociacaoDto rnd: input.getRecursosHospital1()) {
			hospital1.retiraRecurso(rnd.getTipo(), rnd.getQuantidade());
		}
		
		for (RecursoNegociacaoDto rnd: input.getRecursosHospital2()) {
			hospital2.retiraRecurso(rnd.getTipo(), rnd.getQuantidade());
		}
		
		for (RecursoNegociacaoDto rnd: input.getRecursosHospital2()) {
			if (!hospital1.adicionaRecurso(rnd.getTipo(), rnd.getQuantidade())) {
				Recurso recurso = recursoRepository.findByTipo(rnd.getTipo().getCod());
				recursoRepository.save(recurso);
				RecursoInventario recursoInventario = new RecursoInventario(hospital1, recurso, rnd.getQuantidade());
				recursoInventarioRepository.save(recursoInventario);
				hospital1.getRecursos().add(recursoInventario);
			}
		}
		
		for (RecursoNegociacaoDto rnd: input.getRecursosHospital1()) {
			hospital2.adicionaRecurso(rnd.getTipo(), rnd.getQuantidade());
			if (!hospital2.adicionaRecurso(rnd.getTipo(), rnd.getQuantidade())) {
				Recurso recurso = recursoRepository.findByTipo(rnd.getTipo().getCod());
				RecursoInventario recursoInventario = new RecursoInventario(hospital1, recurso, rnd.getQuantidade());
				recursoInventarioRepository.save(recursoInventario);
				hospital2.getRecursos().add(recursoInventario);
			}
		}
		
		String info1 = "Hospital ID: " + hospital1.getId() + " Ofereceu:\n";
		String info2 = "Hospital ID: " + hospital2.getId() + " Ofereceu:\n";
		
		for (RecursoNegociacaoDto rnd: input.getRecursosHospital1()) {
			info1.concat(rnd.getTipo() + " x" + rnd.getQuantidade() + "\n");
		}
		
		for (RecursoNegociacaoDto rnd: input.getRecursosHospital2()) {
			info2.concat(rnd.getTipo() + " x" + rnd.getQuantidade() + "\n");
		}
		
		Negociacao negociacao = new Negociacao(null, hospital1.getId(), hospital2.getId(), OffsetDateTime.now(), info1, info2);
		
		negociacaoRepository.save(negociacao);
		
	}

}
