package br.com.silvaandersonm.trabfinal.messagebroker.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import lombok.Data;

@Data
public class AtletaMensagemDTO {

	private Long id;

	private String nome;

	private String nomeCompleto;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;

	private String cidadeNascimento;

	private String ufNascimento;

	private String paisNascimento;

	private Float altura;

}

