package br.com.andersillva.trabfinal.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.andersillva.trabfinal.domain.model.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

	@Query("select t from Transferencia t join t.clubeOrigem co join t.clubeDestino cd join t.atleta a")
	public List<Transferencia> listar();

	@Query("select t from Transferencia t join t.clubeOrigem co join t.clubeDestino cd join t.atleta a where t.id = :id")
	public Optional<Transferencia> obterPorId(Long id);

}
