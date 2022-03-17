package br.com.silvaandersonm.trabfinal.messagebroker;

import br.com.silvaandersonm.trabfinal.domain.model.Partida;

public interface PartidaBroker {

	public void gerarMensagemInclusao(Partida partida);

	public void gerarMensagemAlteracao(Partida partida);

	public void gerarMensagemExclusao(Partida partida);

}
