package br.com.silvaandersonm.trabfinal.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.silvaandersonm.trabfinal.domain.model.Atleta;
import br.com.silvaandersonm.trabfinal.domain.model.Transferencia;
import br.com.silvaandersonm.trabfinal.domain.repository.AtletaRepository;
import br.com.silvaandersonm.trabfinal.domain.repository.TransferenciaRepository;
import br.com.silvaandersonm.trabfinal.domain.service.TransferenciaService;
import br.com.silvaandersonm.trabfinal.domain.service.exception.AtletaNaoContidoClubeOrigemException;
import br.com.silvaandersonm.trabfinal.domain.service.exception.ParametroRequeridoException;
import br.com.silvaandersonm.trabfinal.domain.service.exception.RegistroDuplicadoException;
import br.com.silvaandersonm.trabfinal.domain.service.exception.RegistroNaoEncontradoException;

@Service
public class TransferenciaServiceImpl implements TransferenciaService {

	@Autowired
	private TransferenciaRepository transferenciaRepository;

	@Autowired
	private AtletaRepository atletaRepository;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void incluir(Transferencia transferencia) {
		transferencia.setId(null);
		salvar(transferencia);
		alterarClubeAtleta(transferencia);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void alterar(Transferencia transferencia) {
		if (transferencia.getId() == null)
			throw new ParametroRequeridoException();

		obterPorId(transferencia.getId());
		salvar(transferencia);
		alterarClubeAtleta(transferencia);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	private void salvar(Transferencia transferencia) {
		try {
			transferenciaRepository.saveAndFlush(transferencia);
		} catch (DataIntegrityViolationException e) {
			throw new RegistroDuplicadoException();
		}
	}

	@Transactional(propagation=Propagation.REQUIRED)
	private void alterarClubeAtleta(Transferencia transferencia) {
		Atleta atleta = atletaRepository.getById(transferencia.getAtleta().getId());
		if (atleta.getClube().getId() != transferencia.getClubeOrigem().getId()) {
			throw new AtletaNaoContidoClubeOrigemException();
		}
		atleta.setClube(transferencia.getClubeDestino());
		atletaRepository.saveAndFlush(transferencia.getAtleta());	
	}

	@Override
	public Transferencia obterPorId(Long id) {
		Optional<Transferencia> transferencia = transferenciaRepository.findById(id);
		return transferencia.orElseThrow(() -> new RegistroNaoEncontradoException());
	}

	@Override
	public List<Transferencia> listar() {
		return transferenciaRepository.findAll();
	}

}
