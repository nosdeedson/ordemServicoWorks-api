package br.com.edson.ordemServicoWorksapi.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.edson.ordemServicoWorksapi.exception.BusinessException;
import br.com.edson.ordemServicoWorksapi.exception.NaoEncontrado;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		final var excecao = new Excecao(status.value(), OffsetDateTime.now(), "Um ou mais campos estão inválidos.");
		var campos = new ArrayList<CampoComErro>();
		
		for (ObjectError erro : ex.getBindingResult().getAllErrors()) {
			String mensagem = erro.getDefaultMessage();
			String atributo = ((FieldError)erro).getField();
			
			campos.add(new CampoComErro(atributo, mensagem));
		}
		
		excecao.setCampos(campos);
		return super.handleExceptionInternal(ex, excecao, headers, status, request);
	}
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> handlerBusinessException(BusinessException ex, WebRequest request) {
		var excecao = new Excecao(HttpStatus.BAD_REQUEST.value(), OffsetDateTime.now(),
				ex.getMessage());
		return handleExceptionInternal(ex, excecao, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(NaoEncontrado.class)
	public ResponseEntity<?> handlerNaoEncontradaException( NaoEncontrado ex, WebRequest request){
		var excecao = new Excecao(HttpStatus.NOT_FOUND.value(), OffsetDateTime.now(),  ex.getMessage());
		
		return handleExceptionInternal(ex, excecao, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
