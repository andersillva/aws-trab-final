package br.com.silvaandersonm.trabfinal.api.resource;

import java.net.URI;
import java.util.List;

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

import br.com.silvaandersonm.trabfinal.api.dto.AtletaAlteracaoDTO;
import br.com.silvaandersonm.trabfinal.api.dto.AtletaInclusaoDTO;
import br.com.silvaandersonm.trabfinal.api.dto.AtletaPersistenciaDTO;
import br.com.silvaandersonm.trabfinal.domain.model.Atleta;
import br.com.silvaandersonm.trabfinal.domain.model.Clube;
import br.com.silvaandersonm.trabfinal.domain.service.AtletaService;
import br.com.silvaandersonm.trabfinal.domain.service.ClubeService;

@RestController
@RequestMapping(path="/api/v1", produces=MediaType.APPLICATION_JSON_VALUE)
public class AtletaResource {

	@Autowired
	private AtletaService atletaService;

	@Autowired
	private ClubeService clubeService;

	@GetMapping("/atletas")
	public ResponseEntity<List<Atleta>> listarAtletas() {
		List<Atleta> atletas = atletaService.listar();
		if (atletas.size() > 0) {
			return new ResponseEntity<>(atletas, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/atletas/{id}")
	public ResponseEntity<Atleta> obterAtleta(@PathVariable("id") Long id) {
		Atleta atleta = atletaService.obterPorId(id);
		return new ResponseEntity<>(atleta, HttpStatus.OK);
	}

	@PostMapping(path="/atletas", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> incluirAtleta(@Valid @RequestBody AtletaInclusaoDTO atletaInclusaoDTO) {
		Atleta atleta = mapearAtleta(atletaInclusaoDTO);
		atletaService.incluir(atleta);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(atleta.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(path="/atletas/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> alterarAtleta(@PathVariable("id") Long id, @Valid @RequestBody AtletaAlteracaoDTO atletaAlteracaoDTO) {
		Atleta atleta = mapearAtleta(atletaAlteracaoDTO);
		atletaService.alterar(atleta);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/atletas/{id}")
	public ResponseEntity<Void> excluirAtleta(@PathVariable("id") Long id) {
		atletaService.excluir(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	private Atleta mapearAtleta(AtletaPersistenciaDTO atletaPersistenciaDTO) {
		ModelMapper mapper = new ModelMapper();
		Atleta atleta = mapper.map(atletaPersistenciaDTO, Atleta.class);
		Clube clube;
		if (atletaPersistenciaDTO.getClass().isInstance(AtletaInclusaoDTO.class)) {
			clube = clubeService.obterPorId(((AtletaInclusaoDTO)atletaPersistenciaDTO).getIdClube());
		} else {
			clube = atletaService.obterPorId(((AtletaAlteracaoDTO)atletaPersistenciaDTO).getId()).getClube();
		}
		atleta.setClube(clube);
		return atleta;
	}
}
