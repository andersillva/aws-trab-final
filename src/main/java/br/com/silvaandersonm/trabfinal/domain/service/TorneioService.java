package br.com.silvaandersonm.trabfinal.domain.service;

import java.util.List;

import br.com.silvaandersonm.trabfinal.domain.model.Torneio;

public interface TorneioService {

	public void incluir(Torneio torneio);

	public void alterar(Torneio torneio);

	public Torneio obterPorId(Long id);

	public List<Torneio> listar();

	public void excluir(Long id);

}
