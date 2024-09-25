package com.project.ecodein.controller;

import org.springframework.web.bind.annotation.RestController;
import com.project.ecodein.service.AmazonS3Service;

@RestController
public class AmazonS3Controller {
	
	private final AmazonS3Service AMAZONS3SERVICE;

	public AmazonS3Controller (AmazonS3Service amazonS3Service) {
		this.AMAZONS3SERVICE = amazonS3Service;
	}

	// 이미지 업로드 process
	public String uploadImage (String path) {
		String imageLink = "";
		
		return imageLink;
	}
	
}
