package com.tzmb2c.utils;


import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 根据文本生成图片的工具
 */
public class ImageUtils {
  private final static IndexColorModel icm = createIndexColorModel();

  static ThreadLocal<DefaultHttpClient> threadhttpclient = new ThreadLocal<DefaultHttpClient>();

  public ImageUtils() {
    SchemeRegistry schemeRegistry = new SchemeRegistry();
    schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
    schemeRegistry.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));
    PoolingClientConnectionManager connectionManager =
        new PoolingClientConnectionManager(schemeRegistry);

    connectionManager.setMaxTotal(100);

    DefaultHttpClient httpclient = new DefaultHttpClient(connectionManager);

    httpclient.getParams().setParameter("http.socket.timeout", 20000);

    httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
    httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);// 数据传输时间60s
    httpclient.getParams().setParameter("http.connection-manager.timeout", 100000000L);

    threadhttpclient.set(httpclient);


  }



  static String ip = "";
  static int port = 0;
  static String http = "";
  static TreeMap<Integer, String> ss = new TreeMap<Integer, String>();
  static String useragert = "";

  /**
   * 生成电子邮件图片
   * 
   * @param email
   * @param out
   * @throws IOException
   */
  public static void MakeEmailImage(String email, OutputStream out) throws IOException {
    int height = 22;
    BufferedImage bi = new BufferedImage(255, height, BufferedImage.TYPE_INT_RGB);
    Graphics2D g = (Graphics2D) bi.getGraphics();
    Font mFont = new Font("Verdana", Font.PLAIN, 14);
    g.setFont(mFont);
    g.drawString(email, 2, 19);
    FontMetrics fm = g.getFontMetrics();
    int new_width = fm.charsWidth(email.toCharArray(), 0, email.length()) + 4;
    int new_height = fm.getHeight();
    BufferedImage nbi =
        new BufferedImage(new_width, new_height, BufferedImage.TYPE_BYTE_INDEXED, icm);
    Graphics2D g2 = (Graphics2D) nbi.getGraphics();
    g2.setColor(new Color(0, 0, 0, 0));// 透明
    g2.fillRect(0, 0, new_width, new_height);
    g2.setFont(mFont);
    g2.setColor(new Color(200, 0, 0));
    g2.drawString(email, 2, new_height - 4);

    ImageIO.write(nbi, "gif", out);
  }

  /**
   * 生成电话号码图片
   * 
   * @param phone
   * @param out
   * @throws IOException
   */
  public static void MakePhoneImage(String phone, OutputStream out) throws IOException {
    int height = 22;
    BufferedImage bi = new BufferedImage(255, height, BufferedImage.TYPE_INT_RGB);
    Graphics2D g = (Graphics2D) bi.getGraphics();
    Font mFont = new Font("Verdana", Font.BOLD, 20);
    g.setFont(mFont);
    g.drawString(phone, 2, 19);
    FontMetrics fm = g.getFontMetrics();
    int new_width = fm.charsWidth(phone.toCharArray(), 0, phone.length()) + 4;
    int new_height = fm.getHeight();
    BufferedImage nbi =
        new BufferedImage(new_width, new_height, BufferedImage.TYPE_BYTE_INDEXED, icm);
    Graphics2D g2 = (Graphics2D) nbi.getGraphics();
    g2.setColor(new Color(0, 0, 0, 0));// 透明
    g2.fillRect(0, 0, new_width, new_height);
    g2.setFont(mFont);
    g2.setColor(new Color(200, 0, 0));
    g2.drawString(phone, 2, new_height - 4);
    ImageIO.write(nbi, "gif", out);
  }

  /**
   * 生成产品关键特征
   * 
   * @param attribute
   * @param out
   * @throws IOException
   */
  public static void MakeProductAttribute(String attribute, OutputStream out) throws IOException {
    int height = 22;
    BufferedImage bi = new BufferedImage(255, height, BufferedImage.TYPE_INT_RGB);
    Graphics2D g = (Graphics2D) bi.getGraphics();
    Font mFont = new Font("CONSOLAS", Font.BOLD, 13);
    g.setFont(mFont);
    g.drawString(new String(attribute), 2, 19);
    FontMetrics fm = g.getFontMetrics();
    int new_width = fm.charsWidth(attribute.toCharArray(), 0, attribute.length()) + 4;
    int new_height = fm.getHeight();
    BufferedImage nbi =
        new BufferedImage(new_width, new_height, BufferedImage.TYPE_BYTE_INDEXED, icm);
    Graphics2D g2 = (Graphics2D) nbi.getGraphics();
    g2.setColor(new Color(0, 0, 0, 0));// 透明
    g2.fillRect(0, 0, new_width, new_height);
    g2.setFont(mFont);
    g2.setColor(new Color(200, 0, 0));
    g2.drawString(attribute, 2, new_height - 4);
    ImageIO.write(nbi, "gif", out);
  }

  /** 图片格式：JPG */
  private static final String PICTRUE_FORMATE_JPG = "jpg";

  /**
   * 添加图片水印
   * 
   * @param targetImg 目标图片路径，如：C://myPictrue//1.jpg
   * @param waterImg 水印图片路径，如：C://myPictrue//logo.png
   * @param x 水印图片距离目标图片左侧的偏移量，如果x<0, 则在正中间
   * @param y 水印图片距离目标图片上侧的偏移量，如果y<0, 则在正中间
   * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
   */
  public final static void pressImage(String targetImg, String waterImg, int x, int y, float alpha,
      boolean jugw) {
    try {
      File file = new File(targetImg);
      Image image = ImageIO.read(file);
      int width = image.getWidth(null);
      int height = image.getHeight(null);
      BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      Graphics2D g = bufferedImage.createGraphics();
      g.drawImage(image, 0, 0, width, height, null);

      if (width < 200) {
        waterImg = waterImg.replace("c.png", "d.png");

      } else if (width > 600 || width > 460 && height > 460) {
        waterImg = waterImg.replace("c.png", "b.png");
      }

      System.out.println(waterImg);
      Image waterImage = ImageIO.read(new File(waterImg)); // 水印文件
      int width_1 = waterImage.getWidth(null);
      int height_1 = waterImage.getHeight(null);
      g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

      int widthDiff = width - width_1;
      int heightDiff = height - height_1;
      //
      if (jugw) {
        if (width > 590) {
          x = width * 445 / 640;
        } else if (width > 460 && height > 460) {
          x = width * 400 / 640;
        } else {
          x = width * 420 / 640;
        }
        if (height > 600) {
          y = height * 360 / 480;
        } else {
          y = height * 345 / 480;
        }
      }
      System.out.println(x + "==" + y + "==" + widthDiff + "==" + heightDiff);
      if (x < 0) {
        x = widthDiff / 2;
      } else if (x > widthDiff) {
        x = widthDiff;
      }
      if (y < 0) {
        y = heightDiff / 2;
      } else if (y > heightDiff) {
        y = heightDiff;
      }
      g.drawImage(waterImage, x, y, width_1, height_1, null); // 水印文件结束
      g.dispose();
      ImageIO.write(bufferedImage, PICTRUE_FORMATE_JPG, file);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 添加图片水印
   * 
   * @param targetImg 目标图片路径，如：C://myPictrue//1.jpg
   * @param waterImg 水印图片路径，如：C://myPictrue//logo.png
   * @param x 水印图片距离目标图片左侧的偏移量，如果x<0, 则在正中间
   * @param y 水印图片距离目标图片上侧的偏移量，如果y<0, 则在正中间
   * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
   */
  public final static void pressImageganji(String targetImg, String waterImg, int x, int y,
      float alpha, boolean jugw) {
    try {
      File file = new File(targetImg);
      Image image = ImageIO.read(file);
      int width = image.getWidth(null);
      int height = image.getHeight(null);
      BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      Graphics2D g = bufferedImage.createGraphics();
      g.drawImage(image, 0, 0, width, height, null);

      if (width < 200) {
        waterImg = waterImg.replace("c.png", "d.png");

      } else if (width > 600 || width > 460 && height > 460) {
        waterImg = waterImg.replace("c.png", "b.png");
      }

      System.out.println(waterImg);
      Image waterImage = ImageIO.read(new File(waterImg)); // 水印文件
      int width_1 = waterImage.getWidth(null);
      int height_1 = waterImage.getHeight(null);
      g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

      int widthDiff = width - width_1;
      int heightDiff = height - height_1;
      System.out.println(width + "==" + x + "=1=" + y + "=1=" + widthDiff + "==" + heightDiff);
      //
      if (jugw) {
        if (width > 600) {
          x = width * 510 / 640;
        } else if (width > 580 && height > 450) {
          x = width * 458 / 480;
        } else if (width > 460 && height > 450) {
          x = width * 438 / 640;
        } else if (width > 460 && height > 430) {
          x = width * 470 / 640;
        } else if (width > 580 && height > 370) {
          x = width * 466 / 640;
        } else if (width > 550 && height > 300) {
          x = width * 480 / 640;
        } else if (width > 530 && height > 280) {
          x = width * 505 / 640;
        } else if (width > 400 && height > 300) {
          x = width * 427 / 640;
        } else if (width > 330 && height > 440) {
          x = width * 370 / 640;
        } else if (width > 330 && height > 430) {
          x = width * 360 / 640;
        } else if (width > 330 && height > 200) {
          x = width * 380 / 640;
        } else {
          x = width * 410 / 640;
        }

        if (height > 600) {
          y = height * 440 / 480;
        } else if (width > 460 && height > 430) {
          y = height * 410 / 480;
        } else if (width > 550 && height > 370) {
          y = height * 400 / 480;
        } else if (width > 400 && height > 300) {
          y = height * 385 / 480;
        } else if (width > 300 && height > 440) {
          y = height * 450 / 480;
        } else if (width > 300 && height > 400) {
          y = height * 400 / 480;
        } else if (width > 500 && height > 200) {
          y = height * 370 / 480;
        } else if (width > 300 && height > 200) {
          y = height * 350 / 480;
        } else {
          y = height * 425 / 480;
        }
      }
      System.out.println(x + "==" + y + "==" + widthDiff + "==" + heightDiff);
      if (x < 0) {
        x = widthDiff / 2;
      } else if (x > widthDiff) {
        x = widthDiff;
      }
      if (y < 0) {
        y = heightDiff / 2;
      } else if (y > heightDiff) {
        y = heightDiff;
      }
      if (width_1 > 670) {
        width_1 = 670;
      }
      g.drawImage(waterImage, x, y, width_1, height_1, null); // 水印文件结束
      g.dispose();
      ImageIO.write(bufferedImage, PICTRUE_FORMATE_JPG, file);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * 添加文字水印
   * 
   * @param targetImg 目标图片路径，如：C://myPictrue//1.jpg
   * @param pressText 水印文字， 如：中国证券网
   * @param fontName 字体名称， 如：宋体
   * @param fontStyle 字体样式，如：粗体和斜体(Font.BOLD|Font.ITALIC)
   * @param fontSize 字体大小，单位为像素
   * @param color 字体颜色
   * @param x 水印文字距离目标图片左侧的偏移量，如果x<0, 则在正中间
   * @param y 水印文字距离目标图片上侧的偏移量，如果y<0, 则在正中间
   * @param alpha 透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
   */
  public static void pressText(String targetImg, String pressText, String fontName, int fontStyle,
      int fontSize, Color color, int x, int y, float alpha) {
    try {
      File file = new File(targetImg);

      Image image = ImageIO.read(file);
      int width = image.getWidth(null);
      int height = image.getHeight(null);
      BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      Graphics2D g = bufferedImage.createGraphics();
      g.drawImage(image, 0, 0, width, height, null);
      g.setFont(new Font(fontName, fontStyle, fontSize));
      g.setColor(color);
      g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));

      int width_1 = fontSize * getLength(pressText);
      int height_1 = fontSize;
      int widthDiff = width - width_1;
      int heightDiff = height - height_1;
      if (x < 0) {
        x = widthDiff / 2;
      } else if (x > widthDiff) {
        x = widthDiff;
      }
      if (y < 0) {
        y = heightDiff / 2;
      } else if (y > heightDiff) {
        y = heightDiff;
      }
      if (x > 670) {
        x = 670;
      }
      g.drawString(pressText, x, y + height_1);
      g.dispose();
      ImageIO.write(bufferedImage, PICTRUE_FORMATE_JPG, file);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 获取字符长度，一个汉字作为 1 个字符, 一个英文字母作为 0.5 个字符
   * 
   * @param text
   * @return 字符长度，如：text="中国",返回 2；text="test",返回 2；text="中国ABC",返回 4.
   */
  public static int getLength(String text) {
    int textLength = text.length();
    int length = textLength;
    for (int i = 0; i < textLength; i++) {
      if (String.valueOf(text.charAt(i)).getBytes().length > 1) {
        length++;
      }
    }
    return length % 2 == 0 ? length / 2 : length / 2 + 1;
  }

  /**
   * 图片缩放
   * 
   * @param filePath 图片路径
   * @param height 高度
   * @param width 宽度
   * @param bb 比例不对时是否需要补白
   */
  public static void resize(String filePath, int height, int width, boolean bb) {
    try {
      double ratio = 0; // 缩放比例
      File f = new File(filePath);
      BufferedImage bi = ImageIO.read(f);
      Image itemp = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
      if (bi.getWidth() < width || bi.getHeight() < height) {
        height = bi.getHeight();
        width = bi.getWidth();
      }
      // 计算比例
      if (bi.getHeight() > height || bi.getWidth() > width) {
        if (bi.getHeight() > bi.getWidth()) {
          ratio = new Integer(height).doubleValue() / bi.getHeight();
        } else {
          ratio = new Integer(width).doubleValue() / bi.getWidth();
        }
        AffineTransformOp op =
            new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
        itemp = op.filter(bi, null);
      }
      if (bb) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);
        if (width == itemp.getWidth(null)) {
          g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2, itemp.getWidth(null),
              itemp.getHeight(null), Color.white, null);
        } else {
          g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null),
              itemp.getHeight(null), Color.white, null);
        }
        g.dispose();
        itemp = image;
      }
      ImageIO.write((BufferedImage) itemp, "jpg", f);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // public static void main(String[] args) throws IOException {
  // // String num = "020-85551111";
  // FileOutputStream fos = new FileOutputStream("D:/phone.gif");
  // // try{
  // // MakeProductAttribute(num, fos);
  // // }finally{
  // // fos.close();
  // // }
  // // String email = "xxxxx@oschina.net";
  // // FileOutputStream fos2 = new FileOutputStream("D:/email.gif");
  // // try{
  // // MakeEmailImage(email, fos2);
  // // }finally{
  // // fos2.close();
  // // }
  // BufferedImage sourceImg =ImageIO.read(new FileInputStream("C://a.jpg"));
  // pressImageganji("C://a.jpg", "C://c.png", sourceImg.getWidth(), sourceImg.getHeight(),
  // 1.0f,true);
  // // pressText("C://pic//jpg", "旺仔之印", "宋体", Font.BOLD|Font.ITALIC, 20, Color.BLACK, 0, 0, 8f);
  // // resize("C://ti.jpg", 400,600, true);
  // // MakePhoneImage("0754-19508928",fos);
  // }


  static IndexColorModel createIndexColorModel() {
    BufferedImage ex = new BufferedImage(1, 1, BufferedImage.TYPE_BYTE_INDEXED);
    IndexColorModel icm = (IndexColorModel) ex.getColorModel();
    int SIZE = 256;
    byte[] r = new byte[SIZE];
    byte[] g = new byte[SIZE];
    byte[] b = new byte[SIZE];
    byte[] a = new byte[SIZE];
    icm.getReds(r);
    icm.getGreens(g);
    icm.getBlues(b);
    java.util.Arrays.fill(a, (byte) 255);
    r[0] = g[0] = b[0] = a[0] = 0; // transparent
    return new IndexColorModel(8, SIZE, r, g, b, a);
  }


  private static String upExt = ",jpg,jpeg,gif,png,"; // 上传扩展名

  public static String getimageurl(String url, String localpath, String pathimg) {
    if (url == null || "".equals(url)) {
      return "";
    }
    if (localpath == null || "".equals(localpath)) {
      return "";
    }
    // data:image/png;base64,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-----------------------------

    File dirr = new File(localpath);
    if (!dirr.exists()) {
      dirr.mkdirs();
    }
    if (threadhttpclient.get() == null) {
      new ImageUtils();
    }

    String sExt;

    if (url.startsWith("data:image")) {
      int pstart = url.indexOf('/') + 1;
      sExt = url.substring(pstart, url.indexOf(';')).toLowerCase();
      if (upExt.indexOf("," + sExt + ",") == -1) {
        return "";
      }
      String path = StringUtil.getCurrentDateStr() + ".jpg";
      GenerateImage(url.substring(url.indexOf("base64,") + 7), localpath + "/" + path);
      return pathimg + "/" + path;
    } else {
      HttpGet httpGet = new HttpGet(url);
      httpGet.addHeader("Referer", url);
      httpGet
          .addHeader(
              "Accept",
              "application/x-ms-application, image/jpeg, application/xaml+xml, image/gif,image/png, image/pjpeg, application/x-ms-xbap, */*");

      String endgs = url.substring(url.lastIndexOf("."), url.length());
      String path = StringUtil.getCurrentDateStr() + endgs;
      HttpResponse response;
      int i = 0;
      int x = 0;

      strjug: while (i == 0) {

        try {
          response = threadhttpclient.get().execute(httpGet);

          if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
            HttpEntity entity = response.getEntity();

            if (entity != null) {
              // System.out.println(entity.getContentType());
              // 可以判断是否是文件数据流
              // System.out.println(entity.isStreaming());


              // 设置本地保存的文件

              // System.out.println(localpath+"\\"+path+"========================================");
              File storeFile = new File(localpath + "\\" + path);
              // File storeFile = new File(savePath+"/"+imgName);
              FileOutputStream output = new FileOutputStream(storeFile);
              // 得到网络资源并写入文件
              InputStream input = entity.getContent();

              byte b[] = new byte[1024];
              int j = 0;
              while ((j = input.read(b)) != -1) {
                output.write(b, 0, j);
              }
              output.flush();
              output.close();

              i = 1;

            }
            if (entity != null) {

              EntityUtils.consume(entity);
            }
          }
          return pathimg + "/" + path;
        } catch (IOException e) {
          x += 1;
          if (x == 5) {
            x = 0;
            i = 1;
          }
          continue strjug;
        }
      }

    }

    return "";
  }

  // 将图片转换成Base64
  public static String GetImageStr(String imgFilePath) {
    // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
    byte[] data = null;
    // 读取图片字节数组
    try {
      InputStream in = new FileInputStream(imgFilePath);
      data = new byte[in.available()];
      in.read(data);
      in.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    // 对字节数组Base64编码
    BASE64Encoder encoder = new BASE64Encoder();
    return encoder.encode(data);// 返回Base64编码过的字节数组字符串
  }


  // 将Base64转换成图片
  public static boolean GenerateImage(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片
    if (imgStr == null) {
      // 图像数据为空
      return false;
    }
    BASE64Decoder decoder = new BASE64Decoder();
    try {
      // Base64解码
      byte[] bytes = decoder.decodeBuffer(imgStr);
      for (int i = 0; i < bytes.length; ++i) {
        if (bytes[i] < 0) {// 调整异常数据
          bytes[i] += 256;
        }
      }
      // 生成jpeg图片
      OutputStream out = new FileOutputStream(imgFilePath);
      out.write(bytes);
      out.flush();
      out.close();
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public static void main(String[] args) {
    String dd =
        "data:image/png;base64,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";

    System.out.println(GenerateImage(dd.substring(dd.indexOf("base64,") + 7), "c:/vv.jpg"));
  }

  public static int getImgWidth(File file) {
    InputStream is = null;
    BufferedImage src = null;
    int ret = -1;
    try {
      is = new FileInputStream(file);
      src = javax.imageio.ImageIO.read(is);
      ret = src.getWidth(null); // 得到源图宽
      is.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ret;
  }

  /**
   * 获取图片高度
   * 
   * @param file 图片文件
   * @return 高度
   */
  public static int getImgHeight(File file) {
    InputStream is = null;
    BufferedImage src = null;
    int ret = -1;
    try {
      is = new FileInputStream(file);
      src = javax.imageio.ImageIO.read(is);
      ret = src.getHeight(null); // 得到源图高
      is.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ret;
  }

  /**
   * 
   * @param imgUrl 图片地址
   * @return
   */
  public static BufferedImage getBufferedImage(String imgUrl) {
    URL url = null;
    InputStream is = null;
    BufferedImage img = null;
    try {
      url = new URL(imgUrl);
      is = url.openStream();
      img = ImageIO.read(is);
    } catch (MalformedURLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {

      try {
        is.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return img;
  }

  /**
   * 图片水印 (Add by EricChen)
   * 
   * @param pressimg 水印图片
   * @param targetimg 目标图片
   * @param location 位置：1，左上角 2，右上角 3，左下角 4，右下角 5，正中间
   * @param alpha 透明度
   */
  public static void pressImages(String pressimg, String targetimg, int location, float alpha) {
    try {
      // 读取目标文件
      File img = new File(targetimg);
      Image src = ImageIO.read(img);
      int width = src.getWidth(null);
      int height = src.getHeight(null);
      BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      Graphics2D g = image.createGraphics();
      g.drawImage(src, 0, 0, width, height, null);
      // 水印文件
      Image src_biao = ImageIO.read(new File(pressimg));
      int width_biao = src_biao.getWidth(null);
      int height_biao = src_biao.getHeight(null);

      // 如果水印图片高或者宽大于目标图片是做的处理，使其水印宽或高等于目标图片的宽高，并且等比例缩放
      int new_width_biao = width_biao;
      int new_height_biao = height_biao;
      if (width_biao > width) {
        new_width_biao = width;
        new_height_biao = (int) ((double) new_width_biao / width_biao * height);
      }
      if (new_height_biao > height) {
        new_height_biao = height;
        new_width_biao = (int) ((double) new_height_biao / height_biao * new_width_biao);
      }

      // 根据位置参数确定坐标位置
      int x = 0;
      int y = 0;
      switch (location) {
        case 1:
          break;
        case 2:
          x = width - new_width_biao;
          break;
        case 3:
          y = height - new_height_biao;
          break;
        case 4:
          x = width - new_width_biao;
          y = height - new_height_biao;
          break;
        case 5:
          x = (width - new_width_biao) / 2;
          y = (height - new_height_biao) / 2;
          break;
        default:
          break;
      }
      g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
      g.drawImage(src_biao, x, y, new_width_biao, new_height_biao, null);
      g.dispose();
      ImageIO.write(image, PICTRUE_FORMATE_JPG, img);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
