package maowu.framework.utils.web;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import maowu.framework.utils.datetime.DateTimeUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.tzmb2c.common.Pager;
import com.tzmb2c.web.pojo.SysLoginPojo;

public class SuperAction extends ActionSupport {

  public static final String LISTPAGE = "listpage";

  public static final String ADDPAGE = "addpage";

  public static final String UPDATEPAGE = "updatepage";

  public static final String LOGINPAGE = "loginpage";

  public static final String ERRORPAGE = "errorpage";

  public static final String INDEXPAGE = "indexpage";

  public static final String MANAGEPAGE = "managepage";

  public static final String DETAILPAGE = "detailpage";

  public static final String FAILPAGE = "failpage";


  protected Pager page;

  protected InputStream inputStream;

  private static final long serialVersionUID = 1L;

  private static final String formatStr = "yyyy-MM-DD";

  /**
   * 下载的文件名
   */
  protected String downloadFileName;

  public InputStream getInputStream() {
    return inputStream;
  }

  public void setInputStream(InputStream inputStream) {
    this.inputStream = inputStream;
  }

  public Pager getPage() {
    return page;
  }

  public void setPage(Pager page) {
    this.page = page;
  }

  public String getDownloadFileName() {
    try {
      downloadFileName = new String(this.downloadFileName.getBytes(), "ISO8859-1");
    } catch (UnsupportedEncodingException e) {

      e.printStackTrace();
    }
    return downloadFileName;
  }

  public void setDownloadFileName(String downloadFileName) {
    this.downloadFileName = downloadFileName;
  }



  /**
   * 
   * 添加到Request
   * 
   * @param key
   * @param value
   */
  protected void addRequest(String key, Object value) {
    ActionContext ac = ActionContext.getContext();
    ac.put(key, value);
  }


  /**
   * 以字符串类型输出到页面(用于Ajax回调)
   * 
   * @param outputString
   * @throws Exception
   */
  protected void outputPageString(String outputString) throws Exception {

    HttpServletResponse response = ServletActionContext.getResponse();
    response.setContentType("text/html; charset=utf-8");
    response.getWriter().print(outputString.toString());
  }

  /**
   * 获得当前登录用户信息
   * 
   * @return userPojo
   */
  protected SysLoginPojo findSessionUser() {

    ActionContext actionContext = ActionContext.getContext();
    SysLoginPojo userPojo = (SysLoginPojo) actionContext.getSession().get("user");
    if (userPojo == null) {
      System.out.println("用户未登录，请登录后再操作！");
      return null;
    } else {
      return userPojo;
    }

  }

  /**
   * 获得当前登录用户ID
   * 
   * @return UserId
   */
  protected Long findSessionUserId() {
    ActionContext actionContext = ActionContext.getContext();
    SysLoginPojo userPojo = (SysLoginPojo) actionContext.getSession().get("user");
    if (userPojo == null) {
      System.out.println("用户未登录，请登录后再操作！");
      return null;
    } else {
      return userPojo.getId();
    }
  }

  /**
   * 获得当前登录用户Name
   * 
   * @return UserName
   */
  protected String findSessionUserName() {
    ActionContext actionContext = ActionContext.getContext();
    SysLoginPojo loginPojo = (SysLoginPojo) actionContext.getSession().get("user");
    if (loginPojo == null) {

      System.out.println("用户未登录，请登录后再操作！");
      return null;
    } else {
      return loginPojo.getName();
    }

  }

  /**
   * 生成4位随机数
   * 
   * @param num
   * @return
   */
  protected String getRandomNum(int num) {
    String[] digits = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
    Random rnum = new Random(new Date().getTime());
    for (int i = 0; i < digits.length; i++) {
      int index = Math.abs(rnum.nextInt()) % 10;
      String tmpDigit = digits[index];
      digits[index] = digits[i];
      digits[i] = tmpDigit;
    }
    String returnStr = digits[0];
    for (int i = 1; i < num; i++) {
      returnStr = digits[i] + returnStr;
    }
    return returnStr;
  }


  /**
   * 通用提示信息并跳转Action
   * 
   * @param message
   */
  protected void returnPopupMessage(String message) {
    try {
      ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
      ServletActionContext.getResponse().getWriter().write(message);
    } catch (Exception ex) {
      LOG.error("Return popup message{" + message + "} to browser error:", ex);
    }
  }

  /**
   * 通用提示信息并可用于跳转Action(可传值)
   * 
   * @param alerMess 提示消息值
   * @param url 1.跳转地址（XXX.do）2.传值（XXX.do？对象属性=值&对象属性=值....）
   */
  protected void alertMessageBySkip(String alerMess, String url) {
    String messages =
        "<script type='text/javascript'>alert('" + alerMess + "');window.location.href='" + url
            + "';</script>";
    try {
      ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
      ServletActionContext.getResponse().getWriter().write(messages);

    } catch (Exception ex) {
      LOG.error("Return popup message{" + messages + "} to browser error:", ex);
    }
  }


  /**
   * 通用跳转Action(可传值)
   * 
   * @param url 1.跳转地址（XXX.do）2.传值（XXX.do？对象属性=值&对象属性=值....）
   * 
   */
  protected void skipAction(String url) {

    String messages = "<script type='text/javascript'>window.location.href='" + url + "';</script>";
    try {
      ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
      ServletActionContext.getResponse().getWriter().write(messages);

    } catch (Exception ex) {
      LOG.error("Return popup message{" + messages + "} to browser error:", ex);
    }

  }

  /**
   * 通用提示信息
   * 
   * @param message 提示消息
   */
  protected void alertMessage(String alerMess) {
    String messages = "<script type='text/javascript'>alert('" + alerMess + "');</script>";
    try {
      ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
      ServletActionContext.getResponse().getWriter().write(messages);

    } catch (Exception ex) {
      LOG.error("Return popup message{" + messages + "} to browser error:", ex);
    }
  }

  protected void alertMessageByClose(String alerMess) {
    String messages =
        "<script type='text/javascript'>alert('" + alerMess + "'); this.window.close();</script>";
    try {
      ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
      ServletActionContext.getResponse().getWriter().write(messages);

    } catch (Exception ex) {
      LOG.error("Return popup message{" + messages + "} to browser error:", ex);
    }
  }



  /**
   * 获取业务主键
   * 
   * @return
   */
  protected String getPrimarykey() {
    String primarykey = DateTimeUtil.getDateTime("yyyyMMddHHmmssSSS");
    if (StringUtils.isNotEmpty(primarykey) && primarykey.length() > 30) {
      primarykey = primarykey.substring(0, 30);
    }
    return primarykey;
  }

  /**
   * 格式化日期
   * 
   * @param dateStr yyyy-MM-dd
   * @return retStr yyyyMMdd
   */
  protected String formatDateStr(String dateStr) {
    String retStr = null;
    if (StringUtils.isNotEmpty(dateStr)) {
      if (dateStr.indexOf("-") > -1) {
        retStr = dateStr.replace("-", "");
      }
    }
    return retStr;
  }



  protected Date stringToDate(String dateStr) {
    DateFormat dd = new SimpleDateFormat(formatStr);
    Date date = null;
    try {
      date = dd.parse(dateStr);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return date;
  }



}
