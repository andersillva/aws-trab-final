package br.com.andersillva.trabfinal.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.andersillva.trabfinal.domain.enumerator.SituacaoUsuario;
import br.com.andersillva.trabfinal.domain.model.Usuario;

public class UsuarioAutenticado implements UserDetails, GrantedAuthority {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	public UsuarioAutenticado(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(this.usuario.getPerfil().toString()));
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.usuario.getSenha();
	}

	@Override
	public String getUsername() {
		return this.usuario.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.usuario.getSituacao().equals(SituacaoUsuario.ATIVO);
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.usuario.getSituacao().equals(SituacaoUsuario.ATIVO);
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.usuario.getSituacao().equals(SituacaoUsuario.ATIVO);
	}

	@Override
	public boolean isEnabled() {
		return this.usuario.getSituacao().equals(SituacaoUsuario.ATIVO);
	}

	@Override
	public String getAuthority() {
		return this.usuario.getPerfil().toString();
	}

	public Long getId() {
		return this.usuario.getId();
	}

}
