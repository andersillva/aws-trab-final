package br.com.silvaandersonm.trabfinal.messagebroker;

import br.com.silvaandersonm.trabfinal.domain.model.Clube;

public interface ClubeBroker {

	public void gerarMensagemInclusao(Clube clube);

	public void gerarMensagemAlteracao(Clube clube);

	public void gerarMensagemExclusao(Clube clube);

}
