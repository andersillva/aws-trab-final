package br.com.silvaandersonm.trabfinal.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

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
@Table(name="transferencia")
@Data
public class Transferencia {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_transferencia", nullable=false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_clube_origem", nullable=false)
	@NotNull
	private Clube clubeOrigem;
	
	@ManyToOne
	@JoinColumn(name = "id_clube_destino", nullable=false)
	@NotNull
	private Clube clubeDestino;

	@ManyToOne
	@JoinColumn(name = "id_atleta", nullable=false)
	@NotNull
	private Atleta atleta;

	@Column(name="dt_transferencia", nullable=false)
	@NotNull
	private LocalDate data;

	@Column(name="vl_transferencia", nullable=false)
	@NotNull
	private BigDecimal valor;

	@Column(name="ds_moeda", nullable=false)
	@NotBlank
	private String moeda;

}
