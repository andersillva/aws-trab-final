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

import br.com.silvaandersonm.trabfinal.api.dto.AtletaClubeDTO;
import br.com.silvaandersonm.trabfinal.domain.model.Atleta;
import br.com.silvaandersonm.trabfinal.domain.model.Clube;
import br.com.silvaandersonm.trabfinal.domain.service.ClubeService;

@RestController
@RequestMapping(path="/api/v1", produces=MediaType.APPLICATION_JSON_VALUE)
public class ClubeResource {

	@Autowired
	private ClubeService clubeService;

	@GetMapping("/clubes")
	public ResponseEntity<List<Clube>> listarClubes() {
		List<Clube> clubes = clubeService.listar();
		if (clubes.size() > 0) {
			return new ResponseEntity<>(clubes, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/clubes/{id}")
	public ResponseEntity<Clube> obterClube(@PathVariable("id") Long id) {
		Clube clube = clubeService.obterPorId(id);
		return new ResponseEntity<>(clube, HttpStatus.OK);
	}

	@GetMapping("/clubes/{id}/atletas")
	public ResponseEntity<List<AtletaClubeDTO>> listarAtletas(@PathVariable("id") Long idClube) {
		List<Atleta> atletas = clubeService.listarAtletas(idClube);
		ModelMapper mapper = new ModelMapper();
		List<AtletaClubeDTO> atletasClubeDTO = atletas.stream().map(a -> mapper.map(a, AtletaClubeDTO.class)).collect(Collectors.toList());
		if (atletas.size() > 0) {
			return new ResponseEntity<>(atletasClubeDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping(path="/clubes", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> incluirClube(@Valid @RequestBody Clube clube) {
		clubeService.incluir(clube);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clube.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(path="/clubes/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> alterarClube(@PathVariable("id") Long id, @Valid @RequestBody Clube clube) {
		clubeService.alterar(clube);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/clubes/{id}")
	public ResponseEntity<Void> excluirClube(@PathVariable("id") Long id) {
		clubeService.excluir(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
