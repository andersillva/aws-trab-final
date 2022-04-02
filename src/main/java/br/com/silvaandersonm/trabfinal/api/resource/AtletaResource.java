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

import br.com.silvaandersonm.trabfinal.api.VersaoAPI;
import br.com.silvaandersonm.trabfinal.api.dto.AtletaDTO;
import br.com.silvaandersonm.trabfinal.api.dto.AtletaInclusaoDTO;
import br.com.silvaandersonm.trabfinal.api.dto.AtletaPersistenciaDTO;
import br.com.silvaandersonm.trabfinal.api.dto.AtletaResumoDTO;
import br.com.silvaandersonm.trabfinal.api.exception.RespostaPadraoErro;
import br.com.silvaandersonm.trabfinal.domain.model.Atleta;
import br.com.silvaandersonm.trabfinal.domain.model.Clube;
import br.com.silvaandersonm.trabfinal.domain.service.AtletaService;
import br.com.silvaandersonm.trabfinal.domain.service.ClubeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path=VersaoAPI.URI_BASE_V1, produces=MediaType.APPLICATION_JSON_VALUE)
@ApiResponses(value={@ApiResponse(responseCode="401", description="Usuário não autenticado. O token não foi informado, ou foi informado, mas é inválido.", content={@Content(schema=@Schema(hidden=true))}),
					 @ApiResponse(responseCode="403", description="Usuário não tem permissão para realizar a operação solicitada.", content={@Content(schema=@Schema(hidden=true))}),
					 @ApiResponse(responseCode="500", description="Ocorreu um erro interno no servidor.", content={@Content(mediaType=MediaType.APPLICATION_JSON_VALUE, schema=@Schema(implementation = RespostaPadraoErro.class))})})
public class AtletaResource {

	@Autowired
	private AtletaService atletaService;

	@Autowired
	private ClubeService clubeService;

	@Operation(summary = "Obtém a lista de atletas.")
	@ApiResponses(value = {@ApiResponse(responseCode="200", description="Requisição processada com sucesso."),
						   @ApiResponse(responseCode="204", description="Não existe nenhum atleta cadastrado.")})
	@GetMapping("/atletas")
	public ResponseEntity<List<AtletaResumoDTO>> listarAtletas() {
		List<Atleta> atletas = atletaService.listar();
		if (atletas.size() > 0) {
			ModelMapper mapper = new ModelMapper();
			List<AtletaResumoDTO> atletasResumoDTO = atletas.stream().map(a -> mapper.map(a, AtletaResumoDTO.class)).collect(Collectors.toList());
			return new ResponseEntity<>(atletasResumoDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@Operation(summary = "Obtém um atleta a partir de seu ID.")
	@ApiResponses(value = {@ApiResponse(responseCode="200", description="Requisição processada com sucesso."),
						   @ApiResponse(responseCode="404", description="Atleta não encontrado.", content={@Content(mediaType=MediaType.APPLICATION_JSON_VALUE, schema=@Schema(implementation = RespostaPadraoErro.class))})})
	@GetMapping("/atletas/{id}")
	public ResponseEntity<AtletaDTO> obterAtleta(@PathVariable("id") Long id) {
		Atleta atleta = atletaService.obterPorId(id);
		ModelMapper mapper = new ModelMapper();
		AtletaDTO atletaDTO = mapper.map(atleta, AtletaDTO.class);
		return new ResponseEntity<>(atletaDTO, HttpStatus.OK);
	}

	@Operation(summary = "Cria um novo atleta.")
	@ApiResponses(value = {@ApiResponse(responseCode="201", description="Atleta criado com sucesso. A URL do novo recurso é adicionada cabeçalho Location."),
						   @ApiResponse(responseCode="400", description="Parâmetros não informados ou com valores inválidos.", content={@Content(mediaType=MediaType.APPLICATION_JSON_VALUE, schema=@Schema(implementation = RespostaPadraoErro.class))}),
						   @ApiResponse(responseCode="404", description="Clube não encontrado.", content={@Content(mediaType=MediaType.APPLICATION_JSON_VALUE, schema=@Schema(implementation = RespostaPadraoErro.class))})})
	@PostMapping(path="/atletas", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> incluirAtleta(@Valid @RequestBody AtletaInclusaoDTO atletaInclusaoDTO) {
		ModelMapper mapper = new ModelMapper();
		Atleta atleta = mapper.map(atletaInclusaoDTO, Atleta.class);
		Clube clube = clubeService.obterPorId(atletaInclusaoDTO.getIdClube());
		atleta.setClube(clube);
		atletaService.incluir(atleta);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(atleta.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@Operation(summary = "Altera um atleta existente a partir de seu ID.")
	@ApiResponses(value = {@ApiResponse(responseCode="200", description="Atleta alterado com sucesso."),
			   			   @ApiResponse(responseCode="400", description="Parâmetros não informados ou com valores inválidos.", content={@Content(mediaType=MediaType.APPLICATION_JSON_VALUE, schema=@Schema(implementation = RespostaPadraoErro.class))}),
			   			   @ApiResponse(responseCode="404", description="Atleta ou clube não encontrado.", content={@Content(mediaType=MediaType.APPLICATION_JSON_VALUE, schema=@Schema(implementation = RespostaPadraoErro.class))})})
	@PutMapping(path="/atletas/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> alterarAtleta(@PathVariable("id") Long id, @Valid @RequestBody AtletaPersistenciaDTO atletaPersistenciaDTO) {
		ModelMapper mapper = new ModelMapper();
		Atleta atleta = mapper.map(atletaPersistenciaDTO, Atleta.class);
		Clube clube = atletaService.obterPorId(id).getClube();
		atleta.setClube(clube);
		atleta.setId(id);
		atletaService.alterar(atleta);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Operation(summary = "Exclui um atleta existente a partir de seu ID.")
	@ApiResponses(value = {@ApiResponse(responseCode="200", description="Atleta excluído com sucesso."),
						   @ApiResponse(responseCode="409", description="Atleta não pode ser excluído, pois possui dependências.", content={@Content(mediaType=MediaType.APPLICATION_JSON_VALUE, schema=@Schema(implementation = RespostaPadraoErro.class))})})
	@DeleteMapping("/atletas/{id}")
	public ResponseEntity<Void> excluirAtleta(@PathVariable("id") Long id) {
		atletaService.excluir(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
