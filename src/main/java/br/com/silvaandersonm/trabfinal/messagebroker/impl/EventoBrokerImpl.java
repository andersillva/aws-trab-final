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

import br.com.silvaandersonm.trabfinal.domain.model.Evento;
import br.com.silvaandersonm.trabfinal.messagebroker.EventoBroker;
import br.com.silvaandersonm.trabfinal.messagebroker.dto.EventoMensagemDTO;
import br.com.silvaandersonm.trabfinal.messagebroker.exception.FalhaSerializacaoMensagemException;

@Component
public class EventoBrokerImpl implements EventoBroker {

	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Value("${app.topic.evento-adicionado}")
    private String INCLUSAO_EVENTO;

    public EventoBrokerImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

	@Override
	@Transactional(propagation=Propagation.MANDATORY)
	public void gerarMensagemInclusao(Evento evento) {
		ModelMapper mapper = new ModelMapper();
		EventoMensagemDTO eventoMensagemDTO = mapper.map(evento, EventoMensagemDTO.class);
		String mensagem;
		try {
			mensagem = objectMapper.writeValueAsString(eventoMensagemDTO);
		} catch (JsonProcessingException e) {
			throw new FalhaSerializacaoMensagemException();
		}
        kafkaTemplate.send(INCLUSAO_EVENTO, mensagem);
	}

}
