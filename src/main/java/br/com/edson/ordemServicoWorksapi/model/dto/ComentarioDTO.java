package br.com.edson.ordemServicoWorksapi.model.dto;

import java.time.OffsetDateTime;

public class ComentarioDTO {
	
	private Long id;
	private String descricao;
	private OffsetDateTime dataEnvio;
	
	
	public ComentarioDTO() {
		super();
	}

	public ComentarioDTO(Long id, String descricao, OffsetDateTime dataEnvio) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.dataEnvio = dataEnvio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public OffsetDateTime getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(OffsetDateTime dataEnvio) {
		this.dataEnvio = dataEnvio;
	}
	
	

}
