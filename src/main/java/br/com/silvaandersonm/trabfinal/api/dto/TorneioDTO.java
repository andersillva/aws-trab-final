package br.com.silvaandersonm.trabfinal.api.dto;

import java.util.Date;

import lombok.Data;

@Data
public class TorneioDTO {

	private Long id;

	private String nome;

	private Integer ano;

	private Date dataInicio;

	private Date dataFim;

}
