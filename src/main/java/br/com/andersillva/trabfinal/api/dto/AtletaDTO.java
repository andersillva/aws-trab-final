package br.com.andersillva.trabfinal.api.dto;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.andersillva.trabfinal.domain.model.Atleta;
import lombok.Data;

@Data
public class AtletaDTO {

	private Long id;

	private String nome;

	private String nomeCompleto;

	private LocalDate dataNascimento;

	private String cidadeNascimento;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String ufNascimento;

	private String paisNascimento;

	private Float altura;

	private ClubeResumoDTO clube;

	public AtletaDTO(Atleta atleta) {
		ModelMapper mapper = new ModelMapper();
		mapper.map(atleta, this);
	}

}
