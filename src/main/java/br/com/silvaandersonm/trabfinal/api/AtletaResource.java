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
import br.com.silvaandersonm.trabfinal.domain.service.AtletaService;

@RestController
@RequestMapping("/api")
public class AtletaResource {

	@Autowired
	private AtletaService atletaService;

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

	@PostMapping("/atletas")
	public ResponseEntity<Atleta> incluirAtleta(@RequestBody Atleta atleta) {
		atletaService.incluir(atleta);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/atletas/{id}")
	public ResponseEntity<Atleta> alterarAtleta(@PathVariable("id") Long id, @RequestBody Atleta atleta) {
		atletaService.alterar(atleta);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
