package com.qiniu.storage.model;

public class FileInfo {
  public String key;
  public String hash;
  public long fsize;
  public long putTime;
  public String mimeType;
  public String endUser;
  public String fileURL;
  public String putTimeStr;
  public String fsizeStr;

  public String getFsizeStr() {
    return fsizeStr;
  }

  public void setFsizeStr(String fsizeStr) {
    this.fsizeStr = fsizeStr;
  }

  public String getPutTimeStr() {
    return putTimeStr;
  }

  public void setPutTimeStr(String putTimeStr) {
    this.putTimeStr = putTimeStr;
  }

  public String getFileURL() {
    return fileURL;
  }

  public void setFileURL(String fileURL) {
    this.fileURL = fileURL;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getHash() {
    return hash;
  }

  public void setHash(String hash) {
    this.hash = hash;
  }

  public long getFsize() {
    return fsize;
  }

  public void setFsize(long fsize) {
    this.fsize = fsize;
  }

  public long getPutTime() {
    return putTime;
  }

  public void setPutTime(long putTime) {
    this.putTime = putTime;
  }

  public String getMimeType() {
    return mimeType;
  }

  public void setMimeType(String mimeType) {
    this.mimeType = mimeType;
  }

  public String getEndUser() {
    return endUser;
  }

  public void setEndUser(String endUser) {
    this.endUser = endUser;
  }


}
