package br.com.edson.ordemServicoWorksapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.edson.ordemServicoWorksapi.model.dto.ComentarioDTO;
import br.com.edson.ordemServicoWorksapi.model.dto.ComentarioInput;
import br.com.edson.ordemServicoWorksapi.service.GestaoOrdemServicoService;

@RestController
@RequestMapping( "ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {
	
	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServico;
	
	@PostMapping
	@ResponseStatus( value = HttpStatus.CREATED)
	public ResponseEntity<?> adicionar( @RequestBody ComentarioInput comentarioInput, //
			@Valid @PathVariable Long ordemServicoId) {
		ComentarioDTO comentarioDTO = gestaoOrdemServico.adicionarComentario(ordemServicoId, comentarioInput.getDescricao());
		return ResponseEntity.ok(comentarioDTO);
	}
	
	
	@GetMapping
	public ResponseEntity<?> listarComentario( @PathVariable Long ordemServicoId){
		List<ComentarioDTO> comentarios = gestaoOrdemServico.listarComentarios(ordemServicoId);
		if( comentarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(comentarios);
	}

}
