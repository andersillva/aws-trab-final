package br.com.silvaandersonm.trabfinal.domain.repository;

import java.util.List;

import br.com.silvaandersonm.trabfinal.domain.model.Atleta;

public interface AtletaRepository {

	public Atleta obterPorId(Long id);

	public List<Atleta> listar();

	public void incluir(Atleta atleta);

	public void atualizar(Atleta atleta);

}
