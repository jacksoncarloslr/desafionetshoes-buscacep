package br.com.netshoes.desafiotecnico.buscacep.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.netshoes.desafiotecnico.buscacep.controllers.dto.Cep;
import br.com.netshoes.desafiotecnico.buscacep.controllers.dto.EnderecoDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String rua;
	private String bairro;
	private String cidade;
	private String estado;
	
	@OneToOne
	private Cep cep;
	
	public Endereco(String rua, String bairro, String cidade, String estado, Cep cep) {
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
	}

	public EnderecoDTO toDTO() {
		return new EnderecoDTO(rua, bairro, cidade, estado);
	}

	
}
