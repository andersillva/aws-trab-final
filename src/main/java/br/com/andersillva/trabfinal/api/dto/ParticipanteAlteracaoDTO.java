package br.com.andersillva.trabfinal.api.dto;

import javax.validation.constraints.NotNull;

import br.com.andersillva.trabfinal.domain.model.enums.SituacaoParticipante;
import lombok.Data;

@Data
public class ParticipanteAlteracaoDTO {

	@NotNull
	private SituacaoParticipante situacao;

	private Integer pontuacao;
}
