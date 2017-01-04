package com.tzmb2c.web.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.geetest.sdk.GeetestUtil;
import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.MD5Util;
import com.tzmb2c.utils.SMSCaptchaUtil;
import com.tzmb2c.utils.SmsSendUtil;
import com.tzmb2c.web.pojo.HelpPojo;
import com.tzmb2c.web.pojo.LoginRecPojo;
import com.tzmb2c.web.pojo.MenuPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserInfoPojo;
import com.tzmb2c.web.pojo.UserVerifyPojo;
import com.tzmb2c.web.service.HelpService;
import com.tzmb2c.web.service.LoginRecService;
import com.tzmb2c.web.service.LoginService;
import com.tzmb2c.web.service.OrderService;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserInfoService;
import com.tzmb2c.web.service.UserVerifyService;

public class LoginAction extends SuperAction {
  private Logger logger = Logger.getLogger(LoginAction.class);
  @Autowired
  private LoginService loginService;
  @Autowired
  private LoginRecService loginRecService;
  @Autowired
  private UserVerifyService userVerifyService;
  @Autowired
  private HelpService helpService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private UserInfoService userInfoService;
  @Autowired
  private OrderService orderService;

  private HelpPojo helpPojo;

  private String loginId;
  private String loginPd;
  private String verifyCode;
  private String msg;
  private String url;// 前端传的返回连接
  private SysLoginPojo sysloginPojo;
  private UserVerifyPojo userVerifyPojo;
  private List<UserVerifyPojo> userVerifyPojos;
  private List<SysLoginPojo> sysLoginPojos;
  private String result;
  private String phone;// 手机号码
  private String content;

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public HelpPojo getHelpPojo() {
    return helpPojo;
  }

  public void setHelpPojo(HelpPojo helpPojo) {
    this.helpPojo = helpPojo;
  }

  public List<UserVerifyPojo> getUserVerifyPojos() {
    return userVerifyPojos;
  }

  public void setUserVerifyPojos(List<UserVerifyPojo> userVerifyPojos) {
    this.userVerifyPojos = userVerifyPojos;
  }

  public List<SysLoginPojo> getSysLoginPojos() {
    return sysLoginPojos;
  }

