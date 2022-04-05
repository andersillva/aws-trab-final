package br.com.andersillva.trabfinal.api.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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

import br.com.andersillva.trabfinal.api.dto.ClubeAtletaDTO;
import br.com.andersillva.trabfinal.api.dto.ClubeDTO;
import br.com.andersillva.trabfinal.api.dto.ClubePersistenciaDTO;
import br.com.andersillva.trabfinal.api.dto.ClubeResumoDTO;
import br.com.andersillva.trabfinal.api.exception.RespostaPadraoErro;
import br.com.andersillva.trabfinal.api.util.ConstantesSwagger;
import br.com.andersillva.trabfinal.api.util.DTOFactory;
import br.com.andersillva.trabfinal.api.util.EntityFactory;
import br.com.andersillva.trabfinal.api.util.VersaoAPI;
import br.com.andersillva.trabfinal.domain.model.Atleta;
import br.com.andersillva.trabfinal.domain.model.Clube;
import br.com.andersillva.trabfinal.domain.service.ClubeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path=VersaoAPI.URI_BASE_V1, produces=MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value={@ApiResponse(responseCode=ConstantesSwagger.UNAUTHORIZED, description=ConstantesSwagger.UNAUTHORIZED_DESCRIPTION, content={@Content(schema=@Schema(hidden=true))}),
		 			 @ApiResponse(responseCode=ConstantesSwagger.FORBIDDEN, description=ConstantesSwagger.FORBIDDEN_DESCRIPTION, content={@Content(schema=@Schema(hidden=true))}),
		 			 @ApiResponse(responseCode=ConstantesSwagger.INTERNAL_SERVER_ERROR, description=ConstantesSwagger.INTERNAL_SERVER_ERROR_DESCRIPTION, content={@Content(mediaType=MediaType.APPLICATION_JSON_VALUE, schema=@Schema(implementation = RespostaPadraoErro.class))})})
public class ClubeResource {

	@Autowired
	private ClubeService clubeService;

