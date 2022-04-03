package br.com.andersillva.trabfinal.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.andersillva.trabfinal.domain.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {

	@Query("select e from Evento e " +
		   "join e.partida p " +
		   "where p.id = :idPartida " +
	       "order by e.data desc")
	public List<Evento> listarPorPartida(Long idPartida);

	@Query("select e from Evento e " +
		   "join e.partida p " +
		   "where e.id = :id")
	public Optional<Evento> obterPorId(Long id);

}
