package com.tzmb2c.web.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import maowu.framework.utils.web.SuperAction;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.MD5Util;
import com.tzmb2c.web.pojo.LoginRecPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserInfoPojo;
import com.tzmb2c.web.pojo.UserVerifyPojo;
import com.tzmb2c.web.service.LoginRecService;
import com.tzmb2c.web.service.LoginService;
import com.tzmb2c.web.service.UserInfoService;
import com.tzmb2c.web.service.UserVerifyService;

public class taobaoActivateAction extends SuperAction {

  private Logger logger = Logger.getLogger(LoginAction.class);
  @Autowired
  private LoginService loginService;
  @Autowired
  private LoginRecService loginRecService;
  @Autowired
  private UserInfoService userInfoService;
  @Autowired
  private UserVerifyService userVerifyService;
  private String verifyCode;// 手机验证码
  private SysLoginPojo sysloginPojo;

  public String getVerifyCode() {
    return verifyCode;
  }

  public void setVerifyCode(String verifyCode) {
    this.verifyCode = verifyCode;
  }

  public SysLoginPojo getSysloginPojo() {
    return sysloginPojo;
  }

  public void setSysloginPojo(SysLoginPojo sysloginPojo) {
    this.sysloginPojo = sysloginPojo;
  }

  /**
   * 第三方登录用户激活
   * 
   * */
  public String doActivate() throws Exception {
    // 得到session的值
    ActionContext actionContext = ActionContext.getContext();
    SysLoginPojo login = (SysLoginPojo) actionContext.getSession().get("wuser");
    // 判断临时session中是openID还是淘宝ID
    if (login.getTaobao_user_id() != null && !login.getTaobao_user_id().equals("")) {
      // 判断手机是否注册
      SysLoginPojo s = loginService.getLoginPojoByLoginname(sysloginPojo.getLoginname());
      if (s != null && s.getTaobao_user_id() == null) {
        FileUtil.alertMessageBySkip("您已经注册过本网站,请移步绑定！", "goBound.do");// 注册过网站但未绑定
        return null;
      } else if (s != null && s.getTaobao_user_id() != null) {
        FileUtil.alertMessageBySkip("您已经注册过本网站并且已经绑定，请直接登录！", "doLoginOutWeb.do");// 注册过并且已经绑定过
        return null;
      } else {
        // 激活淘宝登录
        sysloginPojo.setTaobao_user_id(login.getTaobao_user_id());
        sysloginPojo.setType("1");
        sysloginPojo.setStatus(1);
        sysloginPojo.setSorting(0);
        sysloginPojo.setPassword(MD5Util.MD5(sysloginPojo.getPassword()));
        loginService.insertLoginPojo(sysloginPojo);
        if (loginService.loginCheckWeb(sysloginPojo)) {
          SysLoginPojo logiPojo = (SysLoginPojo) actionContext.getSession().get("wuser");
          if (logiPojo != null) {
            sysloginPojo.prePersist(logiPojo);
            loginService.updateLoginPojo(sysloginPojo);
            HttpServletRequest request = ServletActionContext.getRequest();

            LoginRecPojo loginRecPojo = new LoginRecPojo();
            loginRecPojo.setType(logiPojo.getType());
            loginRecPojo.setLoginDate(new Date());
            loginRecPojo.setLoginIp(getIpAddr(request));
            loginRecPojo.setUserId(logiPojo.getId());
            loginRecService.addLoginRec(loginRecPojo);
            // 注册同时userinfo表需插入一条数据
            sysloginPojo = loginService.getLoginPojoByLoginnameAndPassword(sysloginPojo);
            UserInfoPojo userInfoPojo = new UserInfoPojo();
            userInfoPojo.setUserId(sysloginPojo.getId());
            userInfoPojo.setCreateDate(new Date());
            userInfoPojo.setStatus(1);
            userInfoService.insertUserInfo(userInfoPojo);

            FileUtil.alertMessageBySkip("激活成功！", "goIndexWeb.do");
            return null;
          } else {
            actionContext.put("loop", "false");
            return "loginweb";
          }
        }
      }
    } else if (login.getQqOpenId() != null && !login.getQqOpenId().equals("")) {
      // 判断手机是否注册
      SysLoginPojo s = loginService.getLoginPojoByLoginname(sysloginPojo.getLoginname());
      if (s != null && s.getQqOpenId() == null) {
        FileUtil.alertMessageBySkip("您已经注册过本网站,请移步绑定！", "goBound.do");// 注册过网站但未绑定
        return null;
      } else if (s != null && s.getQqOpenId() != null) {
        FileUtil.alertMessageBySkip("您已经注册过本网站并且已经绑定，请直接登录！", "doLoginOutWeb.do");// 注册过并且已经绑定过
        return null;
      } else {
        // 激活QQ登录
        sysloginPojo.setQqOpenId(login.getQqOpenId());
        sysloginPojo.setType("1");
        sysloginPojo.setStatus(1);
        sysloginPojo.setSorting(0);
        sysloginPojo.setPassword(MD5Util.MD5(sysloginPojo.getPassword()));
        loginService.insertLoginPojo(sysloginPojo);
        if (loginService.loginCheckWeb(sysloginPojo)) {
          SysLoginPojo logiPojo = (SysLoginPojo) actionContext.getSession().get("wuser");
          if (logiPojo != null) {
            sysloginPojo.prePersist(logiPojo);
            loginService.updateLoginPojo(sysloginPojo);
            HttpServletRequest request = ServletActionContext.getRequest();

            LoginRecPojo loginRecPojo = new LoginRecPojo();
            loginRecPojo.setType(logiPojo.getType());
            loginRecPojo.setLoginDate(new Date());
            loginRecPojo.setLoginIp(getIpAddr(request));
            loginRecPojo.setUserId(logiPojo.getId());
            loginRecService.addLoginRec(loginRecPojo);
            // 注册同时userinfo表需插入一条数据
            sysloginPojo = loginService.getLoginPojoByLoginnameAndPassword(sysloginPojo);
            UserInfoPojo userInfoPojo = new UserInfoPojo();
            userInfoPojo.setUserId(sysloginPojo.getId());
            userInfoPojo.setCreateDate(new Date());
            userInfoPojo.setStatus(1);
            userInfoService.insertUserInfo(userInfoPojo);

            FileUtil.alertMessageBySkip("激活成功！", "goIndexWeb.do");
            return null;
          } else {
            actionContext.put("loop", "false");
            return "loginweb";
          }
        }
      }
    }
    return "success";
  }

