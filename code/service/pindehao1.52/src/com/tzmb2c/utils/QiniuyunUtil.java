package com.tzmb2c.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Recorder;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.model.FileListing;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;


public class QiniuyunUtil {
  private static final String ACCESS_KEY = "da-RC7kt9p07VH3vUabGWRENis8mPk6QiZRYYgLA";
  private static final String SECRET_KEY = "5uVkkKgzuS_3zy6iFanO01BmGrzTP7VlpIYsIJZ_";
  private static final int FILE_UPD_SUCC = 0;
  private static final int FILE_UPD_FAIL = 1;
  private static final int FILE_NAME_ERR = 2;
  private static final int FILE_TYPE_ERR = 3;
  private static int LIMIT_SIZE = 1000;
  /** 空间默认域名 */
  private static final String BUCKET_HOST_NAME = "http://7xs3re.com2.z0.glb.qiniucdn.com";
  private static UploadManager uploadManager = new UploadManager();
  /**
   * 断点续传本地路径
   */
  private static String recordPath = "C:/b2c-video/";
  private static Auth auth;

  /**
   * 获取权限.
   * 
   * @return
   */
  public static Auth getAuth() {
    if (auth == null) {
      auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    }
    return auth;
  }

  /**
   * 根据文件名获取公开空间访问链接.
   * 
   * @param buckerDomain 空间域名
   * @param key 文件名
   * @return
   */
  public static String getPublicUrl(String buckerDomain, String key) {
    return buckerDomain.concat(File.separator).concat(key);
  }

  /**
   * 根据文件名生成访问私有空间链接.
   * 
   * @param buckerDomain 空间域名
   * @param key 文件名
   * @param expireTime 过期时间
   * @return
   */
  public static String generalPrivateUrl(String buckerDomain, String key, long expireTime) {
    String fUrl = "";
    fUrl = buckerDomain.concat(File.separator).concat(key);
    fUrl = getAuth().privateDownloadUrl(fUrl, expireTime);
    return fUrl;
  }

  /**
   * 断点上传文件.
   * 
   * @param file 上传文件
   * @param bucketName 七牛空间名
   * @throws IOException
   */
  public static int breakPointUpload(File file, String filename, String bucketName)
      throws IOException {
    // 文件名
    String key = filename;
    if (StringUtils.isBlank(key)) {
      return FILE_NAME_ERR;
    } else if (!key.contains(".mp4")) {
      return FILE_TYPE_ERR;
    }
    // 设置断点记录文件保存在指定文件夹或的File对象
    // 实例化recorder对象
    Recorder recorder = new FileRecorder(recordPath);
    // 实例化上传对象，并且传入一个recorder对象
    UploadManager uploadManager = new UploadManager(recorder);
    Response res = null;
    try {
      // 调用put方法上传
      res = uploadManager.put(file, key, getAuth().uploadToken(bucketName));
      // 打印返回的信息
      System.out.println(">>>>qiniu:" + res.bodyString());
    } catch (QiniuException e) {
      Response r = e.response;
      // 请求失败时打印的异常的信息
      System.out.println(">>>>qiniu:" + r.toString());
      try {
        // 响应的文本信息
        System.out.println(">>>>qiniu:" + r.bodyString());
      } catch (QiniuException e1) {
        e1.printStackTrace();
      }
      return FILE_UPD_FAIL;
    }

    return FILE_UPD_SUCC;
  }

  /**
   * 
   * @Title: listBucket
   * @Description: 返回七牛帐号的所有空间
   * @param @return
   * @param @throws QiniuException
   * @return String[]
   * @throws
   */
  public static String[] listBucket() throws QiniuException {
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    BucketManager bucketManager = new BucketManager(auth);
    return bucketManager.buckets();
  }



