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

import br.com.silvaandersonm.trabfinal.domain.model.Clube;
import br.com.silvaandersonm.trabfinal.messagebroker.ClubeBroker;
import br.com.silvaandersonm.trabfinal.messagebroker.dto.ClubeMensagemDTO;
import br.com.silvaandersonm.trabfinal.messagebroker.exception.FalhaSerializacaoMensagemException;

@Component
public class ClubeBrokerImpl implements ClubeBroker {

	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Value("${app.topic.clube-adicionado}")
    private String INCLUSAO_CLUBE;

    @Value("${app.topic.clube-alterado}")
    private String ALTERACAO_CLUBE;
    
    @Value("${app.topic.clube-excluido}")
    private String EXCLUSAO_CLUBE;

    public ClubeBrokerImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

	@Override
	@Transactional(propagation=Propagation.MANDATORY)
	public void gerarMensagemInclusao(Clube clube) {
		String mensagem = obterMensagemPersistencia(clube);
        kafkaTemplate.send(INCLUSAO_CLUBE, mensagem);
	}

	@Override
	public void gerarMensagemAlteracao(Clube clube) {
		String mensagem = obterMensagemPersistencia(clube);
		kafkaTemplate.send(ALTERACAO_CLUBE, mensagem);
	}

	private String obterMensagemPersistencia(Clube clube) {
		ModelMapper mapper = new ModelMapper();
		ClubeMensagemDTO clubeMensagemDTO = mapper.map(clube, ClubeMensagemDTO.class);
		String mensagem;
		try {
			mensagem = objectMapper.writeValueAsString(clubeMensagemDTO);
		} catch (JsonProcessingException e) {
			throw new FalhaSerializacaoMensagemException();
		}
		return mensagem;
	}

	@Override
	public void gerarMensagemExclusao(Clube clube) {
		String mensagem;
		try {
			mensagem = objectMapper.writeValueAsString(clube.getId());
		} catch (JsonProcessingException e) {
			throw new FalhaSerializacaoMensagemException();
		}
		kafkaTemplate.send(EXCLUSAO_CLUBE, mensagem);
	}

}
