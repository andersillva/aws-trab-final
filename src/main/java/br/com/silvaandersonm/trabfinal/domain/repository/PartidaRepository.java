package br.com.silvaandersonm.trabfinal.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.silvaandersonm.trabfinal.domain.model.Partida;

public interface PartidaRepository extends JpaRepository<Partida, Long> {

	@Query("select p from Partida p " +
		   "join p.torneio t " +
		   "join p.clubeMandante cm " +
	       "join p.clubeVisitante cv " +
		   "where t.id = :idTorneio")
	public List<Partida> listarPorTorneio(Long idTorneio);

	@Query("select p from Partida p " +
	       "join p.torneio t " +
		   "join p.clubeMandante cm " +
	       "join p.clubeVisitante cv " +
		   "where p.id = :id")
	public Optional<Partida> obterPorId(Long id);

}
