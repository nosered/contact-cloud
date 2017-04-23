package br.eti.qisolucoes.contactcloud.service;

import java.util.List;

import br.eti.qisolucoes.contactcloud.model.Usuario;

public interface UsuarioService {
	
	Usuario save(Usuario usuario);
	void remove(Usuario usuario);
	Usuario find(Usuario usuario);
	List<Usuario> list();
	
}
