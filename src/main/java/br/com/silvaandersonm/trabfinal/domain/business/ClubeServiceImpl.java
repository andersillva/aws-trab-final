package br.com.silvaandersonm.trabfinal.domain.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.silvaandersonm.trabfinal.domain.business.exception.BusinessException;
import br.com.silvaandersonm.trabfinal.domain.business.exception.BusinessExceptionType;
import br.com.silvaandersonm.trabfinal.domain.model.Atleta;
import br.com.silvaandersonm.trabfinal.domain.model.Clube;
import br.com.silvaandersonm.trabfinal.domain.repository.ClubeRepository;

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
		if (clube == null || clube.getId() == null)
			throw new BusinessException(BusinessExceptionType.PARAMETROS_OBRIGATORIOS_NAO_INFORMADOS);

		if (clubeRepository.existsById(clube.getId())) {
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
	public List<Atleta> listarAtletas(Long idClube) throws BusinessException {
		Clube clube = obterPorId(idClube);
		if (clube == null) {
			throw new BusinessException(BusinessExceptionType.REGISTRO_NAO_ENCONTRADO);
		}
		return clubeRepository.listarAtletas(idClube);
	}

}
