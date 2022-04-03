package br.com.andersillva.trabfinal.domain.service;

import java.util.List;

import br.com.andersillva.trabfinal.domain.model.Atleta;

public interface AtletaService {

	public void incluir(Atleta clube);

	public void alterar(Atleta clube);

	public Atleta obterPorId(Long id);

	public List<Atleta> listar();

	public void excluir(Long id);

}
