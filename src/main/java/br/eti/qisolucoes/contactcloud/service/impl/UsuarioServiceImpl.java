package br.eti.qisolucoes.contactcloud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.eti.qisolucoes.contactcloud.model.Usuario;
import br.eti.qisolucoes.contactcloud.repository.UsuarioRepository;
import br.eti.qisolucoes.contactcloud.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario save(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

	@Override
	public void remove(Usuario usuario) {
		usuarioRepository.delete(usuario.getId());
	}

	@Override
	public Usuario find(Usuario usuario) {
		return usuarioRepository.findOne(usuario.getId());
	}

	@Override
	public List<Usuario> list() {
		return usuarioRepository.findAll();
	}

}
