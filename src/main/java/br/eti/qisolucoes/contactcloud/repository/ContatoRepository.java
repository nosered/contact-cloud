package br.eti.qisolucoes.contactcloud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.eti.qisolucoes.contactcloud.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
	
	public List<Contato> findAllByNomeContaining(String nome);
	
	public List<Contato> findAllByCidadeContaining(String cidade);

}
