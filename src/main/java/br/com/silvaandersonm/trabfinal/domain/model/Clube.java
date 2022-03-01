package br.com.silvaandersonm.trabfinal.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "clube")
public class Clube {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_clube", nullable=false)
	private Long id;

	@Column(name="nm_clube", length=60, nullable=false)
	private String nome;

	@Column(name="nm_cidade", length=60, nullable=false)
	private String cidade;

	@Column(name="sg_uf", length=2, nullable=false)
	private String uf;

	@Column(name="dt_fundacao", nullable=false)
	private Date dataFundacao;

	@Column(name="nm_estadio", length=30, nullable=true)
	private String estadio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Date getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public String getEstadio() {
		return estadio;
	}

	public void setEstadio(String estadio) {
		this.estadio = estadio;
	}

}
