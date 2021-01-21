package br.com.edson.ordemServicoWorksapi.model.dto;

import javax.validation.constraints.NotBlank;

public class ComentarioInput {
	
	@NotBlank
	private String descricao;
	
	public ComentarioInput() {
		super();
	}

	public ComentarioInput(String descricao) {
		super();
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
