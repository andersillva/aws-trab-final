package br.com.silvaandersonm.trabfinal.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TransferenciaAlteracaoDTO {

	@NotNull
	private LocalDate data;

	@NotNull
	private BigDecimal valor;

	@NotBlank
	private String moeda;

}
