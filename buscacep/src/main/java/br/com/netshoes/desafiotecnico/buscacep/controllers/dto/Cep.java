
package br.com.netshoes.desafiotecnico.buscacep.controllers.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
@Entity
public class Cep {
	
	@ApiModelProperty(hidden = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ApiModelProperty(value = "CEP", example = "07190-210")
	@NotNull
	@Pattern(regexp = "\\d{8}", message = "CEP inválido")
	@Column(name = "numero_cep")
	public String numeroCep;
	
	public Cep(String numeroCep) {
		this.numeroCep = numeroCep;
	}

}
