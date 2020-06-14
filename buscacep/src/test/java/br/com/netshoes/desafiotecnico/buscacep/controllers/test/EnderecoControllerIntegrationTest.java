package br.com.netshoes.desafiotecnico.buscacep.controllers.test;

import java.nio.charset.Charset;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.netshoes.desafiotecnico.buscacep.controllers.dto.Cep;
import br.com.netshoes.desafiotecnico.buscacep.controllers.dto.EnderecoDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class EnderecoControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), 
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	
	@Test
	public void retornaEnderecoComCepValido() throws Exception {
		
		Cep cep = new Cep("04003010");
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/endereco")
				.contentType(APPLICATION_JSON_UTF8)
				.content(asJsonString(cep)))
				.andReturn();
				
		EnderecoDTO enderecoDTO = stringToEnderecoDTO(mvcResult.getResponse().getContentAsString());
		
		Assertions.assertEquals(mvcResult.getResponse().getStatus(), 200);
		Assertions.assertEquals("Rua Leoncio de Carvalho", enderecoDTO.getRua());
		
		
	}
	
	@Test
	public void retornaEnderecoComCepValidoAposTratamentoDeCepNaoEncontrado() throws Exception{
		Cep cep = new Cep("11111111");
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/endereco")
				.contentType(APPLICATION_JSON_UTF8)
				.content(asJsonString(cep)))
				.andReturn();
				
		Assertions.assertEquals(mvcResult.getResponse().getStatus(), 200);
		Assertions.assertTrue(mvcResult.getResponse().getContentAsString().contains("RJ"));
	}
	
	@Test
	public void retornaMensagemDeCepInvalidoQuandoOCepNaoEstiverNoFormatoEsperado() throws Exception {
		Cep cep = new Cep("04003-010");
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/endereco")
				.contentType(APPLICATION_JSON_UTF8)
				.content(asJsonString(cep)))
				.andReturn();
				
		Assertions.assertEquals(mvcResult.getResponse().getStatus(), 400);
		Assertions.assertTrue(mvcResult.getResponse().getContentAsString().contains("CEP inválido"));
	}
	
	@Test
	public void retornaStatus404ParaEnderecoNaoEncontrado() throws Exception {
		Cep cep = new Cep("99999999");
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/endereco")
				.contentType(APPLICATION_JSON_UTF8)
				.content(asJsonString(cep)))
				.andReturn();
				
		Assertions.assertEquals(mvcResult.getResponse().getStatus(), 404);
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	public static EnderecoDTO stringToEnderecoDTO(String response) {
		try {
			return new ObjectMapper().readValue(response, EnderecoDTO.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
