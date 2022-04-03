package br.com.andersillva.trabfinal.api.dto;

import java.time.LocalDate;

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
	private LocalDate dataInicio;

	@NotNull
	private LocalDate dataFim;

}
