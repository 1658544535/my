package com.tzmb2c.web.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.web.pojo.LoginRecPojo;
import com.tzmb2c.web.service.LoginRecService;

public class LoginRecAction {
  @Autowired
  private LoginRecService loginRecService;
  private LoginRecPojo loginRecPojo;

  public String getLoginRecByCondition() throws Exception {
    HttpServletRequest request = ServletActionContext.getRequest();
    LoginRecPojo loginRecPojoInfo =
        loginRecService.queryLoginRecBeforeItem(loginRecPojo.getUserId());
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String date = sdf.format(loginRecPojoInfo.getLoginDate());

    long cha = new Date().getTime() - loginRecPojoInfo.getLoginDate().getTime();
    int result = (int) (cha / (1000 * 60 * 60));
    int dayDifference = result / 24;
    request.setAttribute("preTM", date);
    request.setAttribute("partitionTM", dayDifference);
    return null;
  }



  public LoginRecPojo getLoginRecPojo() {
    return loginRecPojo;
  }

  public void setLoginRecPojo(LoginRecPojo loginRecPojo) {
    this.loginRecPojo = loginRecPojo;
  }

}
