package br.com.silvaandersonm.trabfinal.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.silvaandersonm.trabfinal.domain.model.Atleta;
import br.com.silvaandersonm.trabfinal.domain.model.Clube;

public interface ClubeRepository extends JpaRepository<Clube, Long> {

	@Query("select a from Atleta a join a.clube c where c.id = :idClube")
	public List<Atleta> listarAtletas(Long idClube);

}
