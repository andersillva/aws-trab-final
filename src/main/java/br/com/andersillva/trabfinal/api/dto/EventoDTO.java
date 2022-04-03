package br.com.andersillva.trabfinal.api.dto;

import java.time.LocalDateTime;

import br.com.andersillva.trabfinal.domain.enumerator.TipoEvento;
import lombok.Data;

@Data
public class EventoDTO {

	private Long id;

	private TipoEvento tipo;

	private String descricao;

	private LocalDateTime data;

}
