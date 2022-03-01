package br.com.silvaandersonm.trabfinal.domain.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.silvaandersonm.trabfinal.domain.model.Atleta;
import br.com.silvaandersonm.trabfinal.domain.model.Clube;
import br.com.silvaandersonm.trabfinal.domain.repository.ClubeRepository;
import br.com.silvaandersonm.trabfinal.util.BusinessException;
import br.com.silvaandersonm.trabfinal.util.BusinessExceptionType;

@Service
public class ClubeServiceImpl implements ClubeService {

	@Autowired
	private ClubeRepository clubeRepository;

	@Override
	public void incluir(Clube clube) {
		clubeRepository.saveAndFlush(clube);
	}

	@Override
	public void alterar(Clube clube) throws BusinessException {
		if (clube.getId() != null && clubeRepository.existsById(clube.getId())) {
			clubeRepository.saveAndFlush(clube);
		} else {
			throw new BusinessException(BusinessExceptionType.REGISTRO_NAO_ENCONTRADO);
		}
	}

	@Override
	public Clube obterPorId(Long id) {
		return clubeRepository.findById(id).orElse(null);
	}

	@Override
	public List<Clube> listar() {
		return clubeRepository.findAll();
	}

	@Override
	public List<Atleta> listarAtletas(Long idClube) {
		// TODO Auto-generated method stub
		return null;
	}

}
