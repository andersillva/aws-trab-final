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
@Table(name = "clube")
@Data
public class Clube {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_clube", nullable=false)
	private Long id;

	@Column(name="nm_clube", length=60, nullable=false)
	@NotBlank
	private String nome;

	@Column(name="nm_cidade", length=60, nullable=false)
	@NotBlank
	private String cidade;

	@Column(name="sg_uf", length=2, nullable=false)
	@NotBlank
	private String uf;

	@Column(name="dt_fundacao", nullable=false)
	@NotNull
	private Date dataFundacao;

	@Column(name="nm_estadio", length=30, nullable=true)
	private String estadio;

}
