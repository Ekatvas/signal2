/*
 * Copyright 2013-2020 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */
package org.whispersystems.textsecuregcm.s3;

import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.S3ClientOptions;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.xmlpull.v1.XmlPullParserException;
import io.minio.MinioClient;
import io.minio.errors.MinioException;

import java.net.URL;
import java.util.Date;

public class UrlSigner {

  private static final long   DURATION = 60 * 60 * 1000;

  private final String endpoint;
  private final String accessKey;
  private final String accessSecret;
  private final String bucket;

  public UrlSigner(String endpoint, String accessKey, String accessSecret, String bucket) {
      this.endpoint = endpoint;
      this.accessKey = accessKey;
      this.accessSecret = accessSecret;
      this.bucket      = bucket;
    }
  
  public String getPreSignedUrl(long attachmentId, HttpMethod method) throws MinioException, XmlPullParserException, NoSuchAlgorithmException, IOException, InvalidKeyException {
      String request = geturl(bucket, String.valueOf(attachmentId), method);
      return request;
  }

  public String geturl(String bucketname, String attachmentId, HttpMethod method)
          throws NoSuchAlgorithmException, IOException, InvalidKeyException, XmlPullParserException, MinioException
  {
    String url = null;
    MinioClient minioClient = new MinioClient(endpoint, accessKey, accessSecret);
    try {
      if(method==HttpMethod.PUT){
        url = minioClient.presignedPutObject(bucketname, attachmentId, 60 * 60 * 24);
      }
      if(method==HttpMethod.GET){
        url = minioClient.presignedGetObject(bucketname, attachmentId);
      }
      System.out.println(url);
    } catch(MinioException e) {
      System.out.println("Error occurred: " + e);
    } catch (java.security.InvalidKeyException e) {
      e.printStackTrace();
    }
    return url;
  }
}
