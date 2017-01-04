package com.tzmb2c.web.action;

import java.io.File;
import java.util.List;

import maowu.framework.utils.web.SuperAction;

import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.OssUtil;

public class OssAction extends SuperAction {
  private File[] files;
  private File file;
  private String fileFileName;
  private String filesFileName;
  private String key;
  private List<String> keys;

  public List<String> getKeys() {
    return keys;
  }

  public void setKeys(List<String> keys) {
    this.keys = keys;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public File[] getFiles() {
    return files;
  }

  public void setFiles(File[] files) {
    this.files = files;
  }

  public File getFile() {
    return file;
  }

  public void setFile(File file) {
    this.file = file;
  }

  public String getFileFileName() {
    return fileFileName;
  }

  public void setFileFileName(String fileFileName) {
    this.fileFileName = fileFileName;
  }

  public String getFilesFileName() {
    return filesFileName;
  }

  public void setFilesFileName(String filesFileName) {
    this.filesFileName = filesFileName;
  }

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  private String filename;

  /**
   * 上传文件页面
   * 
   * @return
   */
  /*
   * public String ossUpload() { return SUCCESS; }
   */

  /**
   * 上传文件提交
   * 
   * @return
   * @throws Throwable
   * @throws IOException
   */
  /*
   * public String ossUploadSubmit() throws Throwable { if (file != null) {
   * OssUtil.breakPointUpload(file, file.getName(), "taozhumab2c"); } else if (files != null) { for
   * (int i = 0; i < files.length; i++) { OssUtil.breakPointUpload(files[i], files[i].getName(),
   * "taozhumab2c"); } } return null; }
   */

  /**
   * 文件条数
   * 
   * @return
   * @throws Exception
   */
  /*
   * public String ossFileCount() throws QiniuException { if (page == null){ page = new Pager(); }
   * int FileInfoCount = QiniuyunUtil.listFileOfBucket("b2c-video",filename,10);
   * page.setRowCount(FileInfoCount); return SUCCESS; }
   *//**
   * 文件list
   * 
   * @return
   * @throws Exception
   */
  /*
   * public String ossFileList() throws QiniuException { if (page == null){ page = new Pager(); }
   * List<FileInfo> FileInfoList =
   * QiniuyunUtil.listFileOfBucket2("b2c-video",filename,page.getPageNo()-1,10,""); DecimalFormat df
   * = new DecimalFormat("#.##"); for (FileInfo fileInfo : FileInfoList) {
   * fileInfo.setFileURL(QiniuyunUtil.getFileAccessUrl(fileInfo.getKey()));
   * fileInfo.setPutTimeStr(StringUtil.dateString(new Date(fileInfo.getPutTime() / 10000)));
   * fileInfo.setFsizeStr(df.format(Double.valueOf(fileInfo.getFsize()) / Math.pow(1024 , 2))); }
   * JSONArray json = JSONArray.fromObject(FileInfoList); page.setResult(json.toString()); return
   * SUCCESS; }
   */

  /**
   * 删除文件
   * 
   * @return
   * @throws Exception
   */
  public String delOss() throws Exception {
    try {
      OssUtil.delOss("taozhumab2c", key);
      FileUtil.alertMessageBySkip("删除成功！", "Oss.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("删除失败！", "Oss.do");
    }
    return null;
  }

  /**
   * 批量删除文件
   * 
   * @return
   * @throws Exception
   */
  public String delOssAll() throws Exception {
    try {
      if (keys == null || "".equals(keys)) {
        FileUtil.alertMessageBySkip("请勾选需要删除的文件！", "Oss.do");
        return null;
      } else {
        OssUtil.delOssAll("taozhumab2c", keys);
        FileUtil.alertMessageBySkip("批量删除成功！", "Oss.do");
      }
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("批量删除失败！", "Oss.do");
    }
    return null;
  }
}
