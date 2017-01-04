package com.tzmb2c.web.action;

import java.io.File;
import java.sql.SQLException;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserInfoPojo;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.UserInfoService;

public class UserInfoAction extends SuperAction {

  @Autowired
  private UserInfoService userInfoService;
  @Autowired
  private SysDictService sysDictService;
  private UserInfoPojo userInfo;
  private String result;
  private String[] tids;
  private File upfile;
  private String os, beganday;

  public String getBeganday() {
    return beganday;
  }

  public void setBeganday(String beganday) {
    this.beganday = beganday;
  }

  public String getOs() {
    return os;
  }

  public void setOs(String os) {
    this.os = os;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public UserInfoPojo getUserInfo() {
    return userInfo;
  }

  public void setUserInfo(UserInfoPojo userInfo) {
    this.userInfo = userInfo;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public String getUserInfoCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    ActionContext ac = ActionContext.getContext();
    ac.put("mainCategory", sysDictService.getSysDictListByType("main_category"));
    page.setRowCount(userInfoService.userInfoAllCount(userInfo));
    return SUCCESS;
  }

  public String userInfoAllList() {
    JSONArray json = JSONArray.fromObject(userInfoService.userInfoAllList(userInfo, page));
    page.setResult(json.toString());

    return SUCCESS;
  }

  public String userInfoChecks() {
    if (tids != null) {
      userInfoService.userInfoChecks(tids);
      FileUtil.alertMessageBySkip("审核成功！", "userInfo.do");
    } else {
      FileUtil.alertMessageBySkip("审核失败！", "userInfo.do");
    }

    return null;
  }

  public String checkUserInfo() throws SQLException {
    try {
      userInfoService.checkUserInfo(userInfo.getId());
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  public String goFindUserInfo() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("userInfoPojo", userInfoService.findUserInfoByUserId(userInfo.getUserId()));
    ac.put("sex", sysDictService.getSysDictListByType("sex"));
    ac.put("status", sysDictService.getSysDictListByType("status"));
    ac.put("scale", sysDictService.getSysDictListByType("scale"));
    ac.put("channel", sysDictService.getSysDictListByType("channel"));
    ac.put("isread", sysDictService.getSysDictListByType("isread"));
    ac.put("mainCategory", sysDictService.getSysDictListByType("main_category"));
    return SUCCESS;
  }

  public String updateUserInfo() throws Throwable {
    if (upfile != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/userInfo")
              + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/userInfo/", upfile);
      userInfo.setQrCode(file_name);
    }
    if (beganday != null) {
      userInfo.setBirthday(beganday);
    }

    SysLoginPojo loginPojo = UserUtil.getUser();
    if (UserUtil.getUser() == null) {
      return "loginpage";
    } else {
      userInfo.preUpdate(loginPojo);
    }
    userInfoService.updateUserInfo(userInfo);
    if (os.equals("3")) {
      FileUtil.alertMessageBySkip("修改成功！", "sysLoginMF.do?os=" + os);
    } else if (os.equals("2")) {
      FileUtil.alertMessageBySkip("修改成功！", "sysLoginCS.do?os=" + os);
    } else {
      FileUtil.alertMessageBySkip("修改成功！", "sysLogin.do?os=" + os);
    }


    return null;
  }

  public String delUserInfo() throws Exception {

    userInfoService.delUserInfo(userInfo.getId());
    FileUtil.alertMessageBySkip("删除成功！", "userInfo.do");

    return null;
  }

  public String getJinWanHaoCount() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    ActionContext ac = ActionContext.getContext();
    ac.put("mainCategory", sysDictService.getSysDictListByType("main_category"));
    page.setRowCount(userInfoService.jinwanhaoAllCount(userInfo));
    return SUCCESS;
  }

  public String jinWanHaoAllList() {
    JSONArray json = JSONArray.fromObject(userInfoService.jinwanhaoAllList(userInfo, page));
    page.setResult(json.toString());

    return SUCCESS;
  }
}
