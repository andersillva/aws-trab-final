package br.com.silvaandersonm.trabfinal.domain.service;

import java.util.List;

import br.com.silvaandersonm.trabfinal.domain.model.Participante;

public interface ParticipanteService {

	public List<Participante> listarPorTorneio(Long idTorneio);

	public Participante obterPorId(Long idParticipante);

	public Participante obterPorChave(Long idTorneio, Long idClube);

	public void incluir(Participante participante);

	public void alterar(Participante participante);

	public void excluir(Long idTorneio, Long idClube);

}
