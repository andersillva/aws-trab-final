package br.com.silvaandersonm.trabfinal.api.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtletaInclusaoDTO extends AtletaPersistenciaDTO {

	@NotNull
	private Long idClube;

}
