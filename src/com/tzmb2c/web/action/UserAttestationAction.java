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
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserAttestationPojo;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.UserAttestationService;

public class UserAttestationAction extends SuperAction {

  private File upfile, upfile2;
  private String upfileFileName;
  private String upfileContentType;
  private String[] tids;
  private String type;
  private int userType;
  private String result;
  private int item;
  private String url;
  private Long userId;// 用户ID
  @Autowired
  private UserAttestationService userAttestationService;
  @Autowired
  private SysDictService sysDictService;
  private UserAttestationPojo userAttestationPojo;
  private List<UserAttestationPojo> userAttestationList = null;
  private List<SysDictPojo> attestationStatusList = null;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public List<SysDictPojo> getAttestationStatusList() {
    return attestationStatusList;
  }

  public void setAttestationStatusList(List<SysDictPojo> attestationStatusList) {
    this.attestationStatusList = attestationStatusList;
  }

  public UserAttestationService getUserAttestationService() {
    return userAttestationService;
  }

  public void setUserAttestationService(UserAttestationService userAttestationService) {
    this.userAttestationService = userAttestationService;
  }

  public UserAttestationPojo getUserAttestationPojo() {
    return userAttestationPojo;
  }

  public void setUserAttestationPojo(UserAttestationPojo userAttestationPojo) {
    this.userAttestationPojo = userAttestationPojo;
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

  public File getUpfile2() {
    return upfile2;
  }

  public void setUpfile2(File upfile2) {
    this.upfile2 = upfile2;
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

  public Long getUser_id(Long userId) {
    return userId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  /***
   * 查找数据字典
   */
  private void getList() {
    try {
      attestationStatusList = sysDictService.getSysDictListByType("status");
    } catch (Exception e) {

      e.printStackTrace();
    }
  }

  /***
   * 查找所有的认证信息
   * 
   * @return
   */
  public String userAttestationAllList() {
    getList();
    userAttestationList =
        userAttestationService.attestationInfoAllList(userAttestationPojo, page, type);
    JSONArray json = JSONArray.fromObject(userAttestationList);
    page.setResult(json.toString());
    return SUCCESS;
  }

  /***
   * 查找所有认证信息数目
   * 
   * @return
   * @throws Exception
   */
  public String getUserAttestationCount() throws Exception {
    getList();
    if (page == null) {
      page = new Pager();
    }
    page.setRowCount(userAttestationService.attestationInfoAllCount(userAttestationPojo, type));
    return SUCCESS;
  }

  /***
   * 跳转到添加页面
   * 
   * @return
   * @throws Exception
   */
  public String goAddUserAttestation() throws Exception {
    return SUCCESS;
  }

  /***
   * 添加认证信息
   * 
   * @return
   * @throws Throwable
   */
  public String addUserAttestation() throws Throwable {
    userAttestationPojo = new UserAttestationPojo();
    try {
      if (userAttestationService.findAttestationInfoById(userAttestationPojo.getUser_id(userId)) != null) {
        FileUtil.alertMessageBySkip("您已提交过供应商的店铺认证申请，请不要重复申请！", "goAddUserAttestation.do");
      } else {
        try {
          if (upfile != null) {
            String file_name = StringUtil.getCurrentDateStr() + ".jpg";
            String uploadPath =
                ServletActionContext.getServletContext().getRealPath("/upfiles/attestation")
                    + File.separator;
            FileUtil.uploadFile(file_name, uploadPath, "upfiles/attestation/", upfile);
            userAttestationPojo.setAttestationImage(file_name);
          } else {
            userAttestationPojo.setAttestationImage("");
          }
          if (url != null) {
            SysLoginPojo sysLogin = UserUtil.getWebUser();
            userAttestationPojo.setUserId(sysLogin.getId());
          }
          Date createDate = new Date();
          userAttestationPojo.setType(userType);
          userAttestationPojo.setStatus(0);
          userAttestationPojo.setCreateBy(8L);
          userAttestationPojo.setCreateDate(createDate);
          userAttestationService.addAttestationInfo(userAttestationPojo);
        } catch (Exception e) {
          e.printStackTrace();
        }
        if (url != null) {
          FileUtil.alertMessageBySkip("提交成功！", url);
        } else {
          FileUtil.alertMessageBySkip("添加成功！", "goAddUserAttestation.do");
        }

      }
    } catch (SQLException e) {

      e.printStackTrace();
    }
    return null;
  }

  /***
   * 删除单条认证信息
   * 
   * @return
   * @throws SQLException
   */
  public String delUserAttestation() throws SQLException {
    try {
      userAttestationService.delAttestationInfo(userAttestationPojo);
      this.result = "1";
    } catch (Exception e) {
      this.result = "0";
    }
    return SUCCESS;
  }

  /***
   * 删除全部认证信息
   * 
   * @return
   */
  public String delAllUserAttestationById() {
    userAttestationService.delAllAttestationInfoById(tids);
    FileUtil.alertMessageBySkip("删除成功！", "userAttestationManage.do");
    return null;
  }

  /***
   * 查找单条认证信息
   * 
   * @return
   * @throws Exception
   */
  public String goFindUserAttestation() throws Exception {
    if (userAttestationPojo == null) {
      SysLoginPojo sysLogin = UserUtil.getWebUser();
      userAttestationPojo = new UserAttestationPojo();
      userAttestationPojo.setUserId(sysLogin.getId());
    }
    userAttestationPojo =
        userAttestationService.findAttestationInfoById(userAttestationPojo.getUserId());
    return SUCCESS;
  }

  /***
   * 审核单条认证信息
   * 
   * @return
   * @throws SQLException
   */
  public String verifyUserAttestation() throws SQLException {
    userAttestationService.checkAttestationInfo(userAttestationPojo);
    if (type.equals("1")) {
      FileUtil.alertMessageBySkip("审核成功！", "userAttestationManage.do?type=1");
    }
    if (type.equals("2")) {
      FileUtil.alertMessageBySkip("审核成功！", "userAttestationManage.do?type=2");
    }
    return null;
  }

  /***
   * 批量审核认证信息
   * 
   * @return
   */
  public String verifyAllUserAttestation() {
    userAttestationService.checkAllAttestationInfoById(tids);
    if (type.equals("1")) {
      FileUtil.alertMessageBySkip("审核成功！", "userAttestationManage.do?type=1");
    }
    if (type.equals("2")) {
      FileUtil.alertMessageBySkip("审核成功！", "userAttestationManage.do?type=2");
    }
    return null;
  }

  /***
   * 编辑认证信息
   * 
   * @return
   * @throws Throwable
   */

  public String updateUserAttestation() throws Throwable {
    try {
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/attestation")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/attestation/", upfile);
        userAttestationPojo.setAttestationImage(file_name);
      }
      if (upfile2 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/attestation")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/attestation/", upfile2);
        userAttestationPojo.setUser3cImage(file_name);
      }
      Date createDate = new Date();
      userAttestationPojo.setStatus(userAttestationPojo.getStatus());
      userAttestationPojo.setCreateBy(userAttestationPojo.getCreateBy());
      userAttestationPojo.setUserId(userAttestationPojo.getUserId());
      userAttestationPojo.setCreateDate(createDate);
      if (userAttestationPojo.getAttestationImage() != null
          & userAttestationPojo.getUser3cImage() != null) {
        userAttestationService.updateAttestationInfo(userAttestationPojo);
        if (type != null && type.equals("1")) {
          FileUtil.alertMessageBySkip("编辑成功！", "userAttestationManage.do?type=1");
        }
        if (type != null && type.equals("2")) {
          FileUtil.alertMessageBySkip("编辑成功！", "userAttestationManage.do?type=2");
        }
      } else {
        if (type != null && type.equals("1")) {

          FileUtil.alertMessageBySkip("营业执照与3C认证缺一不可，编辑失败！", "userAttestationManage.do?type=1");
        }
        if (type != null && type.equals("2")) {
          FileUtil.alertMessageBySkip("营业执照与3C认证缺一不可，编辑失败！", "userAttestationManage.do?type=2");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (url != null) {
      FileUtil.alertMessageBySkip("提交成功！", url);
    }
    return null;
  }
}