  /**
   * @Title: listFileOfBucket
   * @Description: 获取指定空间下的文件列表
   * @param bucketName 空间名称
   * @param prefix 文件名前缀
   * @param limit 每次迭代的长度限制，最大1000，推荐值 100[即一个批次从七牛拉多少条]
   * @param @return
   * @return List<FileInfo>
   * @throws
   */
  static List<String> markersArr = null;

  public static int listFileOfBucket(String bucketName, String prefix, int limit) {
    if (markersArr == null) {
      markersArr = new ArrayList<String>();
    }
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    BucketManager bucketManager = new BucketManager(auth);
    BucketManager.FileListIterator it =
        bucketManager.createFileListIterator(bucketName, prefix, limit, null);
    int count = 0;
    List<String> markers = new ArrayList<String>();
    while (it.hasNext()) {
      if (it.getMarker() != null || it.getMarker() == "") {
        markers.add(it.getMarker());
      } else {
        markers.add("");
      }
      FileInfo[] items = it.next();
      count += items.length;
    }
    markersArr.clear();
    markersArr.addAll(markers);
    System.out.println(markersArr);
    return count;
  }

  /**
   * 根据前缀获取文件列表条数
   * 
   * @param bucket 空间名
   * @param prefix 文件名前缀
   * @param marker 上一次获取文件列表时返回的 marker
   * @param limit 每次迭代的长度限制，最大1000，推荐值 100
   * @param pageNo 前台传过来的页码
   * @param delimiter 指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
   * @return
   * @throws QiniuException
   */
  public static List<FileInfo> listFileOfBucket2(String bucket, String prefix, int pageNo,
      int limit, String delimiter) throws QiniuException {
    String marker = markersArr.get(pageNo);
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    BucketManager bucketManager = new BucketManager(auth);
    FileListing it = bucketManager.listFiles(bucket, prefix, marker, limit, delimiter);
    List<FileInfo> list = new ArrayList<FileInfo>();
    FileInfo[] items = it.items;
    if (null != items && items.length > 0) {
      list.addAll(Arrays.asList(items));
    }
    return list;
  }

  /**
   * 
   * @Title: uploadFile
   * @Description: 七牛图片上传
   * @param @param inputStream 待上传文件输入流
   * @param @param bucketName 空间名称
   * @param @param key 空间内文件的key
   * @param @param mimeType 文件的MIME类型，可选参数，不传入会自动判断
   * @param @return
   * @param @throws IOException
   * @return String
   * @throws
   */
  public static String uploadFile(InputStream inputStream, String bucketName, String key,
      String mimeType) throws IOException {
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    String token = auth.uploadToken(bucketName);
    byte[] byteData = IOUtils.toByteArray(inputStream);
    Response response = uploadManager.put(byteData, key, token, null, mimeType, false);
    inputStream.close();
    return response.bodyString();
  }

  /**
   * 
   * @Title: uploadFile
   * @Description: 七牛图片上传
   * @param @param inputStream 待上传文件输入流
   * @param @param bucketName 空间名称
   * @param @param key 空间内文件的key
   * @param @return
   * @param @throws IOException
   * @return String
   * @throws
   */
  public static String uploadFile(InputStream inputStream, String bucketName, String key)
      throws IOException {
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    String token = auth.uploadToken(bucketName);
    byte[] byteData = IOUtils.toByteArray(inputStream);
    Response response = uploadManager.put(byteData, key, token, null, null, false);
    inputStream.close();
    return response.bodyString();
  }

  /**
   * 
   * @Title: uploadFile
   * @Description: 七牛图片上传
   * @param filePath 待上传文件的硬盘路径
   * @param fileName 待上传文件的文件名
   * @param bucketName 空间名称
   * @param key 空间内文件的key
   * @param @return
   * @param @throws IOException
   * @return String
   * @throws
   */
  public static String uploadFile(String filePath, String fileName, String bucketName, String key)
      throws IOException {
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    String token = auth.uploadToken(bucketName);
    InputStream is = new FileInputStream(new File(filePath + fileName));
    byte[] byteData = IOUtils.toByteArray(is);
    Response response =
        uploadManager.put(byteData, key == null || "".equals(key) ? fileName : key, token);
    is.close();
    return response.bodyString();
  }

