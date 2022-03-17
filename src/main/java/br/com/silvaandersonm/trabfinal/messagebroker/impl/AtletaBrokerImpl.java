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

import br.com.silvaandersonm.trabfinal.domain.model.Atleta;
import br.com.silvaandersonm.trabfinal.messagebroker.AtletaBroker;
import br.com.silvaandersonm.trabfinal.messagebroker.dto.AtletaMensagemDTO;
import br.com.silvaandersonm.trabfinal.messagebroker.exception.FalhaSerializacaoMensagemException;

@Component
public class AtletaBrokerImpl implements AtletaBroker {

	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Value("${app.topic.atleta-adicionado}")
    private String INCLUSAO_ATLETA;

    @Value("${app.topic.atleta-alterado}")
    private String ALTERACAO_ATLETA;
    
    @Value("${app.topic.atleta-excluido}")
    private String EXCLUSAO_ATLETA;

    public AtletaBrokerImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

	@Override
	@Transactional(propagation=Propagation.MANDATORY)
	public void gerarMensagemInclusao(Atleta atleta) {
		String mensagem = obterMensagemPersistencia(atleta);
        kafkaTemplate.send(INCLUSAO_ATLETA, mensagem);
	}

	@Override
	public void gerarMensagemAlteracao(Atleta atleta) {
		String mensagem = obterMensagemPersistencia(atleta);
		kafkaTemplate.send(ALTERACAO_ATLETA, mensagem);
	}

	private String obterMensagemPersistencia(Atleta atleta) {
		ModelMapper mapper = new ModelMapper();
		AtletaMensagemDTO atletaMensagemDTO = mapper.map(atleta, AtletaMensagemDTO.class);
		String mensagem;
		try {
			mensagem = objectMapper.writeValueAsString(atletaMensagemDTO);
		} catch (JsonProcessingException e) {
			throw new FalhaSerializacaoMensagemException();
		}
		return mensagem;
	}

	@Override
	public void gerarMensagemExclusao(Atleta atleta) {
		String mensagem;
		try {
			mensagem = objectMapper.writeValueAsString(atleta.getId());
		} catch (JsonProcessingException e) {
			throw new FalhaSerializacaoMensagemException();
		}
		kafkaTemplate.send(EXCLUSAO_ATLETA, mensagem);
	}

}
