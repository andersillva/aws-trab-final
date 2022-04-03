package br.com.andersillva.trabfinal.domain.service.exception;

public class RegistroNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String MENSAGEM_PADRAO = "Registro não encontrado.";
	
	public RegistroNaoEncontradoException() {
        super(MENSAGEM_PADRAO);
    }

	public RegistroNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

}
