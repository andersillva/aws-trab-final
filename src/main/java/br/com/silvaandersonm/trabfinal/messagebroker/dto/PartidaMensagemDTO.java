package br.com.silvaandersonm.trabfinal.messagebroker.dto;

import java.time.LocalDate;

import br.com.silvaandersonm.trabfinal.domain.enumerator.SituacaoPartida;
import lombok.Data;

@Data
public class PartidaMensagemDTO {

	private Long id;

	private Long idClubeMandante;

	private Long idClubeVisitante;

	private LocalDate data;

	private String local;

	private SituacaoPartida situacao;

	private Integer placarMandante;

	private Integer placarVisitante;

}

