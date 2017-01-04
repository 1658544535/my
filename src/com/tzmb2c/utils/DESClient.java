package com.tzmb2c.utils;


/***
 * 报文主体消息加密解密
 * 
 * @author semyrain
 * 
 */
public class DESClient {

  // 加密解密密钥，只支持8为长度的密钥
  private String DECODE_AND_ECODE_KEY = "1Q2W3E4R";

  private DESClient() {}

  /***
   * 获取实例
   * 
   * @param 密钥
   * @return
   */
  public static DESClient getInstance(String DECODE_AND_ECODE_KEY) {
    DESClient instance = new DESClient();
    instance.DECODE_AND_ECODE_KEY = DECODE_AND_ECODE_KEY;

    return instance;
  }

  /**
   * 对字符串进行加密 1. 数字签名保证信息完整性 2. DES加密保证不可阅读性 3. BASE64编码 Base64( 3DES( MD5( 消息体 ) + 消息体)
   * 
   * @param data 待加密字符串
   * @return String 加密后的密文
   */
  public String encrypt(String data) {
    String rtn = null;

    System.out.println("等待加密的消息体明文：" + data);
    try {
      String md5str = MD5Encrypt.md5(data) + data;

      DesTool des = DesTool.getInstance(DECODE_AND_ECODE_KEY);
      byte[] b = des.encrypt(md5str.getBytes("UTF8"));

      rtn = Base64.encode(b);
    } catch (Exception e) {
      System.out.println("encrypt：消息体加密异常:" + e.getMessage());
      e.printStackTrace();
    }

    return rtn;
  }

  /**
   * 对字符串进行解密 Base64( 3DES( MD5( 消息体 ) + 消息体) 逆操作 解密时判断数据签名，如果不匹配则返回null
   * 
   * @param data 待解密字符串
   * @return String
   */
  public String decrypt(String data) {
    String rtn = null;

    try {
      DesTool des = DesTool.getInstance(DECODE_AND_ECODE_KEY);
      byte[] b = Base64.decode(data);
      String md5body = new String(des.decrypt(b), "UTF8");
      if (md5body.length() < 32) {
        System.out.println("错误！消息体长度小于数字签名长度!");
        return null;
      }
      String md5Client = md5body.substring(0, 32);
      rtn = md5body.substring(32);
      String md5Server = MD5Encrypt.md5(rtn);
      if (!md5Client.equals(md5Server)) {
        System.out.println("错误！数字签名不匹配:" + md5Server);
        return null;
      }
    } catch (Exception ex) {
      System.out.println("decrypt：消息体解密异常:" + ex.getMessage());
      ex.printStackTrace();
    }

    System.out.println("解密完成，消息体明文：" + rtn);

    return rtn;
  }
}
