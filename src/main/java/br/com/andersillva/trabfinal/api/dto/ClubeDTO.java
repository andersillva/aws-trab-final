package br.com.andersillva.trabfinal.api.dto;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;

import br.com.andersillva.trabfinal.domain.model.Clube;
import lombok.Data;

@Data
public class ClubeDTO {

	private Long id;

	private String nome;

	private String cidade;

	private String uf;

	private LocalDate dataFundacao;

	private String estadio;

	public ClubeDTO(Clube clube) {
		ModelMapper mapper = new ModelMapper();
		mapper.map(clube, this);
	}

}
