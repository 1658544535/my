package com.tzmb2c.web.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.AccountSecurityPojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.SysSecurityQuestionPojo;
import com.tzmb2c.web.service.AccountSecurityService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysSecurityQuestionService;

/**
 * 安全问题Action 2014-12-15
 * 
 * @author creazylee
 */
public class AccountSecurityAction extends SuperAction {

  private String[] tids;
  private String result;
  private String type;
  @Autowired
  private AccountSecurityService accountSecurityService;
  @Autowired
  private SysSecurityQuestionService sysSecurityQuestionService;
  @Autowired
  private SysDictService sysDictService;
  private AccountSecurityPojo accountSecurityPojo;
  private SysSecurityQuestionPojo sysSecurityQuestionPojo;
  private List<AccountSecurityPojo> securityQuesionList = null;
  private List<SysSecurityQuestionPojo> sysSecurityQuestionList = null;
  private List<SysDictPojo> securityQuesionTypeList = null;
  private SysLoginPojo sysLoginPojo = null;
  private List<AccountSecurityPojo> userQuestionList = null;
  private AccountSecurityPojo userSecurityQuestion1, userSecurityQuestion2, userSecurityQuestion3;

  public AccountSecurityPojo getUserSecurityQuestion1() {
    return userSecurityQuestion1;
  }

  public void setUserSecurityQuestion1(AccountSecurityPojo userSecurityQuestion1) {
    this.userSecurityQuestion1 = userSecurityQuestion1;
  }

  public AccountSecurityPojo getUserSecurityQuestion2() {
    return userSecurityQuestion2;
  }

  public void setUserSecurityQuestion2(AccountSecurityPojo userSecurityQuestion2) {
    this.userSecurityQuestion2 = userSecurityQuestion2;
  }

  public AccountSecurityPojo getUserSecurityQuestion3() {
    return userSecurityQuestion3;
  }

  public void setUserSecurityQuestion3(AccountSecurityPojo userSecurityQuestion3) {
    this.userSecurityQuestion3 = userSecurityQuestion3;
  }

  public List<AccountSecurityPojo> getSecurityQuesionList() {
    return securityQuesionList;
  }

  public void setSecurityQuesionList(List<AccountSecurityPojo> securityQuesionList) {
    this.securityQuesionList = securityQuesionList;
  }

  public SysSecurityQuestionPojo getSysSecurityQuestionPojo() {
    return sysSecurityQuestionPojo;
  }

  public void setSysSecurityQuestionPojo(SysSecurityQuestionPojo sysSecurityQuestionPojo) {
    this.sysSecurityQuestionPojo = sysSecurityQuestionPojo;
  }

  public List<SysSecurityQuestionPojo> getSysSecurityQuestionList() {
    return sysSecurityQuestionList;
  }

  public void setSysSecurityQuestionList(List<SysSecurityQuestionPojo> sysSecurityQuestionList) {
    this.sysSecurityQuestionList = sysSecurityQuestionList;
  }

  public List<SysDictPojo> getSecurityQuesionTypeList() {
    return securityQuesionTypeList;
  }

  public void setSecurityQuesionTypeList(List<SysDictPojo> securityQuesionTypeList) {
    this.securityQuesionTypeList = securityQuesionTypeList;
  }

  public AccountSecurityService getAccountSecurityService() {
    return accountSecurityService;
  }

  public void setAccountSecurityService(AccountSecurityService accountSecurityService) {
    this.accountSecurityService = accountSecurityService;
  }

  public AccountSecurityPojo getAccountSecurityPojo() {
    return accountSecurityPojo;
  }