	@Operation(summary = "Obtém a lista de clubes.")
	@ApiResponses(value = {@ApiResponse(responseCode=ConstantesSwagger.OK, description="Requisição processada com sucesso."),
						   @ApiResponse(responseCode=ConstantesSwagger.NO_CONTENT, description="Não existe nenhum clube cadastrado.")})
	@GetMapping("/clubes")
	public ResponseEntity<List<ClubeResumoDTO>> listarClubes() {
		List<Clube> clubes = clubeService.listar();
		if (clubes.size() > 0) {
			List<ClubeResumoDTO> clubesResumoDTO = DTOFactory.getClubeResumoDTO(clubes);
			return new ResponseEntity<>(clubesResumoDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@Operation(summary = "Obtém um clube a partir de seu ID.")
	@ApiResponses(value = {@ApiResponse(responseCode=ConstantesSwagger.OK, description="Requisição processada com sucesso."),
						   @ApiResponse(responseCode=ConstantesSwagger.NOT_FOUND, description="Clube não encontrado.", content={@Content(mediaType=MediaType.APPLICATION_JSON_VALUE, schema=@Schema(implementation = RespostaPadraoErro.class))})})
	@GetMapping("/clubes/{id}")
	public ResponseEntity<ClubeDTO> obterClube(@PathVariable("id") Long id) {
		Clube clube = clubeService.obterPorId(id);
		ClubeDTO clubeDTO = DTOFactory.getClubeDTO(clube);
		return new ResponseEntity<>(clubeDTO, HttpStatus.OK);
	}

	@Operation(summary = "Obtém a lista de atletas a partir do ID de um clube.")
	@ApiResponses(value = {@ApiResponse(responseCode=ConstantesSwagger.OK, description="Requisição processada com sucesso."),
						   @ApiResponse(responseCode=ConstantesSwagger.NO_CONTENT, description="Clube não possui nenhum atleta."),
						   @ApiResponse(responseCode=ConstantesSwagger.NOT_FOUND, description="Clube não encontrado.", content={@Content(mediaType=MediaType.APPLICATION_JSON_VALUE, schema=@Schema(implementation = RespostaPadraoErro.class))})})
	@GetMapping("/clubes/{id}/atletas")
	public ResponseEntity<List<ClubeAtletaDTO>> listarAtletas(@PathVariable("id") Long idClube) {
		List<Atleta> atletas = clubeService.listarAtletas(idClube);
		if (atletas.size() > 0) {
			List<ClubeAtletaDTO> clubeAtletaDTO = DTOFactory.getClubeAtletaDTO(atletas);
			return new ResponseEntity<>(clubeAtletaDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@Operation(summary = "Cria um novo clube.")
	@ApiResponses(value = {@ApiResponse(responseCode=ConstantesSwagger.CREATED, description="Clube criado com sucesso. A URL do novo recurso é adicionada cabeçalho Location."),
						   @ApiResponse(responseCode=ConstantesSwagger.BAD_REQUEST, description=ConstantesSwagger.BAD_REQUEST_DESCRIPTION, content={@Content(mediaType=MediaType.APPLICATION_JSON_VALUE, schema=@Schema(implementation = RespostaPadraoErro.class))}),
						   @ApiResponse(responseCode=ConstantesSwagger.CONFLICT, description="Já existe um clube com o nome informado.", content={@Content(mediaType=MediaType.APPLICATION_JSON_VALUE, schema=@Schema(implementation = RespostaPadraoErro.class))})})
	@PostMapping(path="/clubes", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> incluirClube(@Valid @RequestBody ClubePersistenciaDTO clubePersistenciaDTO) {
		Clube clube = EntityFactory.getClube(clubePersistenciaDTO);
		clubeService.incluir(clube);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clube.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@Operation(summary = "Altera um clube existente a partir de seu ID.")
	@ApiResponses(value = {@ApiResponse(responseCode=ConstantesSwagger.OK, description="Clube alterado com sucesso."),
			   			   @ApiResponse(responseCode=ConstantesSwagger.BAD_REQUEST, description=ConstantesSwagger.BAD_REQUEST_DESCRIPTION, content={@Content(mediaType=MediaType.APPLICATION_JSON_VALUE, schema=@Schema(implementation = RespostaPadraoErro.class))}),
			   			   @ApiResponse(responseCode=ConstantesSwagger.NOT_FOUND, description="Clube não encontrado.", content={@Content(mediaType=MediaType.APPLICATION_JSON_VALUE, schema=@Schema(implementation = RespostaPadraoErro.class))}),
			   			   @ApiResponse(responseCode=ConstantesSwagger.CONFLICT, description="Já existe outro clube com o nome informado.", content={@Content(mediaType=MediaType.APPLICATION_JSON_VALUE, schema=@Schema(implementation = RespostaPadraoErro.class))})})
	@PutMapping(path="/clubes/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> alterarClube(@PathVariable("id") Long id, @Valid @RequestBody ClubePersistenciaDTO clubePersistenciaDTO) {
		Clube clube = EntityFactory.getClube(clubePersistenciaDTO);
		clube.setId(id);
		clubeService.alterar(clube);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Operation(summary = "Exclui um clube existente a partir de seu ID.")
	@ApiResponses(value = {@ApiResponse(responseCode=ConstantesSwagger.OK, description="Clube excluído com sucesso."),
						   @ApiResponse(responseCode=ConstantesSwagger.CONFLICT, description="Clube não pode ser excluído, pois possui dependências.", content={@Content(mediaType=MediaType.APPLICATION_JSON_VALUE, schema=@Schema(implementation = RespostaPadraoErro.class))})})
	@DeleteMapping("/clubes/{id}")
	public ResponseEntity<Void> excluirClube(@PathVariable("id") Long id) {
		clubeService.excluir(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
