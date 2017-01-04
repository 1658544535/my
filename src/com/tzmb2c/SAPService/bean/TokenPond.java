package com.tzmb2c.SAPService.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.registry.infomodel.User;

import org.apache.log4j.Logger;

import com.tzmb2c.SAPService.SAPServiceClientTest;



public class TokenPond {
  private Logger logger = Logger.getLogger(this.getClass());

  private static final Map<String, Token> TOKENS = new HashMap<String, Token>();

  private TokenPond() {}

  SAPServiceClientTest s = new SAPServiceClientTest("");

  /**
   * 将Token加入token池
   * 
   * @param tokenValue
   * @param user
   */
  public void put(String tokenValue, User user) {
    Token token = this.new Token();
    token.setToken(tokenValue);
    token.setUser(user);

    TOKENS.put(tokenValue, token);
  }

  public User get(String tokenValue) {
    Token token = TOKENS.get(tokenValue);

    if (null == token) {
      return null;
    }

    return token.getUser();
  }

  public void clear() {
    logger.info("当前系统中token数量：" + TOKENS.size() + " 开始清理过期token");

    Token token = null;
    for (String key : TOKENS.keySet()) {
      token = TOKENS.get(key);

      Date currentTime = new Date();
      Date generateTime = token.getGenerateTime();

      long diff = currentTime.getTime() - generateTime.getTime();
      // token已存活时间(分钟)
      long i = diff / (1000 * 60);
      logger.info(token.getToken() + "已经存在" + i + "分钟");
      if (i >= 30) {
        TOKENS.remove(key);
      }
    }
  }

  private class Token {
    private String token;
    private Date generateTime = new Date();
    private User user;

    public String getToken() {
      return token;
    }

    public void setToken(String token) {
      this.token = token;
    }

    public Date getGenerateTime() {
      return generateTime;
    }

    @SuppressWarnings("unused")
    public void setGenerateTime(Date generateTime) {
      this.generateTime = generateTime;
    }

    public User getUser() {
      return user;
    }

    public void setUser(User user) {
      this.user = user;
    }
  }

}
