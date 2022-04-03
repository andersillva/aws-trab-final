package br.com.andersillva.trabfinal.api.dto;

import lombok.Data;

@Data
public class AtletaResumoDTO {

	private Long id;

	private String nome;

	private ClubeResumoDTO clube;

}
