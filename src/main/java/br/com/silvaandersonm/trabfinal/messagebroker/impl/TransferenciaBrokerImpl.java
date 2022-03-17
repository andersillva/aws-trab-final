package br.com.silvaandersonm.trabfinal.messagebroker.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.silvaandersonm.trabfinal.domain.model.Transferencia;
import br.com.silvaandersonm.trabfinal.messagebroker.TransferenciaBroker;
import br.com.silvaandersonm.trabfinal.messagebroker.dto.TransferenciaMensagemDTO;
import br.com.silvaandersonm.trabfinal.messagebroker.exception.FalhaSerializacaoMensagemException;

@Component
public class TransferenciaBrokerImpl implements TransferenciaBroker {

	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Value("${app.topic.transferencia-adicionada}")
    private String INCLUSAO_TRANSFERENCIA;

    @Value("${app.topic.transferencia-alterada}")
    private String ALTERACAO_TRANSFERENCIA;
    
    @Value("${app.topic.transferencia-excluida}")
    private String EXCLUSAO_TRANSFERENCIA;

    public TransferenciaBrokerImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

	@Override
	@Transactional(propagation=Propagation.MANDATORY)
	public void gerarMensagemInclusao(Transferencia transferencia) {
		String mensagem = obterMensagemPersistencia(transferencia);
        kafkaTemplate.send(INCLUSAO_TRANSFERENCIA, mensagem);
	}

	@Override
	public void gerarMensagemAlteracao(Transferencia transferencia) {
		String mensagem = obterMensagemPersistencia(transferencia);
		kafkaTemplate.send(ALTERACAO_TRANSFERENCIA, mensagem);
	}

	private String obterMensagemPersistencia(Transferencia transferencia) {
		ModelMapper mapper = new ModelMapper();
		TransferenciaMensagemDTO transferenciaMensagemDTO = mapper.map(transferencia, TransferenciaMensagemDTO.class);
		String mensagem;
		try {
			mensagem = objectMapper.writeValueAsString(transferenciaMensagemDTO);
		} catch (JsonProcessingException e) {
			throw new FalhaSerializacaoMensagemException();
		}
		return mensagem;
	}

	@Override
	public void gerarMensagemExclusao(Transferencia transferencia) {
		String mensagem;
		try {
			mensagem = objectMapper.writeValueAsString(transferencia.getId());
		} catch (JsonProcessingException e) {
			throw new FalhaSerializacaoMensagemException();
		}
		kafkaTemplate.send(EXCLUSAO_TRANSFERENCIA, mensagem);
	}

}
