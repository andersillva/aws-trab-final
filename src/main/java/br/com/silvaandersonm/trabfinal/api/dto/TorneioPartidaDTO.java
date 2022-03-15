package br.com.silvaandersonm.trabfinal.api.dto;

import java.util.Date;

import lombok.Data;

@Data
public class TorneioPartidaDTO {

	private Long id;

	private ClubeResumoDTO mandante;

	private ClubeResumoDTO visitante;

	private Date data;

	private String local;

	private Integer placarMandante;

	private Integer placarVisitante;
}
