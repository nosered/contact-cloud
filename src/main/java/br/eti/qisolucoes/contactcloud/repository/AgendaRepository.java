package br.eti.qisolucoes.contactcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.eti.qisolucoes.contactcloud.model.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long>{
	
}
