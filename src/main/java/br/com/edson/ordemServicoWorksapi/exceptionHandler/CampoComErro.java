package br.com.edson.ordemServicoWorksapi.exceptionHandler;

public class CampoComErro {

	private String campo;
	private String message;
	
	public CampoComErro(String campo, String message) {
		super();
		this.campo = campo;
		this.message = message;
	}
	public String getCampo() {
		return campo;
	}
	public void setNome(String campo) {
		this.campo = campo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
