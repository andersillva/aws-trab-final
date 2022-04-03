package br.com.andersillva.trabfinal.api.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TorneioDTO {

	private Long id;

	private String nome;

	private Integer ano;

	private LocalDate dataInicio;

	private LocalDate dataFim;

}
