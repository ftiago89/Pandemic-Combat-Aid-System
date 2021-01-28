package com.felipemelo.pandemicsystem.domain.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipemelo.pandemicsystem.api.model.HospitalDto;
import com.felipemelo.pandemicsystem.api.model.OcupacaoInput;
import com.felipemelo.pandemicsystem.domain.model.Cidade;
import com.felipemelo.pandemicsystem.domain.model.Endereco;
import com.felipemelo.pandemicsystem.domain.model.Hospital;
import com.felipemelo.pandemicsystem.domain.model.Ocupacao;
import com.felipemelo.pandemicsystem.domain.model.RecursoInventario;
import com.felipemelo.pandemicsystem.domain.model.exception.ObjectNotFoundException;
import com.felipemelo.pandemicsystem.domain.repository.EnderecoRepository;
import com.felipemelo.pandemicsystem.domain.repository.HospitalRepository;
import com.felipemelo.pandemicsystem.domain.repository.OcupacaoRepository;
import com.felipemelo.pandemicsystem.domain.repository.RecursoInventarioRepository;
import com.felipemelo.pandemicsystem.domain.repository.RecursoRepository;

@Service
public class HospitalService {
	
	@Autowired
	private HospitalRepository hospitalRepository;
	
	@Autowired
	private RecursoRepository recursoRepository;
	
	@Autowired
	private RecursoInventarioRepository recursoInventarioRepository;
	
	@Autowired
	private OcupacaoRepository ocupacaoRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	
	public List<Hospital> findAll(){
		return hospitalRepository.findAll();
	}
	
	public Hospital find(Long idHospital) {
		Optional<Hospital> obj = hospitalRepository.findById(idHospital);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + idHospital));
	}

	/*é necessário adicionar um hospital junto com seus itens de inventario e a sua ocupação atual*/
	public Hospital insertHospital(Hospital hospital) {

		enderecoRepository.save(hospital.getEndereco());
		ocupacaoRepository.save(hospital.getOcupacao());
		
		for (RecursoInventario x: hospital.getRecursos()) {
			x.setHospital(hospital);
			recursoRepository.save(x.getRecurso());
			recursoInventarioRepository.save(x);
		}
		hospitalRepository.save(hospital);
		return hospital;
	}
	
	public Hospital updateOcupacao(Long idHospital, OcupacaoInput novaOcupacao) {
		Hospital hospital = find(idHospital);
		Ocupacao ocupacao = new Ocupacao(null, novaOcupacao.getPercentualAtualizado(), OffsetDateTime.now());
		hospital.setOcupacao(ocupacao);
		ocupacao.setHospital(hospital);
		
		ocupacaoRepository.save(ocupacao);
		
		return hospitalRepository.save(hospital);
	}
	
	public boolean existsById(Long idHospital) {
		if (hospitalRepository.existsById(idHospital)) {
			return true;
		}
		return false;
	}
	
	//TRANSFERENCIA DE MODEL PARA REPRESENTATION MODEL E VICE VERSA
	
	public HospitalDto toDto(Hospital hospital) {
		HospitalDto hospitalDto = new HospitalDto(hospital.getId(), hospital.getNome(), hospital.getCnpj(),
				hospital.getOcupacao().getPercentual(), hospital.getRecursos(), hospital.getEndereco().getLogradouro(),
				hospital.getEndereco().getNumero(), hospital.getEndereco().getComplemento(), hospital.getEndereco().getBairro(),
				hospital.getEndereco().getCep(), hospital.getEndereco().getLatitude(), hospital.getEndereco().getLongitude(),
				hospital.getEndereco().getCidade().getId());
		return hospitalDto;
	}
	
	public List<HospitalDto> toDtoCollection(List<Hospital> hospitais){
		List<HospitalDto> hospitaisDto = new ArrayList<>();
		for (Hospital h: hospitais) {
			hospitaisDto.add(toDto(h));
		}
		return hospitaisDto;
	}
	
	public Hospital fromDto(HospitalDto hospitalDto) {
		Ocupacao ocupacao = new Ocupacao(null, hospitalDto.getPercentualOcupacao(), OffsetDateTime.now());
		
		Hospital hospital = new Hospital(null, hospitalDto.getNome(), hospitalDto.getCnpj(),
				ocupacao);
		
		hospital.setOcupacao(ocupacao);
		
		Set<RecursoInventario> recursos = hospitalDto.getRecursos();
		
		Cidade cid = new Cidade(hospitalDto.getCidadeId(), null, null);
		
		hospital.setEndereco(new Endereco(null, hospitalDto.getLogradouro(), hospitalDto.getNumero(),
				hospitalDto.getComplemento(), hospitalDto.getBairro(), hospitalDto.getCep(), cid,
				hospitalDto.getLatitude(), hospitalDto.getLongitude()));
		
		hospital.setRecursos(recursos);
		ocupacao.setHospital(hospital);
		
		return hospital;
		
	}
	

}