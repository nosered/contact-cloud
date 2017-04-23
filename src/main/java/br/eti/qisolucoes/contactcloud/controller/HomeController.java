package br.eti.qisolucoes.contactcloud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	/*@RequestMapping(value = "/agenda")
	public String home(Authentication authentication) {
		Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		if(authentication != null){
			Object obj = authentication.getPrincipal();
			if (obj instanceof Usuario){
				Usuario usuario = (Usuario) obj;
				model.addAttribute("agenda", usuario.getAgenda());
			}		
		}
		return "home";
	}*/
	
	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}
}
