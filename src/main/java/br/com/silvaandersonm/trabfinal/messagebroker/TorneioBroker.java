package br.com.silvaandersonm.trabfinal.messagebroker;

import br.com.silvaandersonm.trabfinal.domain.model.Torneio;

public interface TorneioBroker {

	public void gerarMensagemInclusao(Torneio torneio);

	public void gerarMensagemAlteracao(Torneio transftorneioerencia);

	public void gerarMensagemExclusao(Torneio torneio);

}
