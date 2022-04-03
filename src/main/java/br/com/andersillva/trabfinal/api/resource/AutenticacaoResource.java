package br.com.andersillva.trabfinal.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.andersillva.trabfinal.api.VersaoAPI;
import br.com.andersillva.trabfinal.api.dto.AutenticacaoDTO;
import br.com.andersillva.trabfinal.api.dto.TokenDTO;
import br.com.andersillva.trabfinal.api.exception.RespostaPadraoErro;
import br.com.andersillva.trabfinal.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(path=VersaoAPI.URI_BASE_V1, produces=MediaType.APPLICATION_JSON_VALUE)
public class AutenticacaoResource {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping("/login")
	@Operation(summary = "Autentica o usuário para obtenção de um token de acesso.")
	@ApiResponses(value = {@ApiResponse(responseCode="200", description="Requisição processada com sucesso."),
						   @ApiResponse(responseCode="404", description="Usuário ou senha inválidos.", content={@Content(mediaType=MediaType.APPLICATION_JSON_VALUE, schema=@Schema(implementation = RespostaPadraoErro.class))}),
						   @ApiResponse(responseCode="500", description="Ocorreu um erro interno no servidor.", content={@Content(mediaType=MediaType.APPLICATION_JSON_VALUE, schema=@Schema(implementation = RespostaPadraoErro.class))})})
	public ResponseEntity<TokenDTO> auth(@RequestBody @Validated AutenticacaoDTO autenticacaoDTO){
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(autenticacaoDTO.getLogin(), autenticacaoDTO.getSenha());
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		String token = tokenService.generateToken(authentication);
		return ResponseEntity.ok(TokenDTO.builder().type("Bearer").token(token).build());
	}

}
