package br.com.silvaandersonm.trabfinal.domain.service.exception;

public class IntegridadeReferencialException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String MENSAGEM_PADRAO = "Registro não pode ser excluído, pois possui dependências.";

	public IntegridadeReferencialException() {
        super(MENSAGEM_PADRAO);
    }

	public IntegridadeReferencialException(String mensagem) {
        super(mensagem);
    }

}
