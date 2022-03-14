package br.com.silvaandersonm.trabfinal.api.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TransferenciaAlteracaoDTO {

	@NotNull
	private Date data;

	@NotNull
	private BigDecimal valor;

	@NotBlank
	private String moeda;

}
