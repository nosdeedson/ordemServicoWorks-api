package br.com.edson.ordemServicoWorksapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.edson.ordemServicoWorksapi.exception.BusinessException;
import br.com.edson.ordemServicoWorksapi.model.Cliente;
import br.com.edson.ordemServicoWorksapi.repository.ClienteRepository;

@Service
public class CadastroClienteService {
	@Autowired
	ClienteRepository clienteRepository;
	
	public Cliente atualizar( Long clienteId, Cliente cliente) {
		if( !clienteRepository.existsById(clienteId)) {
			return null;
		}
		cliente.setId(clienteId);
		return clienteRepository.save(cliente);
	}
	
	public Optional<Cliente> buscar(Long clienteId) {
		return clienteRepository.findById(clienteId);
	}
	
	public void deletar(Long clienteId) throws Exception {
		
		if( !clienteRepository.existsById(clienteId))
			return;
		try {
			clienteRepository.deleteById(clienteId);
		} catch (Exception e) {
			throw new Exception("Falha ao deletar");
		}
		
	}
	
	public Cliente salvar(Cliente cliente) {
		Optional<Cliente> existe = clienteRepository.findByEmail(cliente.getEmail());
		if (existe.isPresent() && cliente.getEmail().equals(existe.get().getEmail())){
			throw new BusinessException("Email já usado.");
		}
		return clienteRepository.save(cliente);
	}

	public List<Cliente> todos() {
		return clienteRepository.findAll();
	}
}
