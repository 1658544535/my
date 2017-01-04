package maowu.framework.utils.base64;

public class Base64Util {

  /**
   * 
   * base64解码
   * 
   * @param s 需解码字符串
   * @param encoding 解码字符集
   * @return String 解码后的数据
   * 
   */
  public static String getStrFromBASE64(String s, String encoding) {
    if (s == null) {
      return null;
    }
    sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
    try {
      byte[] b = decoder.decodeBuffer(s);
      return new String(b, encoding);
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * base64解码
   * 
   * @param s 需解码字符串
   * @return String 解码后的数据
   */
  public static String getStrFromBASE64(String s) {
    if (s == null) {
      return null;
    }
    sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
    try {
      byte[] b = decoder.decodeBuffer(s);
      return new String(b);
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * base64解码
   * 
   * @param s 需解码字符串
   * @return byte[] 解码后的数据
   */
  public static byte[] getByteFromBASE64(String s) {
    if (s == null) {
      return null;
    }
    sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
    try {
      return decoder.decodeBuffer(s);
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * 用base64编码
   * 
   * @param byte[]
   * @return String
   */
  public static String getBASE64(byte[] b) {
    if (b == null) {
      return null;
    }
    sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
    try {
      return encoder.encode(b);
    } catch (Exception e) {
      return null;
    }

  }

  /**
   * 用base64编码
   * 
   * @param String
   * @return String
   */
  public static String getBASE64(String str) {
    if (str == null) {
      return null;
    }
    sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
    try {
      return encoder.encode(str.getBytes());
    } catch (Exception e) {
      return null;
    }
  }

}
