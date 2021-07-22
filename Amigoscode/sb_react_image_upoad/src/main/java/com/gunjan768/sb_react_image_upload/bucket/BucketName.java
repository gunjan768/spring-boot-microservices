package com.gunjan768.sb_react_image_upload.bucket;

public enum BucketName {

    PROFILE_IMAGE("react-sb-amigoscode");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}