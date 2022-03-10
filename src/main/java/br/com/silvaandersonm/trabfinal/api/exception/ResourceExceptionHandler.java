package br.com.silvaandersonm.trabfinal.api.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.silvaandersonm.trabfinal.domain.service.exception.ParametroRequeridoException;
import br.com.silvaandersonm.trabfinal.domain.service.exception.RegistroNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(RegistroNaoEncontradoException.class)
	public ResponseEntity<RetornoPadraoErro> resourceNotFound(RegistroNaoEncontradoException e, HttpServletRequest request){
		RetornoPadraoErro erro = new RetornoPadraoErro(HttpStatus.NOT_FOUND.value(), e.getMessage()); 
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);		
	}

	@ExceptionHandler(ParametroRequeridoException.class)
	public ResponseEntity<RetornoPadraoErro> missingParameters(ParametroRequeridoException e, HttpServletRequest request){
		RetornoPadraoErro erro = new RetornoPadraoErro(HttpStatus.BAD_REQUEST.value(), e.getMessage()); 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);		
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<RetornoPadraoErro> internalError(Exception e, HttpServletRequest request){
		RetornoPadraoErro erro = new RetornoPadraoErro(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocorreu um erro interno no servidor. Por favor, tente novamente mais tarde."); 
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);		
	}

}
