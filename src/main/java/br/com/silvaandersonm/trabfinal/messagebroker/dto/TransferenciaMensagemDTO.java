package br.com.silvaandersonm.trabfinal.messagebroker.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import lombok.Data;

@Data
public class TransferenciaMensagemDTO {

	private Long id;

	private Long idClubeOrigem;

	private Long idClubeDestino;

	private Long idAtleta;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate data;

	private BigDecimal valor;

	private String moeda;

}

