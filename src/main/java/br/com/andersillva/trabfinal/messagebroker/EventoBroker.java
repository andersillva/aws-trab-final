package br.com.andersillva.trabfinal.messagebroker;

import br.com.andersillva.trabfinal.domain.model.Evento;

public interface EventoBroker {

	public void gerarMensagemInclusao(Evento evento);

}
