package br.com.silvaandersonm.trabfinal.api.resource;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.silvaandersonm.trabfinal.api.VersaoAPI;
import br.com.silvaandersonm.trabfinal.api.dto.EventoDTO;
import br.com.silvaandersonm.trabfinal.api.dto.EventoPersistenciaDTO;
import br.com.silvaandersonm.trabfinal.api.dto.ParticipanteAlteracaoDTO;
import br.com.silvaandersonm.trabfinal.api.dto.ParticipanteInclusaoDTO;
import br.com.silvaandersonm.trabfinal.api.dto.PartidaPersistenciaDTO;
import br.com.silvaandersonm.trabfinal.api.dto.TorneioDTO;
import br.com.silvaandersonm.trabfinal.api.dto.TorneioParticipanteDTO;
import br.com.silvaandersonm.trabfinal.api.dto.TorneioPartidaDTO;
import br.com.silvaandersonm.trabfinal.api.dto.TorneioPersistenciaDTO;
import br.com.silvaandersonm.trabfinal.api.dto.TorneioResumoDTO;
import br.com.silvaandersonm.trabfinal.api.exception.RespostaPadraoInsucesso;
import br.com.silvaandersonm.trabfinal.domain.enumerator.TipoEvento;
import br.com.silvaandersonm.trabfinal.domain.model.Clube;
import br.com.silvaandersonm.trabfinal.domain.model.Evento;
import br.com.silvaandersonm.trabfinal.domain.model.Participante;
import br.com.silvaandersonm.trabfinal.domain.model.Partida;
import br.com.silvaandersonm.trabfinal.domain.model.Torneio;
import br.com.silvaandersonm.trabfinal.domain.service.ClubeService;
import br.com.silvaandersonm.trabfinal.domain.service.EventoService;
import br.com.silvaandersonm.trabfinal.domain.service.ParticipanteService;
import br.com.silvaandersonm.trabfinal.domain.service.PartidaService;
import br.com.silvaandersonm.trabfinal.domain.service.TorneioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(path=VersaoAPI.URI_BASE_V1, produces=MediaType.APPLICATION_JSON_VALUE)
public class TorneioResource {

	@Autowired
	private TorneioService torneioService;

	@Autowired
	private PartidaService partidaService;

	@Autowired
	private ParticipanteService participanteService;

	@Autowired
	private EventoService eventoService;

	@Autowired
	private ClubeService clubeService;

