package br.com.netshoes.desafiotecnico.buscacep.controllers.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.netshoes.desafiotecnico.buscacep.controllers.EnderecoController;
import br.com.netshoes.desafiotecnico.buscacep.controllers.dto.Cep;
import br.com.netshoes.desafiotecnico.buscacep.controllers.dto.EnderecoDTO;
import br.com.netshoes.desafiotecnico.buscacep.model.Endereco;
import br.com.netshoes.desafiotecnico.buscacep.repository.EnderecoRepository;
import br.com.netshoes.desafiotecnico.buscacep.services.EnderecoService;

@SpringBootTest
public class EnderecoControllerTest {

	@InjectMocks
	private EnderecoController enderecoController;
	
	@Mock
	private EnderecoService enderecoService;
	
	@Mock
	private EnderecoRepository enderecoRepository;
	
	@BeforeEach
	public void setUp() {
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	}
	
	@Test
	public void retornaEnderecoEStatusOkDadoUmCepValido() {
		
		Cep cep = new Cep("04003010");
		Endereco endereco = new Endereco();
		endereco.setRua("Rua Leôncio de Carvalho");
		endereco.setCep(cep);
		
		when(enderecoService.buscarEnderecoPor(cep)).thenReturn(Optional.of(endereco));
		when(enderecoRepository.findEnderecoByCep_NumeroCep(cep.getNumeroCep())).thenReturn(Optional.of(endereco));
		
		
		ResponseEntity<EnderecoDTO> enderecoRetornado = enderecoController.buscaEnderecoPor(cep);
		
		assertThat(enderecoRetornado.getStatusCode().is2xxSuccessful());
		Assertions.assertEquals(enderecoRetornado.getBody().getRua(), "Rua Leôncio de Carvalho");
	}
	
	@Test
	public void retorna404QuandoNaoEncontraEnderecoDadoUmCepValido() {
		
		Cep cep = new Cep("07190210");
		
		when(enderecoService.buscarEnderecoPor(cep)).thenReturn(Optional.ofNullable(null));
		when(enderecoRepository.findEnderecoByCep_NumeroCep(cep.getNumeroCep())).thenReturn(null);
		
		
		ResponseEntity<EnderecoDTO> enderecoRetornado = enderecoController.buscaEnderecoPor(cep);
		
		assertEquals(enderecoRetornado.getStatusCode().value(), 404);

	}
}
