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
import org.springframework.security.access.annotation.Secured;
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
import br.com.silvaandersonm.trabfinal.api.dto.ClubeAtletaDTO;
import br.com.silvaandersonm.trabfinal.api.dto.ClubeDTO;
import br.com.silvaandersonm.trabfinal.api.dto.ClubePersistenciaDTO;
import br.com.silvaandersonm.trabfinal.api.dto.ClubeResumoDTO;
import br.com.silvaandersonm.trabfinal.api.exception.RespostaPadraoErro;
import br.com.silvaandersonm.trabfinal.domain.model.Atleta;
import br.com.silvaandersonm.trabfinal.domain.model.Clube;
import br.com.silvaandersonm.trabfinal.domain.service.ClubeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path=VersaoAPI.URI_BASE_V1, produces=MediaType.APPLICATION_JSON_VALUE)
public class ClubeResource {

	@Autowired
	private ClubeService clubeService;

	@Operation(summary = "Obtém a lista de clubes.")
	@ApiResponses(value = {@ApiResponse(responseCode="200", description="Requisição processada com sucesso."),
						   @ApiResponse(responseCode="204", description="Não existe nenhum clube cadastrado.")})
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

	@Operation(summary = "Obtém um clube a partir de seu ID.")
	@ApiResponses(value = {@ApiResponse(responseCode="200", description="Requisição processada com sucesso."),
						   @ApiResponse(responseCode="404", description="Clube não encontrado.", content={@Content(mediaType=MediaType.APPLICATION_JSON_VALUE, schema=@Schema(implementation = RespostaPadraoErro.class))})})
	@GetMapping("/clubes/{id}")
	public ResponseEntity<ClubeDTO> obterClube(@PathVariable("id") Long id) {
		Clube clube = clubeService.obterPorId(id);
		ModelMapper mapper = new ModelMapper();
		ClubeDTO clubeDTO = mapper.map(clube, ClubeDTO.class);
		return new ResponseEntity<>(clubeDTO, HttpStatus.OK);
	}

	@Operation(summary = "Obtém a lista de atletas a partir do ID de um clube.")
	@ApiResponses(value = {@ApiResponse(responseCode="200", description="Requisição processada com sucesso."),
						   @ApiResponse(responseCode="204", description="Clube não possui nenhum atleta."),
						   @ApiResponse(responseCode="404", description="Clube não encontrado.", content={@Content(mediaType=MediaType.APPLICATION_JSON_VALUE, schema=@Schema(implementation = RespostaPadraoErro.class))})})
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

	@Operation(summary = "Cria um novo clube.")
	@ApiResponses(value = {@ApiResponse(responseCode="201", description="Clube criado com sucesso. A URL do novo recurso é adicionada cabeçalho Location."),
						   @ApiResponse(responseCode="400", description="Parâmetros não informados ou com valores inválidos.", content={@Content(mediaType=MediaType.APPLICATION_JSON_VALUE, schema=@Schema(implementation = RespostaPadraoErro.class))}),
						   @ApiResponse(responseCode="409", description="Já existe um clube com o nome informado.", content={@Content(mediaType=MediaType.APPLICATION_JSON_VALUE, schema=@Schema(implementation = RespostaPadraoErro.class))})})
	@PostMapping(path="/clubes", consumes=MediaType.APPLICATION_JSON_VALUE)
	@Secured("ADMINISTRACAO")
	public ResponseEntity<Void> incluirClube(@Valid @RequestBody ClubePersistenciaDTO clubePersistenciaDTO) {
		ModelMapper mapper = new ModelMapper();
		Clube clube = mapper.map(clubePersistenciaDTO, Clube.class);
		clubeService.incluir(clube);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clube.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@Operation(summary = "Altera um clube existente a partir de seu ID.")
	@ApiResponses(value = {@ApiResponse(responseCode="200", description="Clube alterado com sucesso."),
			   			   @ApiResponse(responseCode="400", description="Parâmetros não informados ou com valores inválidos.", content={@Content(mediaType=MediaType.APPLICATION_JSON_VALUE, schema=@Schema(implementation = RespostaPadraoErro.class))}),
			   			   @ApiResponse(responseCode="404", description="Clube não encontrado.", content={@Content(mediaType=MediaType.APPLICATION_JSON_VALUE, schema=@Schema(implementation = RespostaPadraoErro.class))}),
			   			   @ApiResponse(responseCode="409", description="Já existe outro clube com o nome informado.", content={@Content(mediaType=MediaType.APPLICATION_JSON_VALUE, schema=@Schema(implementation = RespostaPadraoErro.class))})})
	@PutMapping(path="/clubes/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> alterarClube(@PathVariable("id") Long id, @Valid @RequestBody ClubePersistenciaDTO clubePersistenciaDTO) {
		ModelMapper mapper = new ModelMapper();
		Clube clube = mapper.map(clubePersistenciaDTO, Clube.class);
		clube.setId(id);
		clubeService.alterar(clube);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Operation(summary = "Exclui um clube existente a partir de seu ID.")
	@ApiResponses(value = {@ApiResponse(responseCode="200", description="Clube excluído com sucesso."),
						   @ApiResponse(responseCode="409", description="Clube não pode ser excluído, pois possui dependências.", content={@Content(mediaType=MediaType.APPLICATION_JSON_VALUE, schema=@Schema(implementation = RespostaPadraoErro.class))})})
	@DeleteMapping("/clubes/{id}")
	public ResponseEntity<Void> excluirClube(@PathVariable("id") Long id) {
		clubeService.excluir(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
