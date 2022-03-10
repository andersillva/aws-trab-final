package br.com.silvaandersonm.trabfinal.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.silvaandersonm.trabfinal.domain.model.Atleta;

public interface AtletaRepository extends JpaRepository<Atleta, Long> {

}
