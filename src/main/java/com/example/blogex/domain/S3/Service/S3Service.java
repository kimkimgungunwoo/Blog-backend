package com.example.blogex.domain.S3.Service;


import com.amazonaws.services.s3.AmazonS3;
import com.example.blogex.domain.S3.Dto.S3UploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final AmazonS3 amazonS3;

    @Value("${app.s3.bucket}")
    private String bucket;

    public S3UploadDto upload(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID()+"-"+(file.getOriginalFilename()==null?"":file.getOriginalFilename());

        String Key=fileName;

        amazonS3.putObject(bucket,Key,file.getInputStream(),null);

        String url=amazonS3.getUrl(bucket,Key).toString();
        return new S3UploadDto(url,Key);
    }

    public void delete(String key){
        amazonS3.deleteObject(bucket,key);
    }



}
