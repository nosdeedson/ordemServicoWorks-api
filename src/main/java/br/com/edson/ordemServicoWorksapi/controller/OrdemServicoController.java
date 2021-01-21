package br.com.edson.ordemServicoWorksapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.edson.ordemServicoWorksapi.exception.BusinessException;
import br.com.edson.ordemServicoWorksapi.model.OrdemServico;
import br.com.edson.ordemServicoWorksapi.model.dto.OrdemServicoDTO;
import br.com.edson.ordemServicoWorksapi.model.dto.OrdemServicoInput;
import br.com.edson.ordemServicoWorksapi.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {
	
	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServicoService;
	
	@PostMapping
	@ResponseStatus( value = HttpStatus.CREATED)
	public ResponseEntity<?> criar(@Valid @RequestBody OrdemServicoInput ordem){
		OrdemServicoDTO ordemDTO = gestaoOrdemServicoService.adicionar(ordem);
		return ResponseEntity.status(HttpStatus.CREATED).body(ordemDTO);
	}
	
	@GetMapping("{ordemId}")
	public ResponseEntity<?> busca(@PathVariable Long ordemId){
		OrdemServicoDTO ordem = gestaoOrdemServicoService.buscar(ordemId);
		return ResponseEntity.ok(ordem);
	}
	
	@PutMapping("{ordemServicoId}/finalizacao")
	public ResponseEntity<?> finalizar(@PathVariable Long ordemServicoId){
		OrdemServico ordem = gestaoOrdemServicoService.finalizarOrdemServico(ordemServicoId);
		if( Optional.ofNullable(ordem).isPresent())
			return ResponseEntity.ok(ordem);
		throw new BusinessException("Ordem não salva tente novamente");
	}
	
	@GetMapping
	public ResponseEntity<?> todas(){
		List<OrdemServicoDTO> lista = gestaoOrdemServicoService.todas();
		if( lista.isEmpty()) {
			return ResponseEntity.ok("Não há ordem de serviço");
		}
		return ResponseEntity.ok(lista);
	}
	
}
