package br.com.silvaandersonm.trabfinal.api.dto;

import java.time.LocalDateTime;

import br.com.silvaandersonm.trabfinal.domain.enumerator.TipoEvento;
import lombok.Data;

@Data
public class EventoDTO {

	private Long id;

	private TipoEvento tipo;

	private String descricao;

	private LocalDateTime data;

}
