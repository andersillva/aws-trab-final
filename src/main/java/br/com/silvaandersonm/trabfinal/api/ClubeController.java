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

import br.com.silvaandersonm.trabfinal.domain.business.ClubeService;
import br.com.silvaandersonm.trabfinal.domain.model.Atleta;
import br.com.silvaandersonm.trabfinal.domain.model.Clube;
import br.com.silvaandersonm.trabfinal.util.BusinessException;

@RestController
@RequestMapping("/api")
public class ClubeController {

	@Autowired
	private ClubeService clubeService;

	@GetMapping("/clubes")
	public ResponseEntity<List<Clube>> getClubes() {
		try {
			List<Clube> clubes = clubeService.listar();
			if (clubes.size() > 0) {
				return new ResponseEntity<>(clubes, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/clubes/{id}")
	public ResponseEntity<Clube> getClube(@PathVariable("id") Long id) {
		Clube clube = clubeService.obterPorId(id);
		if (clube != null) {
			return new ResponseEntity<>(clube, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/clubes/{id}/atletas")
	public ResponseEntity<List<Atleta>> getAtletas(@PathVariable("id") Long idClube) {
		try {
			List<Atleta> atletas = clubeService.listarAtletas(idClube);
			if (atletas.size() > 0) {
				return new ResponseEntity<>(atletas, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/clubes")
	public ResponseEntity<Clube> postClube(@RequestBody Clube clube) {
		try {
			clubeService.incluir(clube);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/clubes/{id}")
	public ResponseEntity<Clube> putClube(@PathVariable("id") Long id, @RequestBody Clube clube) {
		try {
			clubeService.alterar(clube);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
