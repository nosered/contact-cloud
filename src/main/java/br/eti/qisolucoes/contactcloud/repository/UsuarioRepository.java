package br.eti.qisolucoes.contactcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.eti.qisolucoes.contactcloud.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Usuario findByLogin(String login);

}
