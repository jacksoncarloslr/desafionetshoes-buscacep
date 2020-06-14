package br.com.netshoes.desafiotecnico.buscacep.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroValidacao> validacaoDeCamposHandler(MethodArgumentNotValidException exception) {
		List<FieldError> fieldError = exception.getBindingResult().getFieldErrors();
		List<ErroValidacao> erroValidacaoList = new ArrayList<ErroValidacao>();
		
		fieldError.forEach(erro ->{
			String mensagem = messageSource.getMessage(erro, LocaleContextHolder.getLocale());
			ErroValidacao erroValidacao = new ErroValidacao(erro.getField(), mensagem);
			erroValidacaoList.add(erroValidacao);
		});
		
		return erroValidacaoList;
	}
}
