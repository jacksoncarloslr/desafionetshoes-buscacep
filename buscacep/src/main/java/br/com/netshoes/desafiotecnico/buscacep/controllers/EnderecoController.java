package br.com.netshoes.desafiotecnico.buscacep.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.netshoes.desafiotecnico.buscacep.controllers.dto.Cep;
import br.com.netshoes.desafiotecnico.buscacep.controllers.dto.EnderecoDTO;
import br.com.netshoes.desafiotecnico.buscacep.model.Endereco;
import br.com.netshoes.desafiotecnico.buscacep.services.EnderecoService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@ApiOperation("Retorna um endereço, dado um CEP válido")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EnderecoDTO> buscaEnderecoPor(@Valid @RequestBody Cep cep) {
		Optional<Endereco> enderecoBase = enderecoService.buscarEnderecoPor(cep);
		return enderecoBase.isPresent() ? ResponseEntity.ok(enderecoBase.get().toDTO()) : ResponseEntity.notFound().build();
	}

}
