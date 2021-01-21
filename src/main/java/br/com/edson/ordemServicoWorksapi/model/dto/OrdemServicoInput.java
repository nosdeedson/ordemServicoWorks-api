package br.com.edson.ordemServicoWorksapi.model.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.edson.ordemServicoWorksapi.model.OrdemServico;

public class OrdemServicoInput {
	
	@NotBlank
	private String descricao;
	
	@NotNull
	private BigDecimal preco;
	
	@NotNull
	private Long idCliente;
	
	public OrdemServicoInput() {
		super();
	}

	public OrdemServicoInput(String descricao, BigDecimal preco, Long idCliente) {
		super();
		this.descricao = descricao;
		this.preco = preco;
		this.idCliente = idCliente;
	}
	
	public OrdemServico toEntity( OrdemServicoInput ordemInput) {
		OrdemServico ordemServico = new OrdemServico();
		ordemServico.setDescricao(ordemInput.getDescricao());
		ordemServico.setPreco(ordemInput.getPreco());
		return ordemServico;
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

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	
	
	
}
