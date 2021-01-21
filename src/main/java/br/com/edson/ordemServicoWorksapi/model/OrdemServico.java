package br.com.edson.ordemServicoWorksapi.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import br.com.edson.ordemServicoWorksapi.ValidationGroups;
import br.com.edson.ordemServicoWorksapi.exception.BusinessException;

@Entity
@Table(name = "ordem_servico")
public class OrdemServico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
	@NotNull
	@ManyToOne
	private Cliente cliente;
	
	@NotBlank
	private String descricao;
	
	@NotNull
	@Column(precision = 2)
	private BigDecimal preco;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status_ordem_servico")
	@JsonProperty(access = Access.READ_ONLY)
	private StatusOrdemServicoEnum status;
	
	@JsonProperty(access = Access.READ_ONLY)
	@Column(name = "data_abertura")
	private OffsetDateTime dataAbertura;
	
	@JsonProperty(access = Access.READ_ONLY)
	@Column(name = "data_finalizacao")
	private OffsetDateTime dataFinalizacao;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "ordemServico")
	private List<Comentario> comentarios = new ArrayList<>();
	
	public OrdemServico() {
		super();
	}
	
	public OrdemServico(Long id, Cliente cliente, String descricao, BigDecimal preco, StatusOrdemServicoEnum status,
			OffsetDateTime dataAbertura, OffsetDateTime dataFinalizacao) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.descricao = descricao;
		this.preco = preco;
		this.status = status;
		this.dataAbertura = dataAbertura;
		this.dataFinalizacao = dataFinalizacao;
	}

	public boolean naoPodeSerFinalizada() {
		return !StatusOrdemServicoEnum.ABERTA.equals(getStatus());
	}
	
	public void finalizar() {
		if( this.naoPodeSerFinalizada())
			throw new BusinessException("Ordem de serviço não pode ser finalizada.");
		this.setDataFinalizacao(OffsetDateTime.now());
		this.setStatus(StatusOrdemServicoEnum.FINALIZADA);
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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

	
	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdemServico other = (OrdemServico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
