package br.com.silvaandersonm.trabfinal.domain.service;

import java.util.List;

import br.com.silvaandersonm.trabfinal.domain.model.Evento;

public interface EventoService {

	public void incluir(Evento partida);

	public void alterar(Evento partida);

	public Evento obterPorId(Long id);

	public List<Evento> listarPorPartida(Long idPartida);

	public void excluir(Long id);

}
