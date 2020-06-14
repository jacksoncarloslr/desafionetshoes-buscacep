package br.com.netshoes.desafiotecnico.buscacep.controllers.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EnderecoDTO {
	
	private String rua;
	private String bairro;
	private String cidade;
	private String estado;
	
	@JsonIgnore
	private String cep;

	public EnderecoDTO(String rua, String bairro, String cidade, String estado) {
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}

}
