package br.com.silvaandersonm.trabfinal.messagebroker;

import br.com.silvaandersonm.trabfinal.domain.model.Atleta;

public interface AtletaBroker {

	public void gerarMensagemInclusao(Atleta atleta);

	public void gerarMensagemAlteracao(Atleta atleta);

	public void gerarMensagemExclusao(Atleta atleta);

}
