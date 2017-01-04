package com.tzmb2c.utils;

import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.UploadFileRequest;


public class OssUtil {
  private static Logger logger = Logger.getLogger(OssUtil.class);
  private static String endpoint = "http://oss-cn-shenzhen.aliyuncs.com/";
  private static String accessKeyId = "LTAIsDYF6mjRLc9u";
  private static String accessKeySecret = "eKCfMcZQJClOWtUo0m9OEMpkzij2RW";
  private static OSSClient ossClient;
  private static ObjectListing objectListing;

  public static OSSClient getOssClient() {
    return ossClient;
  }

  public static void setOssClient(OSSClient ossClient) {
    OssUtil.ossClient = ossClient;
  }

  public static String getAccessKeySecret() {
    return accessKeySecret;
  }

  public static void setAccessKeySecret(String accessKeySecret) {
    OssUtil.accessKeySecret = accessKeySecret;
  }

  public static String getAccessKeyId() {
    return accessKeyId;
  }

  public static void setAccessKeyId(String accessKeyId) {
    OssUtil.accessKeyId = accessKeyId;
  }

  public static String getEndpoint() {
    return endpoint;
  }

  public static void setEndpoint(String endpoint) {
    OssUtil.endpoint = endpoint;
  }

  /**
   * 断点上传文件.
   * 
   * @param localFile 上传文件
   * @param key 上传文件命名
   * @param bucketName OSS空间名
   * @throws Throwable
   */
  public static void breakPointUpload(String filename, String key, String bucketName)
      throws Throwable {
    try {
      OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
      UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, key);
      uploadFileRequest.setUploadFile(filename);
      uploadFileRequest.setTaskNum(1);
      uploadFileRequest.setEnableCheckpoint(true);
      ossClient.uploadFile(uploadFileRequest);
    } catch (OSSException oe) {
      logger.info("Caught an OSSException, which means your request made it to OSS, "
          + "but was rejected with an error response for some reason.");
      logger.info("Error Message: " + oe.getErrorCode());
      logger.info("Error Code:       " + oe.getErrorCode());
      logger.info("Request ID:      " + oe.getRequestId());
      logger.info("Host ID:           " + oe.getHostId());
    } catch (ClientException ce) {
      logger.info("Caught an ClientException, which means the client encountered "
          + "a serious internal problem while trying to communicate with OSS, "
          + "such as not being able to access the network.");
      logger.info("Error Message: " + ce.getMessage());
    }
    if (ossClient != null && !"".equals(ossClient)) {
      ossClient.shutdown();
    }
  }

  /**
   * 普通上传文件.
   * 
   * @param fin 上传文件IN流
   * @param key 上传文件命名
   * @param bucketName OSS空间名
   * @throws Throwable
   */
  public static void ordinaryUpload(String bucketName, String key, InputStream fin) {
    try {
      OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
      ossClient.putObject(bucketName, key, fin);
    } catch (OSSException oe) {
      logger.info("Caught an OSSException, which means your request made it to OSS, "
          + "but was rejected with an error response for some reason.");
      logger.info("Error Message: " + oe.getErrorCode());
      logger.info("Error Code:       " + oe.getErrorCode());
      logger.info("Request ID:      " + oe.getRequestId());
      logger.info("Host ID:           " + oe.getHostId());
    } catch (ClientException ce) {
      logger.info("Caught an ClientException, which means the client encountered "
          + "a serious internal problem while trying to communicate with OSS, "
          + "such as not being able to access the network.");
      logger.info("Error Message: " + ce.getMessage());
    } catch (Exception ex) {
      logger.info("Caught an Exception");
      logger.info("Error Message: " + ex.getMessage());
    }
    if (ossClient != null && !"".equals(ossClient)) {
      ossClient.shutdown();
    }
  }

  /**
   * 分页所有获取指定前缀的Object文件
   * 
   * @param bucketName OSS空间名
   * @param prefix Object前缀
   * @param nextMarker 下一页标记
   * @return
   * @throws Exception
   */
  public static ObjectListing listFileOfBucket(String bucketName, String prefix, String nextMarker)
      throws Exception {
    final int maxKeys = 10;
    final String keyPrefix = prefix;
    OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    do {
      objectListing =
          ossClient.listObjects(new ListObjectsRequest(bucketName).withPrefix(keyPrefix)
              .withMarker(nextMarker).withMaxKeys(maxKeys));

      List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
      for (OSSObjectSummary s : sums) {
        logger.info("\t" + s.getKey());
      }

      nextMarker = objectListing.getNextMarker();

    } while (objectListing.isTruncated());
    return objectListing;
  }

  /**
   * 删除单个OSS存储的文件
   * 
   * @param bucketName OSS空间名
   * @param key 需求删除的文件名
   * @return
   * @throws Exception
   */
  public static String delOss(String bucketName, String key) throws Exception {
    try {
      OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
      ossClient.deleteObject(bucketName, key);
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (ossClient != null && !"".equals(ossClient)) {
      ossClient.shutdown();
    }
    return null;
  }

  /**
   * 删除多个OSS存储的文件
   * 
   * @param bucketName OSS空间名
   * @param keys 需求删除的文件名集组
   * @return
   * @throws Exception
   */
  public static String delOssAll(String bucketName, List<String> keys) throws Exception {
    try {
      OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
      DeleteObjectsResult deleteObjectsResult =
          ossClient.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(keys));
      deleteObjectsResult.getDeletedObjects();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (ossClient != null && !"".equals(ossClient)) {
      ossClient.shutdown();
    }
    return null;
  }
}
