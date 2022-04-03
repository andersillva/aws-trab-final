package br.com.andersillva.trabfinal.api.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.andersillva.trabfinal.domain.enumerator.SituacaoPartida;
import lombok.Data;

@Data
public class TorneioPartidaDTO {

	private Long id;

	private ClubeResumoDTO mandante;

	private ClubeResumoDTO visitante;

	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", locale = "UTC-03")
	private LocalDate data;

	private String local;

	private SituacaoPartida situacao;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer placarMandante;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer placarVisitante;

}
