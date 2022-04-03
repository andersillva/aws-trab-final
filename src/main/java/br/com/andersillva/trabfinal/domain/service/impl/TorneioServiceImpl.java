package br.com.andersillva.trabfinal.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.andersillva.trabfinal.domain.model.Torneio;
import br.com.andersillva.trabfinal.domain.repository.TorneioRepository;
import br.com.andersillva.trabfinal.domain.service.TorneioService;
import br.com.andersillva.trabfinal.domain.service.exception.ParametroRequeridoException;
import br.com.andersillva.trabfinal.domain.service.exception.RegistroDuplicadoException;
import br.com.andersillva.trabfinal.domain.service.exception.RegistroNaoEncontradoException;

@Service
public class TorneioServiceImpl implements TorneioService {

	@Autowired
	private TorneioRepository torneioRepository;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void incluir(Torneio torneio) {
		torneio.setId(null);
		salvar(torneio);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void alterar(Torneio torneio) {
		if (torneio.getId() == null)
			throw new ParametroRequeridoException();

		obterPorId(torneio.getId());
		salvar(torneio);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	private void salvar(Torneio torneio) {
		try {
			torneioRepository.saveAndFlush(torneio);
		} catch (DataIntegrityViolationException e) {
			throw new RegistroDuplicadoException();
		}
	}

	@Override
	public Torneio obterPorId(Long id) {
		Optional<Torneio> torneio = torneioRepository.findById(id);
		return torneio.orElseThrow(() -> new RegistroNaoEncontradoException());
	}

	@Override
	public List<Torneio> listar() {
		return torneioRepository.findAll();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void excluir(Long id) {
		if (torneioRepository.existsById(id)) {
			torneioRepository.deleteById(id);
		}
	}

}
