package br.com.edson.ordemServicoWorksapi.exception;

public class NaoEncontrado extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NaoEncontrado( String msg) {
		super(msg);
	}
}
