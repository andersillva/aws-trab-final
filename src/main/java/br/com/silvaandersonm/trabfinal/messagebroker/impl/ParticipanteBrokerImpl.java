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

import br.com.silvaandersonm.trabfinal.domain.model.Participante;
import br.com.silvaandersonm.trabfinal.messagebroker.ParticipanteBroker;
import br.com.silvaandersonm.trabfinal.messagebroker.dto.ParticipanteMensagemDTO;
import br.com.silvaandersonm.trabfinal.messagebroker.exception.FalhaSerializacaoMensagemException;

@Component
public class ParticipanteBrokerImpl implements ParticipanteBroker {

	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Value("${app.topic.participante-adicionado}")
    private String INCLUSAO_PARTICIPANTE;

    @Value("${app.topic.participante-alterado}")
    private String ALTERACAO_PARTICIPANTE;
    
    @Value("${app.topic.participante-excluido}")
    private String EXCLUSAO_PARTICIPANTE;

    public ParticipanteBrokerImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

	@Override
	@Transactional(propagation=Propagation.MANDATORY)
	public void gerarMensagemInclusao(Participante participante) {
		String mensagem = obterMensagemPersistencia(participante);
        kafkaTemplate.send(INCLUSAO_PARTICIPANTE, mensagem);
	}

	@Override
	public void gerarMensagemAlteracao(Participante participante) {
		String mensagem = obterMensagemPersistencia(participante);
		kafkaTemplate.send(ALTERACAO_PARTICIPANTE, mensagem);
	}

	private String obterMensagemPersistencia(Participante participante) {
		ModelMapper mapper = new ModelMapper();
		ParticipanteMensagemDTO participanteMensagemDTO = mapper.map(participante, ParticipanteMensagemDTO.class);
		String mensagem;
		try {
			mensagem = objectMapper.writeValueAsString(participanteMensagemDTO);
		} catch (JsonProcessingException e) {
			throw new FalhaSerializacaoMensagemException();
		}
		return mensagem;
	}

	@Override
	public void gerarMensagemExclusao(Participante participante) {
		String mensagem;
		try {
			mensagem = objectMapper.writeValueAsString(participante.getId());
		} catch (JsonProcessingException e) {
			throw new FalhaSerializacaoMensagemException();
		}
		kafkaTemplate.send(EXCLUSAO_PARTICIPANTE, mensagem);
	}

}
