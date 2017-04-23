package br.eti.qisolucoes.contactcloud.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;

import br.eti.qisolucoes.contactcloud.service.S3Service;

import static br.eti.qisolucoes.contactcloud.constants.AwsConstants.BUCKET_NAME;
import static br.eti.qisolucoes.contactcloud.constants.AwsConstants.S3_PREFIX;

@Service
public class S3ServiceImpl implements S3Service {
	
	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	private AmazonS3 amazonS3;

	@Override
	public void upload(String key, MultipartFile foto) {
		System.out.println("SALVAR IMAGEM: " + S3_PREFIX + BUCKET_NAME + "/" + key);
		Resource resource = this.resourceLoader.getResource(S3_PREFIX + BUCKET_NAME + "/" + key);
		WritableResource writableResource = (WritableResource) resource;
		try {
			OutputStream output = writableResource.getOutputStream();
			output.write(foto.getBytes());
			System.out.println("> > > SUCCESS UPLOAD < < <");
		} catch (IOException e) {
			System.out.println("> > > FAILED UPLOAD < < <");
			e.printStackTrace();
		}
	}
	
	public void upload2(String key, MultipartFile foto) {
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType(foto.getContentType());
		try {
			InputStream input = foto.getInputStream();
			amazonS3.putObject(BUCKET_NAME, key , input, metadata);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean exists(String key) {
		if (amazonS3.getObject(new GetObjectRequest(BUCKET_NAME, key)) == null)
			return false;
		return true;
	}
}
