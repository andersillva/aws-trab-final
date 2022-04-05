package br.com.andersillva.trabfinal.api.util;

import org.modelmapper.ModelMapper;

import br.com.andersillva.trabfinal.api.dto.AtletaInclusaoDTO;
import br.com.andersillva.trabfinal.api.dto.AtletaPersistenciaDTO;
import br.com.andersillva.trabfinal.api.dto.ClubePersistenciaDTO;
import br.com.andersillva.trabfinal.api.dto.EventoPersistenciaDTO;
import br.com.andersillva.trabfinal.api.dto.ParticipanteInclusaoDTO;
import br.com.andersillva.trabfinal.api.dto.PartidaPersistenciaDTO;
import br.com.andersillva.trabfinal.api.dto.TorneioPersistenciaDTO;
import br.com.andersillva.trabfinal.api.dto.TransferenciaInclusaoDTO;
import br.com.andersillva.trabfinal.domain.model.Atleta;
import br.com.andersillva.trabfinal.domain.model.Clube;
import br.com.andersillva.trabfinal.domain.model.Evento;
import br.com.andersillva.trabfinal.domain.model.Participante;
import br.com.andersillva.trabfinal.domain.model.Partida;
import br.com.andersillva.trabfinal.domain.model.Torneio;
import br.com.andersillva.trabfinal.domain.model.Transferencia;

public class EntityFactory {

	private static ModelMapper mapper = new ModelMapper();

	public static Atleta getAtleta(AtletaInclusaoDTO atletaInclusaoDTO) {
		Atleta atleta = mapper.map(atletaInclusaoDTO, Atleta.class);
		return atleta;
	}
	
	public static Atleta getAtleta(AtletaPersistenciaDTO atletaPersistenciaDTO) {
		Atleta atleta = mapper.map(atletaPersistenciaDTO, Atleta.class);
		return atleta;
	}

	public static Clube getClube(ClubePersistenciaDTO clubePersistenciaDTO) {
		Clube clube = mapper.map(clubePersistenciaDTO, Clube.class);
		return clube;
	}

	public static Transferencia getTransferencia(TransferenciaInclusaoDTO transferenciaInclusaoDTO) {
		Transferencia transferencia = mapper.map(transferenciaInclusaoDTO, Transferencia.class);
		return transferencia;
	}

	public static Torneio getTorneio(TorneioPersistenciaDTO torneioPersistenciaDTO) {
		Torneio torneio = mapper.map(torneioPersistenciaDTO, Torneio.class);
		return torneio;
	}

	public static Participante getParticipante(ParticipanteInclusaoDTO participanteInclusaoDTO) {
		Participante participante = mapper.map(participanteInclusaoDTO, Participante.class);
		return participante;
	}

	public static Partida getPartida(PartidaPersistenciaDTO partidaPersistenciaDTO) {
		ModelMapper mapperPartida = new ModelMapper();
		mapperPartida.getConfiguration().setAmbiguityIgnored(true);
		Partida partida = mapperPartida.map(partidaPersistenciaDTO, Partida.class);
		return partida;
	}

	public static Evento getEvento(EventoPersistenciaDTO eventoPersistenciaDTO) {
		Evento evento = mapper.map(eventoPersistenciaDTO, Evento.class);
		return evento;
	}

}
