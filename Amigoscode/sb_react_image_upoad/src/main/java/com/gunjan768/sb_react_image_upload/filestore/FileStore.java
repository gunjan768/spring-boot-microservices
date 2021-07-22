package com.gunjan768.sb_react_image_upload.filestore;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

//Error : https://stackoverflow.com/questions/59517989/spring-cloud-aws-sqs-fails-to-connect-to-service-endpoint-locally

@Service
public class FileStore {

    private final AmazonS3 amazonS3;

    @Autowired
    public FileStore(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    // Optional<Map<String, String>> will serve as the meta-data that we can pass to store images or files so can include the
    // content type or content length
    public void save(String path, String fileName, Optional<Map<String, String>> optionalMetaData, InputStream inputStream) {

        ObjectMetadata metaData = new ObjectMetadata();

        optionalMetaData.ifPresent(map -> {
            if(!map.isEmpty()) {
                map.forEach(metaData::addUserMetadata);
            }
        });

        try {
            amazonS3.putObject(path, fileName, inputStream, metaData);
        } catch (AmazonServiceException e) {
            throw new IllegalStateException("Failed to store the file to S3 storage ", e);
        }
    }

    public byte[] download(String path, String key) {
        try {
            S3Object object = amazonS3.getObject(path, key);
            return IOUtils.toByteArray(object.getObjectContent());
        } catch (AmazonServiceException | IOException e) {
            throw new IllegalStateException("Failed to download file to s3", e);
        }
    }
}
