package br.com.edson.ordemServicoWorksapi.model.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import br.com.edson.ordemServicoWorksapi.model.StatusOrdemServicoEnum;

public class OrdemServicoDTO {
	
	private Long ordemId;
	private ClienteDTO cliente;
	private String descricao;
	private BigDecimal preco;
	private StatusOrdemServicoEnum status;
	private OffsetDateTime dataAbertura;
	private OffsetDateTime dataFinalizacao;
	
	
	public OrdemServicoDTO() {}
	
	public OrdemServicoDTO(Long ordemId, String nomeCliente, String descricao, BigDecimal preco,
			StatusOrdemServicoEnum status, OffsetDateTime dataAbertura, OffsetDateTime dataFinalizacao,
			ClienteDTO cliente) {
		super();
		this.ordemId = ordemId;
		this.descricao = descricao;
		this.preco = preco;
		this.status = status;
		this.dataAbertura = dataAbertura;
		this.dataFinalizacao = dataFinalizacao;
		this.cliente = cliente;
	}
	
	public OrdemServicoDTO( String nomeCliente, String descricao, BigDecimal preco,
			StatusOrdemServicoEnum status, OffsetDateTime dataAbertura, OffsetDateTime dataFinalizacao,
			ClienteDTO cliente) {
		super();
		this.descricao = descricao;
		this.preco = preco;
		this.status = status;
		this.dataAbertura = dataAbertura;
		this.dataFinalizacao = dataFinalizacao;
		this.cliente = cliente;
	}


	public Long getOrdemId() {
		return ordemId;
	}
	public void setOrdemId(Long ordemId) {
		this.ordemId = ordemId;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public StatusOrdemServicoEnum getStatus() {
		return status;
	}
	public void setStatus(StatusOrdemServicoEnum status) {
		this.status = status;
	}
	public OffsetDateTime getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(OffsetDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public OffsetDateTime getDataFinalizacao() {
		return dataFinalizacao;
	}
	public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}
	
	
}
