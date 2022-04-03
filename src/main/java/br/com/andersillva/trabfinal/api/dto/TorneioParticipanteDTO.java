package br.com.andersillva.trabfinal.api.dto;

import br.com.andersillva.trabfinal.domain.enumerator.SituacaoParticipante;
import lombok.Data;

@Data
public class TorneioParticipanteDTO {

	private Long id;

	private ClubeResumoDTO clube;

	private SituacaoParticipante situacao;

	private Integer pontuacao;

}
