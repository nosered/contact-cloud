package br.eti.qisolucoes.contactcloud.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.eti.qisolucoes.contactcloud.model.Contato;
import br.eti.qisolucoes.contactcloud.repository.ContatoRepository;
import br.eti.qisolucoes.contactcloud.service.ContatoService;
import br.eti.qisolucoes.contactcloud.util.AwsS3Handle;

import static br.eti.qisolucoes.contactcloud.constants.AwsConstants.S3_DEFAULT_FILE;

@Service
public class ContatoServiceImpl implements ContatoService{

	@Autowired
	private ContatoRepository contatoRepository;
	@Autowired
	private AwsS3Handle s3;
	
	@Override
	public Contato save(Contato contato, MultipartFile imagem) {
		if(contato.getId() == null) { // ADICIONA CONTATO
			contato = contatoRepository.saveAndFlush(contato);
			if(!imagem.isEmpty()) { // COM FOTO
				String key = contato.getAgenda().getId() + "-" + contato.getId() + "-" + Calendar.getInstance().getTimeInMillis();
				contato.setFoto(key);
				s3.upload2(key, imagem);
				return contatoRepository.saveAndFlush(contato);
			}
			// SEM FOTO
			contato.setFoto(S3_DEFAULT_FILE);
			return contatoRepository.saveAndFlush(contato);
		} else { // ATUALIZA CONTATO
			if(!imagem.isEmpty()) { // COM FOTO
				// REMOVE FOTO ANTIGA EXCETO FOTO PADRAO
				if(!contato.getFoto().equals(S3_DEFAULT_FILE)){ 
					s3.delete(contato.getFoto());
				}
				String key = contato.getAgenda().getId() + "-" + contato.getId() + "-" + Calendar.getInstance().getTimeInMillis();
				contato.setFoto(key);
				s3.upload2(key, imagem);
				return contatoRepository.saveAndFlush(contato);
			}
			// SEM FOTO
			return contatoRepository.saveAndFlush(contato);
		}
	}

	@Override
	public void remove(Contato contato) {
		if(!contato.getFoto().equals(S3_DEFAULT_FILE)) {
			s3.delete(contato.getFoto());
		}
		contato = contatoRepository.findOne(contato.getId());
		contatoRepository.delete(contato);
	}
	
	public List<Contato> findByNome(String nome) {
		return contatoRepository.findAllByNomeContaining(nome);
	}
	
	public List<Contato> findByCidade(String cidade) {
		return contatoRepository.findAllByCidadeContaining(cidade);
	}
}
