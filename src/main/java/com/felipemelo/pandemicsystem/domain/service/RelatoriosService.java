package com.felipemelo.pandemicsystem.domain.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipemelo.pandemicsystem.domain.model.Hospital;
import com.felipemelo.pandemicsystem.domain.model.Negociacao;
import com.felipemelo.pandemicsystem.domain.model.Ocupacao;
import com.felipemelo.pandemicsystem.domain.model.RecursoInventario;
import com.felipemelo.pandemicsystem.domain.repository.HospitalRepository;
import com.felipemelo.pandemicsystem.domain.repository.NegociacaoRepository;
import com.felipemelo.pandemicsystem.domain.repository.OcupacaoRepository;
import com.felipemelo.pandemicsystem.domain.repository.RecursoInventarioRepository;

/*Realiza os cálculos para a geração dos relatórios. Os resultados são passados como string
 * para compor os relatórios no controller.*/

@Service
public class RelatoriosService {
	
	@Autowired
	private OcupacaoRepository ocupacaoRepository;
	
	@Autowired
	private RecursoInventarioRepository recursoInventarioRepository;
	
	@Autowired
	private HospitalRepository hospitalRepository;
	
	@Autowired
	private NegociacaoRepository negociacaoRepository;

	public String hospitaisMaiorQue90() {
		List<Ocupacao> ocupacoes = ocupacaoRepository.findAll();
		int contador = 0;
		
		for (Ocupacao oc: ocupacoes) {
			if (oc.getPercentual() >= 90.0) {
				++contador;
			}
		}
		return "Atualmente, "  + (((double)contador/ocupacoes.size())*100) + "% dos hospitais estão com ocupação maior ou igual a 90%";
	}
	
	public String hospitaisMenorQue90() {
		List<Ocupacao> ocupacoes = ocupacaoRepository.findAll();
		int contador = 0;
		
		for (Ocupacao oc: ocupacoes) {
			if (oc.getPercentual() < 90.0) {
				++contador;
			}
		}
		return "Atualmente, "  + (((double)contador/ocupacoes.size())*100) + "% dos hospitais estão com ocupação menor que 90%";
	}
	
	public List<String> recursosMedia(){
		List<RecursoInventario> recursos = recursoInventarioRepository.findAll();
		List<Hospital> hospitais = hospitalRepository.findAll();
		List<String> resultados = new ArrayList<>();
		int totalMedicos = 0;
		int totalEnfermeiros = 0;
		int totalRespiradores = 0;
		int totalTomografos = 0;
		int totalAmbulancias = 0;
		
		for (RecursoInventario ri: recursos) {
			switch (ri.getRecurso().getTipo()) {
			case MEDICO:
				++totalMedicos;
				break;
			case ENFERMEIRO:
				++totalEnfermeiros;
				break;
			case RESPIRADOR:
				++totalRespiradores;
				break;
			case TOMOGRAFO:
				++totalTomografos;
				break;
			case AMBULANCIA:
				++totalAmbulancias;
				break;
			}
		}
		resultados.add("Atualmente, existem " + ((double)totalMedicos/hospitais.size()) + " Médicos por hospital.");
		resultados.add("Atualmente, existem " + ((double)totalEnfermeiros/hospitais.size()) + " Enfermeiros por hospital.");
		resultados.add("Atualmente, existem " + ((double)totalRespiradores/hospitais.size()) + " Respiradores por hospital.");
		resultados.add("Atualmente, existem " + ((double)totalTomografos/hospitais.size()) + " Tomógrafos por hospital.");
		resultados.add("Atualmente, existem " + ((double)totalAmbulancias/hospitais.size()) + " Ambulâncias por hospital.");
		
		return resultados;
	}
	
	public String hospitalMaisCritico() {
		List<Ocupacao> ocupacoes = ocupacaoRepository.findAll();
		Ocupacao maisAntiga = new Ocupacao();
		OffsetDateTime dataAux = OffsetDateTime.now();
		
		for (Ocupacao oc: ocupacoes) {
			if ((oc.getPercentual() > 90.0) && (oc.getData().isBefore(dataAux))) {
				maisAntiga = oc;
				dataAux = oc.getData();
			}
		}
		
		return "O hospital há mais tempo em situação crítica é o hospital " + maisAntiga.getHospital().getNome() + ". Localizado na cidade"
				+ " de " + maisAntiga.getHospital().getEndereco().getCidade().getNome() + " do Estado " + maisAntiga.getHospital().getEndereco().getCidade().getEstado().getNome()+
				". Sua porcentagem de ocupação atual é de: " + maisAntiga.getPercentual() + "%";
	}
	
	public String hospitalMenosCritico() {
		List<Ocupacao> ocupacoes = ocupacaoRepository.findAll();
		Ocupacao maisAntiga = new Ocupacao(null, 100.0, null);
		OffsetDateTime dataAux = OffsetDateTime.now();
		
		for (Ocupacao oc: ocupacoes) {
			if ((oc.getPercentual() < maisAntiga.getPercentual()) && (oc.getData().isBefore(dataAux))) {
				maisAntiga = oc;
				dataAux = oc.getData();
			}
		}
		
		return "O hospital há mais tempo em situação menos crítica é o hospital " + maisAntiga.getHospital().getNome() + ". Localizado na cidade"
				+ " de " + maisAntiga.getHospital().getEndereco().getCidade().getNome() + " do Estado " + maisAntiga.getHospital().getEndereco().getCidade().getEstado().getNome()+
				". Sua porcentagem de ocupação atual é de: " + maisAntiga.getPercentual() + "%";
	}
	
	public List<String> historicoNegociacoes() {
		List<Negociacao> negociacoes = negociacaoRepository.findAll();
		List<String> historico = new ArrayList<>();
		
		for (Negociacao ng: negociacoes) {
			String aux = "Negociacao ID: " + ng.getId() + " Realizada na data: " + ng.getData() + " " +
					ng.getInfoItensHospital1() + ng.getInfoItensHospital2();
			historico.add(aux);
		}
		return historico;
	}
}
