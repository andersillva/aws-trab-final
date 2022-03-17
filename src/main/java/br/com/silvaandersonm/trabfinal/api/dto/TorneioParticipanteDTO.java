package br.com.silvaandersonm.trabfinal.api.dto;

import br.com.silvaandersonm.trabfinal.domain.enumerator.SituacaoParticipante;
import lombok.Data;

@Data
public class TorneioParticipanteDTO {

	private Long id;

	private ClubeResumoDTO clube;

	private SituacaoParticipante situacao;

	private Integer pontuacao;

}