  public void setAccountSecurityPojo(AccountSecurityPojo accountSecurityPojo) {
    this.accountSecurityPojo = accountSecurityPojo;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  /***
   * 查找数据字典
   */
  private void getList() {
    try {
      securityQuesionTypeList = sysDictService.getSysDictListByType("status");
    } catch (Exception e) {

      e.printStackTrace();
    }
  }

  /***
   * 查找所有安全问题记录
   * 
   * @return
   */
  public String securityQuesionAllList() {
    getList();
    securityQuesionList =
        accountSecurityService.securityQuesionAllList(accountSecurityPojo, page, type);
    JSONArray json = JSONArray.fromObject(securityQuesionList);
    page.setResult(json.toString());
    return SUCCESS;
  }

  /***
   * 查找所有安全问题数目
   * 
   * @return
   * @throws Exception
   */
  public String getSecurityQuesionCount() throws Exception {
    getList();
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(accountSecurityService.securityQuesionAllCount(accountSecurityPojo, type));
    if ("1".equals(type)) {
      return "purchaser";// 采购商
    }
    return SUCCESS;// 供应商
  }

  /**
   * 
   * @return
   * @throws Exception
   */
  public String getSecurityQuesionRowCount() throws Exception {
    getList();
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(accountSecurityService.securityQuesionAllCount(accountSecurityPojo, type));
    // 局部刷新是在当前页面,so不需要跳转页面
    // if ("1".equals(type)) {
    // return "purchaser";// 采购商
    // }
    return SUCCESS;
  }

  /***
   * 删除单条安全问题
   * 
   * @return
   * @throws SQLException
   */
  public String delSecurityQuesion() throws SQLException {
    try {
      accountSecurityService.delQuesion(accountSecurityPojo);
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  /***
   * 删除全部安全问题
   * 
   * @return
   */
  public String delAllSecurityQuesionById() {
    accountSecurityService.delAllQuesionById(tids);
    if (type.equals("1")) {
      FileUtil.alertMessageBySkip("删除成功！", "accountSecurityManage.do?type=1");
    }
    if (type.equals("2")) {
      FileUtil.alertMessageBySkip("删除成功！", "accountSecurityManage.do?type=2");
    }
    return null;
  }

  /***
   * 审核单条安全问题
   * 
   * @return
   * @throws SQLException
   */
  public String verifySecurityQuesion() throws SQLException {
    try {
      accountSecurityService.verifySecurityQuesion(accountSecurityPojo);
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  /**
   * 审核全部安全问题
   * 
   * @return
   */
  public String verifyAllSecurityQuesion() {
    accountSecurityService.checkAllQuesionById(tids);
    if (type.equals("1")) {
      FileUtil.alertMessageBySkip("审核成功！", "accountSecurityManage.do?type=1");
    }
    if (type.equals("2")) {
      FileUtil.alertMessageBySkip("审核成功！", "accountSecurityManage.do?type=2");
    }
    return null;
  }

  // 前端页面调用
  /**
   * 采购商密保问题设置
   * 
   * @return 密保问题的设置页面
   */
  public String goToSetAccountSecurityQuestion() {
    try {
      // 查询安全问题表
      SysLoginPojo loginPojo = UserUtil.getWebUser();
      securityQuesionList =
          accountSecurityService.securityQuesionbyUserId(loginPojo.getId().toString());
      sysSecurityQuestionList =
          sysSecurityQuestionService.sysSecurityQuestionListWeb(sysSecurityQuestionPojo);
    } catch (SQLException e) {

      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 供应商密保问题设置
   * 
   * @return 密保问题的设置页面
   */
  public String goToSetManufacturerAccountSecurityQuestion() {
    try {
      // 查询安全问题表
      SysLoginPojo loginPojo = UserUtil.getWebUser();
      securityQuesionList =
          accountSecurityService.securityQuesionbyUserId(loginPojo.getId().toString());
      sysSecurityQuestionList =
          sysSecurityQuestionService.sysSecurityQuestionListWeb(sysSecurityQuestionPojo);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /***
   * 设置密保问题
   * 
   * @return
   * @throws IOException
   * @throws SQLException
   */
  public String setAccountSecurityQuestion() throws IOException, SQLException {
    ActionContext actionContext = ActionContext.getContext();
    sysLoginPojo = (SysLoginPojo) actionContext.getSession().get("wuser");
    Long UID = sysLoginPojo.getId();
    // 管理类型：0.管理员1.普通用户2.供应商3.采购商
    securityQuesionList = accountSecurityService.securityQuesionbyUserId(UID.toString());
    String userType = sysLoginPojo.getType();
    if (securityQuesionList.isEmpty()) {
      // 问题1
      // userSecurityQuestion1 = new AccountSecurityPojo();
      userSecurityQuestion1.setUserId(UID);
      if (userType.equals("2")) {
        userSecurityQuestion1.setType(2);
      } else if (userType.equals("3")) {
        userSecurityQuestion1.setType(1);
      }
      userSecurityQuestion1.setStatus(0);
      userSecurityQuestion1.setCreateBy(UID);
      userSecurityQuestion1.setCreateDate(new Date());
      userSecurityQuestion1.setQuesion(userSecurityQuestion1.getQuesion());
      userSecurityQuestion1.setAnswer(userSecurityQuestion1.getAnswer());
      // 问题2
      // userSecurityQuestion2 = new AccountSecurityPojo();
      userSecurityQuestion2.setUserId(UID);
      if (userType.equals("2")) {
        userSecurityQuestion2.setType(2);
      } else if (userType.equals("3")) {
        userSecurityQuestion2.setType(1);
      }
      userSecurityQuestion2.setStatus(0);
      userSecurityQuestion2.setCreateBy(UID);
      userSecurityQuestion2.setCreateDate(new Date());
      userSecurityQuestion2.setQuesion(userSecurityQuestion2.getQuesion());
      userSecurityQuestion2.setAnswer(userSecurityQuestion2.getAnswer());
      // 问题3
      // userSecurityQuestion3 = new AccountSecurityPojo();
      userSecurityQuestion3.setUserId(UID);
      if (userType.equals("2")) {
        userSecurityQuestion3.setType(2);
      } else if (userType.equals("3")) {
        userSecurityQuestion3.setType(1);
      }
      userSecurityQuestion3.setStatus(0);
      userSecurityQuestion3.setCreateBy(UID);
      userSecurityQuestion3.setCreateDate(new Date());
      userSecurityQuestion3.setQuesion(userSecurityQuestion3.getQuesion());
      userSecurityQuestion3.setAnswer(userSecurityQuestion3.getAnswer());

      userQuestionList = new ArrayList<AccountSecurityPojo>();// list初始化
      userQuestionList.add(userSecurityQuestion1);
      userQuestionList.add(userSecurityQuestion2);
      userQuestionList.add(userSecurityQuestion3);
      accountSecurityService.insertUserQuestion(userQuestionList);// 插入密保问题数据

      if (userType.equals("2")) {
        FileUtil.alertMessageBySkip("操作成功！", "gomySupplier.do");// 供应商
      } else if (userType.equals("3")) {
        FileUtil.alertMessageBySkip("操作成功！", "myConsumerWeb.do");// 采购商
      }
    } else {
      if (userType.equals("2")) {
        FileUtil.alertMessageBySkip("你已经设置安全问题请不要重复设置！", "goSetAccountSecurityQuestion.do");// 供应商
      } else if (userType.equals("3")) {
        FileUtil.alertMessageBySkip("你已经设置安全问题请不要重复设置！",
            "goToSetManufacturerAccountSecurityQuestion.do");// 采购商
      }
    }
    return null;
  }

}
