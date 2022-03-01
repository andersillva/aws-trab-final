package br.com.silvaandersonm.trabfinal.domain.repository;

import java.util.List;

import br.com.silvaandersonm.trabfinal.domain.model.Clube;

public interface ClubeRepository {

	public Clube obterPorId(Long id);

	public List<Clube> listar();

	public void incluir(Clube clube);

	public void atualizar(Clube clube);

}
