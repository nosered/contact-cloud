package br.eti.qisolucoes.contactcloud.util;

import static br.eti.qisolucoes.contactcloud.constants.AwsConstants.BUCKET_NAME;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Component
public class AwsS3Handle {

	@Autowired
	private AmazonS3 amazonS3;
	
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
	
	public void delete(String key) {
		amazonS3.deleteObject(new DeleteObjectRequest(BUCKET_NAME, key));
	}
}
