package com.project.ecodein.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;

@Service
@RequiredArgsConstructor
public class AmazonS3Service {

    private final S3Client S3_CLIENT;
    // 개발기간만 가용됨.
    private final String folder = "devlop/";

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    // bucket 에서 업로드되어있는 파일을 가져오는 메서드
    public String getThumbnail(String path) {
//        return s3Client.getUrl(bucketName, folder+path).toString();

        GetUrlRequest getUrlRequest = GetUrlRequest.builder().bucket(bucketName).key(folder.concat(path)).build();

        return S3_CLIENT.utilities().getUrl(getUrlRequest).toString();
    }
    
    // bucket 서버에 파일을 업로드하는 메서드
    public void uploadThumnail(String path) {
    	
    }
}
