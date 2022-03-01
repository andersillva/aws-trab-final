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
@Table(name = "atleta")
public class Atleta {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_atleta", nullable=false)
	private Long id;

	@Column(name="nm_atleta", length=30, nullable=false)
	private String nome;

	@Column(name="nm_completo", length=60, nullable=false)
	private String nomeCompleto;

	@Column(name="dt_nascimento", nullable=false)
	private Date dataNascimento;

	@Column(name="nm_cidade_nascimento", length=60, nullable=false)
	private String cidadeNascimento;

	@Column(name="sg_uf_nascimento", length=2, nullable=true)
	private String ufNascimento;

	@Column(name="nm_pais_nascimento", length=30, nullable=false)
	private String paisNascimento;

	@Column(name="nr_altura", precision=10, scale=2, nullable=false)
	private Float altura;

	@ManyToOne
	@JoinColumn(name = "id_clube", nullable=false)
	private Clube clube;

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

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCidadeNascimento() {
		return cidadeNascimento;
	}

	public void setCidadeNascimento(String cidadeNascimento) {
		this.cidadeNascimento = cidadeNascimento;
	}

	public String getUfNascimento() {
		return ufNascimento;
	}

	public void setUfNascimento(String ufNascimento) {
		this.ufNascimento = ufNascimento;
	}

	public String getPaisNascimento() {
		return paisNascimento;
	}

	public void setPaisNascimento(String paisNascimento) {
		this.paisNascimento = paisNascimento;
	}

	public Float getAltura() {
		return altura;
	}

	public void setAltura(Float altura) {
		this.altura = altura;
	}

	public Clube getClube() {
		return clube;
	}

	public void setClube(Clube clube) {
		this.clube = clube;
	}

}
