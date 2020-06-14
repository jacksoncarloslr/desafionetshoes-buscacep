package br.com.netshoes.desafiotecnico.buscacep.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {
	
	@Bean
	public Docket swaggerDocumentation() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.netshoes.desafiotecnico.buscacep"))
				.paths(PathSelectors.ant("/endereco/**"))
				.build()
				.apiInfo(apiInfo());
	}
	
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("API de Busca de endereços utilizando CEP")
				.description("API que retorna um endereço contendo rua, bairro, cidade e estado, dado um CEP válido")
				.contact(new Contact("Jackson Ramalho", null, "jacksoncarloslr@gmail.com"))
				.build();
	}
}
