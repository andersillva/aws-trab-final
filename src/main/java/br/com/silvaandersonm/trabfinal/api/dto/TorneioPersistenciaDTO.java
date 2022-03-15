package br.com.silvaandersonm.trabfinal.api.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TorneioPersistenciaDTO {

	@NotBlank
	private String nome;

	@NotNull
	private Integer ano;

	@NotNull
	private Date dataInicio;

	@NotNull
	private Date dataFim;

}
