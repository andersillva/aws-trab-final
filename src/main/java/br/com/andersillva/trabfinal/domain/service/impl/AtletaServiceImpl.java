package br.com.andersillva.trabfinal.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.andersillva.trabfinal.domain.model.Atleta;
import br.com.andersillva.trabfinal.domain.repository.AtletaRepository;
import br.com.andersillva.trabfinal.domain.service.AtletaService;
import br.com.andersillva.trabfinal.domain.service.exception.ParametroRequeridoException;
import br.com.andersillva.trabfinal.domain.service.exception.RegistroDuplicadoException;
import br.com.andersillva.trabfinal.domain.service.exception.RegistroNaoEncontradoException;

@Service
public class AtletaServiceImpl implements AtletaService {

	@Autowired
	private AtletaRepository atletaRepository;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void incluir(Atleta atleta) {
		atleta.setId(null);
		salvar(atleta);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void alterar(Atleta atleta) {
		if (atleta.getId() == null)
			throw new ParametroRequeridoException();

		obterPorId(atleta.getId());
		salvar(atleta);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	private void salvar(Atleta atleta) {
		try {
			atletaRepository.saveAndFlush(atleta);
		} catch (DataIntegrityViolationException e) {
			throw new RegistroDuplicadoException();
		}
	}

	@Override
	public Atleta obterPorId(Long id) {
		Optional<Atleta> atleta = atletaRepository.obterPorId(id);
		return atleta.orElseThrow(() -> new RegistroNaoEncontradoException());
	}

	@Override
	public List<Atleta> listar() {
		return atletaRepository.listar();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void excluir(Long id) {
		if (atletaRepository.existsById(id)) {
			atletaRepository.deleteById(id);
		}
	}

}