  /**
   * 
   * @Title: uploadFile
   * @Description: 七牛图片上传[若没有指定文件的key,则默认将fileName参数作为文件的key]
   * @param filePath 待上传文件的硬盘路径
   * @param fileName 待上传文件的文件名
   * @param bucketName 空间名称
   * @param @return
   * @param @throws IOException
   * @return String
   * @throws
   */
  public static String uploadFile(String filePath, String fileName, String bucketName)
      throws IOException {
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    String token = auth.uploadToken(bucketName);
    InputStream is = new FileInputStream(new File(filePath + fileName));
    byte[] byteData = IOUtils.toByteArray(is);
    Response response = uploadManager.put(byteData, fileName, token);
    is.close();
    return response.bodyString();
  }

  /**
   * @throws QiniuException
   * 
   * @Title: fetchToBucket
   * @Description: 提取网络资源并上传到七牛空间里
   * @param url 网络上一个资源文件的URL
   * @param bucketName 空间名称
   * @param key 空间内文件的key[唯一的]
   * @param @return
   * @return String
   * @throws
   */
  public static String fetchToBucket(String url, String bucketName, String key)
      throws QiniuException {
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    BucketManager bucketManager = new BucketManager(auth);
    DefaultPutRet putret = bucketManager.fetch(url, bucketName, key);
    return putret.key;
  }

  /**
   * 
   * @Title: fetchToBucket
   * @Description: 提取网络资源并上传到七牛空间里,不指定key，则默认使用url作为文件的key
   * @param url
   * @param bucketName
   * @param @return
   * @param @throws QiniuException
   * @return String
   * @throws
   */
  public static String fetchToBucket(String url, String bucketName) throws QiniuException {
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    BucketManager bucketManager = new BucketManager(auth);
    DefaultPutRet putret = bucketManager.fetch(url, bucketName);
    return putret.key;
  }

  /**
   * @throws QiniuException
   * 
   * @Title: copyFile
   * @Description: 七牛空间内文件复制
   * @param bucket 源空间名称
   * @param key 源空间里文件的key(唯一的)
   * @param targetBucket 目标空间
   * @param targetKey 目标空间里文件的key(唯一的)
   * @return void
   * @throws
   */
  public static void copyFile(String bucket, String key, String targetBucket, String targetKey)
      throws QiniuException {
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    BucketManager bucketManager = new BucketManager(auth);
    bucketManager.copy(bucket, key, targetBucket, targetKey);
  }

  /**
   * 
   * @Title: moveFile
   * @Description: 七牛空间内文件剪切
   * @param bucket 源空间名称
   * @param key 源空间里文件的key(唯一的)
   * @param targetBucket 目标空间
   * @param targetKey 目标空间里文件的key(唯一的)
   * @param @throws QiniuException
   * @return void
   * @throws
   */
  public static void moveFile(String bucket, String key, String targetBucket, String targetKey)
      throws QiniuException {
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    BucketManager bucketManager = new BucketManager(auth);
    bucketManager.move(bucket, key, targetBucket, targetKey);
  }

  /**
   * 
   * @Title: renameFile
   * @Description: 七牛空间内文件重命名
   * @param bucket
   * @param key
   * @param targetKey
   * @param @throws QiniuException
   * @return void
   * @throws
   */
  public static void renameFile(String bucket, String key, String targetKey) throws QiniuException {
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    BucketManager bucketManager = new BucketManager(auth);
    bucketManager.rename(bucket, key, targetKey);
  }

