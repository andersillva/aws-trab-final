package br.com.andersillva.trabfinal.domain.model;

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
@Table(name="atleta")
@Data
public class Atleta {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_atleta", nullable=false)
	private Long id;

	@Column(name="nm_atleta", length=30, nullable=false)
	@NotBlank
	private String nome;

	@Column(name="nm_completo", length=60, nullable=false)
	@NotBlank
	private String nomeCompleto;

	@Column(name="dt_nascimento", nullable=false)
	@NotNull
	private LocalDate dataNascimento;

	@Column(name="nm_cidade_nascimento", length=60, nullable=false)
	@NotBlank
	private String cidadeNascimento;

	@Column(name="sg_uf_nascimento", length=2, nullable=true)
	private String ufNascimento;

	@Column(name="nm_pais_nascimento", length=30, nullable=false)
	@NotBlank
	private String paisNascimento;

	@Column(name="nr_altura", precision=10, scale=2, nullable=false)
	@NotNull
	private Float altura;

	@ManyToOne
	@JoinColumn(name = "id_clube", nullable=false)
	@NotNull
	private Clube clube;

}
