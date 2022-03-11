package br.com.silvaandersonm.trabfinal.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.silvaandersonm.trabfinal.domain.model.Transferencia;
import br.com.silvaandersonm.trabfinal.domain.repository.TransferenciaRepository;
import br.com.silvaandersonm.trabfinal.domain.service.TransferenciaService;
import br.com.silvaandersonm.trabfinal.domain.service.exception.ParametroRequeridoException;
import br.com.silvaandersonm.trabfinal.domain.service.exception.RegistroDuplicadoException;
import br.com.silvaandersonm.trabfinal.domain.service.exception.RegistroNaoEncontradoException;

@Service
public class TransferenciaServiceImpl implements TransferenciaService {

	@Autowired
	private TransferenciaRepository transferenciaRepository;

	@Override
	public void incluir(Transferencia transferencia) {
		transferencia.setId(null);
		try {
			transferenciaRepository.saveAndFlush(transferencia);
		} catch (DataIntegrityViolationException e) {
			throw new RegistroDuplicadoException();
		}
	}

	@Override
	public void alterar(Transferencia transferencia) {
		if (transferencia.getId() == null)
			throw new ParametroRequeridoException();

		obterPorId(transferencia.getId());
		transferenciaRepository.saveAndFlush(transferencia);
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
