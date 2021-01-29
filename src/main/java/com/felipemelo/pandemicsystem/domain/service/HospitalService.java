package com.felipemelo.pandemicsystem.domain.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.felipemelo.pandemicsystem.api.model.InputHospitalDto;
import com.felipemelo.pandemicsystem.api.model.OcupacaoInput;
import com.felipemelo.pandemicsystem.api.model.OutputHospitalDto;
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

/*Realiza a busca, inserção e update de ocupação de um hospital.
 * 
 * Decidi importar os objetos do modelo de representação para cá, com
 * a finalidade de não ter tantos objetos do dominio na camada da api.*/

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
	
	
	public List<OutputHospitalDto> findAll(){
		return toDtoCollection(hospitalRepository.findAll());
	}
	
	public Hospital find(Long idHospital) {
		Optional<Hospital> obj = hospitalRepository.findById(idHospital);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + idHospital));
	}

	/*é necessário adicionar um hospital junto com seus itens de inventario e a sua ocupação atual*/
	public OutputHospitalDto insertHospital(InputHospitalDto hospitalDto) {
		
		Hospital hospital = fromDto(hospitalDto);

		/*É importante que os objetos sejam salvos nessa ordem para prevenir
		 * algum erro na inserção dos mesmos no banco.*/
		estadoRepository.save(hospital.getEndereco().getCidade().getEstado());
		cidadeRepository.save(hospital.getEndereco().getCidade());
		enderecoRepository.save(hospital.getEndereco());
		ocupacaoRepository.save(hospital.getOcupacao());
		
		for (RecursoInventario ri: hospital.getRecursos()) {
			ri.setHospital(hospital);
			recursoRepository.save(ri.getRecurso());
			recursoInventarioRepository.save(ri);
		}
		
		hospitalRepository.save(hospital);
		return toDto(hospital);
	}
	
	/*Quando uma ocupação nova é atualizada, é feito o teste para saber se o hospital está
	 * cheio = 100% de capacidade.*/
	public OutputHospitalDto updateOcupacao(Long idHospital, OcupacaoInput novaOcupacao) {
		Hospital hospital = find(idHospital);
		Ocupacao ocupacao = new Ocupacao(null, novaOcupacao.getPercentualAtualizado(), OffsetDateTime.now());
		hospital.setOcupacao(ocupacao);
		if (novaOcupacao.getPercentualAtualizado() == 100.0) {
			hospital.setCheio(true);
		}
		ocupacao.setHospital(hospital);
		
		ocupacaoRepository.save(ocupacao);
		hospitalRepository.save(hospital);
		
		return toDto(hospital);
	}
	
	//método feito para que não seja necessário importar o repository no controller.
	public boolean existsById(Long idHospital) {
		if (hospitalRepository.existsById(idHospital)) {
			return true;
		}
		return false;
	}
	
	/*Transferência entre o modelo de domínio e o modelo de representação é relizada por esses métodos*/
	
	public OutputHospitalDto toDto(Hospital hospital) {
		List<String> cidadeEstadoDto = toCidadeDto(hospital.getEndereco().getCidade());
		OutputHospitalDto hospitalDto = new OutputHospitalDto(hospital.getId(), hospital.getNome(), hospital.getCnpj(),
				hospital.getOcupacao().getPercentual(), toRecursoInventarioDto(hospital.getRecursos()), hospital.getEndereco().getLogradouro(),
				hospital.getEndereco().getNumero(), hospital.getEndereco().getComplemento(), hospital.getEndereco().getBairro(),
				hospital.getEndereco().getCep(), hospital.getEndereco().getLatitude(), hospital.getEndereco().getLongitude(),
				cidadeEstadoDto.get(0), cidadeEstadoDto.get(1), hospital.getCheio());
		return hospitalDto;
	}
	
	public List<OutputHospitalDto> toDtoCollection(List<Hospital> hospitais){
		List<OutputHospitalDto> hospitaisDto = new ArrayList<>();
		for (Hospital h: hospitais) {
			hospitaisDto.add(toDto(h));
		}
		return hospitaisDto;
	}
	
	public Hospital fromDto(InputHospitalDto hospitalDto) {
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
	
	public List<RecursoInventario> fromRecursoInventarioDto(InputHospitalDto hospitalDto){
		List<RecursoInventario> recursos = new ArrayList<>();
		
		for (RecursoInventarioDto rid: hospitalDto.getRecursos()) {
			recursos.add(new RecursoInventario(null, fromRecursoDto(rid.getTipo()), rid.getQuantidade()));
		}
		return recursos;
	}
	
	public Recurso fromRecursoDto(TipoRecurso tipo) {
		/*Caso um tipo de recurso já exista, é feita uma busca para não
		 * precisar cadastrar o mesmo tipo de recurso mais de uma vez.*/
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
	
	public Cidade fromCidadeDto(InputHospitalDto hospitalDto) {
		Cidade cidade = cidadeRepository.findByNome(hospitalDto.getCidade());
		Estado estado = estadoRepository.findByNome(hospitalDto.getEstado());
		
		if(estado == null) {
			estado = new Estado(null, hospitalDto.getEstado());
		}
		
		/*Duas cidades podem ter o mesmo nome em estados diferentes, então
		 * se a cidade já existir no banco de dados, mas o estado for diferente,
		 * esta cidade é criada como uma nova.*/
		if (cidade == null) {
			cidade = new Cidade(null, hospitalDto.getCidade(), estado);
		}else {
			if (!(cidade.getEstado().getNome().equals(estadoRepository.findByNome(estado.getNome())))) {
				cidade = new Cidade(null, hospitalDto.getCidade(), estado);
			}
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
