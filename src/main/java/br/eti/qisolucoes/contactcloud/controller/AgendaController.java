package br.eti.qisolucoes.contactcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.eti.qisolucoes.contactcloud.model.Usuario;
import br.eti.qisolucoes.contactcloud.service.AgendaService;

@Controller
@RequestMapping(value = "/agenda")
public class AgendaController {
	
	@Autowired
	private AgendaService agendaService;
	
	@GetMapping(value = "/abrir")
	public ModelAndView get(Authentication authentication) {
		Usuario usuario = (Usuario) authentication.getPrincipal();
		return new ModelAndView("agenda").addObject("contatos", agendaService.find(usuario.getId()).getContatos());
	}
	
}
