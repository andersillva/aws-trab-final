package br.com.silvaandersonm.trabfinal.api.exception;

import java.io.Serializable;

import lombok.Data;

@Data
public class RespostaPadraoInsucesso implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer status;
	private String mensagem;

	public RespostaPadraoInsucesso(Integer status, String mensagem) {
		super();
		this.status = status;
		this.mensagem = mensagem;
	}

}
