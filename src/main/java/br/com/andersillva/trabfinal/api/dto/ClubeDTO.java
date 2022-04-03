package br.com.andersillva.trabfinal.api.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ClubeDTO {

	private Long id;

	private String nome;

	private String cidade;

	private String uf;

	private LocalDate dataFundacao;

	private String estadio;

}
