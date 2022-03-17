package br.com.silvaandersonm.trabfinal.api.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
public class AtletaDTO {

	private Long id;

	private String nome;

	private String nomeCompleto;

	private LocalDate dataNascimento;

	private String cidadeNascimento;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String ufNascimento;

	private String paisNascimento;

	private Float altura;

	private ClubeResumoDTO clube;

}
