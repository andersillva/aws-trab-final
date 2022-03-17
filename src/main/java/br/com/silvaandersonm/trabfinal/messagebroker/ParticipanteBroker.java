package br.com.silvaandersonm.trabfinal.messagebroker;

import br.com.silvaandersonm.trabfinal.domain.model.Participante;

public interface ParticipanteBroker {

	public void gerarMensagemInclusao(Participante participante);

	public void gerarMensagemAlteracao(Participante participante);

	public void gerarMensagemExclusao(Participante participante);

}
