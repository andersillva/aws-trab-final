package br.com.silvaandersonm.trabfinal.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name="torneio")
@Data
public class Torneio {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_torneio", nullable=false)
	private Long id;

	@Column(name="nm_torneio", length=30, nullable=false)
	@NotBlank
	private String nome;

	@Column(name="nr_ano", nullable=false)
	@NotNull
	private Integer ano;

	@Column(name="dt_inicio", nullable=false)
	@NotNull
	private Date dataInicio;

	@Column(name="dt_fim", nullable=false)
	@NotNull
	private Date dataFim;

}
