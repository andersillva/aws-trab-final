package br.com.silvaandersonm.trabfinal.messagebroker;

import br.com.silvaandersonm.trabfinal.domain.model.Evento;

public interface EventoBroker {

	public void gerarMensagemInclusao(Evento evento);

}
