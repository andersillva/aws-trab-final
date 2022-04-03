package br.com.andersillva.trabfinal.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.andersillva.trabfinal.domain.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@Query("select u from Usuario u where u.login = :login")
	public Optional<Usuario> obterPorLogin(String login);

}
