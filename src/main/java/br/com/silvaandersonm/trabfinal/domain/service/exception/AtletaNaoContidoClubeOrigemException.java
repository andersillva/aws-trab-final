package br.com.silvaandersonm.trabfinal.domain.service.exception;

public class AtletaNaoContidoClubeOrigemException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String MENSAGEM_PADRAO = "O atleta não pertence ao clube de origem informado.";
	
	public AtletaNaoContidoClubeOrigemException() {
        super(MENSAGEM_PADRAO);
    }

	public AtletaNaoContidoClubeOrigemException(String mensagem) {
        super(mensagem);
    }

}
