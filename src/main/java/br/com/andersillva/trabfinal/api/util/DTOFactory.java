package br.com.andersillva.trabfinal.api.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import br.com.andersillva.trabfinal.api.dto.AtletaDTO;
import br.com.andersillva.trabfinal.api.dto.AtletaResumoDTO;
import br.com.andersillva.trabfinal.api.dto.ClubeAtletaDTO;
import br.com.andersillva.trabfinal.api.dto.ClubeDTO;
import br.com.andersillva.trabfinal.api.dto.ClubeResumoDTO;
import br.com.andersillva.trabfinal.api.dto.EventoDTO;
import br.com.andersillva.trabfinal.api.dto.TorneioDTO;
import br.com.andersillva.trabfinal.api.dto.TorneioParticipanteDTO;
import br.com.andersillva.trabfinal.api.dto.TorneioPartidaDTO;
import br.com.andersillva.trabfinal.api.dto.TorneioResumoDTO;
import br.com.andersillva.trabfinal.api.dto.TransferenciaDTO;
import br.com.andersillva.trabfinal.domain.model.Atleta;
import br.com.andersillva.trabfinal.domain.model.Clube;
import br.com.andersillva.trabfinal.domain.model.Evento;
import br.com.andersillva.trabfinal.domain.model.Participante;
import br.com.andersillva.trabfinal.domain.model.Partida;
import br.com.andersillva.trabfinal.domain.model.Torneio;
import br.com.andersillva.trabfinal.domain.model.Transferencia;

public class DTOFactory {

	private static ModelMapper mapper = new ModelMapper();

	public static List<AtletaResumoDTO> getAtletaResumoDTO(List<Atleta> atletas) {
		List<AtletaResumoDTO> atletasResumoDTO = atletas.stream().map(a -> mapper.map(a, AtletaResumoDTO.class)).collect(Collectors.toList());
		return atletasResumoDTO;
	}

	public static AtletaDTO getAtletaDTO(Atleta atleta) {
		AtletaDTO atletaDTO = new AtletaDTO(atleta);
		return atletaDTO;
	}

	public static List<ClubeResumoDTO> getClubeResumoDTO(List<Clube> clubes) {
		List<ClubeResumoDTO> clubesResumoDTO = clubes.stream().map(c -> mapper.map(c, ClubeResumoDTO.class)).collect(Collectors.toList());
		return clubesResumoDTO;
	}

	public static ClubeDTO getClubeDTO(Clube clube) {
		ClubeDTO clubeDTO = new ClubeDTO(clube);
		return clubeDTO;
	}

	public static List<ClubeAtletaDTO> getClubeAtletaDTO(List<Atleta> atletas) {
		List<ClubeAtletaDTO> clubeAtletaDTO = atletas.stream().map(a -> mapper.map(a, ClubeAtletaDTO.class)).collect(Collectors.toList());
		return clubeAtletaDTO;
	}

	public static List<TransferenciaDTO> getTransferenciaDTO(List<Transferencia> transferencias) {
		List<TransferenciaDTO> transferenciasDTO = transferencias.stream().map(t -> mapper.map(t, TransferenciaDTO.class))
	            														  .collect(Collectors.toList());
		return transferenciasDTO;
	}

	public static TransferenciaDTO getTransferenciaDTO(Transferencia transferencia) {
		TransferenciaDTO transferenciaDTO = mapper.map(transferencia, TransferenciaDTO.class);
		return transferenciaDTO;
	}

	public static List<TorneioResumoDTO> getTorneioResumoDTO(List<Torneio> torneios) {
		List<TorneioResumoDTO> torneiosResumoDTO = torneios.stream().map(c -> mapper.map(c, TorneioResumoDTO.class)).collect(Collectors.toList());
		return torneiosResumoDTO;
	}

	public static TorneioDTO getTorneioDTO(Torneio torneio) {
		TorneioDTO torneioDTO = mapper.map(torneio, TorneioDTO.class);
		return torneioDTO;
	}

	public static List<TorneioParticipanteDTO> getTorneioParticipanteDTO(List<Participante> participantes) {
		List<TorneioParticipanteDTO> torneioParticipanteDTO = participantes.stream().map(a -> mapper.map(a, TorneioParticipanteDTO.class)).collect(Collectors.toList());
		return torneioParticipanteDTO;
	}

	public static TorneioParticipanteDTO getTorneioParticipanteDTO(Participante participante) {
		TorneioParticipanteDTO torneioParticipanteDTO = mapper.map(participante, TorneioParticipanteDTO.class);
		return torneioParticipanteDTO;
	}

	public static List<TorneioPartidaDTO> getTorneioPartidaDTO(List<Partida> partidas) {
		List<TorneioPartidaDTO> torneioPartidaDTO = partidas.stream().map(a -> mapper.map(a, TorneioPartidaDTO.class)).collect(Collectors.toList());
		return torneioPartidaDTO;
	}

	public static TorneioPartidaDTO getTorneioPartidaDTO(Partida partida) {
		TorneioPartidaDTO torneioPartidaDTO = mapper.map(partida, TorneioPartidaDTO.class);
		return torneioPartidaDTO;
	}

	public static EventoDTO getEventoDTO(Evento evento) {
		EventoDTO eventoDTO = mapper.map(evento, EventoDTO.class);
		return eventoDTO;
	}

	public static List<EventoDTO> getEventoDTO(List<Evento> eventos) {
		List<EventoDTO> eventoDTO = eventos.stream().map(a -> mapper.map(a, EventoDTO.class)).collect(Collectors.toList());
		return eventoDTO;
	}

}
