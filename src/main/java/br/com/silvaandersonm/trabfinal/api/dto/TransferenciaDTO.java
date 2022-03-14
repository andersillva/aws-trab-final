package br.com.silvaandersonm.trabfinal.api.dto;

import java.util.Date;

import lombok.Data;

@Data
public class TransferenciaDTO {

	private Long id;

	private ClubeResumoDTO clubeOrigem;
	
	private ClubeResumoDTO clubeDestino;
	
	private AtletaResumoDTO atleta;

	private Date data;

	private Double valor;

	private String moeda;

}
