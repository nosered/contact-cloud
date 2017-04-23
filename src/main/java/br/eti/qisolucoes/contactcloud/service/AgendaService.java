package br.eti.qisolucoes.contactcloud.service;

import br.eti.qisolucoes.contactcloud.model.Agenda;
import br.eti.qisolucoes.contactcloud.model.Contato;

public interface AgendaService {
	
	Agenda save(Agenda agenda);
	void remove(Long id);
	Agenda find(Long id);
	void addContato(Agenda agenda, Contato contato);
}
