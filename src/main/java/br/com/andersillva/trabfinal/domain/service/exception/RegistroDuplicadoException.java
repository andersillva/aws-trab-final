package br.com.andersillva.trabfinal.domain.service.exception;

public class RegistroDuplicadoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String MENSAGEM_PADRAO = "Registro já existe.";

	public RegistroDuplicadoException() {
        super(MENSAGEM_PADRAO);
    }

	public RegistroDuplicadoException(String mensagem) {
        super(mensagem);
    }

}
