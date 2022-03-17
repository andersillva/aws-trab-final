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

import br.com.silvaandersonm.trabfinal.domain.model.Partida;
import br.com.silvaandersonm.trabfinal.messagebroker.PartidaBroker;
import br.com.silvaandersonm.trabfinal.messagebroker.dto.PartidaMensagemDTO;
import br.com.silvaandersonm.trabfinal.messagebroker.exception.FalhaSerializacaoMensagemException;

@Component
public class PartidaBrokerImpl implements PartidaBroker {

	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Value("${app.topic.partida-adicionada}")
    private String INCLUSAO_PARTIDA;

    @Value("${app.topic.partida-alterada}")
    private String ALTERACAO_PARTIDA;
    
    @Value("${app.topic.partida-excluida}")
    private String EXCLUSAO_PARTIDA;

    public PartidaBrokerImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

	@Override
	@Transactional(propagation=Propagation.MANDATORY)
	public void gerarMensagemInclusao(Partida partida) {
		String mensagem = obterMensagemPersistencia(partida);
        kafkaTemplate.send(INCLUSAO_PARTIDA, mensagem);
	}

	@Override
	public void gerarMensagemAlteracao(Partida partida) {
		String mensagem = obterMensagemPersistencia(partida);
		kafkaTemplate.send(ALTERACAO_PARTIDA, mensagem);
	}

	private String obterMensagemPersistencia(Partida partida) {
		ModelMapper mapper = new ModelMapper();
		PartidaMensagemDTO partidaMensagemDTO = mapper.map(partida, PartidaMensagemDTO.class);
		String mensagem;
		try {
			mensagem = objectMapper.writeValueAsString(partidaMensagemDTO);
		} catch (JsonProcessingException e) {
			throw new FalhaSerializacaoMensagemException();
		}
		return mensagem;
	}

	@Override
	public void gerarMensagemExclusao(Partida partida) {
		String mensagem;
		try {
			mensagem = objectMapper.writeValueAsString(partida.getId());
		} catch (JsonProcessingException e) {
			throw new FalhaSerializacaoMensagemException();
		}
		kafkaTemplate.send(EXCLUSAO_PARTIDA, mensagem);
	}

}
