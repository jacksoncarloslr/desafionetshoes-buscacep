package br.com.netshoes.desafiotecnico.buscacep.validation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErroValidacao {
	
	private String campo;
	private String mensagem;
	
}
