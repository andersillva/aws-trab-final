package br.com.silvaandersonm.trabfinal.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.silvaandersonm.trabfinal.domain.model.Participante;
import br.com.silvaandersonm.trabfinal.domain.repository.ParticipanteRepository;
import br.com.silvaandersonm.trabfinal.domain.repository.TorneioRepository;
import br.com.silvaandersonm.trabfinal.domain.service.ParticipanteService;
import br.com.silvaandersonm.trabfinal.domain.service.exception.ParametroRequeridoException;
import br.com.silvaandersonm.trabfinal.domain.service.exception.RegistroDuplicadoException;
import br.com.silvaandersonm.trabfinal.domain.service.exception.RegistroNaoEncontradoException;

@Service
public class ParticipanteServiceImpl implements ParticipanteService {

	@Autowired
	private ParticipanteRepository participanteRepository;

	@Autowired
	private TorneioRepository torneioRepository;

	@Override
	public List<Participante> listarPorTorneio(Long idTorneio) {
		torneioRepository.findById(idTorneio).orElseThrow(() -> new RegistroNaoEncontradoException());
		return participanteRepository.listarPorTorneio(idTorneio);
	}

	@Override
	public Participante obterPorId(Long idParticipante) {
		Optional<Participante> participante = participanteRepository.findById(idParticipante);
		return participante.orElseThrow(() -> new RegistroNaoEncontradoException());
	}

	@Override
	public Participante obterPorChave(Long idTorneio, Long idClube) {
		Optional<Participante> participante = participanteRepository.obterPorChave(idTorneio, idClube);
		return participante.orElseThrow(() -> new RegistroNaoEncontradoException());
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void incluir(Participante participante) {
		participante.setId(null);
		salvar(participante);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void alterar(Participante participante) {
		if (participante.getId() == null)
			throw new ParametroRequeridoException();

		obterPorId(participante.getId());
		salvar(participante);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	private void salvar(Participante participante) {
		try {
			participanteRepository.saveAndFlush(participante);
		} catch (DataIntegrityViolationException e) {
			throw new RegistroDuplicadoException();
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void excluir(Long idTorneio, Long idClube) {
		Optional<Participante> participante = participanteRepository.obterPorChave(idTorneio, idClube);
		if (participante.isPresent()) {
			participanteRepository.deleteById(participante.get().getId());
		}
	}

}
