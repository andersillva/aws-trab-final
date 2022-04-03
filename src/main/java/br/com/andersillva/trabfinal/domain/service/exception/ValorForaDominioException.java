package br.com.andersillva.trabfinal.domain.service.exception;

public class ValorForaDominioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String MENSAGEM_PADRAO = "Valor informado fora do domínio.";

	public ValorForaDominioException() {
        super(MENSAGEM_PADRAO);
    }

	public ValorForaDominioException(String mensagem) {
        super(mensagem);
    }

}