  /**
   * 
   * @Title: deleteFile
   * @Description: 七牛空间内文件删除
   * @param bucket 空间名称
   * @param key 空间内文件的key[唯一的]
   * @param @throws QiniuException
   * @return void
   * @throws
   */
  public static void deleteFile(String bucket, String key) throws QiniuException {
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    BucketManager bucketManager = new BucketManager(auth);
    bucketManager.delete(bucket, key);
  }

  /**
   * 
   * @Title: findFiles
   * @Description: 返回指定空间下的所有文件信息
   * @param @param bucketName 空间名称
   * @param @param prefix 文件key的前缀
   * @param @param limit 批量提取的最大数目
   * @param @return
   * @param @throws QiniuException
   * @return FileInfo[]
   * @throws
   */
  public static FileInfo[] findFiles(String bucketName, String prefix, int limit)
      throws QiniuException {
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    BucketManager bucketManager = new BucketManager(auth);
    FileListing listing = bucketManager.listFiles(bucketName, prefix, null, limit, null);
    if (listing == null || listing.items == null || listing.items.length <= 0) {
      return null;
    }
    return listing.items;
  }

  /**
   * 
   * @Title: findFiles
   * @Description: 返回指定空间下的所有文件信息
   * @param @param bucketName 空间名称
   * @param @param prefix 文件key的前缀
   * @param @return
   * @param @throws QiniuException
   * @return FileInfo[]
   * @throws
   */
  public static FileInfo[] findFiles(String bucketName, String prefix) throws QiniuException {
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    BucketManager bucketManager = new BucketManager(auth);
    FileListing listing = bucketManager.listFiles(bucketName, prefix, null, LIMIT_SIZE, null);
    if (listing == null || listing.items == null || listing.items.length <= 0) {
      return null;
    }
    return listing.items;
  }

  /**
   * 
   * @Title: findFiles
   * @Description: 返回指定空间下的所有文件信息
   * @param @param bucketName
   * @param @param key
   * @param @return
   * @param @throws QiniuException
   * @return FileInfo[]
   * @throws
   */
  public static FileInfo[] findFiles(String bucketName) throws QiniuException {
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    BucketManager bucketManager = new BucketManager(auth);
    FileListing listing = bucketManager.listFiles(bucketName, null, null, LIMIT_SIZE, null);
    if (listing == null || listing.items == null || listing.items.length <= 0) {
      return null;
    }
    return listing.items;
  }

  /**
   * 
   * @Title: findOneFile
   * @Description: 返回指定空间下的某个文件
   * @param @param bucketName
   * @param @param key
   * @param @param limit
   * @param @return
   * @param @throws QiniuException
   * @return FileInfo
   * @throws
   */
  public static FileInfo findOneFile(String bucketName, String key, int limit)
      throws QiniuException {
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    BucketManager bucketManager = new BucketManager(auth);
    FileListing listing = bucketManager.listFiles(bucketName, key, null, limit, null);
    if (listing == null || listing.items == null || listing.items.length <= 0) {
      return null;
    }
    return listing.items[0];
  }

  /**
   * 
   * @Title: findOneFile
   * @Description: 返回指定空间下的某个文件(重载)
   * @param @param bucketName
   * @param @param key
   * @param @return
   * @param @throws QiniuException
   * @return FileInfo
   * @throws
   */
  public static FileInfo findOneFile(String bucketName, String key) throws QiniuException {
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    BucketManager bucketManager = new BucketManager(auth);
    FileListing listing = bucketManager.listFiles(bucketName, key, null, LIMIT_SIZE, null);
    if (listing == null || listing.items == null || listing.items.length <= 0) {
      return null;
    }
    return listing.items[0];
  }

  /**
   * 
   * @Title: getFileAccessUrl
   * @Description: 返回七牛空间内指定文件的访问URL
   * @param @param key
   * @param @return
   * @param @throws QiniuException
   * @return String
   * @throws
   */
  public static String getFileAccessUrl(String key) throws QiniuException {
    return BUCKET_HOST_NAME + "/" + key;
  }


}
