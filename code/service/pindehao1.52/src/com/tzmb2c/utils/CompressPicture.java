/**
 * 缩略图实现，将图片(jpg、bmp、png、gif等等)真实的变成想要的大小
 */
package com.tzmb2c.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import com.tzmb2c.business.service.ConstParam;

/*******************************************************************************
 * 缩略图类（通用） 本java类能将jpg、bmp、png、gif图片文件，进行等比或非等比的大小转换。 具体使用方法
 * compressPic(大图片路径,生成小图片路径,大图片文件名,生成小图片文名,生成小图片宽度,生成小图片高度,是否等比缩放(默认为true))
 */
public class CompressPicture {
  private File file = null; // 文件对象
  private String inputDir; // 输入图路径
  private String inputNetDir; // 输入图路径（网盘）
  private String outputDir; // 输出图路径
  private String outputNetDir; // 输出图路径（网盘）
  private String inputFileName; // 输入图文件名
  private String outputFileName; // 输出图文件名
  private int outputWidth = 100; // 默认输出图片宽
  private int outputHeight = 100; // 默认输出图片高
  private boolean proportion = true; // 是否等比缩放标记(默认为等比缩放)

  public CompressPicture() { // 初始化变量
    inputDir = "";
    inputNetDir = "";
    outputDir = "";
    outputNetDir = "";
    inputFileName = "";
    outputFileName = "";
    outputWidth = 100;
    outputHeight = 100;
  }

  public void setInputDir(String inputDir) {
    this.inputDir = inputDir;
  }

  public void setOutputDir(String outputDir) {
    this.outputDir = outputDir;
  }

  public void setInputNetDir(String inputNetDir) {
    this.inputNetDir = inputNetDir;
  }

  public void setOutputNetDir(String outputNetDir) {
    this.outputNetDir = outputNetDir;
  }

  public void setInputFileName(String inputFileName) {
    this.inputFileName = inputFileName;
  }

  public void setOutputFileName(String outputFileName) {
    this.outputFileName = outputFileName;
  }

  public void setOutputWidth(int outputWidth) {
    this.outputWidth = outputWidth;
  }

  public void setOutputHeight(int outputHeight) {
    this.outputHeight = outputHeight;
  }

  public void setWidthAndHeight(int width, int height) {
    this.outputWidth = width;
    this.outputHeight = height;
  }

  /*
   * 获得图片大小 传入参数 String path ：图片路径
   */
  public long getPicSize(String path) {
    file = new File(path);
    return file.length();
  }

