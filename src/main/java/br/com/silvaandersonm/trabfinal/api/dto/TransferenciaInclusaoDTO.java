package br.com.silvaandersonm.trabfinal.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TransferenciaInclusaoDTO {

	@NotNull
	private Long idClubeOrigem;

	@NotNull
	private Long idClubeDestino;

	@NotNull
	private Long idAtleta;

	@NotNull
	private LocalDate data;

	@NotNull
	private BigDecimal valor;

	@NotBlank
	private String moeda;

}
