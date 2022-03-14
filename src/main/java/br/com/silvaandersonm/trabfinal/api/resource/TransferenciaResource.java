package br.com.silvaandersonm.trabfinal.api.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.silvaandersonm.trabfinal.api.dto.TransferenciaAlteracaoDTO;
import br.com.silvaandersonm.trabfinal.api.dto.TransferenciaDTO;
import br.com.silvaandersonm.trabfinal.api.dto.TransferenciaPersistenciaDTO;
import br.com.silvaandersonm.trabfinal.domain.model.Atleta;
import br.com.silvaandersonm.trabfinal.domain.model.Clube;
import br.com.silvaandersonm.trabfinal.domain.model.Transferencia;
import br.com.silvaandersonm.trabfinal.domain.service.AtletaService;
import br.com.silvaandersonm.trabfinal.domain.service.ClubeService;
import br.com.silvaandersonm.trabfinal.domain.service.TransferenciaService;

@RestController
@RequestMapping(path="/api/v1", produces=MediaType.APPLICATION_JSON_VALUE)
public class TransferenciaResource {

	@Autowired
	private TransferenciaService transferenciaService;

	@Autowired
	private ClubeService clubeService;

	@Autowired
	private AtletaService atletaService;

	@GetMapping("/transferencias")
	public ResponseEntity<List<TransferenciaDTO>> listarTransferencias() {
		List<Transferencia> transferencias = transferenciaService.listar();
		if (transferencias.size() > 0) {
			ModelMapper mapper = new ModelMapper();
			List<TransferenciaDTO> transferenciasDTO = transferencias.stream()
					                                                 .map(t -> mapper.map(t, TransferenciaDTO.class))
					                                                 .collect(Collectors.toList());
			return new ResponseEntity<>(transferenciasDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/transferencias/{id}")
	public ResponseEntity<TransferenciaDTO> obterTransferencia(@PathVariable("id") Long id) {
		Transferencia transferencia = transferenciaService.obterPorId(id);
		ModelMapper mapper = new ModelMapper();
		TransferenciaDTO transferenciaDTO = mapper.map(transferencia, TransferenciaDTO.class);
		return new ResponseEntity<>(transferenciaDTO, HttpStatus.OK);
	}

	@PostMapping(path="/transferencias", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> incluirTransferencia(@Valid @RequestBody TransferenciaPersistenciaDTO transferenciaPersistenciaDTO) {
		Transferencia transferencia = mapearTransferencia(transferenciaPersistenciaDTO);
		transferenciaService.incluir(transferencia);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(transferencia.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(path="/transferencias/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> alterarTransferencia(@PathVariable("id") Long id, 
													 @Valid @RequestBody TransferenciaAlteracaoDTO transferenciaAlteracaoDTO) {
		Transferencia transferencia = mapearTransferencia(transferenciaAlteracaoDTO);
		transferenciaService.alterar(transferencia);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	private Transferencia mapearTransferencia(TransferenciaPersistenciaDTO transferenciaPersistenciaDTO) {
		Clube clubeOrigem = clubeService.obterPorId(transferenciaPersistenciaDTO.getIdClubeOrigem());
		Clube clubeDestino = clubeService.obterPorId(transferenciaPersistenciaDTO.getIdClubeDestino());
		Atleta atleta = atletaService.obterPorId(transferenciaPersistenciaDTO.getIdAtleta());

		ModelMapper mapper = new ModelMapper();
		Transferencia transferencia = mapper.map(transferenciaPersistenciaDTO, Transferencia.class);
		transferencia.setClubeOrigem(clubeOrigem);
		transferencia.setClubeDestino(clubeDestino);
		transferencia.setAtleta(atleta);

		return transferencia;
	}
}
