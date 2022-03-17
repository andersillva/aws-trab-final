package br.com.silvaandersonm.trabfinal.messagebroker.dto;

import br.com.silvaandersonm.trabfinal.domain.enumerator.SituacaoParticipante;
import lombok.Data;

@Data
public class ParticipanteMensagemDTO {

	private Long id;

	private Long idClube;

	private SituacaoParticipante situacao;

	private Integer pontuacao;

	private Integer idTorneio;

}

