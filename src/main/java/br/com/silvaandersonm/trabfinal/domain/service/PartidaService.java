package br.com.silvaandersonm.trabfinal.domain.service;

import java.util.List;

import br.com.silvaandersonm.trabfinal.domain.model.Partida;

public interface PartidaService {

	public void incluir(Partida partida);

	public void alterar(Partida partida);

	public Partida obterPorId(Long id);

	public List<Partida> listarPorTorneio(Long idTorneio);

	public void excluir(Long id);

}
