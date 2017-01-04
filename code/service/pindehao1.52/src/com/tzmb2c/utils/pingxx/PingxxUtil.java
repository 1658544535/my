package com.tzmb2c.utils.pingxx;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;

public class PingxxUtil {
  /**
   * base64解码√
   * 
   * @param file
   * @param base64
   * @return
   * @throws Exception
   */
  public static byte[] getByteFromFile(String sign, boolean base64) throws Exception {
    byte[] fileBytes = null;
    if (sign == null) {
      return null;
    }
    if (base64) {
      fileBytes = sign.getBytes();
      fileBytes = Base64.decodeBase64(fileBytes);
    } else {
      fileBytes = sign.getBytes();
    }
    return fileBytes;
  }

  /**
   * 获得公钥√
   * 
   * @return
   * @throws Exception
   */
  public static PublicKey getPubKey(String pubKey) throws Exception {
    byte[] keyBytes = getByteFromFile(pubKey, true);
    System.out.println("这是keyBytes：" + keyBytes);
    X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PublicKey publicKey = keyFactory.generatePublic(spec);
    return publicKey;
  }

  /**
   * 验证签名
   * 
   * @param data
   * @param sigBytes
   * @param publicKey
   * @return
   * @throws NoSuchAlgorithmException
   * @throws InvalidKeyException
   * @throws SignatureException
   */
  public static boolean verifyData(byte[] data, byte[] sigBytes, PublicKey publicKey)
      throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
    Signature signature = Signature.getInstance("SHA256withRSA");
    signature.initVerify(publicKey);
    signature.update(data);
    return signature.verify(sigBytes);
  }
}
