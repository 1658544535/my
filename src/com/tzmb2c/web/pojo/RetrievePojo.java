package com.tzmb2c.web.pojo;

import maowu.framework.utils.pojo.SuperPojo;

/**
 * 忘记密码
 * 
 * @author fu
 */

public class RetrievePojo extends SuperPojo {

  private String loginname;// 帐号
  private String authcode;// 验证码
  private String newpass;// 新密码
  private String newpassaffirm;// 新密码确认

  public String getLoginname() {
    return loginname;
  }

  public void setLoginname(String loginname) {
    this.loginname = loginname;
  }

  public String getAuthcode() {
    return authcode;
  }

  public void setAuthcode(String authcode) {
    this.authcode = authcode;
  }

  public String getNewpass() {
    return newpass;
  }

  public void setNewpass(String newpass) {
    this.newpass = newpass;
  }

  public String getNewpassaffirm() {
    return newpassaffirm;
  }

  public void setNewpassaffirm(String newpassaffirm) {
    this.newpassaffirm = newpassaffirm;
  }


}
