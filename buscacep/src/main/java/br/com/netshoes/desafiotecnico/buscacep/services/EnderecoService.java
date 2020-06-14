package br.com.netshoes.desafiotecnico.buscacep.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.netshoes.desafiotecnico.buscacep.controllers.dto.Cep;
import br.com.netshoes.desafiotecnico.buscacep.model.Endereco;
import br.com.netshoes.desafiotecnico.buscacep.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Optional<Endereco> buscarEnderecoPor(Cep cep) {
		
		Optional<Endereco> endereco = this.enderecoRepository.findEnderecoByCep_NumeroCep(cep.getNumeroCep());
		
		if(!endereco.isPresent()) {
			
			List<String> cepsTratados = tratarCep(cep.getNumeroCep());
			
			for (String cepTratado : cepsTratados) {
				endereco = this.enderecoRepository.findEnderecoByCep_NumeroCep(cepTratado);
				
				if(endereco.isPresent()) {
					return endereco;
				}
			}
		}
		
		return endereco;
	}

	private List<String> tratarCep(String cep) {
		
		List<String> cepsTratados = new ArrayList<String>();
		StringBuilder cepBuilder = new StringBuilder(cep);
		
		int cepSize = cepBuilder.length();
		int aux = 0;
		
		for(int i = 1; i <= cepSize; i++) {
			
			cepBuilder = cepBuilder.replace(cepSize-i, cepSize-aux, "0");
			cepsTratados.add(cepBuilder.toString());
			aux++;
			
		}
		
		return cepsTratados;
		
	}
}
