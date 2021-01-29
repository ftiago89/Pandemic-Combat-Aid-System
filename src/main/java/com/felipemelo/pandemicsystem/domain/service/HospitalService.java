package com.felipemelo.pandemicsystem.domain.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipemelo.pandemicsystem.api.model.HospitalDto;
import com.felipemelo.pandemicsystem.api.model.OcupacaoInput;
import com.felipemelo.pandemicsystem.api.model.RecursoInventarioDto;
import com.felipemelo.pandemicsystem.domain.model.Cidade;
import com.felipemelo.pandemicsystem.domain.model.Endereco;
import com.felipemelo.pandemicsystem.domain.model.Estado;
import com.felipemelo.pandemicsystem.domain.model.Hospital;
import com.felipemelo.pandemicsystem.domain.model.Ocupacao;
import com.felipemelo.pandemicsystem.domain.model.Recurso;
import com.felipemelo.pandemicsystem.domain.model.RecursoInventario;
import com.felipemelo.pandemicsystem.domain.model.enums.TipoRecurso;
import com.felipemelo.pandemicsystem.domain.model.exception.ObjectNotFoundException;
import com.felipemelo.pandemicsystem.domain.repository.CidadeRepository;
import com.felipemelo.pandemicsystem.domain.repository.EnderecoRepository;
import com.felipemelo.pandemicsystem.domain.repository.EstadoRepository;
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
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	
	public List<HospitalDto> findAll(){
		return toDtoCollection(hospitalRepository.findAll());
	}
	
	public Hospital find(Long idHospital) {
		Optional<Hospital> obj = hospitalRepository.findById(idHospital);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + idHospital));
	}

	/*é necessário adicionar um hospital junto com seus itens de inventario e a sua ocupação atual*/
	public HospitalDto insertHospital(HospitalDto hospitalDto) {
		
		Hospital hospital = fromDto(hospitalDto);
		
		

		estadoRepository.save(hospital.getEndereco().getCidade().getEstado());
		cidadeRepository.save(hospital.getEndereco().getCidade());
		enderecoRepository.save(hospital.getEndereco());
		ocupacaoRepository.save(hospital.getOcupacao());
		
		for (RecursoInventario ri: hospital.getRecursos()) {
			ri.setHospital(hospital);
			System.out.println("\n\n\n\npassou\n\n\n\n");
			recursoRepository.save(ri.getRecurso());
			recursoInventarioRepository.save(ri);
		}
		
		hospitalRepository.save(hospital);
		return toDto(hospital);
	}
	
	public HospitalDto updateOcupacao(Long idHospital, OcupacaoInput novaOcupacao) {
		Hospital hospital = find(idHospital);
		System.out.println(novaOcupacao.getPercentualAtualizado());
		Ocupacao ocupacao = new Ocupacao(null, novaOcupacao.getPercentualAtualizado(), OffsetDateTime.now());
		hospital.setOcupacao(ocupacao);
		ocupacao.setHospital(hospital);
		

		
		ocupacaoRepository.save(ocupacao);
		hospitalRepository.save(hospital);
		
		return toDto(hospital);
	}
	
	public boolean existsById(Long idHospital) {
		if (hospitalRepository.existsById(idHospital)) {
			return true;
		}
		return false;
	}
	
	//TRANSFERENCIA DE REPRESENTATION MODEL PARA MODEL E VICE VERSA
	
	public HospitalDto toDto(Hospital hospital) {
		List<String> cidadeEstadoDto = toCidadeDto(hospital.getEndereco().getCidade());
		HospitalDto hospitalDto = new HospitalDto(hospital.getId(), hospital.getNome(), hospital.getCnpj(),
				hospital.getOcupacao().getPercentual(), toRecursoInventarioDto(hospital.getRecursos()), hospital.getEndereco().getLogradouro(),
				hospital.getEndereco().getNumero(), hospital.getEndereco().getComplemento(), hospital.getEndereco().getBairro(),
				hospital.getEndereco().getCep(), hospital.getEndereco().getLatitude(), hospital.getEndereco().getLongitude(),
				cidadeEstadoDto.get(0), cidadeEstadoDto.get(1));
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
		
		List<RecursoInventario> recursos = fromRecursoInventarioDto(hospitalDto);

		Cidade cid = fromCidadeDto(hospitalDto);
		
		hospital.setEndereco(new Endereco(null, hospitalDto.getLogradouro(), hospitalDto.getNumero(),
				hospitalDto.getComplemento(), hospitalDto.getBairro(), hospitalDto.getCep(), cid,
				hospitalDto.getLatitude(), hospitalDto.getLongitude()));
		
		hospital.setRecursos(recursos);
		ocupacao.setHospital(hospital);
		
		return hospital;
		
	}
	
	public List<RecursoInventario> fromRecursoInventarioDto(HospitalDto hospitalDto){
		List<RecursoInventario> recursos = new ArrayList<>();
		
		for (RecursoInventarioDto rid: hospitalDto.getRecursos()) {
			recursos.add(new RecursoInventario(null, fromRecursoDto(rid.getTipo()), rid.getQuantidade()));
		}
		return recursos;
	}
	
	public Recurso fromRecursoDto(TipoRecurso tipo) {
		Recurso recurso = recursoRepository.findByTipo(tipo.getCod());
		if (recurso == null) {
			recurso = new Recurso(null, tipo);
		}
		return recurso;
	}
	
	public List<RecursoInventarioDto> toRecursoInventarioDto(List<RecursoInventario> recursos){
		List<RecursoInventarioDto> recursosDto = new ArrayList<>();
		
		for (RecursoInventario ri: recursos) {
			recursosDto.add(new RecursoInventarioDto(ri.getRecurso().getTipo(), ri.getQuantidade()));
		}
		return recursosDto;
	}
	
	public Cidade fromCidadeDto(HospitalDto hospitalDto) {
		Cidade cidade = cidadeRepository.findByNome(hospitalDto.getCidade());
		Estado estado = estadoRepository.findByNome(hospitalDto.getEstado());
		
		if(estado == null) {
			estado = new Estado(null, hospitalDto.getEstado());
		}
		
		if (cidade == null) {
			cidade = new Cidade(null, hospitalDto.getCidade(), estado);
		}
		return cidade;
	}
	
	public List<String> toCidadeDto(Cidade cidade){
		String cidadeDto = cidade.getNome();
		String estadoDto = cidade.getEstado().getNome();
		List<String> cidadeEstadoDto = new ArrayList<>();
		cidadeEstadoDto.addAll(Arrays.asList(cidadeDto, estadoDto));
		return cidadeEstadoDto;
	}

}
