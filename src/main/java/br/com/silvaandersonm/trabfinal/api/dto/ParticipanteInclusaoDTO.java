package br.com.silvaandersonm.trabfinal.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ParticipanteInclusaoDTO {

	@NotNull
	private Long idClube;

	@NotBlank
	private String situacao;

	private Integer pontuacao;
}
