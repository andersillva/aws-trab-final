package br.com.silvaandersonm.trabfinal.api.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AtletaPersistenciaDTO {

	@NotBlank
	private String nome;

	@NotBlank
	private String nomeCompleto;

	@NotNull
	private Date dataNascimento;

	@NotBlank
	private String cidadeNascimento;

	private String ufNascimento;

	@NotBlank
	private String paisNascimento;

	@NotNull
	private Float altura;

}
