package br.com.silvaandersonm.trabfinal.util;

public enum BusinessExceptionType {

	PARAMETROS_OBRIGATORIOS_NAO_INFORMADOS("001", "Parâmetros de entrada obrigatórios não informados."),
	REGISTRO_NAO_ENCONTRADO("002", "Registro não encontrado.");

    private String code;
	
    private String message;

    BusinessExceptionType(String code, String message) {
        this.code = code;
        this.message = message;
    }

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
