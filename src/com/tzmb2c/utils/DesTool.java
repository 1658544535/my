package com.tzmb2c.utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


/***
 * 报文主体消息加密解密
 * 
 * @author semyrain
 * 
 */
public class DesTool {

  // 只支持8为长度的密钥
  private String DECODE_AND_ECODE_KEY = "1Q2W3E4R";

  private final static String DES = "DES";

  private DesTool() {

  }

  /***
   * 获取实例
   * 
   * @param 密钥
   * @return
   */
  public static DesTool getInstance(String DECODE_AND_ECODE_KEY) {
    DesTool instance = new DesTool();
    instance.DECODE_AND_ECODE_KEY = DECODE_AND_ECODE_KEY;
    return instance;
  }

  /**
   * 
   * 加密
   * 
   * @param src 数据源
   * @param key 密钥，长度必须是8的倍数
   * @return 返回加密后的数据
   * @throws Exception
   * 
   */
  public byte[] encrypt(byte[] src) throws Exception {

    // DES算法要求有一个可信任的随机数源
    SecureRandom sr = new SecureRandom();

    // 从原始密匙数据创建DESKeySpec对象
    DESKeySpec dks = new DESKeySpec(DECODE_AND_ECODE_KEY.getBytes());

    // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);

    SecretKey securekey = keyFactory.generateSecret(dks);

    // Cipher对象实际完成加密操作
    Cipher cipher = Cipher.getInstance(DES);

    // 用密匙初始化Cipher对象
    cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

    // 正式执行加密操作
    return byte2hex(cipher.doFinal(src));

  }

  /**
   * 
   * 解密
   * 
   * @param src 数据源
   * @param key 密钥，长度必须是8的倍数
   * @return 返回解密后的原始数据
   * @throws Exception
   * 
   */
  public byte[] decrypt(byte[] src) throws Exception {

    src = hex2byte(src);

    // DES算法要求有一个可信任的随机数源
    SecureRandom sr = new SecureRandom();

    // 从原始密匙数据创建一个DESKeySpec对象
    DESKeySpec dks = new DESKeySpec(DECODE_AND_ECODE_KEY.getBytes());

    // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成一个SecretKey对象
    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
    SecretKey securekey = keyFactory.generateSecret(dks);

    // Cipher对象实际完成解密操作
    Cipher cipher = Cipher.getInstance(DES);

    // 用密匙初始化Cipher对象
    cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

    // 正式执行解密操作
    return cipher.doFinal(src);

  }

  private byte[] byte2hex(byte[] b) {

    String hs = "";

    String stmp = "";

    for (int n = 0; n < b.length; n++) {

      stmp = java.lang.Integer.toHexString(b[n] & 0XFF);

      if (stmp.length() == 1) {
        hs = hs + "0" + stmp;
      } else {
        hs = hs + stmp;
      }

    }

    return hs.toUpperCase().getBytes();

  }

  private byte[] hex2byte(byte[] b) {

    if (b.length % 2 != 0) {
      throw new IllegalArgumentException("长度不是偶数");
    }

    byte[] b2 = new byte[b.length / 2];

    for (int n = 0; n < b.length; n += 2) {

      String item = new String(b, n, 2);

      b2[n / 2] = (byte) Integer.parseInt(item, 16);

    }

    return b2;
  }
}
