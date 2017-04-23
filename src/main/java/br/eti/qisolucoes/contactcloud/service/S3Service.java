package br.eti.qisolucoes.contactcloud.service;

import org.springframework.web.multipart.MultipartFile;

public interface S3Service {
	
	void upload(String key, MultipartFile foto);
	void upload2(String key, MultipartFile foto);
	boolean exists(String key);
	
}
