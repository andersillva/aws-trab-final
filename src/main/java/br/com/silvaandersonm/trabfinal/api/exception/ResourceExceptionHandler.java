package br.com.silvaandersonm.trabfinal.api.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.silvaandersonm.trabfinal.domain.service.exception.AtletaNaoContidoClubeOrigemException;
import br.com.silvaandersonm.trabfinal.domain.service.exception.ParametroRequeridoException;
import br.com.silvaandersonm.trabfinal.domain.service.exception.RegistroDuplicadoException;
import br.com.silvaandersonm.trabfinal.domain.service.exception.RegistroNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(RegistroNaoEncontradoException.class)
	public ResponseEntity<RespostaPadraoInsucesso> responderErro(RegistroNaoEncontradoException e, HttpServletRequest request){
		RespostaPadraoInsucesso resposta = new RespostaPadraoInsucesso(HttpStatus.NOT_FOUND.value(), e.getMessage()); 
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<RespostaPadraoInsucesso> responderErro(MethodArgumentNotValidException e, HttpServletRequest request){
		RespostaPadraoInsucesso resposta = new RespostaPadraoInsucesso(HttpStatus.BAD_REQUEST.value(), "Parâmetro(s) obrigatório(s) não informado(s)."); 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
	}

	@ExceptionHandler(ParametroRequeridoException.class)
	public ResponseEntity<RespostaPadraoInsucesso> responderErro(ParametroRequeridoException e, HttpServletRequest request){
		RespostaPadraoInsucesso resposta = new RespostaPadraoInsucesso(HttpStatus.BAD_REQUEST.value(), e.getMessage()); 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
	}

	@ExceptionHandler(RegistroDuplicadoException.class)
	public ResponseEntity<RespostaPadraoInsucesso> responderErro(RegistroDuplicadoException e, HttpServletRequest request){
		RespostaPadraoInsucesso resposta = new RespostaPadraoInsucesso(HttpStatus.BAD_REQUEST.value(), e.getMessage()); 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
	}

	@ExceptionHandler(AtletaNaoContidoClubeOrigemException.class)
	public ResponseEntity<RespostaPadraoInsucesso> responderErro(AtletaNaoContidoClubeOrigemException e, HttpServletRequest request){
		RespostaPadraoInsucesso resposta = new RespostaPadraoInsucesso(HttpStatus.BAD_REQUEST.value(), e.getMessage()); 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<RespostaPadraoInsucesso> responderErro(Exception e, HttpServletRequest request){
		Throwable resultCause = getResultCause(e);
	    if (resultCause instanceof SQLIntegrityConstraintViolationException) {
			RespostaPadraoInsucesso resposta = new RespostaPadraoInsucesso(HttpStatus.BAD_REQUEST.value(), "Registro não pode ser excluído, pois possui dependências."); 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
	    } else {
	    	//RespostaPadraoInsucesso resposta = new RespostaPadraoInsucesso(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocorreu um erro interno no servidor. Por favor, tente novamente mais tarde.");
	    	RespostaPadraoInsucesso resposta = new RespostaPadraoInsucesso(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
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
