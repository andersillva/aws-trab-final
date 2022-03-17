package br.com.silvaandersonm.trabfinal.domain.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.silvaandersonm.trabfinal.domain.enumerator.TipoEvento;
import lombok.Data;

@Entity
@Table(name="evento")
@Data
public class Evento {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_evento", nullable=false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_partida", nullable=false)
	@NotNull
	private Partida partida;

	@Column(name="tp_evento", length=15, nullable=false)
	@Enumerated(EnumType.STRING)
	@NotNull
	private TipoEvento tipo;

	@Column(name="ds_evento", length=15, nullable=false)
	@NotBlank
	private String descricao;

	@Column(name="dt_evento", nullable=false)
	@NotNull
	private LocalDateTime data;

}
