package com.tzmb2c.web.action;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.qiniu.common.QiniuException;
import com.qiniu.storage.model.FileInfo;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.QiniuyunUtil;
import com.tzmb2c.utils.StringUtil;

public class QiNiuAction extends SuperAction {
  private File[] files;
  private File file;
  private String fileFileName;
  private String filesFileName;
  private String key;
  private String[] tids;

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  private String filename;

  /**
   * 七牛上传文件页面
   * 
   * @return
   */
  public String qiniuUpload() {

    return SUCCESS;
  }

  /**
   * 七牛上传文件提交
   * 
   * @return
   * @throws IOException
   */
  public String qiniuUploadSubmit() throws IOException {
    HttpServletRequest request = ServletActionContext.getRequest();

    if (file != null) {
      int result = 0;
      String filename = (String) request.getAttribute("fileFileName");
      result = QiniuyunUtil.breakPointUpload(file, filename, "b2c-video");
      if (result == 0) {
        FileUtil.alertMessageBySkip("上传成功！", "qiniuManage.do");
      } else if (result == 2) {
        FileUtil.alertMessageBySkip("上传失败！请选择要上传的文件！", "qiniuManage.do");
      } else if (result == 3) {
        FileUtil.alertMessageBySkip("上传失败！上传的文件只能为MP4类型！", "qiniuManage.do");
      } else {
        FileUtil.alertMessageBySkip("上传失败！", "qiniuManage.do");
      }
    } else if (files != null) {
      int result = 0;
      String filenames = (String) request.getAttribute("filesFileName");
      String[] filenamestr = filenames.split(",");
      for (int i = 0; i < files.length; i++) {
        result += QiniuyunUtil.breakPointUpload(files[i], filenamestr[i].trim(), "b2c-video");
      }
      if (result == 0) {
        FileUtil.alertMessageBySkip("上传成功！", "qiniuManage.do");
        // } else if (result == 2) {
        // FileUtil.alertMessageBySkip("上传失败！请选择要上传的文件！", "qiniuManage.do");
        // } else if (result == 3) {
        // FileUtil.alertMessageBySkip("上传失败！上传的文件只能为MP4类型！", "qiniuManage.do");
      } else {
        FileUtil.alertMessageBySkip("上传失败！", "qiniuManage.do");
      }
    } else {
      FileUtil.alertMessageBySkip("上传失败！请选择要上传的文件！", "qiniuManage.do");
    }
    return null;
  }

  /**
   * 文件条数
   * 
   * @return
   * @throws QiniuException
   */
  public String qiniuFileCount() throws QiniuException {
    if (page == null) {
      page = new Pager();
    }
    int FileInfoCount = QiniuyunUtil.listFileOfBucket("b2c-video", filename, 10);
    page.setRowCount(FileInfoCount);
    return SUCCESS;
  }

  /**
   * 文件list
   * 
   * @return
   * @throws QiniuException
   */
  public String qiniuFileList() throws QiniuException {
    if (page == null) {
      page = new Pager();
    }
    List<FileInfo> FileInfoList =
        QiniuyunUtil.listFileOfBucket2("b2c-video", filename, page.getPageNo() - 1, 10, "");
    DecimalFormat df = new DecimalFormat("#.##");
    for (FileInfo fileInfo : FileInfoList) {
      fileInfo.setFileURL(QiniuyunUtil.getFileAccessUrl(fileInfo.getKey()));
      fileInfo.setPutTimeStr(StringUtil.dateString(new Date(fileInfo.getPutTime() / 10000)));
      fileInfo.setFsizeStr(df.format(Double.valueOf(fileInfo.getFsize()) / Math.pow(1024, 2)));
    }
    JSONArray json = JSONArray.fromObject(FileInfoList);
    page.setResult(json.toString());
    return SUCCESS;
  }

  /**
   * 删除文件
   * 
   * @return
   * @throws QiniuException
   */
  public String delQiNiu() throws QiniuException {
    try {
      QiniuyunUtil.deleteFile("b2c-video", key);
      FileUtil.alertMessageBySkip("删除成功！", "qiniuManage.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("删除失败！", "qiniuManage.do");
    }
    return null;
  }

  /**
   * 批量删除文件
   * 
   * @return
   * @throws QiniuException
   */
  public String delQiNiuAll() throws QiniuException {
    try {
      if (tids == null || "".equals(tids)) {
        FileUtil.alertMessageBySkip("请勾选需要删除的文件！", "qiniuManage.do");
        return null;
      } else {
        for (String key : tids) {
          QiniuyunUtil.deleteFile("b2c-video", key);
        }
        FileUtil.alertMessageBySkip("批量删除成功！", "qiniuManage.do");
      }
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("批量删除失败！", "qiniuManage.do");
    }
    return null;
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
}
