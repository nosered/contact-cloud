package br.eti.qisolucoes.contactcloud.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.eti.qisolucoes.contactcloud.model.Agenda;
import br.eti.qisolucoes.contactcloud.model.Contato;
import br.eti.qisolucoes.contactcloud.repository.AgendaRepository;
import br.eti.qisolucoes.contactcloud.service.AgendaService;

@Service
public class AgendaServiceImpl implements AgendaService{

	@Autowired
	private AgendaRepository agendaRepository;
	
	@Override
	public Agenda save(Agenda agenda) {
		return agendaRepository.save(agenda);
	}

	@Override
	public void remove(Long id) {
		agendaRepository.delete(id);
	}

	@Override
	public Agenda find(Long id) {
		return agendaRepository.findOne(id);
	}
	
	public void addContato(Agenda agenda, Contato contato) {
		agenda.getContatos().add(contato);
		agendaRepository.save(agenda);
	}
}
