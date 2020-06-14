# Desafio Técnico - Netshoes

O desafio consiste em criar um serviço de Busca de CEP que deve retornar um Endereço com as informações de Rua, Bairro, Cidade e Estado.

**Exemplo:**

Request:
{
	“numeroCep” : “04003010”
}

Response:
{
	“rua” : “Rua Leôncio de Carvalho”,
	“bairro” : “Paraiso”,
	“cidade” : “São Paulo”,
	“estado” : “SP”
}

## Linguagem e Tecnologias Utilizadas:

O serviço foi desenvolvido em SpringBoot utilizando a linguagem Java 8. Além disso, utilizei o banco em memória H2 para armazenar os dados de endereço
e CEP, de modo que fosse possível a realização de testes via ferramenta Postman e também para realização de testes integrados. 

O Maven foi utilizado para gerenciar as dependências. As bibliotecas utilizadas foram SpringBoot Starter Web/Test, Data JPA, DevTools, Lombok, H2, além do SpringFox para documentação Swagger e o Mockito para os testes. 


## Arquitetura do Serviço e Padrões de Projeto

Aproveitando os “stereotypes” do Spring, construi o serviço em 3 camadas distintas:

- ### Camada de Apresentação:

Aqui defini o controller da aplicação chamado de EnderecoController, anotado com @RestController. Esse controller é responsável por receber as requisições de entrada e está mapeado com a URI “/endereco”. Nessa classe há um único método mapeado com GET que é o responsável por buscar o Endereço, e recebe como 
parâmetro de entrada um CEP.

Como boa prática, criei um EnderecoDTO para encapsular o retorno do método buscaEnderecoPor(cep), de modo que fossem retornadas apenas as informações especificadas da descrição do desafio (RUA, BAIRRO, CIDADE, ESTADO).

Criei um ValidationHandler(@ControllerAdvice) para tratar a validação do CEP na entrada.

- ### Camada de Negócio:

Como boa pratica, criei um EnderecoService, anotado com @Service para armazenar os métodos responsáveis pelas regras de negócio do serviço. Entre os métodos criados nessa classe está o tratarCep(cep). Essa classe também efetua o “meio campo” entre a camada de apresentação e de persistência.

- ### Camada de Persistência:

Por último, criei a interface EnderecoRepository responsável por realizar as operações com o banco H2 já mencionado. Herdei o comportamento da interface JpaRepository para aproveitar todos os benefícios que o SpringData oferece.

Criei uma classe de modelo chamada “Endereco” para armazenar as informações de RUA, BAIRRO, CIDADE, ESTADO e CEP. Para o mapeamento objeto-relacional utilizei Hibernate/JPA.

## Testes

No pacote de testes me preocupei em criar 3 classes distintas para testar o serviço.

Na classe **EnderecoServiceTest**, criei os testes unitários utilizando JUnit 5 simulando alguns cenários de dados de entrada (CEP válido, inválido, não formatado, não encontrado) . O objetivo era simular o comportamento dos métodos da classe EnderecoService e sua interação com a interface EnderecoRepository, e para isso utilizei Mocks.

Na classe **EnderecoControllerTest**, o objetivo era simular o comportamento do método buscaEnderecoPor(cep) para vários cenários também utilizando dados mocados.

Por último, a classe **EnderecoControllerIntegratioTest** contém testes simulando a integração com todas as camadas do serviço e também com o banco de dados H2 criado em memória  e já populado com dados fake de Endereco e CEP.  Nessa classe foi possível criar mais cenários e simular comportamentos do serviço desde a entrada do cep até o retorno do Endereço.

**Obs:** O banco H2 foi criado e populado apenas para facilitar o testes desse serviço.

## Configurações Adicionais e Extras

Criei um script SQL **“import.sql”** carregado no start da aplicação para popular as tabelas do banco H2 de modo que fosse possível testar as requisições e efetuar os testes integrados.

Também construi uma documentação da API utilizando o **Swagger**:
http://localhost:8080/swagger-ui.html

Há um **Dockerfile** simples para gerar uma imagem docker do serviço.
