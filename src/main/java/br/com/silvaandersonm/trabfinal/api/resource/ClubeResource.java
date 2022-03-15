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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.silvaandersonm.trabfinal.api.dto.ClubeAtletaDTO;
import br.com.silvaandersonm.trabfinal.api.dto.ClubeDTO;
import br.com.silvaandersonm.trabfinal.api.dto.ClubePersistenciaDTO;
import br.com.silvaandersonm.trabfinal.api.dto.ClubeResumoDTO;
import br.com.silvaandersonm.trabfinal.domain.model.Atleta;
import br.com.silvaandersonm.trabfinal.domain.model.Clube;
import br.com.silvaandersonm.trabfinal.domain.service.ClubeService;

@RestController
@RequestMapping(path="/api/v1", produces=MediaType.APPLICATION_JSON_VALUE)
public class ClubeResource {

	@Autowired
	private ClubeService clubeService;

	@GetMapping("/clubes")
	public ResponseEntity<List<ClubeResumoDTO>> listarClubes() {
		List<Clube> clubes = clubeService.listar();
		if (clubes.size() > 0) {
			ModelMapper mapper = new ModelMapper();
			List<ClubeResumoDTO> clubesResumoDTO = clubes.stream().map(c -> mapper.map(c, ClubeResumoDTO.class)).collect(Collectors.toList());
			return new ResponseEntity<>(clubesResumoDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/clubes/{id}")
	public ResponseEntity<ClubeDTO> obterClube(@PathVariable("id") Long id) {
		Clube clube = clubeService.obterPorId(id);
		ModelMapper mapper = new ModelMapper();
		ClubeDTO clubeDTO = mapper.map(clube, ClubeDTO.class);
		return new ResponseEntity<>(clubeDTO, HttpStatus.OK);
	}

	@GetMapping("/clubes/{id}/atletas")
	public ResponseEntity<List<ClubeAtletaDTO>> listarAtletas(@PathVariable("id") Long idClube) {
		List<Atleta> atletas = clubeService.listarAtletas(idClube);
		if (atletas.size() > 0) {
			ModelMapper mapper = new ModelMapper();
			List<ClubeAtletaDTO> clubeAtletaDTO = atletas.stream().map(a -> mapper.map(a, ClubeAtletaDTO.class)).collect(Collectors.toList());
			return new ResponseEntity<>(clubeAtletaDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping(path="/clubes", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> incluirClube(@Valid @RequestBody ClubePersistenciaDTO clubePersistenciaDTO) {
		ModelMapper mapper = new ModelMapper();
		Clube clube = mapper.map(clubePersistenciaDTO, Clube.class);
		clubeService.incluir(clube);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clube.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(path="/clubes/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> alterarClube(@PathVariable("id") Long id, @Valid @RequestBody ClubePersistenciaDTO clubePersistenciaDTO) {
		ModelMapper mapper = new ModelMapper();
		Clube clube = mapper.map(clubePersistenciaDTO, Clube.class);
		clube.setId(id);
		clubeService.alterar(clube);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/clubes/{id}")
	public ResponseEntity<Void> excluirClube(@PathVariable("id") Long id) {
		clubeService.excluir(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
