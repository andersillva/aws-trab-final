package br.com.silvaandersonm.trabfinal.api.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
public class AtletaClubeDTO {

	private Long id;

	private String nome;

	private String nomeCompleto;

	private Date dataNascimento;

	private String cidadeNascimento;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String ufNascimento;

	private String paisNascimento;

	private Float altura;

}
