package br.com.silvaandersonm.trabfinal.domain.model;

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
@Table(name="participante")
@Data
public class Participante {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_participante", nullable=false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_torneio", nullable=false)
	@NotNull
	private Torneio torneio;

	@ManyToOne
	@JoinColumn(name = "id_clube", nullable=false)
	@NotNull
	private Clube clube;

	@Column(name="st_participante", length=15, nullable=false)
	@NotBlank
	private String situacao;

	@Column(name="nr_pontos", nullable=true)
	private Integer pontuacao;

}
