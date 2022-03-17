package br.com.silvaandersonm.trabfinal.messagebroker;

import br.com.silvaandersonm.trabfinal.domain.model.Transferencia;

public interface TransferenciaBroker {

	public void gerarMensagemInclusao(Transferencia transferencia);

	public void gerarMensagemAlteracao(Transferencia transferencia);

	public void gerarMensagemExclusao(Transferencia transferencia);

}
