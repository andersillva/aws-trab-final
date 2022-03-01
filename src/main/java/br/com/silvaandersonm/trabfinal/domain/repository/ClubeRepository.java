package br.com.silvaandersonm.trabfinal.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.silvaandersonm.trabfinal.domain.model.Clube;

public interface ClubeRepository extends JpaRepository<Clube, Long> {

}
