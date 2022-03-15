package br.com.silvaandersonm.trabfinal.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name="partida")
@Data
public class Partida {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_partida", nullable=false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_torneio", nullable=false)
	@NotNull
	private Torneio torneio;

	@ManyToOne
	@JoinColumn(name = "id_clube_mandante", nullable=false)
	@NotNull
	private Clube clubeMandante;

	@ManyToOne
	@JoinColumn(name = "id_clube_visitante", nullable=false)
	@NotNull
	private Clube clubeVisitante;

	@Column(name="dt_partida", nullable=false)
	@NotNull
	private Date data;

	@Column(name="ds_local", length=30, nullable=false)
	@NotBlank
	private String local;

	@Column(name="nr_placar_mandante", nullable=true)
	private Integer placarMandante;

	@Column(name="nr_placar_visitante", nullable=true)
	private Integer placarVisitante;

}
