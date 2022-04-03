package br.com.andersillva.trabfinal.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.andersillva.trabfinal.domain.model.Participante;

public interface ParticipanteRepository extends JpaRepository<Participante, Long> {

	@Query("select p from Participante p " +
	       "join p.torneio t " +
	       "join p.clube c " +
		   "where p.id = :id")
	public Optional<Participante> obterPorId(Long id);

	@Query("select p from Participante p " +
	       "join p.torneio t " +
	       "join p.clube c " +
		   "where t.id = :idTorneio " +
	       "and c.id = :idClube")
	public Optional<Participante> obterPorChave(Long idTorneio, Long idClube);

	@Query("select p from Participante p " +
		       "join p.torneio t " +
		       "join p.clube c " +
			   "where t.id = :idTorneio")
	public List<Participante> listarPorTorneio(Long idTorneio);

}
