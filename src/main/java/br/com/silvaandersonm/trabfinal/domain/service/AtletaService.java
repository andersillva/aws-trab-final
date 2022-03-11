package br.com.silvaandersonm.trabfinal.domain.service;

import java.util.List;

import br.com.silvaandersonm.trabfinal.domain.model.Atleta;

public interface AtletaService {

	public void incluir(Atleta clube);

	public void alterar(Atleta clube);

	public Atleta obterPorId(Long id);

	public List<Atleta> listar();

	public void excluir(Long id);

}
