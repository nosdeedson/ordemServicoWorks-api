package br.com.edson.ordemServicoWorksapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.edson.ordemServicoWorksapi.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	List<Cliente> findByNome(String nome);
	
	List<Cliente> findByNomeContaining(String padrao);
	
	Optional<Cliente> findByEmail(String email);
}
