package com.tzmb2c.utils;

import io.rong.util.CodeUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;

import com.baidu.ueditor.define.FileType;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tzmb2c.business.service.ConstParam;


/**
 * 
 * @Title:文件操作工具类
 * @Description:
 * @Author:fengjianshe
 * @Since:2013年8月2日
 * @Version:1.1.0
 */

public class FileUtil extends ActionSupport {
  private static final Log LOG = LogFactory.getLog(FileUtil.class);

  /**
   * 
   * @param in 来源的数据输入流（网络Or本地文件）
   * @param filePath 复制目的文件的路径
   * @throws IOException
   * @Description:可用于本地文件拷贝，远程文件上传
   */
  public static void copyFile(InputStream in, String filePath) throws IOException {
    BufferedInputStream inBuff = null;
    BufferedOutputStream outBuff = null;
    File targetFile = null;
    try {
      inBuff = new BufferedInputStream(in);
      targetFile = new File(filePath);
      outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));
      byte[] b = new byte[1024 * 5];
      int len;
      while ((len = inBuff.read(b)) != -1) {
        outBuff.write(b, 0, len);
      }
      outBuff.flush();
    } finally {
      if (inBuff != null) {
        inBuff.close();
      }
      if (outBuff != null) {
        outBuff.close();
      }
    }
  }


  /**
   * 
   * 基于Struts2的上传文件
   * 
   * @param file 上传的文件（jsp: <input type="file" name="files">）
   * @param filePath 上传到指定的地址
   * @param fileName 定义文件名以及后缀
   * @Description:用于文件上传
   * @throws Exception
   */
  public static void uploadFile(File file, String filePath, String fileName) throws Exception {
    FileInputStream fis = null;
    OutputStream os = null;
    try {

      fis = new FileInputStream(file);
      // 设置文件路径与文件名以及后缀
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/maowup") + filePath;

      File dirfile = new File(uploadPath);

      // 是否存在目录 如果不存在创建文件
      if (!dirfile.exists() || null != dirfile) {
        dirfile.mkdirs();
      }

      File toFile = new File(uploadPath, fileName);

      // 创建一个输出流
      os = new FileOutputStream(toFile);
      // 设置缓存
      byte[] buffer = new byte[1024];
      int length = 0;

      // 读取myFile文件输出到toFile文件中
      while ((length = fis.read(buffer)) > 0) {
        os.write(buffer, 0, length);
      }

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (fis != null) {
        // 关闭输入流
        fis.close();
      }
      // 关闭输出流
      if (os != null) {
        os.close();
      }
    }


  }



  /**
   * 
   * 文件下载方法
   * 
   * @param filePath 文件地址
   * @param fileName 文件名
   * @return true文件存在 false文件不存在
   * @throws UnsupportedEncodingException
   */
  public static boolean downloadFile(String filePath, String fileName)
      throws UnsupportedEncodingException {
    HttpServletResponse response = ServletActionContext.getResponse();
    boolean returnboolean = true;
    String uploadPath =
        ServletActionContext.getServletContext().getRealPath("/maowup" + filePath + fileName);
    InputStream input = null;
    OutputStream output = null;

    try {
      File file = new File(uploadPath);
      if (file.length() > 0) {


        response.setContentType("application/x-msdownload; charset=UTF-8");
        response.setHeader("Content-Disposition",
            "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
        response.addHeader("Content-Length", file.length() + "");

        output = response.getOutputStream();
        input = new FileInputStream(file);
        byte[] temp = new byte[1024 * 10];
        while (input.read(temp) != -1) {
          output.write(temp);
        }
        output.flush();
        returnboolean = true;
      } else {
        returnboolean = false;
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

    }
    return returnboolean;
  }


  /**
   * 删除本地文件/目录
   * 
   * @param file
   * @throws Exception
   */
  public static void deleteFile(File file) throws Exception {
    if (file.exists()) { // 判断文件是否存在
      if (file.isFile()) { // 判断是否是文件
        file.delete(); // 删除文件
      } else if (file.isDirectory()) { // 否则如果它是一个目录
        File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
        for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
          deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
        }
      }
      file.delete();
    } else {
      System.out.println("所删除的文件不存在！" + '\n');
    }
  }


  /**
   * 
   * 根据文件名获得web../maowup/...目录下的文件
   * 
   * @param fielName
   * @return
   */
  public static File findFile(String fielName) {

    String uploadPath = ServletActionContext.getServletContext().getRealPath("/maowup" + fielName);
    File myDelFile = new File(uploadPath);
    return myDelFile;

  }



  /**
   * 
   * 验证判断文件大小是否超过2MB
   * 
   * @param files 上传的文件
   * @return
   */
  public static boolean testFileSize(File files) {
    boolean returnboolean = true;
    if (files != null) {



      Integer filesize = Integer.parseInt(files.length() / 1024000 + "");
      if (filesize >= 2) {


        returnboolean = false;
      } else {
        returnboolean = true;

      }
    }
    return returnboolean;

  }


  /**
   * 
   * 验证判断多个文件大小是否超过2MB
   * 
   * @param files 上传的文件
   * @return
   */
  public static boolean testFileSizeMulti(File[] files) {
    boolean returnboolean = true;
    if (files != null) {

      for (int j = 0; j < files.length; j++) {
        Integer filesize = Integer.parseInt(files[j].length() / 1024000 + "");
        if (filesize >= 2) {

          returnboolean = false;
        } else {
          returnboolean = true;

        }
      }
    }
    return returnboolean;

  }



  /**
   * 生成4位随机数
   * 
   * @param num
   * @return
   */
  public static String getRandomNum(int num) {
    String[] digits = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
    Random rnum = new Random(new Date().getTime());
    for (int i = 0; i < digits.length; i++) {
      int index = Math.abs(rnum.nextInt()) % 10;
      String tmpDigit = digits[index];
      digits[index] = digits[i];
      digits[i] = tmpDigit;
    }
    String returnStr = digits[0];
    for (int i = 1; i < num; i++) {
      returnStr = digits[i] + returnStr;
    }
    return returnStr;
  }


  /**
   * 通用提示信息并跳转Action
   * 
   * @param message
   */
  public static void returnPopupMessage(String message) {
    try {
      ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
      ServletActionContext.getResponse().getWriter().write(message);
    } catch (Exception ex) {
      LOG.error("Return popup message{" + message + "} to browser error:", ex);
    }
  }

  /**
   * 通用提示信息并可用于跳转Action(可传值)
   * 
   * @param alerMess 提示消息值
   * @param url 1.跳转地址（XXX.do）2.传值（XXX.do？对象属性=值&对象属性=值....）
   */
  public static void alertMessageBySkip(String alerMess, String url) {
    String messages =
        "<script type='text/javascript'>alert('" + alerMess + "');window.location.href='" + url
            + "';</script>";
    try {
      ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
      ServletActionContext.getResponse().getWriter().write(messages);

    } catch (Exception ex) {
      LOG.error("Return popup message{" + messages + "} to browser error:", ex);
    }
  }



  /**
   * 通用提示信息并可用于跳转Action(可传值)
   * 
   * @param alerMess 提示消息值
   * @param url 1.跳转地址（XXX.do）2.传值（XXX.do？对象属性=值&对象属性=值....）
   */
  public static void alertMessageBySkipAttaDetail(String alerMess, String url) {
    String messages =
        "<script type='text/javascript'>alert('" + alerMess + "');window.location.href='" + url
            + "';</script>";
    try {
      ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
      ServletActionContext.getResponse().getWriter().write(messages);

    } catch (Exception ex) {
      LOG.error("Return popup message{" + messages + "} to browser error:", ex);
    }
  }

  /**
   * 通用跳转Action(可传值)
   * 
   * @param url 1.跳转地址（XXX.do）2.传值（XXX.do？对象属性=值&对象属性=值....）
   * 
   */
  public static void skipAction(String url) {

    String messages = "<script type='text/javascript'>window.location.href='" + url + "';</script>";
    try {
      ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
      ServletActionContext.getResponse().getWriter().write(messages);

    } catch (Exception ex) {
      LOG.error("Return popup message{" + messages + "} to browser error:", ex);
    }

  }

  /**
   * 通用跳转上一页面
   * 
   * 
   */
  public static void skipHistoryAction() {

    String messages = "<script type='text/javascript'>window.history.go(-2);</script>";
    try {
      ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
      ServletActionContext.getResponse().getWriter().write(messages);

    } catch (Exception ex) {
      LOG.error("Return popup message{" + messages + "} to browser error:", ex);
    }

  }

  /**
   * 通用跳转上N页面
   * 
   * @param i 跳回上几步
   */
  public static void skipActionByHistory(Integer i) {
    String messages = "<script type='text/javascript'>window.history.go(" + i + ");</script>";
    try {
      ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
      ServletActionContext.getResponse().getWriter().write(messages);
    } catch (Exception ex) {
      LOG.error("Return popup message{" + messages + "} to browser error:", ex);
    }

  }

  /**
   * 通用提示信息
   * 
   * @param message 提示消息
   */
  public static void alertMessage(String alerMess) {
    String messages = "<script type='text/javascript'>alert('" + alerMess + "');</script>";
    try {
      ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
      ServletActionContext.getResponse().getWriter().write(messages);

    } catch (Exception ex) {
      LOG.error("Return popup message{" + messages + "} to browser error:", ex);
    }
  }

  /**
   * 确认 取消对话框
   * 
   * @param message 提示消息
   * @param ifStrJS if操作JS代码
   * @param elseStrJS else操作JS代码
   */
  public static void confirmMessage(String message, String ifStrJS, String elseStrJS) {
    String messages =
        "<script type='text/javascript'>if(window.confirm('" + message + "')){" + ifStrJS
            + "}else{" + elseStrJS + "}</script>";
    try {
      ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
      ServletActionContext.getResponse().getWriter().write(messages);

    } catch (Exception ex) {
      LOG.error("Return popup message{" + messages + "} to browser error:", ex);
    }
  }

  public static void alertMessageByClose(String alerMess) {
    String messages =
        "<script type='text/javascript'>alert('" + alerMess + "'); this.window.close();</script>";
    try {
      ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
      ServletActionContext.getResponse().getWriter().write(messages);

    } catch (Exception ex) {
      LOG.error("Return popup message{" + messages + "} to browser error:", ex);
    }
  }

  /**
   * 复制单个文件
   * 
   * @param oldPath String 原文件路径 如：c:/fqf.txt
   * @param newPath String 复制后路径 如：f:/fqf.txt
   * @return boolean
   */
  public static void copyFile(String oldPath, String newPath) {
    InputStream inStream = null;
    FileOutputStream fs = null;
    try {
      int byteread = 0;
      File oldfile = new File(oldPath);
      if (oldfile.exists()) { // 文件存在时
        inStream = new FileInputStream(oldPath); // 读入原文件
        fs = new FileOutputStream(newPath);
        byte[] buffer = new byte[1444];
        while ((byteread = inStream.read(buffer)) != -1) {
          // System.out.println(bytesum);
          fs.write(buffer, 0, byteread);
        }
        fs.flush();
      }
    } catch (Exception e) {
      System.out.println("复制单个文件操作出错");
      e.printStackTrace();
    } finally {
      if (fs != null) {
        try {
          fs.close();
        } catch (Exception e2) {
          e2.printStackTrace();
        }
      }
      if (inStream != null) {
        try {
          inStream.close();
        } catch (Exception e2) {
          e2.printStackTrace();
        }
      }
    }

  }

  /**
   * 复制整个文件夹内容
   * 
   * @param oldPath String 原文件路径 如：c:/fqf
   * @param newPath String 复制后路径 如：f:/fqf/ff
   * @return boolean
   */
  public static void copyFolder(String oldPath, String newPath) {
    FileInputStream input = null;
    FileOutputStream output = null;
    try {
      new File(newPath).mkdirs(); // 如果文件夹不存在 则建立新文件夹
      File a = new File(oldPath);
      String[] file = a.list();
      File temp = null;
      for (int i = 0; i < file.length; i++) {
        if (oldPath.endsWith(File.separator)) {
          temp = new File(oldPath + file[i]);
        } else {
          temp = new File(oldPath + File.separator + file[i]);
        }

        if (temp.isFile()) {
          input = new FileInputStream(temp);
          output = new FileOutputStream(newPath + "/" + temp.getName().toString());
          byte[] b = new byte[1024 * 5];
          int len;
          while ((len = input.read(b)) != -1) {
            output.write(b, 0, len);
          }
          output.flush();
        }
        if (temp.isDirectory()) {// 如果是子文件夹
          copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
        }
      }
    } catch (Exception e) {
      System.out.println("复制整个文件夹内容操作出错");
      e.printStackTrace();
    } finally {
      if (output != null) {
        try {
          output.close();
        } catch (Exception e2) {
          e2.printStackTrace();
        }
      }
      if (input != null) {
        try {
          input.close();
        } catch (Exception e2) {
          e2.printStackTrace();
        }
      }
    }

  }

  // 删除文件夹
  // param folderPath 文件夹完整绝对路径

  public static void delFolder(String folderPath) {
    try {
      delAllFile(folderPath); // 删除完里面所有内容
      String filePath = folderPath;
      filePath = filePath.toString();
      java.io.File myFilePath = new java.io.File(filePath);
      myFilePath.delete(); // 删除空文件夹
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 删除指定文件夹下所有文件
  // param path 文件夹完整绝对路径
  public static boolean delAllFile(String path) {
    boolean flag = false;
    File file = new File(path);
    if (!file.exists()) {
      return flag;
    }
    if (!file.isDirectory()) {
      return flag;
    }
    String[] tempList = file.list();
    File temp = null;
    for (int i = 0; i < tempList.length; i++) {
      if (path.endsWith(File.separator)) {
        temp = new File(path + tempList[i]);
      } else {
        temp = new File(path + File.separator + tempList[i]);
      }
      if (temp.isFile()) {
        temp.delete();
      }
      if (temp.isDirectory()) {
        delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
        delFolder(path + "/" + tempList[i]);// 再删除空文件夹
        flag = true;
      }
    }
    return flag;
  }

  /**
   * 用于上传文件至服务器或OSS.
   * 
   * @param fName 文件保存名
   * @param realPath 本地存储绝对地址(完整路径，结尾有"/")
   * @param relativePath 网络存储相对地址(注意开头没有"/",结尾有"/")
   * @param upfile 上传的文件
   * @throws Exception
   * @throw
   * @return void
   */
  public static void uploadFile(String fName, String realPath, String relativePath, File upfile)
      throws Exception {
    // 写入OSS开关 1开0关
    String ossSign = PropertiesHelper.getValue("oss_sign");
    if (StringUtils.isNotBlank(ossSign) && upfile != null) {
      FileInputStream fin = null;
      try {
        fin = new FileInputStream(upfile);
        if ("1".equals(ossSign)) {
          // OSS API
          OssUtil.ordinaryUpload(ConstParam.OSS_BUKET, relativePath + fName, fin);
        } else {
          // 写入本地文件
          File dirfile = new File(realPath);
          if (!dirfile.exists() || null != dirfile) {
            dirfile.mkdirs();
          }
          FileUtil.copyFile(fin, realPath + fName);
        }
      } finally {
        if (fin != null) {
          try {
            fin.close();
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    }
  }

  /**
   * 删除文件
   * 
   * @param filePathAndName String 文件路径及名称 如c:/fqf.txt
   * @param fileContent String
   * @return boolean
   */
  public static void delFile(String filePathAndName) {
    try {
      ActionContext ac = ActionContext.getContext();
      ServletContext sc = (ServletContext) ac.get(StrutsStatics.SERVLET_CONTEXT);
      String webRootPath = sc.getRealPath("/");
      String filePath = webRootPath + filePathAndName;
      filePath = filePath.toString();
      java.io.File myDelFile = new java.io.File(filePath);
      myDelFile.delete();
    } catch (Exception e) {
      System.out.println("删除文件操作出错");
      e.printStackTrace();
    }
  }

  /**
   * 
   * 文件上传
   * 
   * @param file 上传的文件
   * @param size 限制上传的文件的大小（最大限制，例如：100；默认0，无限制；单位KB）
   * @param orgfileName 文件的原名称（例如：xxx.jpg）
   * @param storePath 要上传的路径（例如：/upfiles/product）
   * @param isCompress 是否添加压缩（true/false，true时width、height、proportion、retain有效)
   * @param width isCompress=true时自定义宽度（例如：300）
   * @param height isCompress=true时自定义高度（例如：300）
   * @param proportion isCompress=true时判断是否是等比缩放（true/false）
   * @param retain isCompress=true时判断是否保留原图（true/false）
   * @param allowTypes 允许上传的文件的类型（文件后缀名，例如：.jpg/.png/.xxx；默认null或者""，无限制）
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public static String uploadFile(File file, int size, String orgfileName, String storePath,
      boolean isCompress, int width, int height, boolean proportion, boolean retain,
      String allowTypes) throws Exception {

    long filesize = file.length();
    if (size != 0 && filesize / 1024 > size) {
      FileUtil.alertMessage("上传失败，上传的文件大小超过限制！~");
      return null;
    }

    String file_name = orgfileName;
    // HttpServletRequest request = ServletActionContext.getRequest();
    // file_name = (String) request.getAttribute("fileFileName");

    String fileLast = FileType.getSuffixByFilename(file_name);
    if (allowTypes != null && !"".equals(allowTypes)) {
      // String allowTypeArray[] = allowTypes.split("/");
      // int temp = 0;
      // if (allowTypeArray.length > 0) {
      // for (String s : allowTypeArray) {
      // if (!fileLast.equals(s)) {
      // temp++;
      // }
      // }
      // }
      // if (temp == allowTypeArray.length) {
      if (!allowTypes.contains(fileLast)) {
        FileUtil.alertMessage("上传失败，上传的文件格式不正确！~");
        return null;
      }
      // } else {
      // FileUtil.alertMessage("上传失败，未定义上传的文件的类型！~");
      // return null;
    }

    // if (allowTypes != null && !"".equals(allowTypes)) {
    // file_name = StringUtil.getCurrentDateStr() + allowTypes;

    // } else {
    file_name = StringUtil.getCurrentDateStr() + fileLast;
    // file_name =
    // StringUtil.getCurrentDateStr() + file_name.substring(file_name.lastIndexOf("."));
    // }

    if (isCompress == true) {
      if (retain == true) {
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath(storePath) + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, storePath + "/", file);
      }

      storePath += "/small/";

      CompressPicture cp = new CompressPicture();
      String compressPath =
          ServletActionContext.getServletContext().getRealPath(storePath) + File.separator;
      cp.compressPic(file, compressPath, storePath, file_name, width, height, proportion);
    } else {
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath(storePath) + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, storePath + "/", file);
    }

    return file_name;
  }


  /**
   * 
   * 文件上传
   * 
   * @param file 上传的文件
   * @param size 限制上传的文件的大小（最大限制，例如：100；默认0，无限制；单位KB）
   * @param orgfileName 文件的原名称（例如：xxx.jpg）
   * @param storePath 要上传的路径（例如：/upfiles/product）
   * @param isCompress 是否添加压缩（true/false，true时width、height、proportion、retain有效)
   * @param width isCompress=true时自定义宽度（例如：300）
   * @param height isCompress=true时自定义高度（例如：300）
   * @param proportion isCompress=true时判断是否是等比缩放（true/false）
   * @param retain isCompress=true时判断是否保留原图（true/false）
   * @param allowTypes 允许上传的文件的类型（文件后缀名，例如：.jpg/.png/.xxx；默认null或者""，无限制）
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public static String uploadFile1(File file, int size, String orgfileName, String storePath,
      boolean isCompress, int width, int height, boolean proportion, boolean retain,
      String allowTypes) throws Exception {

    long filesize = file.length();
    if (size != 0 && filesize / 1024 > size) {
      FileUtil.alertMessage("上传失败，上传的文件大小超过限制！~");
      return null;
    }

    String file_name = orgfileName;
    // HttpServletRequest request = ServletActionContext.getRequest();
    // file_name = (String) request.getAttribute("fileFileName");

    String fileLast = FileType.getSuffixByFilename(file_name);
    if (allowTypes != null && !"".equals(allowTypes)) {
      // String allowTypeArray[] = allowTypes.split("/");
      // int temp = 0;
      // if (allowTypeArray.length > 0) {
      // for (String s : allowTypeArray) {
      // if (!fileLast.equals(s)) {
      // temp++;
      // }
      // }
      // }
      // if (temp == allowTypeArray.length) {
      if (!allowTypes.contains(fileLast)) {
        FileUtil.alertMessage("上传失败，上传的文件格式不正确！~");
        return null;
      }
      // } else {
      // FileUtil.alertMessage("上传失败，未定义上传的文件的类型！~");
      // return null;
    }

    // if (allowTypes != null && !"".equals(allowTypes)) {
    // file_name = StringUtil.getCurrentDateStr() + allowTypes;

    // } else {
    InputStream input = null;
    byte[] byt = null;
    try {
      input = new FileInputStream(file);
      byt = new byte[input.available()];
      file_name = CodeUtil.hexSHA1(CodeUtil.byteToHexString(byt)) + fileLast;
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    // input = new FileInputStream(file);
    // byte[] byt = new byte[input.available()];
    // file_name = CodeUtil.hexSHA1(CodeUtil.byteToHexString(byt)) + fileLast;
    // file_name =
    // StringUtil.getCurrentDateStr() + file_name.substring(file_name.lastIndexOf("."));
    // }

    if (isCompress == true) {
      if (retain == true) {
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath(storePath) + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, storePath + "/", file);
      }

      storePath += "/small/";

      CompressPicture cp = new CompressPicture();
      String compressPath =
          ServletActionContext.getServletContext().getRealPath(storePath) + File.separator;
      cp.compressPic(file, compressPath, storePath, file_name, width, height, proportion);
    } else {
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath(storePath) + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, storePath + "/", file);
    }

    return file_name;
  }

  /**
   * 
   * 文件上传（无添加压缩）
   * 
   * @param file 上传的文件
   * @param size 限制上传的文件的大小（最大限制，例如：100；默认0，无限制；单位KB）
   * @param orgfileName 文件的原名称（例如：xxx.jpg）
   * @param storePath 要上传的路径（例如：/upfiles/product）
   * @param allowTypes 允许上传的文件的类型（文件后缀名，例如：.jpg/.png/.xxx；默认null或者""，无限制）
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public static String uploadFile(File file, int size, String orgfileName, String storePath,
      String allowTypes) throws Exception {

    return uploadFile(file, size, orgfileName, storePath, false, 300, 300, true, true, allowTypes);
  }

  /**
   * 
   * 文件上传（无限制上传文件的大小）
   * 
   * @param file 上传的文件
   * @param orgfileName 文件的原名称（例如：xxx.jpg）
   * @param storePath 要上传的路径（例如：/upfiles/product）
   * @param isCompress 是否添加压缩（true/false，true时width、height、proportion、retain有效)
   * @param width isCompress=true时自定义宽度（例如：300）
   * @param height isCompress=true时自定义高度（例如：300）
   * @param proportion isCompress=true时判断是否是等比缩放（true/false）
   * @param retain isCompress=true时判断是否保留原图（true/false）
   * @param allowTypes 允许上传的文件的类型（文件后缀名，例如：.jpg/.png/.xxx；默认null或者""，无限制）
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public static String uploadFile(File file, String orgfileName, String storePath,
      boolean isCompress, int width, int height, boolean proportion, boolean retain,
      String allowTypes) throws Exception {

    return uploadFile(file, 0, orgfileName, storePath, isCompress, width, height, proportion,
        retain, allowTypes);
  }

  /**
   * 
   * 文件上传（无限制上传文件的大小和无添加压缩）
   * 
   * @param file 上传的文件
   * @param orgfileName 文件的原名称（例如：xxx.jpg）
   * @param storePath 要上传的路径（例如：/upfiles/product）
   * @param allowTypes 允许上传的文件的类型（文件后缀名，例如：.jpg/.png/.xxx；默认null或者""，无限制）
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public static String uploadFile(File file, String orgfileName, String storePath, String allowTypes)
      throws Exception {

    return uploadFile(file, 0, orgfileName, storePath, false, 300, 300, true, true, allowTypes);
  }

  /**
   * 
   * 文件上传（有添加压缩，300*300；等比缩放）
   * 
   * @param file 上传的文件
   * @param orgfileName 文件的原名称（例如：xxx.jpg）
   * @param storePath 要上传的路径（例如：/upfiles/product）
   * @param allowTypes 允许上传的文件的类型（文件后缀名，例如：.jpg/.png/.xxx；默认null或者""，无限制）
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public static String uploadFileDefualt(File file, String orgfileName, String storePath,
      String allowTypes) throws Exception {

    return uploadFile(file, 0, orgfileName, storePath, true, 300, 300, true, true, allowTypes);
  }

  /*
   * public static void main(String[] args) { File dir = new
   * File("C:/Users/Administrator/Desktop/img"); if (dir != null && dir.exists()) { if
   * (dir.isDirectory()) { String path = dir.getPath(); System.out.println(path); String[] childDirs
   * = dir.list(); File temp = null; String[] files = null; String filepath = null; String outpath =
   * "C:/Users/Administrator/Desktop/cmps"; String newpath = ""; File img = null; File outfile =
   * null; CompressPicture cp = new CompressPicture(); for (String dirnm : childDirs) { temp = new
   * File(path + File.separator + dirnm); if (temp != null && temp.exists() && temp.isDirectory()) {
   * filepath = temp.getPath(); files = temp.list(); if (files.length > 0) { newpath = outpath +
   * File.separator + dirnm; outfile = new File(newpath); if (!outfile.exists()) { outfile.mkdir();
   * } } for (String file : files) { img = new File(filepath + File.separator + file); if
   * (file.endsWith(".gif")) { try { FileUtil.uploadFile(file, newpath + File.separator, "", img); }
   * catch (Exception e) { e.printStackTrace(); } } else { System.out.println(file);
   * cp.compressPic(img, newpath + File.separator, "", file, 0, 0, false); } } } } } } }
   */

}
