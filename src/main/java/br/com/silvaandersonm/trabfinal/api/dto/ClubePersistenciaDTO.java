package br.com.silvaandersonm.trabfinal.api.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ClubePersistenciaDTO {

	@NotBlank
	private String nome;

	@NotBlank
	private String cidade;

	@NotBlank
	private String uf;

	@NotNull
	private Date dataFundacao;

	private String estadio;

}
