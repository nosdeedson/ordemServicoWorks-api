	package br.com.edson.ordemServicoWorksapi.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.edson.ordemServicoWorksapi.model.Cliente;
import br.com.edson.ordemServicoWorksapi.service.CadastroClienteService;


@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private CadastroClienteService cadastroCliente;
	
	
	@GetMapping
	public List<Cliente> lista() {
		return cadastroCliente.todos();
	}
	
	@GetMapping("{clienteId}")
	public ResponseEntity<?> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cli = cadastroCliente.buscar(clienteId);
		if (cli.isPresent()) {
			return ResponseEntity.ok(cli.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> adicionar (@Valid @RequestBody Cliente cliente){
		Cliente retorno = cadastroCliente.salvar(cliente);
		if( retorno != null) {
			return ResponseEntity.ok(retorno);
		}
		return ResponseEntity.ok("Email já usado.");
	}
	
	@PutMapping("{clienteId}")
	public ResponseEntity<?> atualizar(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente){
				
		cliente = cadastroCliente.atualizar(clienteId, cliente);
		if( cliente == null)
			ResponseEntity.noContent().build();
		
		return ResponseEntity.ok(cliente);
		
	}
	
	@DeleteMapping("{clienteId}")
	public ResponseEntity<?> remover(@PathVariable Long clienteId) throws Exception{
		cadastroCliente.deletar(clienteId);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	
	
	
	
}
