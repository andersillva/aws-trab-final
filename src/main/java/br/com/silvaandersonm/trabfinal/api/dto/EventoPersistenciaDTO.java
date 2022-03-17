package br.com.silvaandersonm.trabfinal.api.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class EventoPersistenciaDTO {

	@NotBlank
	private String descricao;

}
