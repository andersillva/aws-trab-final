package br.com.silvaandersonm.trabfinal.api.dto;

import lombok.Data;

@Data
public class TorneioParticipanteDTO {

	private Long id;

	private ClubeResumoDTO clube;

	private String situacao;

	private Integer pontuacao;

}
