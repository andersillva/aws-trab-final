package br.com.silvaandersonm.trabfinal.api.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ClubeDTO {

	private Long id;

	private String nome;

	private String cidade;

	private String uf;

	private Date dataFundacao;

	private String estadio;

}
