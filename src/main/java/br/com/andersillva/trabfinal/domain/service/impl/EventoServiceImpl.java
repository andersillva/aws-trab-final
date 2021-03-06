package br.com.andersillva.trabfinal.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.andersillva.trabfinal.domain.model.Evento;
import br.com.andersillva.trabfinal.domain.repository.EventoRepository;
import br.com.andersillva.trabfinal.domain.service.EventoService;
import br.com.andersillva.trabfinal.domain.service.exception.ParametroRequeridoException;
import br.com.andersillva.trabfinal.domain.service.exception.RegistroDuplicadoException;
import br.com.andersillva.trabfinal.domain.service.exception.RegistroNaoEncontradoException;
import br.com.andersillva.trabfinal.messagebroker.EventoBroker;

@Service
public class EventoServiceImpl implements EventoService {

	@Autowired
	private EventoRepository eventoRepository;

	@Autowired
	private EventoBroker eventoBroker;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void incluir(Evento evento) {
		evento.setId(null);
		salvar(evento);
		eventoBroker.gerarMensagemInclusao(evento);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void alterar(Evento evento) {
		if (evento.getId() == null)
			throw new ParametroRequeridoException();

		obterPorId(evento.getId());
		salvar(evento);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	private void salvar(Evento evento) {
		try {
			eventoRepository.saveAndFlush(evento);
		} catch (DataIntegrityViolationException e) {
			throw new RegistroDuplicadoException();
		}
	}

	@Override
	public Evento obterPorId(Long id) {
		Optional<Evento> evento = eventoRepository.obterPorId(id);
		return evento.orElseThrow(() -> new RegistroNaoEncontradoException());
	}

	@Override
	public List<Evento> listarPorPartida(Long idPartida) {
		return eventoRepository.listarPorPartida(idPartida);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void excluir(Long id) {
		if (eventoRepository.existsById(id)) {
			eventoRepository.deleteById(id);
		}
	}

}
