package br.com.andersillva.trabfinal.domain.service.exception;

public class ParametroRequeridoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static final String MENSAGEM_PADRAO = "Parâmetro(s) obrigatório(s) não informado(s).";

	public ParametroRequeridoException() {
        super(MENSAGEM_PADRAO);
    }

	public ParametroRequeridoException(String mensagem) {
        super(mensagem);
    }

}
