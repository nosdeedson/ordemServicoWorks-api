package br.com.edson.ordemServicoWorksapi.model.dto;

public class ClienteDTO {
	
	private Long id;
	private String nome;
	
	ClienteDTO(){}
	
	public ClienteDTO(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
