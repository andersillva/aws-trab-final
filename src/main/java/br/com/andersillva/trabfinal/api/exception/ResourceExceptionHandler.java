package br.com.andersillva.trabfinal.api.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.andersillva.trabfinal.domain.service.exception.AtletaNaoContidoClubeOrigemException;
import br.com.andersillva.trabfinal.domain.service.exception.ParametroRequeridoException;
import br.com.andersillva.trabfinal.domain.service.exception.RegistroDuplicadoException;
import br.com.andersillva.trabfinal.domain.service.exception.RegistroNaoEncontradoException;
import br.com.andersillva.trabfinal.domain.service.exception.ValorForaDominioException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(RegistroNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public RespostaPadraoErro responderErro(RegistroNaoEncontradoException e, HttpServletRequest request){
		RespostaPadraoErro resposta = new RespostaPadraoErro(HttpStatus.NOT_FOUND.value(), e.getMessage()); 
		return resposta;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public RespostaPadraoErro responderErro(MethodArgumentNotValidException e, HttpServletRequest request){
		RespostaPadraoErro resposta = new RespostaPadraoErro(HttpStatus.BAD_REQUEST.value(), "Parâmetro(s) obrigatório(s) não informado(s)."); 
		return resposta;
	}

	@ExceptionHandler(ParametroRequeridoException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public RespostaPadraoErro responderErro(ParametroRequeridoException e, HttpServletRequest request){
		RespostaPadraoErro resposta = new RespostaPadraoErro(HttpStatus.BAD_REQUEST.value(), e.getMessage()); 
		return resposta;
	}

	@ExceptionHandler(RegistroDuplicadoException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	@ResponseBody
	public RespostaPadraoErro responderErro(RegistroDuplicadoException e, HttpServletRequest request){
		RespostaPadraoErro resposta = new RespostaPadraoErro(HttpStatus.CONFLICT.value(), e.getMessage()); 
		return resposta;
	}

	@ExceptionHandler(AtletaNaoContidoClubeOrigemException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public RespostaPadraoErro responderErro(AtletaNaoContidoClubeOrigemException e, HttpServletRequest request){
		RespostaPadraoErro resposta = new RespostaPadraoErro(HttpStatus.BAD_REQUEST.value(), e.getMessage()); 
		return resposta;
	}

	@ExceptionHandler(ValorForaDominioException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public RespostaPadraoErro responderErro(ValorForaDominioException e, HttpServletRequest request){
		RespostaPadraoErro resposta = new RespostaPadraoErro(HttpStatus.BAD_REQUEST.value(), e.getMessage()); 
		return resposta;
	}

	@ExceptionHandler(NumberFormatException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public RespostaPadraoErro responderErro(NumberFormatException e, HttpServletRequest request){
		RespostaPadraoErro resposta = new RespostaPadraoErro(HttpStatus.BAD_REQUEST.value(), e.getMessage()); 
		return resposta;
	}

	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public RespostaPadraoErro responderErro(BadCredentialsException e, HttpServletRequest request){
		RespostaPadraoErro resposta = new RespostaPadraoErro(HttpStatus.NOT_FOUND.value(), "Usuário ou senha inválidos.");
		return resposta;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<RespostaPadraoErro> responderErro(Exception e, HttpServletRequest request){
		Throwable resultCause = getResultCause(e);
	    if (resultCause instanceof SQLIntegrityConstraintViolationException) {
			RespostaPadraoErro resposta = new RespostaPadraoErro(HttpStatus.CONFLICT.value(), "Registro não pode ser excluído, pois possui dependências."); 
			return ResponseEntity.status(HttpStatus.CONFLICT).body(resposta);
	    } else {
	    	RespostaPadraoErro resposta = new RespostaPadraoErro(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocorreu um erro interno no servidor. Por favor, tente novamente mais tarde.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resposta);
	    }

	}

	private Throwable getResultCause(Exception e) {
		Throwable cause, resultCause = e;
	    while ((cause = resultCause.getCause()) != null && resultCause != cause) {
	        resultCause = cause;
	    }
	    return resultCause;
	}

}
