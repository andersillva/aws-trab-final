package br.com.silvaandersonm.trabfinal.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.silvaandersonm.trabfinal.domain.model.Partida;
import br.com.silvaandersonm.trabfinal.domain.repository.PartidaRepository;
import br.com.silvaandersonm.trabfinal.domain.service.PartidaService;
import br.com.silvaandersonm.trabfinal.domain.service.exception.ParametroRequeridoException;
import br.com.silvaandersonm.trabfinal.domain.service.exception.RegistroDuplicadoException;
import br.com.silvaandersonm.trabfinal.domain.service.exception.RegistroNaoEncontradoException;

@Service
public class PartidaServiceImpl implements PartidaService {

	@Autowired
	private PartidaRepository partidaRepository;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void incluir(Partida partida) {
		partida.setId(null);
		salvar(partida);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void alterar(Partida partida) {
		if (partida.getId() == null)
			throw new ParametroRequeridoException();

		obterPorId(partida.getId());
		salvar(partida);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	private void salvar(Partida partida) {
		try {
			partidaRepository.saveAndFlush(partida);
		} catch (DataIntegrityViolationException e) {
			throw new RegistroDuplicadoException();
		}
	}

	@Override
	public Partida obterPorId(Long id) {
		Optional<Partida> partida = partidaRepository.obterPorId(id);
		return partida.orElseThrow(() -> new RegistroNaoEncontradoException());
	}

	@Override
	public List<Partida> listarPorTorneio(Long idTorneio) {
		return partidaRepository.listarPorTorneio(idTorneio);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void excluir(Long id) {
		if (partidaRepository.existsById(id)) {
			partidaRepository.deleteById(id);
		}
	}

}
