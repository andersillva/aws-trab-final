package br.com.silvaandersonm.trabfinal.messagebroker.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import br.com.silvaandersonm.trabfinal.domain.enumerator.TipoEvento;
import lombok.Data;

@Data
public class EventoMensagemDTO {

	private Long id;

	private TipoEvento tipo;

	private String descricao;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime data;

	private Long idPartida;

}

