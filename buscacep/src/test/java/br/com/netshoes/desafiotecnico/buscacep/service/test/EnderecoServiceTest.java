package br.com.netshoes.desafiotecnico.buscacep.service.test;

import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.netshoes.desafiotecnico.buscacep.controllers.dto.Cep;
import br.com.netshoes.desafiotecnico.buscacep.model.Endereco;
import br.com.netshoes.desafiotecnico.buscacep.repository.EnderecoRepository;
import br.com.netshoes.desafiotecnico.buscacep.services.EnderecoService;

@SpringBootTest
public class EnderecoServiceTest {
	
	@Mock
	private EnderecoRepository enderecoRepositoryMock;
	
	@InjectMocks
	private EnderecoService enderecoService;
	
	@Test
	public void retornaEnderecoComCepValido() {
		
		Cep cep = new Cep("07190210");
		
		when(enderecoRepositoryMock.findEnderecoByCep_NumeroCep(cep.getNumeroCep())).thenReturn(Optional.of(new Endereco("Rua Jatoba", "Pq. Cecap",
				"Gaurulhos", "SP", new Cep("07190210"))));
		
		Optional<Endereco> enderecoMock = enderecoService.buscarEnderecoPor(cep);
		
		
		Assertions.assertEquals("SP", enderecoMock.get().getEstado());
	}
	
	@Test
	public void retornaNullParaEnderecoComCepInexistenteMesmoAposTratamentoDeCep() {
		String cepInexistente = "12345678";
		when(enderecoRepositoryMock.findEnderecoByCep_NumeroCep("100000000")).thenReturn(null);
		
		Optional<Endereco> enderecoMock = enderecoService.buscarEnderecoPor(new Cep(cepInexistente));
		Assertions.assertThrows(NoSuchElementException.class, () -> {
			enderecoMock.get();
		});
	}
	
	@Test
	public void retornaEnderecoAposTratamentoDeCepInexistente() {
		
		String numeroCepInexistente = "12345678";
		String numeroCepAposTratamento = "10000000";
		
		when(enderecoRepositoryMock.findEnderecoByCep_NumeroCep(numeroCepAposTratamento)).thenReturn(Optional.of(new Endereco("Rua Jatoba",
				"Pq. Cecap", "Gaurulhos", "SP", new Cep("10000000"))));
		
		Optional<Endereco> enderecoMock = enderecoService.buscarEnderecoPor(new Cep(numeroCepInexistente));
		
		Assertions.assertEquals(true, enderecoMock.isPresent());
	}

}
