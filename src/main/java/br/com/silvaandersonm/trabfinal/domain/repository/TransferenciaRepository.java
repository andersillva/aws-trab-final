package br.com.silvaandersonm.trabfinal.domain.repository;

import java.util.List;

import br.com.silvaandersonm.trabfinal.domain.model.Transferencia;

public interface TransferenciaRepository {

	public Transferencia obterPorId(Long id);

	public List<Transferencia> listar();

	public void incluir(Transferencia transferencia);

}
