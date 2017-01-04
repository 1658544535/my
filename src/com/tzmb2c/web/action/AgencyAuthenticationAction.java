package com.tzmb2c.web.action;

import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.AgencyAuthenticationPojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.AgencyAuthenticationService;
import com.tzmb2c.web.service.SysDictService;

public class AgencyAuthenticationAction extends SuperAction {

  private File upfile;
  private String upfileFileName;
  private String upfileContentType;
  private String[] tids;
  private String type;
  private int userType;
  private String result;
  private int item;
  private String url;


  @Autowired
  private AgencyAuthenticationService agencyAuthenticationService;
  @Autowired
  private SysDictService sysDictService;

  private AgencyAuthenticationPojo agencyAuthenticationPojo;

  private List<AgencyAuthenticationPojo> authenticationAgencyList = null;
  private List<SysDictPojo> authenticationAuthStatusList = null;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public List<SysDictPojo> getAuthenticationAuthStatusList() {
    return authenticationAuthStatusList;
  }

  public void setAuthenticationAuthStatusList(List<SysDictPojo> authenticationAuthStatusList) {
    this.authenticationAuthStatusList = authenticationAuthStatusList;
  }

  public AgencyAuthenticationService getAgencyAuthenticationService() {
    return agencyAuthenticationService;
  }

  public void setAgencyAuthenticationService(AgencyAuthenticationService agencyAuthenticationService) {
    this.agencyAuthenticationService = agencyAuthenticationService;
  }

  public AgencyAuthenticationPojo getAgencyAuthenticationPojo() {
    return agencyAuthenticationPojo;
  }

  public void setAgencyAuthenticationPojo(AgencyAuthenticationPojo agencyAuthenticationPojo) {
    this.agencyAuthenticationPojo = agencyAuthenticationPojo;
  }

  public int getUserType() {
    return userType;
  }

  public void setUserType(int userType) {
    this.userType = userType;
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

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public String getUpfileContentType() {
    return upfileContentType;
  }

  public void setUpfileContentType(String upfileContentType) {
    this.upfileContentType = upfileContentType;
  }

  public String getUpfileFileName() {
    return upfileFileName;
  }

  public void setUpfileFileName(String upfileFileName) {
    this.upfileFileName = upfileFileName;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public int getItem() {
    return item;
  }

  public void setItem(int item) {
    this.item = item;
  }

  /***
   * 查找数据字典
   */
  private void getList() {
    try {
      authenticationAuthStatusList = sysDictService.getSysDictListByType("auth_status");
    } catch (Exception e) {

      e.printStackTrace();
    }
  }

  /***
   * 查找所有的认证信息
   * 
   * @return
   */
  public String authenticationAgencyAllList() throws SQLException {
    getList();
    authenticationAgencyList =
        agencyAuthenticationService.authenticationAgencyInfoAllList(agencyAuthenticationPojo, page,
            type);
    JSONArray json = JSONArray.fromObject(authenticationAgencyList);
    page.setResult(json.toString());
    return SUCCESS;
  }

  /***
   * 查找所有认证信息数目
   * 
   * @return
   * @throws Exception
   */
  public String getAuthenticationAgencyAllCount() throws Exception {
    getList();
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(agencyAuthenticationService.authenticationAgencyInfoAllCount(
        agencyAuthenticationPojo, type));
    return SUCCESS;
  }

  /***
   * 审核单条认证信息
   * 
   * @return
   * @throws SQLException
   */
  public String verifyAuthenticationAgency() throws SQLException {
    agencyAuthenticationService.checkAuthenticationInfo(agencyAuthenticationPojo);
    if (type.equals("6")) {
      FileUtil.alertMessageBySkip("认证成功！", "authenticationAgencyManage.do?type=6");
    }
    return null;
  }

  /***
   * 编辑认证信息
   * 
   * @return
   * @throws Throwable
   */
  public String updateAuthenticationAgency() throws Throwable {
    try {
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/authentication")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/authentication/", upfile);
        agencyAuthenticationPojo.setAuth_licence(file_name);
      }

      Date create_date = new Date();
      agencyAuthenticationPojo.setAuth_status(agencyAuthenticationPojo.getAuth_status());
      agencyAuthenticationPojo.setCreate_by(agencyAuthenticationPojo.getCreate_by());
      agencyAuthenticationPojo.setUser_id(agencyAuthenticationPojo.getUser_id());
      agencyAuthenticationPojo.setCreate_date(create_date);
      agencyAuthenticationService.updateAuthenticationInfo(agencyAuthenticationPojo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (type != null && type.equals("6")) {
      FileUtil.alertMessageBySkip("编辑成功！", "authenticationAgencyManage.do?type=6");
    }

    if (url != null) {
      FileUtil.alertMessageBySkip("提交成功！", url);
    }

    return null;
  }

  /***
   * 查找单条认证信息
   * 
   * @return
   * @throws Exception
   */
  public String goFindAuthenticationAgency() throws Exception {
    if (agencyAuthenticationPojo == null) {
      SysLoginPojo sysLogin = UserUtil.getUser();
      agencyAuthenticationPojo = new AgencyAuthenticationPojo();
      agencyAuthenticationPojo.setUser_id(sysLogin.getId());
    }
    agencyAuthenticationPojo =
        agencyAuthenticationService.findAuthenticationInfoById(agencyAuthenticationPojo
            .getUser_id());
    return SUCCESS;
  }

  /***
   * 批量审核认证信息
   * 
   * @return
   */
  public String verifyAllAuthenticationAgency() {
    agencyAuthenticationService.checkAllAuthenticationInfoById(tids);
    if (type.equals("6")) {
      FileUtil.alertMessageBySkip("认证成功！", "authenticationAgencyManage.do?type=6");
    }

    return null;
  }

  /***
   * 删除单条认证信息
   * 
   * @return
   * @throws SQLException
   */
  public String delAuthenticationAgency() throws SQLException {
    try {
      agencyAuthenticationService.delAuthenticationInfo(agencyAuthenticationPojo);
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }
}
