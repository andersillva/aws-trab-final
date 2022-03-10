package br.com.silvaandersonm.trabfinal.domain.business;

import java.util.List;

import br.com.silvaandersonm.trabfinal.domain.business.exception.BusinessException;
import br.com.silvaandersonm.trabfinal.domain.model.Atleta;
import br.com.silvaandersonm.trabfinal.domain.model.Clube;

public interface ClubeService {

	public void incluir(Clube clube);
	public void alterar(Clube clube) throws BusinessException;
	public Clube obterPorId(Long id);
	public List<Clube> listar();
	public List<Atleta> listarAtletas(Long idClube) throws BusinessException;

}
