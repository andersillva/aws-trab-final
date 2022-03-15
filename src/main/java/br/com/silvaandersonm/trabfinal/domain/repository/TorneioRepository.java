package br.com.silvaandersonm.trabfinal.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.silvaandersonm.trabfinal.domain.model.Torneio;

public interface TorneioRepository extends JpaRepository<Torneio, Long> {

}
