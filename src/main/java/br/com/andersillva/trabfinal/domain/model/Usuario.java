package br.com.andersillva.trabfinal.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.andersillva.trabfinal.domain.enumerator.PerfilUsuario;
import br.com.andersillva.trabfinal.domain.enumerator.SituacaoUsuario;
import lombok.Data;

@Entity
@Table(name="usuario")
@Data
public class Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_usuario", nullable=false)
	private Long id;

	@Column(name="nm_usuario", length=60, nullable=false)
	@NotBlank
	private String nome;

	@Column(name="nm_login", length=30, nullable=false)
	@NotBlank
	private String login;

	@Column(name="ds_senha", length=100, nullable=false)
	@NotBlank
	private String senha;

	@Column(name="st_usuario", length=10, nullable=false)
	@Enumerated(EnumType.STRING)
	@NotNull
	private SituacaoUsuario situacao;

	@Column(name="tp_perfil", length=15, nullable=false)
	@Enumerated(EnumType.STRING)
	@NotNull
	private PerfilUsuario perfil;

}
