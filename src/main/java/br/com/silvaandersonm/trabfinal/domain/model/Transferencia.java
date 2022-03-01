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

@Entity
@Table(name = "transferencia")
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Clube getClubeOrigem() {
		return clubeOrigem;
	}

	public void setClubeOrigem(Clube clubeOrigem) {
		this.clubeOrigem = clubeOrigem;
	}

	public Clube getClubeDestino() {
		return clubeDestino;
	}

	public void setClubeDestino(Clube clubeDestino) {
		this.clubeDestino = clubeDestino;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getMoeda() {
		return moeda;
	}

	public void setMoeda(String moeda) {
		this.moeda = moeda;
	}

}
