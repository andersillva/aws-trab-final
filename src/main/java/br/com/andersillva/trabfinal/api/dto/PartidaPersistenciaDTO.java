package br.com.andersillva.trabfinal.api.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.andersillva.trabfinal.domain.model.enums.SituacaoPartida;
import lombok.Data;

@Data
public class PartidaPersistenciaDTO {

	@NotNull
	private Long idClubeMandante;

	@NotNull
	private Long idClubeVisitante;

	@NotNull
	private LocalDate data;

	@NotBlank
	private String local;

	@NotNull
	private SituacaoPartida situacao;

	private Integer placarMandante;

	private Integer placarVisitante;

}
