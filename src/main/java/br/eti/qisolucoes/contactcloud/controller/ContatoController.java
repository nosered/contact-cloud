package br.eti.qisolucoes.contactcloud.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.eti.qisolucoes.contactcloud.model.Agenda;
import br.eti.qisolucoes.contactcloud.model.Contato;
import br.eti.qisolucoes.contactcloud.model.Usuario;
import br.eti.qisolucoes.contactcloud.service.AgendaService;
import br.eti.qisolucoes.contactcloud.service.ContatoService;

@Controller
@RequestMapping(value = "/contato")
public class ContatoController {

	@Autowired
	private ContatoService contatoService;
	@Autowired
	private AgendaService agendaService;
	
	@GetMapping(value = "/form")
	public ModelAndView form(Contato contato) {
		ModelAndView mv = new ModelAndView("contato-form");
		mv.addObject("contato", contato);
		return mv;
	}
	
	@PostMapping(value = "/salvar")
	@Transactional
	public ModelAndView save(@ModelAttribute("contato") Contato contato, @RequestParam("imagem") MultipartFile imagem, Authentication authentication) {
		Usuario usuario = (Usuario) authentication.getPrincipal();
		Agenda agenda = agendaService.find(usuario.getId());
		contato.setAgenda(agenda);
		contato = contatoService.save(contato, imagem);
		return new ModelAndView("redirect:/agenda/abrir");
	}
	
	@GetMapping(value = "/editar/{id}")
	public ModelAndView edit(@PathVariable("id") Contato contato) {
		return form(contato);
	}
	
	@GetMapping(value = "/remover/{id}")
	public ModelAndView remove(@PathVariable("id") Contato contato) {
		System.out.println("> > > REMOVER CONTATO");
		contatoService.remove(contato);
		return new ModelAndView("redirect:/agenda/abrir");
	}
	
	@GetMapping(value = "/buscar/nome/{busca}")
	public ModelAndView findByNome(@PathVariable("busca") String nome) {
		List<Contato> contatos = contatoService.findByNome(nome);
		return new ModelAndView("agenda").addObject("contatos", contatos);
	}
	
	@GetMapping(value = "/buscar/cidade/{busca}")
	public ModelAndView findByCidade(@PathVariable("busca") String cidade) {
		List<Contato> contatos = contatoService.findByCidade(cidade);
		return new ModelAndView("agenda").addObject("contatos", contatos);
	}
	
}