  public void setSysLoginPojos(List<SysLoginPojo> sysLoginPojos) {
    this.sysLoginPojos = sysLoginPojos;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public UserVerifyPojo getUserVerifyPojo() {
    return userVerifyPojo;
  }

  public void setUserVerifyPojo(UserVerifyPojo userVerifyPojo) {
    this.userVerifyPojo = userVerifyPojo;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public SysLoginPojo getSysloginPojo() {
    return sysloginPojo;
  }

  public void setSysloginPojo(SysLoginPojo sysloginPojo) {
    this.sysloginPojo = sysloginPojo;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getLoginId() {
    return loginId;
  }

  public void setLoginId(String loginId) {
    this.loginId = loginId;
  }

  public String getLoginPd() {
    return loginPd;
  }

  public void setLoginPd(String loginPd) {
    this.loginPd = loginPd;
  }

  public String getVerifyCode() {
    return verifyCode;
  }

  public void setVerifyCode(String verifyCode) {
    this.verifyCode = verifyCode;
  }

  public String doLogin() throws Exception {
    logger.info("登录系统!");

    ActionContext actionContext = ActionContext.getContext();

    if (StringUtils.isNotEmpty(loginId) && StringUtils.isNotEmpty(loginPd)) {

      SysLoginPojo loginPojo = new SysLoginPojo();
      loginPojo.setLoginname(loginId);

      loginPojo.setPassword(MD5Util.MD5(loginPd));
      loginPojo.setType("0");
      if (loginService.loginCheckService(loginPojo)) {
        SysLoginPojo logiPojo = (SysLoginPojo) actionContext.getSession().get("user");
        if (logiPojo.getStatus() == 1) {
          HttpServletRequest request = ServletActionContext.getRequest();

          if (logiPojo != null) {

            if (request.getSession().getAttribute("loginPojoId") == null) {
              LoginRecPojo loginRecPojo = new LoginRecPojo();
              loginRecPojo.setType(logiPojo.getType());
              loginRecPojo.setLoginDate(new Date());
              loginRecPojo.setLoginIp(getIpAddr(request));
              loginRecPojo.setUserId(logiPojo.getId());
              loginRecService.addLoginRec(loginRecPojo);
              request.getSession().setAttribute("loginPojoId", logiPojo.getId());
            }
            List<MenuPojo> menuList = loginService.getMenuService(logiPojo.getId());
            List<MenuPojo> menuListAll = new ArrayList<MenuPojo>();
            for (MenuPojo m : menuList) {
              menuListAll.addAll(m.getMenuPojoList());
            }
            actionContext.getSession().put("menuListAll", menuListAll);
            actionContext.put("menuList", menuList);
            request.getSession().setAttribute("menuList", menuList);
            request.getSession().setAttribute("loginname", logiPojo.getLoginname());
            request.getSession().setAttribute("username", logiPojo.getName());
            LoginRecPojo loginRecPojoInfo =
                loginRecService.queryLoginRecBeforeItem(logiPojo.getId());
            if (loginRecPojoInfo != null) {
              SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              String date = sdf.format(loginRecPojoInfo.getLoginDate());

              long cha = new Date().getTime() - loginRecPojoInfo.getLoginDate().getTime();
              int result = (int) (cha / (1000 * 60 * 60));
              int dayDifference = result / 24;
              request.getSession().setAttribute("preTM", date);
              request.getSession().setAttribute("partitionTM", dayDifference);
            }
            return SUCCESS;
          } else {

            return "loginpage";
          }
        } else {
          msg = "您的帐号暂时不能登录，请联系管理员！";
          actionContext.put("loop", "false");
          return "loginpage";
        }

        // path = SUCCESS;

      }
    }
    msg = "用户名或密码错误！";
    actionContext.put("loop", "false");
    return "loginpage";
  }

  public String loginOut() {
    String path = "loginpage";

    ActionContext.getContext().getSession().clear();
    // HttpServletRequest request=ServletActionContext.getRequest();
    // System.out.println(ServletActionContext.getRequest().getAttribute("user"));
    // System.out.println(request.getSession().getAttribute("menuList"));

    return path;
  }

  public String doLoginWeb() throws Exception {
    ActionContext actionContext = ActionContext.getContext();

    if (StringUtils.isNotEmpty(loginId) && StringUtils.isNotEmpty(loginPd)) {

      SysLoginPojo loginPojo = new SysLoginPojo();
      loginPojo.setLoginname(loginId);

      loginPojo.setPassword(MD5Util.MD5(loginPd));

      if (loginService.loginCheckWeb(loginPojo)) {
        SysLoginPojo logiPojo = (SysLoginPojo) actionContext.getSession().get("wuser");
        if (logiPojo != null && logiPojo.getStatus() == 1) {
          HttpServletRequest request = ServletActionContext.getRequest();

          LoginRecPojo loginRecPojo = new LoginRecPojo();
          loginRecPojo.setType(logiPojo.getType());
          loginRecPojo.setLoginDate(new Date());
          loginRecPojo.setLoginIp(getIpAddr(request));
          loginRecPojo.setUserId(logiPojo.getId());
          loginRecService.addLoginRec(loginRecPojo);
          if (url != null && !url.equals("")) {
            FileUtil.alertMessageBySkip("登录成功", url);
            return null;
          } else {
            return SUCCESS;
          }

        } else {

          msg = "您的帐号暂时不能登录，请联系管理员！";
          actionContext.put("loop", "false");
          return "loginweb";
        }
      }
    }
    msg = "用户名或密码错误！";
    actionContext.put("loop", "false");
    return "loginweb";
  }

  public String doLoginOutWeb() {
    ActionContext.getContext().getSession().clear();
    // HttpServletRequest request=ServletActionContext.getRequest();
    // System.out.println(ServletActionContext.getRequest().getAttribute("user"));
    // System.out.println(request.getSession().getAttribute("menuList"));
    return "loginweb";
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

  public String goRegister() {
    return SUCCESS;
  }

  public String doRegister() {
    try {
      ActionContext actionContext = ActionContext.getContext();
      sysLoginPojos = sysLoginService.getSysLoginByName(sysloginPojo.getName());
      if (sysLoginPojos.size() > 0) {
        msg = "该会员名称已经被注册了!";
        return "registerweb";
      }
      UserVerifyPojo userVerify = new UserVerifyPojo();
      userVerify.setLoginname(sysloginPojo.getLoginname());
      userVerify = userVerifyService.findNewestByPhone(userVerify);
      if (userVerify == null || userVerify.getCaptcha() == null
          || !verifyCode.equals(userVerify.getCaptcha())) {
        msg = "验证码错误！请重新输入！";
        return "registerweb";
      }
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

          FileUtil.alertMessageBySkip("注册成功！", "goIndexWeb.do");
          return null;
        } else {
          msg = "该手机号码已被注册，请直接登录";
          actionContext.put("loop", "false");
          return "loginweb";
        }
      }
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }
    return "registerweb";
  }

  /**
   * 验证验证码是否正确
   * 
   * @throws IOException
   * */
  public void checkVerify() throws IOException {
    HttpServletResponse response = ServletActionContext.getResponse();
    response.setContentType("text/html; charset=utf-8");
    UserVerifyPojo userVerify = new UserVerifyPojo();
    userVerify.setLoginname(sysloginPojo.getLoginname());
    userVerify = userVerifyService.findNewestByPhone(userVerify);
    if (userVerify == null || userVerify.getCaptcha() == null
        || !verifyCode.equals(userVerify.getCaptcha())) {
      // 验证码错误
      response.getWriter().print("0".toString());
    } else {
      response.getWriter().print("1".toString());
    }
  }

  /**
   * 
   * 验证会员名称
   * */
  public void checkNikename() {
    try {

      sysLoginPojos = sysLoginService.getSysLoginByName(sysloginPojo.getName());
      HttpServletResponse response = ServletActionContext.getResponse();
      response.setContentType("text/html; charset=utf-8");
      if (sysLoginPojos.size() > 0) {
        // 已经被注册
        response.getWriter().print("0".toString());

      } else {

        response.getWriter().print("1".toString());
      }
    } catch (Exception e) {

      e.printStackTrace();
    }

  }

  public void checkLoginname() {
    try {
      sysloginPojo = loginService.getLoginPojoByLoginname(sysloginPojo.getLoginname());

      HttpServletResponse response = ServletActionContext.getResponse();
      response.setContentType("text/html; charset=utf-8");
      if (sysloginPojo == null) {

        response.getWriter().print("0".toString());

      } else {
        response.getWriter().print("1".toString());
      }
    } catch (Exception e) {

      e.printStackTrace();
    }
  }

  public String serviceWeb() {
    HelpPojo pojo = new HelpPojo();
    pojo.setId(236L);
    helpPojo = helpService.goHelpUpdate(pojo);
    ActionContext ac = ActionContext.getContext();
    ac.put("tittle", helpPojo.getTitle());
    ac.put("content", helpPojo.getContent());
    return SUCCESS;
  }

  public String sendVerify() throws SQLException, UnsupportedEncodingException {
    HttpServletRequest request = ServletActionContext.getRequest();
    boolean flag = GeetestUtil.validGeetestCaptcha(request);
    if (flag) {
      /**
       * 发送短信验证码方法
       * 
       * parm :手机号码 result：6位随机验证码
       * 
       */
      Random rd = new Random();
      String code = "";
      int getNum;
      do {
        getNum = Math.abs(rd.nextInt()) % 10 + 48;// 产生数字0-9的随机数
        // getNum = Math.abs(rd.nextInt())%26 + 97;//产生字母a-z的随机数
        char num1 = (char) getNum;
        String dn = Character.toString(num1);
        code += dn;
      } while (code.length() < 6);
      System.out.println("随机的6位密码是：" + code);

      String content = "【拼得好】亲，您此次登录的验证码是" + code + "，有效期15分钟。如非本人操作，请勿透露！玩具低至7.7等你抢！";
      // MxtongSMSCaptchaUtil.sendMxtongSMS(userVerifyPojo.getLoginname(), content);
      SmsSendUtil.sendSMS(userVerifyPojo.getLoginname(), content);
      // Map<String, Object> map = new HashMap<String, Object>();
      // map.put("loginname", "13068904483");
      // userVerifyPojos = userVerifyService.userVerifyFind(map);
      userVerifyPojo.setCaptcha(code);
      userVerifyPojo.setCreateDate(new Date());
      userVerifyService.userVerifyAdd(userVerifyPojo);
      this.result = "1";
    } else {
      this.result = "2";
    }
    return SUCCESS;
  }

  public String sendSMS() {
    Boolean success;
    Integer result = null;
    String error_msg = null;

    Map<String, Object> map = new HashMap<String, Object>();
    if (phone == null || phone.equals("")) {
      success = true;
      result = 0;
      error_msg = "请输入手机号码！";
    } else if (content == null || content.equals("")) {
      success = true;
      result = 0;
      error_msg = "请输入要发送的内容！";
    } else {
      SMSCaptchaUtil.SendCaptcha(phone, content);
      UserVerifyPojo pojo = new UserVerifyPojo();
      pojo.setCaptcha(content);
      pojo.setLoginname(phone);
      pojo.setCreateDate(new Date());
      success = true;
      result = 1;
      error_msg = "发送成功！";
    }
    map.put("success", success);
    map.put("result", result);
    map.put("error_msg", error_msg);

    JSONObject json = JSONObject.fromObject(map);
    ActionContext actionContext = ActionContext.getContext();
    actionContext.put("result", json.toString());
    // try {
    // ServletActionContext.getResponse().setContentType(
    // "text/html; charset=utf-8");
    // ServletActionContext.getResponse().getWriter()
    // .write(json.toString());
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    return SUCCESS;

  }
}
