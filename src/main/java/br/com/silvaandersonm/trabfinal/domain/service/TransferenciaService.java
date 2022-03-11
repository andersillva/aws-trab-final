package br.com.silvaandersonm.trabfinal.domain.service;

import java.util.List;

import br.com.silvaandersonm.trabfinal.domain.model.Transferencia;

public interface TransferenciaService {

	public void incluir(Transferencia transferencia);

	public void alterar(Transferencia transferencia);

	public Transferencia obterPorId(Long id);

	public List<Transferencia> listar();

}
