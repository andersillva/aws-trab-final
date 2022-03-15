package br.com.silvaandersonm.trabfinal.api.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PartidaPersistenciaDTO {

	@NotNull
	private Long idClubeMandante;

	@NotNull
	private Long idClubeVisitante;

	@NotNull
	private Date data;

	@NotBlank
	private String local;

	private Integer placarMandante;

	private Integer placarVisitante;
}
