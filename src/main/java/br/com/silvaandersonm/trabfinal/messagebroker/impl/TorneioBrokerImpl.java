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

import br.com.silvaandersonm.trabfinal.domain.model.Torneio;
import br.com.silvaandersonm.trabfinal.messagebroker.TorneioBroker;
import br.com.silvaandersonm.trabfinal.messagebroker.dto.TorneioMensagemDTO;
import br.com.silvaandersonm.trabfinal.messagebroker.exception.FalhaSerializacaoMensagemException;

@Component
public class TorneioBrokerImpl implements TorneioBroker {

	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Value("${app.topic.torneio-adicionado}")
    private String INCLUSAO_TORNEIO;

    @Value("${app.topic.torneio-alterado}")
    private String ALTERACAO_TORNEIO;
    
    @Value("${app.topic.torneio-excluido}")
    private String EXCLUSAO_TORNEIO;

    public TorneioBrokerImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

	@Override
	@Transactional(propagation=Propagation.MANDATORY)
	public void gerarMensagemInclusao(Torneio torneio) {
		String mensagem = obterMensagemPersistencia(torneio);
        kafkaTemplate.send(INCLUSAO_TORNEIO, mensagem);
	}

	@Override
	public void gerarMensagemAlteracao(Torneio torneio) {
		String mensagem = obterMensagemPersistencia(torneio);
		kafkaTemplate.send(ALTERACAO_TORNEIO, mensagem);
	}

	private String obterMensagemPersistencia(Torneio torneio) {
		ModelMapper mapper = new ModelMapper();
		TorneioMensagemDTO torneioMensagemDTO = mapper.map(torneio, TorneioMensagemDTO.class);
		String mensagem;
		try {
			mensagem = objectMapper.writeValueAsString(torneioMensagemDTO);
		} catch (JsonProcessingException e) {
			throw new FalhaSerializacaoMensagemException();
		}
		return mensagem;
	}

	@Override
	public void gerarMensagemExclusao(Torneio torneio) {
		String mensagem;
		try {
			mensagem = objectMapper.writeValueAsString(torneio.getId());
		} catch (JsonProcessingException e) {
			throw new FalhaSerializacaoMensagemException();
		}
		kafkaTemplate.send(EXCLUSAO_TORNEIO, mensagem);
	}

}
