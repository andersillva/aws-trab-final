package br.com.silvaandersonm.trabfinal.api.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ParticipanteAlteracaoDTO {

	@NotBlank
	private String situacao;

	private Integer pontuacao;
}
