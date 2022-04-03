package br.com.andersillva.trabfinal.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.andersillva.trabfinal.domain.model.Atleta;

public interface AtletaRepository extends JpaRepository<Atleta, Long> {

	@Query("select a from Atleta a join a.clube c")
	public List<Atleta> listar();

	@Query("select a from Atleta a join a.clube c where a.id = :id")
	public Optional<Atleta> obterPorId(Long id);

}
