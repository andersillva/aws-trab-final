package br.com.silvaandersonm.trabfinal.api.resource;

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

import br.com.silvaandersonm.trabfinal.api.VersaoAPI;
import br.com.silvaandersonm.trabfinal.api.dto.AutenticacaoDTO;
import br.com.silvaandersonm.trabfinal.api.dto.TokenDTO;
import br.com.silvaandersonm.trabfinal.seguranca.TokenService;

@RestController
@RequestMapping(path=VersaoAPI.URI_BASE_V1, produces=MediaType.APPLICATION_JSON_VALUE)
public class AutenticacaoResource {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity<TokenDTO> auth(@RequestBody @Validated AutenticacaoDTO autenticacaoDTO){

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(autenticacaoDTO.getLogin(), autenticacaoDTO.getSenha());
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		String token = tokenService.generateToken(authentication);
		return ResponseEntity.ok(TokenDTO.builder().type("Bearer").token(token).build());
		
	}

}