  /**
   * 第三方用户绑定已有的账户
   * 
   * @throws Exception
   * */
  public String doBound() throws Exception {
    // 根据手机号码查询登录信息表
    SysLoginPojo loginPojo = loginService.getLoginPojoByLoginname(sysloginPojo.getLoginname());
    // 得到临时存到session的值
    ActionContext actionContext = ActionContext.getContext();
    SysLoginPojo login = (SysLoginPojo) actionContext.getSession().get("wuser");
    // 判断验证码
    UserVerifyPojo userVerify = new UserVerifyPojo();
    userVerify.setLoginname(sysloginPojo.getLoginname());
    userVerify = userVerifyService.findNewestByPhone(userVerify);
    if (userVerify == null || userVerify.getCaptcha() == null
        || !verifyCode.equals(userVerify.getCaptcha())) {
      FileUtil.alertMessageBySkip("您的验证码输入有误！", "goBound.do");
      return null;
    } else if (loginPojo == null) {
      FileUtil.alertMessageBySkip("您没有注册过本网站,请移步激活！", "goActivate.do");
      return null;
    } else if (loginPojo.getTaobao_user_id() != null && !login.getTaobao_user_id().equals("")) {
      FileUtil.alertMessageBySkip("您的手机已经绑定过淘宝登录,请移步登录！", "doLoginOutWeb.do");
      return null;
    } else if (loginPojo.getQqOpenId() != null && !login.getQqOpenId().equals("")) {
      FileUtil.alertMessageBySkip("您的手机已经绑定过QQ登录,请移步登录！", "doLoginOutWeb.do");
      return null;
    } else if ((login.getTaobao_user_id() == null || login.getTaobao_user_id().equals(""))
        && (login.getQqOpenId() == null || login.getQqOpenId().equals(""))) {
      // 三方登录失效
      FileUtil.alertMessageBySkip("第三方登录ID失效！", "doLoginWeb.do");
      return null;
    } else if ((login.getTaobao_user_id() == null || login.getTaobao_user_id().equals(""))
        && (login.getQqOpenId() != null || !login.getQqOpenId().equals(""))) {
      // 绑定QQ
      loginPojo.setQqOpenId(login.getQqOpenId());
      loginService.updateLoginPojo(loginPojo);
      actionContext.getSession().put("wuser", loginPojo);

    } else if ((login.getQqOpenId() == null || login.getQqOpenId().equals(""))
        && (login.getTaobao_user_id() != null || !login.getTaobao_user_id().equals(""))) {
      // 绑定淘宝
      loginPojo.setTaobao_user_id(login.getTaobao_user_id());
      loginService.updateLoginPojo(loginPojo);
      actionContext.getSession().put("wuser", loginPojo);
    }
    return SUCCESS;
  }

