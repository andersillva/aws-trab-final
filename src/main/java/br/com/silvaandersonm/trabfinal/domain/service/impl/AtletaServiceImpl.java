package br.com.silvaandersonm.trabfinal.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.silvaandersonm.trabfinal.domain.model.Atleta;
import br.com.silvaandersonm.trabfinal.domain.repository.AtletaRepository;
import br.com.silvaandersonm.trabfinal.domain.service.AtletaService;
import br.com.silvaandersonm.trabfinal.domain.service.exception.ParametroRequeridoException;
import br.com.silvaandersonm.trabfinal.domain.service.exception.RegistroNaoEncontradoException;

@Service
public class AtletaServiceImpl implements AtletaService {

	@Autowired
	private AtletaRepository atletaRepository;

	@Override
	public void incluir(Atleta atleta) {
		atleta.setId(null);
		atletaRepository.saveAndFlush(atleta);
	}

	@Override
	public void alterar(Atleta atleta) {
		if (atleta == null || atleta.getId() == null)
			throw new ParametroRequeridoException();

		obterPorId(atleta.getId());
		atletaRepository.saveAndFlush(atleta);
	}

	@Override
	public Atleta obterPorId(Long id) {
		Optional<Atleta> atleta = atletaRepository.findById(id);
		return atleta.orElseThrow(() -> new RegistroNaoEncontradoException());
	}

	@Override
	public List<Atleta> listar() {
		return atletaRepository.findAll();
	}

}
