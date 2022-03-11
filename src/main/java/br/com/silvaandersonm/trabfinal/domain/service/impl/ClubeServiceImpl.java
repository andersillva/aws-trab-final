package br.com.silvaandersonm.trabfinal.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.silvaandersonm.trabfinal.domain.model.Atleta;
import br.com.silvaandersonm.trabfinal.domain.model.Clube;
import br.com.silvaandersonm.trabfinal.domain.repository.ClubeRepository;
import br.com.silvaandersonm.trabfinal.domain.service.ClubeService;
import br.com.silvaandersonm.trabfinal.domain.service.exception.IntegridadeReferencialException;
import br.com.silvaandersonm.trabfinal.domain.service.exception.ParametroRequeridoException;
import br.com.silvaandersonm.trabfinal.domain.service.exception.RegistroDuplicadoException;
import br.com.silvaandersonm.trabfinal.domain.service.exception.RegistroNaoEncontradoException;

@Service
public class ClubeServiceImpl implements ClubeService {

	@Autowired
	private ClubeRepository clubeRepository;

	@Override
	public void incluir(Clube clube) {
		clube.setId(null);
		try {
			clubeRepository.saveAndFlush(clube);
		} catch (DataIntegrityViolationException e) {
			throw new RegistroDuplicadoException();
		}
	}

	@Override
	public void alterar(Clube clube) {
		if (clube.getId() == null)
			throw new ParametroRequeridoException();

		obterPorId(clube.getId());
		clubeRepository.saveAndFlush(clube);
	}

	@Override
	public Clube obterPorId(Long id) {
		Optional<Clube> clube = clubeRepository.findById(id);
		return clube.orElseThrow(() -> new RegistroNaoEncontradoException());
	}

	@Override
	public List<Clube> listar() {
		return clubeRepository.findAll();
	}

	@Override
	public List<Atleta> listarAtletas(Long idClube) {
		obterPorId(idClube);
		return clubeRepository.listarAtletas(idClube);
	}

	@Override
	public void excluir(Long id) {
		if (clubeRepository.existsById(id)) {
			try {
				clubeRepository.deleteById(id);
			} catch (DataIntegrityViolationException e) {
				throw new IntegridadeReferencialException();
			}
		}
	}

}
