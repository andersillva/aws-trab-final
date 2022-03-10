package br.com.silvaandersonm.trabfinal.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.silvaandersonm.trabfinal.domain.model.Atleta;
import br.com.silvaandersonm.trabfinal.domain.model.Clube;
import br.com.silvaandersonm.trabfinal.domain.service.ClubeService;

@RestController
@RequestMapping("/api")
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
	public ResponseEntity<List<Atleta>> listarAtletas(@PathVariable("id") Long idClube) {
		List<Atleta> atletas = clubeService.listarAtletas(idClube);
		if (atletas.size() > 0) {
			return new ResponseEntity<>(atletas, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/clubes")
	public ResponseEntity<Clube> incluirClube(@RequestBody Clube clube) {
		clubeService.incluir(clube);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/clubes/{id}")
	public ResponseEntity<Clube> alterarClube(@PathVariable("id") Long id, @RequestBody Clube clube) {
		clubeService.alterar(clube);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
