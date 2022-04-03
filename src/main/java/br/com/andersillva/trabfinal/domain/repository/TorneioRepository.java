package br.com.andersillva.trabfinal.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.andersillva.trabfinal.domain.model.Torneio;

public interface TorneioRepository extends JpaRepository<Torneio, Long> {

}
