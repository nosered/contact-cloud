package br.eti.qisolucoes.contactcloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.eti.qisolucoes.contactcloud.model.Usuario;
import br.eti.qisolucoes.contactcloud.repository.UsuarioRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByLogin(login);
		
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		
		return usuario;
	}
}