	@GetMapping("/torneios")
	public ResponseEntity<List<TorneioResumoDTO>> listarTorneios() {
		List<Torneio> torneios = torneioService.listar();
		if (torneios.size() > 0) {
			ModelMapper mapper = new ModelMapper();
			List<TorneioResumoDTO> torneiosResumoDTO = torneios.stream().map(c -> mapper.map(c, TorneioResumoDTO.class)).collect(Collectors.toList());
			return new ResponseEntity<>(torneiosResumoDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/torneios/{id}")
	public ResponseEntity<TorneioDTO> obterTorneio(@PathVariable("id") Long id) {
		Torneio torneio = torneioService.obterPorId(id);
		ModelMapper mapper = new ModelMapper();
		TorneioDTO torneioDTO = mapper.map(torneio, TorneioDTO.class);
		return new ResponseEntity<>(torneioDTO, HttpStatus.OK);
	}

	@PostMapping(path="/torneios", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> incluirTorneio(@Valid @RequestBody TorneioPersistenciaDTO torneioPersistenciaDTO) {
		ModelMapper mapper = new ModelMapper();
		Torneio torneio = mapper.map(torneioPersistenciaDTO, Torneio.class);
		torneioService.incluir(torneio);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(torneio.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(path="/torneios/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> alterarTorneio(@PathVariable("id") Long id, @Valid @RequestBody TorneioPersistenciaDTO torneioPersistenciaDTO) {
		ModelMapper mapper = new ModelMapper();
		Torneio torneio = mapper.map(torneioPersistenciaDTO, Torneio.class);
		torneio.setId(id);
		torneioService.alterar(torneio);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/torneios/{id}")
	public ResponseEntity<Void> excluirTorneio(@PathVariable("id") Long id) {
		torneioService.excluir(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/torneios/{id}/participantes")
	public ResponseEntity<List<TorneioParticipanteDTO>> listarParticipantes(@PathVariable("id") Long idTorneio) {
		List<Participante> participantes = participanteService.listarPorTorneio(idTorneio);
		if (participantes.size() > 0) {
			ModelMapper mapper = new ModelMapper();
			List<TorneioParticipanteDTO> torneioParticipanteDTO = participantes.stream().map(a -> mapper.map(a, TorneioParticipanteDTO.class)).collect(Collectors.toList());
			return new ResponseEntity<>(torneioParticipanteDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/torneios/{id}/participantes/{id-clube}")
	public ResponseEntity<TorneioParticipanteDTO> obterParticipante(@PathVariable("id") Long id,
												  			   		@PathVariable("id-clube") Long idClube) {
		Participante participante = participanteService.obterPorChave(id, idClube);
		ModelMapper mapper = new ModelMapper();
		TorneioParticipanteDTO torneioParticipanteDTO = mapper.map(participante, TorneioParticipanteDTO.class);
		return new ResponseEntity<>(torneioParticipanteDTO, HttpStatus.OK);
	}

	@PostMapping(path="/torneios/{id}/participantes", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> incluirParticipante(@PathVariable("id") Long id, @Valid @RequestBody ParticipanteInclusaoDTO participanteInclusaoDTO) {
		ModelMapper mapper = new ModelMapper();
		Participante participante = mapper.map(participanteInclusaoDTO, Participante.class);
		Torneio torneio = torneioService.obterPorId(id);
		participante.setTorneio(torneio);
		Clube clube = clubeService.obterPorId(participanteInclusaoDTO.getIdClube());
		participante.setClube(clube);
		participanteService.incluir(participante);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(participante.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(path="/torneios/{id}/participantes/{id-clube}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> alterarParticipante(@PathVariable("id") Long id, 
			                                        @PathVariable("id-clube") Long idClube, 
			                                        @Valid @RequestBody ParticipanteAlteracaoDTO participanteAlteracaoDTO) {

		Participante participante = participanteService.obterPorChave(id, idClube);
		participante.setSituacao(participanteAlteracaoDTO.getSituacao());
		participante.setPontuacao(participanteAlteracaoDTO.getPontuacao());
		participanteService.alterar(participante);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/torneios/{id}/participantes/{id-clube}")
	public ResponseEntity<Void> excluirParticipante(@PathVariable("id") Long id, @PathVariable("id-clube") Long idClube) {
		participanteService.excluir(id, idClube);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/torneios/{id}/partidas")
	public ResponseEntity<List<TorneioPartidaDTO>> listarPartidas(@PathVariable("id") Long idTorneio) {
		List<Partida> partidas = partidaService.listarPorTorneio(idTorneio);
		if (partidas.size() > 0) {
			ModelMapper mapper = new ModelMapper();
			List<TorneioPartidaDTO> torneioPartidaDTO = partidas.stream().map(a -> mapper.map(a, TorneioPartidaDTO.class)).collect(Collectors.toList());
			return new ResponseEntity<>(torneioPartidaDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/torneios/{id}/partidas/{id-partida}")
	public ResponseEntity<TorneioPartidaDTO> obterPartida(@PathVariable("id") Long id,
												  @PathVariable("id-partida") Long idPartida) {
		Partida partida = partidaService.obterPorId(idPartida);
		ModelMapper mapper = new ModelMapper();
		TorneioPartidaDTO torneioPartidaDTO = mapper.map(partida, TorneioPartidaDTO.class);
		return new ResponseEntity<>(torneioPartidaDTO, HttpStatus.OK);
	}

	@PostMapping(path="/torneios/{id}/partidas", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> incluirPartida(@PathVariable("id") Long id, @Valid @RequestBody PartidaPersistenciaDTO partidaPersistenciaDTO) {
		Partida partida = mapearPartida(partidaPersistenciaDTO, id);
		partidaService.incluir(partida);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(partida.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(path="/torneios/{id}/partidas/{id-partida}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> alterarPartida(@PathVariable("id") Long id, 
			                                   @PathVariable("id-partida") Long idPartida, 
			                                   @Valid @RequestBody PartidaPersistenciaDTO partidaPersistenciaDTO) {
		Partida partida = mapearPartida(partidaPersistenciaDTO, id, idPartida);
		partidaService.alterar(partida);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	private Partida mapearPartida(PartidaPersistenciaDTO partidaPersistenciaDTO, Long idTorneio) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setAmbiguityIgnored(true);
		Partida partida = mapper.map(partidaPersistenciaDTO, Partida.class);
		Torneio torneio = torneioService.obterPorId(idTorneio);
		partida.setTorneio(torneio);
		Clube mandante = clubeService.obterPorId(partidaPersistenciaDTO.getIdClubeMandante());
		partida.setClubeMandante(mandante);
		Clube visitante = clubeService.obterPorId(partidaPersistenciaDTO.getIdClubeVisitante());
		partida.setClubeVisitante(visitante);
		return partida;
	}

	private Partida mapearPartida(PartidaPersistenciaDTO partidaPersistenciaDTO, Long idTorneio, Long idPartida) {
		Partida partida = mapearPartida(partidaPersistenciaDTO, idTorneio);
		partida.setId(idPartida);
		return partida;
	}

	@DeleteMapping("/torneios/{id}/partidas/{id-partida}")
	public ResponseEntity<Void> excluirPartida(@PathVariable("id") Long id, @PathVariable("id-partida") Long idPartida) {
		partidaService.excluir(idPartida);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Operation(summary = "Adiciona um novo evento em uma partida.")
	@ApiResponses(value = { 
	  @ApiResponse(responseCode="201", description="Evento criado com sucesso."),
	  @ApiResponse(responseCode="400",
	               description="Parâmetros não informados ou com valores inválidos.",
	               content={@Content(mediaType=MediaType.APPLICATION_JSON_VALUE, schema=@Schema(implementation = RespostaPadraoInsucesso.class)) }), 
	  @ApiResponse(responseCode="404", description="Partida ou tipo de evento não encontrado.", content=@Content) })
	@PostMapping(path="/torneios/{id}/partidas/{id-partida}/eventos/{tipo-evento}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> incluirEvento(@PathVariable("id") Long id,
			                                  @PathVariable("id-partida") Long idPartida,
			                                  @Parameter(description = "Tipos suportados: inicio, gol, penalti, lance-perigoso, lance-normal, intervalo, acrescimo, substituicao, advertencia, expulsao, revisao-var, pausa e fim.") @PathVariable("tipo-evento") String tipoEvento, 
			                                  @Valid @RequestBody EventoPersistenciaDTO eventoPersistenciaDTO) {
		ModelMapper mapper = new ModelMapper();
		Evento evento = mapper.map(eventoPersistenciaDTO, Evento.class);
		Partida partida = partidaService.obterPorId(idPartida);
		evento.setPartida(partida);
		evento.setTipo(TipoEvento.getByValue(tipoEvento));
		evento.setData(LocalDateTime.now());
		eventoService.incluir(evento);
		URI uri = ServletUriComponentsBuilder.fromCurrentServletMapping()
											 .path(VersaoAPI.URI_BASE_V1 + "/torneios/{id}/partidas/{id-partida}/eventos/{id-evento}")
											 .buildAndExpand(id, idPartida, evento.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/torneios/{id}/partidas/{id-partida}/eventos/{id-evento}")
	public ResponseEntity<EventoDTO> obterEvento(@PathVariable("id") Long id,
												 @PathVariable("id-partida") Long idPartida,
												 @PathVariable("id-evento") Long idEvento) {
		Evento evento = eventoService.obterPorId(idEvento);
		ModelMapper mapper = new ModelMapper();
		EventoDTO eventoDTO = mapper.map(evento, EventoDTO.class);
		return new ResponseEntity<>(eventoDTO, HttpStatus.OK);
	}

	@GetMapping("/torneios/{id}/partidas/{id-partida}/eventos")
	public ResponseEntity<List<EventoDTO>> listarEventos(@PathVariable("id") Long id,
			 											 @PathVariable("id-partida") Long idPartida) {
		List<Evento> eventos = eventoService.listarPorPartida(idPartida);
		if (eventos.size() > 0) {
			ModelMapper mapper = new ModelMapper();
			List<EventoDTO> eventoDTO = eventos.stream().map(a -> mapper.map(a, EventoDTO.class)).collect(Collectors.toList());
			return new ResponseEntity<>(eventoDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
