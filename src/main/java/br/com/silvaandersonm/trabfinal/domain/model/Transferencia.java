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

import lombok.Data;

@Entity
@Table(name = "transferencia")
@Data
public class Transferencia {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_transferencia", nullable=false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_clube_origem", nullable=false)
	private Clube clubeOrigem;
	
	@ManyToOne
	@JoinColumn(name = "id_clube_destino", nullable=false)
	private Clube clubeDestino;

	@Column(name="dt_transferencia", nullable=false)
	private Date data;

	@Column(name="vl_transferencia", nullable=false)
	private Double valor;
	
	@Column(name="ds_moeda", nullable=false)
	private String moeda;

}
