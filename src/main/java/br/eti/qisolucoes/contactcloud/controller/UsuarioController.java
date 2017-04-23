package br.eti.qisolucoes.contactcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.eti.qisolucoes.contactcloud.model.Agenda;
import br.eti.qisolucoes.contactcloud.model.Contato;
import br.eti.qisolucoes.contactcloud.model.Usuario;
import br.eti.qisolucoes.contactcloud.service.AgendaService;
import br.eti.qisolucoes.contactcloud.service.ContatoService;
import br.eti.qisolucoes.contactcloud.service.UsuarioService;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private AgendaService agendaService;
	@Autowired
	private ContatoService contatoService;
	
	@GetMapping("/form")
	public ModelAndView form(Usuario usuario) {
		return new ModelAndView("register").addObject("usuario", usuario);
	}
	
	@GetMapping("/editar/form")
	public ModelAndView passwordForm(Authentication authentication) {
		Usuario usuario = (Usuario) authentication.getPrincipal();
		usuarioService.find(usuario);
		return new ModelAndView("password-change").addObject("usuario", usuario);
	}
	
	@PostMapping("/salvar")
	public ModelAndView save(@ModelAttribute("usuario") Usuario usuario) {
		usuario = usuarioService.save(usuario);
		if(usuario != null) {
			Agenda agenda = new Agenda();
			agenda.setId(usuario.getId());
			agenda.setUsuario(usuario);
			agenda = agendaService.save(agenda);
		}
		return new ModelAndView("login");
	}
	
	@PostMapping("/atualizar")
	public ModelAndView update(@ModelAttribute("usuario") Usuario usuario, @RequestParam("senhaAtual") String senhaAtual ,Authentication authentication) {
		Usuario usuarioBD = usuarioService.find(usuario);
		if(senhaAtual.equals(usuarioBD.getSenha())) {
			usuarioService.save(usuario);
			return new ModelAndView("redirect:/logout");
		}
		return passwordForm(authentication);
	}
	
	@GetMapping("/remover")
	public ModelAndView remove(Authentication authentication) {
		Usuario usuario = (Usuario) authentication.getPrincipal();
		Agenda agenda = agendaService.find(usuario.getId());
		for(Contato contato : agenda.getContatos()) {
			contatoService.remove(contato);
		}
		agendaService.remove(agenda.getId());
		usuarioService.remove(usuario);
		return new ModelAndView("redirect:/logout");
	}
}
