package br.com.silvaandersonm.trabfinal.api.dto;

import javax.validation.constraints.NotNull;

import br.com.silvaandersonm.trabfinal.domain.enumerator.SituacaoParticipante;
import lombok.Data;

@Data
public class ParticipanteAlteracaoDTO {

	@NotNull
	private SituacaoParticipante situacao;

	private Integer pontuacao;
}
