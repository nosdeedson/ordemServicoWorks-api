package br.com.edson.ordemServicoWorksapi.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.edson.ordemServicoWorksapi.exception.BusinessException;
import br.com.edson.ordemServicoWorksapi.exception.NaoEncontrado;
import br.com.edson.ordemServicoWorksapi.model.Cliente;
import br.com.edson.ordemServicoWorksapi.model.Comentario;
import br.com.edson.ordemServicoWorksapi.model.OrdemServico;
import br.com.edson.ordemServicoWorksapi.model.StatusOrdemServicoEnum;
import br.com.edson.ordemServicoWorksapi.model.dto.ComentarioDTO;
import br.com.edson.ordemServicoWorksapi.model.dto.OrdemServicoDTO;
import br.com.edson.ordemServicoWorksapi.model.dto.OrdemServicoInput;
import br.com.edson.ordemServicoWorksapi.repository.ClienteRepository;
import br.com.edson.ordemServicoWorksapi.repository.ComentarioRepository;
import br.com.edson.ordemServicoWorksapi.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired(required = true)
	private ModelMapper modelMapper;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	public OrdemServicoDTO adicionar(OrdemServicoInput ordemInput) {
		
		OrdemServico ordemServico = ordemInput.toEntity(ordemInput);
		
		ordemServico.setStatus(StatusOrdemServicoEnum.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		Cliente cliente = clienteRepository.findById(ordemInput.getIdCliente())
				.orElseThrow(() -> new BusinessException("cliente não existe."));
		ordemServico.setCliente(cliente);
		try {
			ordemServico = this.ordemServicoRepository.save(ordemServico);
			return toOrdemServicoDTO(ordemServico);
		} catch (Exception e) {
			throw new BusinessException("falha ao salvar a ordem");
		}
	}
	
	public OrdemServicoDTO buscar(Long ordemId) {
		Optional<OrdemServico> ordem = ordemServicoRepository.findById(ordemId);
		if( ordem.isEmpty())
			throw new NaoEncontrado("Não encontrada");
		return toOrdemServicoDTO(ordem.get());
	}
	
	public OrdemServico finalizarOrdemServico( Long ordemServicoId) {
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow( () -> new NaoEncontrado("Ordem não encontrada."));
		
		ordemServico.finalizar();
		try {
			return ordemServicoRepository.save(ordemServico);
		} catch (Exception e) {
			throw new BusinessException("Falha ao atualizar.");
		}
			
	}
	
	public List<OrdemServicoDTO> todas(){
		List<OrdemServicoDTO> ordensDTO = new ArrayList<>();
		List<OrdemServico> ordens = ordemServicoRepository.findAll();
		
		ordensDTO = toListaOrdemServicoDTO(ordens);
		return ordensDTO;
	}
	
	
	/**
	 * REFERE-SE AOS COMENTARIOS
	 */
	/**
	 *  adicionar comentario
	 * @param ordemServicoId
	 * @param descricao
	 * @return
	 */
	public ComentarioDTO adicionarComentario( Long ordemServicoId, String descricao) {
		
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId).orElseThrow(() -> new NaoEncontrado("Ordem não encotrada."));
		
		Comentario comentario = new Comentario();
		
		comentario.setDataEnvio(OffsetDateTime.now());
		comentario.setDescricao(descricao);
		comentario.setOrdemServico(ordemServico);
		comentario = comentarioRepository.save(comentario);
		
		return toComentarioDTO(comentario);
	}
	
	public List<ComentarioDTO> listarComentarios(Long ordemServicoId){
		
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow( () -> new NaoEncontrado("Ordem servico não encontrada."));
		
		return toListaComentarioDTO(ordemServico.getComentarios());
	}
	
	// metodos privados
	
	/**
	 * transforma ordem serviço em DTO
	 * @return
	 */
	private OrdemServicoDTO toOrdemServicoDTO( OrdemServico ordem) {
		return modelMapper.map(ordem, OrdemServicoDTO.class);
	}
	
	/**
	 * transforma uma lista de ordem de serviço em um lista de dto
	 * @param ordensServico
	 * @return
	 */
	private List<OrdemServicoDTO> toListaOrdemServicoDTO( List<OrdemServico> ordensServico){
		return ordensServico.stream()
				.map(ordemServico -> toOrdemServicoDTO(ordemServico))
				.collect(Collectors.toList());
	}
	
	/**
	 * transforma um comentati em dto
	 * @param comentario
	 * @return
	 */
	private ComentarioDTO toComentarioDTO( Comentario comentario) {
		return modelMapper.map(comentario, ComentarioDTO.class);
	}
	
	private List<ComentarioDTO> toListaComentarioDTO(List<Comentario> comentarios){
		return comentarios.stream().map(comentario -> toComentarioDTO(comentario))
				.collect(Collectors.toList());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
