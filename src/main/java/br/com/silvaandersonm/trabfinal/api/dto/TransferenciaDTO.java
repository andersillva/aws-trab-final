package br.com.silvaandersonm.trabfinal.api.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class TransferenciaDTO {

	private Long id;

	private ClubeResumoDTO clubeOrigem;
	
	private ClubeResumoDTO clubeDestino;
	
	private ClubeAtletaDTO atleta;

	private Date data;

	private BigDecimal valor;

	private String moeda;

}
