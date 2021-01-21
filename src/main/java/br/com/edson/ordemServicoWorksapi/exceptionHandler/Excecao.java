package br.com.edson.ordemServicoWorksapi.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(content = Include.NON_EMPTY)
public class Excecao {
	
	private Integer status;
	private OffsetDateTime dataHora;
	private String titulo;
	private List<CampoComErro>	campos = new ArrayList<>();
	public Excecao(Integer status, OffsetDateTime dataHora, String titulo) {
		super();
		this.status = status;
		this.dataHora = dataHora;
		this.titulo = titulo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public OffsetDateTime getDataHora() {
		return dataHora;
	}
	public void setDataHora(OffsetDateTime dataHora) {
		this.dataHora = dataHora;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public List<CampoComErro> getCampos() {
		return campos;
	}
	public void setCampos(List<CampoComErro> campos) {
		this.campos = campos;
	}
	
	

}
