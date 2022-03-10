package br.com.silvaandersonm.trabfinal.domain.service;

import java.util.List;

import br.com.silvaandersonm.trabfinal.domain.model.Atleta;
import br.com.silvaandersonm.trabfinal.domain.model.Clube;

public interface ClubeService {

	public void incluir(Clube clube);

	public void alterar(Clube clube);

	public Clube obterPorId(Long id);

	public List<Clube> listar();

	public List<Atleta> listarAtletas(Long idClube);

}
