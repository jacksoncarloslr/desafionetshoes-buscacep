package br.com.netshoes.desafiotecnico.buscacep.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.netshoes.desafiotecnico.buscacep.model.Endereco;


@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	Optional<Endereco> findEnderecoByCep_NumeroCep(String numero);

	
}
