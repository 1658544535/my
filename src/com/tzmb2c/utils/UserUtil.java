package com.tzmb2c.utils;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.web.pojo.SysLoginPojo;

public class UserUtil {
  public static SysLoginPojo getUser() {

    ActionContext actionContext = ActionContext.getContext();
    SysLoginPojo logiPojo = (SysLoginPojo) actionContext.getSession().get("user");
    return logiPojo;
  }

  public static SysLoginPojo getWebUser() {
    ActionContext actionContext = ActionContext.getContext();
    SysLoginPojo logiPojo = (SysLoginPojo) actionContext.getSession().get("wuser");
    return logiPojo;
  }

  public static SysLoginPojo getGeekUser() {
    ActionContext actionContext = ActionContext.getContext();
    SysLoginPojo logiPojo = (SysLoginPojo) actionContext.getSession().get("geekuser");
    return logiPojo;
  }
  
  public static SysLoginPojo getTarentoUser() {
    ActionContext actionContext = ActionContext.getContext();
    SysLoginPojo logiPojo = (SysLoginPojo) actionContext.getSession().get("tarentouser");
    return logiPojo;
  }
}