  /**
   * 
   * 淘宝登录解除绑定
   * 
   * @throws Exception
   * */
  public String doRerandTaobao() throws Exception {
    // 根据手机号码查询登录信息表
    SysLoginPojo loginPojo = loginService.getLoginPojoByLoginname(sysloginPojo.getLoginname());
    // 判断验证码
    UserVerifyPojo userVerify = new UserVerifyPojo();
    userVerify.setLoginname(sysloginPojo.getLoginname());
    userVerify = userVerifyService.findNewestByPhone(userVerify);
    if (loginPojo == null) {
      FileUtil.alertMessageBySkip("没有找到该手机号码啦！╮(╯_╰)╭", "reBound.do");
      return null;
    } else if (userVerify == null || userVerify.getCaptcha() == null
        || !verifyCode.equals(userVerify.getCaptcha())) {
      FileUtil.alertMessageBySkip("您的验证码或者手机号码输入有点小问题哦！(づ￣ 3￣)づ", "reBound.do");
      return null;
    } else if (loginPojo.getTaobao_user_id() == null) {
      FileUtil.alertMessageBySkip("您没有绑定过淘宝哦，不用解绑啦O(∩_∩)O~", "reBound.do");
      return null;
    } else {
      SysLoginPojo sysLoginPojo = new SysLoginPojo();
      sysLoginPojo.setTaobao_user_id("1");
      sysLoginPojo.setLoginname(sysloginPojo.getLoginname());
      // 将该用户的qq_openid设置为空
      loginService.updateOauthBrand(sysLoginPojo);
      FileUtil.alertMessageBySkip("解绑成功咯，您可以绑定其他淘宝帐号O(∩_∩)O~", "doLoginOutWeb.do");
      return null;
    }
  }

  /**
   * 
   * QQ登录解除绑定
   * 
   * @throws Exception
   * */
  public String doRebrandQQ() throws Exception {
    // 根据手机号码查询登录信息表
    SysLoginPojo loginPojo = loginService.getLoginPojoByLoginname(sysloginPojo.getLoginname());
    // 判断验证码
    UserVerifyPojo userVerify = new UserVerifyPojo();
    userVerify.setLoginname(sysloginPojo.getLoginname());
    userVerify = userVerifyService.findNewestByPhone(userVerify);
    if (loginPojo == null) {
      FileUtil.alertMessageBySkip("没有找到该手机号码啦！╮(╯_╰)╭", "reBound.do");
      return null;
    } else if (userVerify == null || userVerify.getCaptcha() == null
        || !verifyCode.equals(userVerify.getCaptcha())) {
      FileUtil.alertMessageBySkip("您的验证码或者手机号码输入有点小问题哦！(づ￣ 3￣)づ", "reBound.do");
      return null;
    } else if (loginPojo.getQqOpenId() == null) {
      FileUtil.alertMessageBySkip("您没有绑定过QQ哦，不用解绑啦O(∩_∩)O~", "reBound.do");
      return null;
    } else {
      SysLoginPojo sysLoginPojo = new SysLoginPojo();
      sysLoginPojo.setQqOpenId("1");
      sysLoginPojo.setLoginname(sysloginPojo.getLoginname());
      // 将该用户的qq_openid设置为空
      loginService.updateOauthBrand(sysLoginPojo);
      FileUtil.alertMessageBySkip("解绑成功咯，您可以绑定其他QQ号码O(∩_∩)O~", "doLoginOutWeb.do");
      return null;
    }
  }

  /**
   * 激活页面跳转
   * */
  public String goActivate() {
    return SUCCESS;
  }

  /**
   * 绑定页面跳转
   * */
  public String goBound() {
    return SUCCESS;
  }

  /**
   * 解除绑定页面跳转
   * */
  public String reBound() {
    return SUCCESS;
  }

  public String getIpAddr(HttpServletRequest request) {
    String ip = request.getHeader("x-forwarded-for");
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }
    return ip;
  }
}