  // 图片处理
  public String compressPic() {
    FileOutputStream fout = null;
    ByteOutputStream bout = null;
    Image img = null;
    // 上传OSS开关 1开0关
    String ossSign = PropertiesHelper.getValue("oss_sign");
    try {
      if (StringUtils.isNotBlank(ossSign) && "1".equals(ossSign)
          && StringUtils.isNotBlank(inputNetDir) && StringUtils.isNotBlank(outputNetDir)) {
        String urlStr = PropertiesHelper.getValue("seller_dns") + "/" + inputNetDir + inputFileName;
        URL url = new URL(urlStr);
        img = ImageIO.read(url);
      } else {
        // 获得源文件
        file = new File(inputDir + inputFileName);
        if (!file.exists() || null != file) {
          file.mkdirs();
        }
        img = ImageIO.read(file);
      }

      // 判断图片格式是否正确
      if (img.getWidth(null) == -1) {
        System.out.println(" can't read,retry!" + "<BR>");
        return "no";
      } else {
        int newWidth;
        int newHeight;
        // 判断是否是等比缩放
        if (this.proportion == true) {
          // 为等比缩放计算输出的图片宽度及高度
          double rate1 = (double) img.getWidth(null) / (double) outputWidth + 0.1;
          double rate2 = (double) img.getHeight(null) / (double) outputHeight + 0.1;
          // 根据缩放比率大的进行缩放控制
          double rate = rate1 > rate2 ? rate1 : rate2;
          newWidth = (int) (img.getWidth(null) / rate);
          newHeight = (int) (img.getHeight(null) / rate);
        } else {
          newWidth = outputWidth; // 输出的图片宽度
          newHeight = outputHeight; // 输出的图片高度
        }
        BufferedImage tag = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

        /*
         * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
         */
        tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH),
            0, 0, null);

        if (StringUtils.isNotBlank(ossSign) && "1".equals(ossSign)
            && StringUtils.isNotBlank(inputNetDir) && StringUtils.isNotBlank(outputNetDir)) {
          bout = new ByteOutputStream();
          // JPEGImageEncoder可适用于其他图片类型的转换
          JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bout);
          encoder.encode(tag);
          ByteInputStream in = new ByteInputStream(bout.getBytes(), bout.size());
          // bout.close();
          OssUtil.ordinaryUpload(ConstParam.OSS_BUKET, outputNetDir + outputFileName, in);
        } else {
          fout = new FileOutputStream(outputDir + outputFileName);
          // JPEGImageEncoder可适用于其他图片类型的转换
          JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fout);
          encoder.encode(tag);
          // fout.close();
        }
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      if (fout != null) {
        try {
          fout.close();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
      if (bout != null) {
        bout.close();
      }
    }
    return "ok";
  }

  public String compressPicture() {
    FileOutputStream fout = null;
    ByteOutputStream bout = null;
    Image img = null;
    // 上传OSS开关 1开0关
    String ossSign = PropertiesHelper.getValue("oss_sign");
    try {
      img = ImageIO.read(file);

      // 判断图片格式是否正确
      if (img.getWidth(null) == -1) {
        System.out.println(" can't read,retry!" + "<BR>");
        return "no";
      } else {
        int newWidth = 0;
        int newHeight = 0;
        // 判断是否是等比缩放
        if (this.proportion == true) {
          // 为等比缩放计算输出的图片宽度及高度
          double rate1 = (double) img.getWidth(null) / (double) outputWidth + 0.1;
          double rate2 = (double) img.getHeight(null) / (double) outputHeight + 0.1;
          // 根据缩放比率大的进行缩放控制
          double rate = rate1 > rate2 ? rate1 : rate2;
          newWidth = (int) (img.getWidth(null) / rate);
          newHeight = (int) (img.getHeight(null) / rate);
        } else {
          if (outputWidth != 0 && outputHeight != 0) {
            newWidth = outputWidth; // 输出的图片宽度
            newHeight = outputHeight; // 输出的图片高度
          } else {
            newWidth = img.getWidth(null);// 输出的图片宽度
            newHeight = img.getHeight(null); // 输出的图片高度
          }
        }
        BufferedImage tag = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

        /*
         * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
         */
        tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH),
            0, 0, null);

        if (StringUtils.isNotBlank(ossSign) && "1".equals(ossSign)
            && StringUtils.isNotBlank(outputNetDir)) {
          bout = new ByteOutputStream();
          // JPEGImageEncoder可适用于其他图片类型的转换
          JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bout);
          encoder.encode(tag);
          ByteInputStream in = new ByteInputStream(bout.getBytes(), bout.size());
          // bout.close();
          OssUtil.ordinaryUpload(ConstParam.OSS_BUKET, outputNetDir + outputFileName, in);
        } else {
          fout = new FileOutputStream(outputDir + outputFileName);
          // JPEGImageEncoder可适用于其他图片类型的转换
          JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fout);
          encoder.encode(tag);
          // fout.close();
        }
      }
    } catch (IOException ex) {
      ex.printStackTrace();
    } finally {
      if (fout != null) {
        try {
          fout.close();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
      if (bout != null) {
        bout.close();
      }
    }
    return "ok";
  }

  public String compressPic(String inputDir, String outputDir, String inputFileName,
      String outputFileName) {
    // 输入图路径
    this.inputDir = inputDir;
    // 输出图路径
    this.outputDir = outputDir;
    // 输入图文件名
    this.inputFileName = inputFileName;
    // 输出图文件名
    this.outputFileName = outputFileName;
    return compressPic();
  }

  public String compressPic(String inputDir, String outputDir, String inputFileName,
      String outputFileName, int width, int height, boolean gp) {
    // 输入图路径
    this.inputDir = inputDir;
    // 输出图路径
    this.outputDir = outputDir;
    // 输入图文件名
    this.inputFileName = inputFileName;
    // 输出图文件名
    this.outputFileName = outputFileName;
    // 设置图片长宽
    setWidthAndHeight(width, height);
    // 是否是等比缩放 标记
    this.proportion = gp;
    return compressPic();
  }

  public String compressPic(String inputDir, String outputDir, String inputNetDir,
      String outputNetDir, String inputFileName, String outputFileName, int width, int height,
      boolean gp) {
    // 输入图路径
    this.inputDir = inputDir;
    // 输入图网络路径
    this.inputNetDir = inputNetDir;
    // 输出图路径
    this.outputDir = outputDir;
    // 输出图网络路径
    this.outputNetDir = outputNetDir;
    // 输入图文件名
    this.inputFileName = inputFileName;
    // 输出图文件名
    this.outputFileName = outputFileName;
    // 设置图片长宽
    setWidthAndHeight(width, height);
    // 是否是等比缩放 标记
    this.proportion = gp;
    return compressPic();
  }

  public String compressPic(File file, String outputDir, String outputNetDir,
      String outputFileName, int width, int height, boolean gp) {
    // 输入流
    this.file = file;
    // 输出图路径
    this.outputDir = outputDir;
    // 输出图网络路径
    this.outputNetDir = outputNetDir;
    // 输出图文件名
    this.outputFileName = outputFileName;
    // 设置图片长宽
    setWidthAndHeight(width, height);
    // 是否是等比缩放 标记
    this.proportion = gp;
    return compressPicture();
  }

}
