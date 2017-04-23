package br.eti.qisolucoes.contactcloud.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.eti.qisolucoes.contactcloud.model.Contato;

public interface ContatoService {
	
	Contato save(Contato contato, MultipartFile imagem);
	void remove(Contato contato);
	List<Contato> findByNome(String nome);
	List<Contato> findByCidade(String cidade);
}
