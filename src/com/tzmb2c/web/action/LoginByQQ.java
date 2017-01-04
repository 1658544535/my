package com.tzmb2c.web.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import maowu.framework.utils.web.SuperAction;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import com.qq.connect.utils.http.HttpClient;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.SysLoginService;

public class LoginByQQ extends SuperAction {

  @Autowired
  private SysLoginService sysLoginService;

  /**
   * 
   * 跳转QQ登录页面
   * */
  public void toLoginQQ() {
    getResponse().setContentType("text/html; charset=utf-8");
    Oauth oauth = new Oauth();
    try {
      String url = oauth.getAuthorizeURL(getRequest());
      getResponse().sendRedirect(url);
    } catch (QQConnectException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /**
   * 
   * QQ登录回调方法
   * 
   * @throws SQLException
   * */
  public String doLoginByQQ() throws SQLException {
    this.getResponse();
    HttpServletRequest request = this.getRequest();
    HttpSession session = request.getSession();
    getResponse().setContentType("text/html; charset=utf-8");
    try {
      AccessToken qqtoken = new Oauth().getAccessTokenByRequest(request);
      if (qqtoken.getAccessToken().equals("")) {
        return "login";
      } else {
        String accessToken = qqtoken.getAccessToken();
        System.out.println(accessToken);
        Long tokenExpireIn = qqtoken.getExpireIn();
        OpenID oId = new OpenID(accessToken);
        String openid = oId.getUserOpenID();
        session.setAttribute("demo_access_token", accessToken);
        session.setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));
        session.setAttribute("demo_openid", openid);
        UserInfo userinfo = new UserInfo(accessToken, openid);
        UserInfoBean ub = userinfo.getUserInfo();
        System.out.println(ub.getNickname());
        System.out.println(ub.getLevel());
        com.qq.connect.api.weibo.UserInfo weiboUserInfo =
            new com.qq.connect.api.weibo.UserInfo(accessToken, openid);
        System.out.println(weiboUserInfo.getUserInfo().getName());
        System.out.println(weiboUserInfo.getUserInfo().getEmail());
        System.out.println(weiboUserInfo.getUserInfo().getLevel());
        session.setAttribute("zoneInfo", ub);
        session.setAttribute("weiboInfo", weiboUserInfo.getUserInfo());
        // 将QQ登录得到的唯一标识保存到session中
        SysLoginPojo sysloginPojo = new SysLoginPojo();
        sysloginPojo.setName(ub.getNickname());
        sysloginPojo.setQqOpenId(openid);
        // 根据openID判断是否有过注册
        List<SysLoginPojo> sysLoginPojos = sysLoginService.getSysLoginByOpenId(openid);
        // 如果没有暂时保存到session中，跳转到激活页面
        if (sysLoginPojos.size() == 0) {
          ActionContext.getContext().getSession().put("wuser", sysloginPojo);
          return "ACTIVATE";
        }
        // 将查询到的用户信息传递到session中
        ActionContext.getContext().getSession().put("wuser", sysLoginPojos.get(0));
        return "success";
      }
    } catch (QQConnectException e) {
      e.printStackTrace();
      return "login";
    }
  }

  public HttpClient httpClient;

  /**
   * 获得request
   * 
   * @return
   */
  public HttpServletRequest getRequest() {
    return ServletActionContext.getRequest();
  }

  /**
   * 获得response
   * 
   * @return
   */
  public HttpServletResponse getResponse() {
    return ServletActionContext.getResponse();
  }

  /**
   * 获得session
   * 
   * @return
   */
  public HttpSession getSession() {
    return ServletActionContext.getRequest().getSession();
  }

}
