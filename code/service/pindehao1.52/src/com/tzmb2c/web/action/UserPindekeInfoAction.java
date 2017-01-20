/*
 * 文 件 名: UserPindekeInfoAction.java 创 建 人: admin 创建时间: 2016-10-15
 */
package com.tzmb2c.web.action;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.business.service.WalletService;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserPindekeInfoPojo;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserPindekeInfoService;

public class UserPindekeInfoAction extends SuperAction {
  @Autowired
  private UserPindekeInfoService userPindekeInfoService;
  private UserPindekeInfoPojo userPindekeInfoPojo;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private WalletService walletService;
  private SysLoginPojo sysLoginPojo;
  private Long id;
  private String[] tids;
  private String result;
  private Integer status;
  private String returnMsg;
  private Long userId;
  private File upfile1, upfile2, upfile3, upfile4, upfile5;
  private String upfile1FileName, upfile2FileName, upfile3FileName, upfile4FileName,
      upfile5FileName;
  private Integer isDelete;



  public Integer getIsDelete() {
    return isDelete;
  }

  public void setIsDelete(Integer isDelete) {
    this.isDelete = isDelete;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getReturnMsg() {
    return returnMsg;
  }

  public void setReturnMsg(String returnMsg) {
    this.returnMsg = returnMsg;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public File getUpfile1() {
    return upfile1;
  }

  public void setUpfile1(File upfile1) {
    this.upfile1 = upfile1;
  }

  public File getUpfile2() {
    return upfile2;
  }

  public void setUpfile2(File upfile2) {
    this.upfile2 = upfile2;
  }

  public File getUpfile3() {
    return upfile3;
  }

  public void setUpfile3(File upfile3) {
    this.upfile3 = upfile3;
  }

  public File getUpfile4() {
    return upfile4;
  }

  public void setUpfile4(File upfile4) {
    this.upfile4 = upfile4;
  }

  public File getUpfile5() {
    return upfile5;
  }

  public void setUpfile5(File upfile5) {
    this.upfile5 = upfile5;
  }

  public String getUpfile1FileName() {
    return upfile1FileName;
  }

  public void setUpfile1FileName(String upfile1FileName) {
    this.upfile1FileName = upfile1FileName;
  }

  public String getUpfile2FileName() {
    return upfile2FileName;
  }

  public void setUpfile2FileName(String upfile2FileName) {
    this.upfile2FileName = upfile2FileName;
  }

  public String getUpfile3FileName() {
    return upfile3FileName;
  }

  public void setUpfile3FileName(String upfile3FileName) {
    this.upfile3FileName = upfile3FileName;
  }

  public String getUpfile4FileName() {
    return upfile4FileName;
  }

  public void setUpfile4FileName(String upfile4FileName) {
    this.upfile4FileName = upfile4FileName;
  }

  public String getUpfile5FileName() {
    return upfile5FileName;
  }

  public void setUpfile5FileName(String upfile5FileName) {
    this.upfile5FileName = upfile5FileName;
  }

  public UserPindekeInfoPojo getUserPindekeInfoPojo() {
    return userPindekeInfoPojo;
  }

  public void setUserPindekeInfoPojo(UserPindekeInfoPojo userPindekeInfoPojo) {
    this.userPindekeInfoPojo = userPindekeInfoPojo;
  }

  public SysLoginPojo getSysLoginPojo() {
    return sysLoginPojo;
  }

  public void setSysLoginPojo(SysLoginPojo sysLoginPojo) {
    this.sysLoginPojo = sysLoginPojo;
  }

  /**
   * 查询全部条数
   */
  public String goUserPindekeInfo() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (userPindekeInfoPojo != null) {
        map.put("status", userPindekeInfoPojo.getStatus());
        map.put("nameAndPhone", userPindekeInfoPojo.getName());
        map.put("pindekeDateStr", userPindekeInfoPojo.getPindekeDateStr());
        map.put("pindekeDateEndStr", userPindekeInfoPojo.getPindekeDateEndStr());
      }
      int i = userPindekeInfoService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部条数
   */
  public String userPindekeInfoRowCnt() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      if (userPindekeInfoPojo != null) {
        map.put("status", userPindekeInfoPojo.getStatus());
        map.put("nameAndPhone", userPindekeInfoPojo.getName());
        map.put("pindekeDateStr", userPindekeInfoPojo.getPindekeDateStr());
        map.put("pindekeDateEndStr", userPindekeInfoPojo.getPindekeDateEndStr());
      }
      int i = userPindekeInfoService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部记录
   */
  public String userPindekeInfoList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    List<UserPindekeInfoPojo> userPindekeInfoList = null;
    try {
      if (userPindekeInfoPojo != null) {
        map.put("status", userPindekeInfoPojo.getStatus());
        map.put("nameAndPhone", userPindekeInfoPojo.getName());
        map.put("pindekeDateStr", userPindekeInfoPojo.getPindekeDateStr());
        map.put("pindekeDateEndStr", userPindekeInfoPojo.getPindekeDateEndStr());
      }
      userPindekeInfoList = userPindekeInfoService.listPage(map);
      JSONArray json = JSONArray.fromObject(userPindekeInfoList);
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  /**
   * 跳转新增页面
   * 
   * @return
   * @throws Exception
   */
  public String goAddUserPindekeInfo() throws Exception {
    return SUCCESS;
  }

  /**
   * 新增提交
   * 
   * @return
   * @throws Throwable
   */
  public String add() throws Throwable {
    SysLoginPojo user = UserUtil.getUser();
    if (user != null && userPindekeInfoPojo != null) {
      userPindekeInfoPojo.setCreateBy(user.getId());
      userPindekeInfoPojo.setCreateDate(new Date());
      userPindekeInfoPojo.setUpdateBy(user.getId());
      userPindekeInfoPojo.setUpdateDate(new Date());

      if (upfile1 != null) {
        String orgfileName = getUpfile1FileName();
        String filename =
            FileUtil.uploadFile(upfile1, orgfileName, "/upfiles/userpindeke", false, 400, 400,
                true, true, "");
        userPindekeInfoPojo.setExtendImg1(filename);
      } else {
        userPindekeInfoPojo.setExtendImg1("");
      }

      if (upfile2 != null) {
        String orgfileName = getUpfile2FileName();
        String filename =
            FileUtil.uploadFile(upfile2, orgfileName, "/upfiles/userpindeke", false, 400, 400,
                true, true, "");
        userPindekeInfoPojo.setExtendImg2(filename);
      } else {
        userPindekeInfoPojo.setExtendImg2("");
      }

      if (upfile3 != null) {
        String orgfileName = getUpfile3FileName();
        String filename =
            FileUtil.uploadFile(upfile3, orgfileName, "/upfiles/userpindeke", false, 400, 400,
                true, true, "");
        userPindekeInfoPojo.setExtendImg3(filename);
      } else {
        userPindekeInfoPojo.setExtendImg3("");
      }

      if (upfile4 != null) {
        String orgfileName = getUpfile4FileName();
        String filename =
            FileUtil.uploadFile(upfile4, orgfileName, "/upfiles/userpindeke", false, 400, 400,
                true, true, "");
        userPindekeInfoPojo.setExtendImg4(filename);
      } else {
        userPindekeInfoPojo.setExtendImg4("");
      }

      if (upfile5 != null) {
        String orgfileName = getUpfile5FileName();
        String filename =
            FileUtil.uploadFile(upfile5, orgfileName, "/upfiles/userpindeke", false, 400, 400,
                true, true, "");
        userPindekeInfoPojo.setExtendImg5(filename);
      } else {
        userPindekeInfoPojo.setExtendImg5("");
      }

      try {
        String loginname = userPindekeInfoPojo.getLoginname();
        SysLoginPojo sysLoginPojobyloginname = sysLoginService.getSysLoginByLoginName(loginname);
        if (sysLoginPojobyloginname != null) {
          userPindekeInfoPojo.setUserId(sysLoginPojobyloginname.getId());
          userPindekeInfoService.add(userPindekeInfoPojo);
          FileUtil.alertMessageBySkip("新增成功！", "goUserPindekeInfo.do");
        } else {
          FileUtil.alertMessageBySkip("操作失败！账号不存在！", "goUserPindekeInfo.do");
        }
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("新增失败！", "goAddUserPindekeInfo.do");
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goUserPindekeInfo.do");
    }

    return null;
  }

  /**
   * 编辑页面，查看页面公用方法
   * 
   * @return
   * @throws Exception
   */
  public String goCheckUserPindekeInfo() throws Exception {
    if (id != null && id > 0) {
      userPindekeInfoPojo = userPindekeInfoService.getById(id);
    }
    return SUCCESS;
  }

  /**
   * 编辑提交
   * 
   * @return
   * @throws Throwable
   */
  public String update() throws Throwable {
    SysLoginPojo user = UserUtil.getUser();
    if (user != null && userPindekeInfoPojo != null) {
      userPindekeInfoPojo.setUpdateBy(user.getId());
      userPindekeInfoPojo.setUpdateDate(new Date());
      try {
        userPindekeInfoService.update(userPindekeInfoPojo);
        FileUtil.alertMessageBySkip("提交成功！", "goUserPindekeInfo.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("提交失败！",
            "goEditUserPindekeInfo.do?id=" + userPindekeInfoPojo.getId());
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goUserPindekeInfo.do");
    }

    return null;
  }

  /**
   * 根据id审核
   * 
   * @return
   */
  public String check() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    String code = walletService.genInviteCode();
    Map<String, Object> map = new HashMap<String, Object>();
    result = "0";
    if (user != null && id != null && id > 0) {
      userPindekeInfoPojo = new UserPindekeInfoPojo();
      userPindekeInfoPojo.setId(id);
      userPindekeInfoPojo.setStatus(1);
      userPindekeInfoPojo.setUpdateBy(user.getId());
      userPindekeInfoPojo.setUpdateDate(new Date());
      map.put("invitationCode", code);
      map.put("isPindeke", 1);
      map.put("userId", userId);
      String invitCode = "";
      // 查询拼得客申请时的邀请码
      UserPindekeInfoPojo userPindekeInfo = userPindekeInfoService.findByUserId(userId);
      if (userPindekeInfo != null && StringUtils.isNotEmpty(userPindekeInfo.getInvitationCode())) {
        invitCode = userPindekeInfo.getInvitationCode();
      }
      SysLoginPojo loginPojo = sysLoginService.findSysLoginById(userId);
      // 判断邀请码是否有效
      if ((loginPojo.getInviterId() == null || loginPojo.getInviterId() == 0)
          && StringUtils.isNotBlank(invitCode) && invitCode.length() == 6) {
        SysLoginPojo sysLogin = new SysLoginPojo();
        sysLogin.setInvitationCode(invitCode);
        sysLogin = sysLoginService.getUserIdByInvitationCode(sysLogin);
        if (sysLogin != null && sysLogin.getStatus() == 1 && sysLogin.getIsPindeke() == 1) {
          map.put("inviterId", sysLogin.getId());
        }
      }
      try {
        userPindekeInfoService.update(userPindekeInfoPojo);
        userPindekeInfoService.updateInvitationCode(map);
        result = "1";
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return SUCCESS;
  }

  /**
   * 根据id取审
   * 
   * @return
   */
  public String uncheck() throws Exception {
    SysLoginPojo user = UserUtil.getUser();

    result = "0";
    if (user != null && id != null && id > 0) {
      userPindekeInfoPojo = new UserPindekeInfoPojo();
      userPindekeInfoPojo.setId(id);
      userPindekeInfoPojo.setStatus(2);
      userPindekeInfoPojo.setUpdateBy(user.getId());
      userPindekeInfoPojo.setUpdateDate(new Date());
      try {
        userPindekeInfoService.update(userPindekeInfoPojo);
        result = "1";
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return SUCCESS;
  }

  public String UserPindekeInfoReturnMsg() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("isPindeke", 0);
    map.put("userId", userId);
    result = "0";
    if (user != null && id != null && id > 0 && status != null) {
      userPindekeInfoPojo = new UserPindekeInfoPojo();
      userPindekeInfoPojo.setId(id);
      userPindekeInfoPojo.setStatus(status);
      if (status == 2) {
        userPindekeInfoPojo.setReturnMsg(returnMsg);
      }
      userPindekeInfoPojo.setUpdateBy(user.getId());
      try {
        userPindekeInfoService.update(userPindekeInfoPojo);
        userPindekeInfoService.updateInvitationCode(map);
        result = "1";
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return SUCCESS;
  }

  /**
   * 根据id冻结
   * 
   * @return
   */
  public String frozen() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && id != null && id > 0) {
      userPindekeInfoPojo = new UserPindekeInfoPojo();
      userPindekeInfoPojo.setId(id);
      userPindekeInfoPojo.setStatus(3);
      userPindekeInfoPojo.setUpdateBy(user.getId());
      userPindekeInfoPojo.setUpdateDate(new Date());
      try {
        userPindekeInfoService.update(userPindekeInfoPojo);
        result = "1";
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return SUCCESS;
  }

  /**
   * 根据id删除,只是修改删除状态
   * 
   * @return
   */
  public String delete() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("id", id);
    params.put("isDelete", isDelete);
    result = "0";
    if (user != null && id != null && id > 0) {
      try {
        userPindekeInfoService.delete(params);
        result = "1";
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return SUCCESS;
  }

  /**
   * 审核选中
   * 
   * @return
   */
  public String checkAll() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      userPindekeInfoPojo = new UserPindekeInfoPojo();
      userPindekeInfoPojo.setStatus(1);
      for (String tid : tids) {
        userPindekeInfoPojo.setId(Long.valueOf(tid));
        userPindekeInfoPojo.setUpdateBy(user.getId());
        userPindekeInfoPojo.setUpdateDate(new Date());
        try {
          userPindekeInfoService.update(userPindekeInfoPojo);
        } catch (Exception e) {
          part = true;
          e.printStackTrace();
        }
      }
      if (part) {
        result = "2";
      } else {
        result = "1";
      }
    }
    return SUCCESS;
  }

  /**
   * 选中取消审核
   * 
   * @return
   */
  public String uncheckAll() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      userPindekeInfoPojo = new UserPindekeInfoPojo();
      userPindekeInfoPojo.setStatus(0);
      for (String tid : tids) {
        userPindekeInfoPojo.setId(Long.valueOf(tid));
        userPindekeInfoPojo.setUpdateBy(user.getId());
        userPindekeInfoPojo.setUpdateDate(new Date());
        try {
          userPindekeInfoService.update(userPindekeInfoPojo);
        } catch (Exception e) {
          part = true;
          e.printStackTrace();
        }
      }
      if (part) {
        result = "2";
      } else {
        result = "1";
      }
    }
    return SUCCESS;
  }

  /**
   * 删除选中
   * 
   * @return
   */
  public String deleteAll() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("isDelete", isDelete);
    result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      for (String tid : tids) {
        try {
          params.put("id", Long.valueOf(tid));
          userPindekeInfoService.delete(params);
        } catch (Exception e) {
          part = true;
          e.printStackTrace();
        }
      }
      if (part) {
        result = "2";
      } else {
        result = "1";
      }
    }
    return SUCCESS;
  }

  /**
   * 修改拼得客编号
   * 
   * @return
   * @throws Throwable
   */
  public String updatePindekeNumber() throws Throwable {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && userPindekeInfoPojo != null) {
      userPindekeInfoPojo.setUpdateBy(user.getId());
      try {
        userPindekeInfoService.update(userPindekeInfoPojo);
        result = "1";
      } catch (Exception e) {
        e.printStackTrace();
        result = "0";
      }
    } else {
      result = "0";
    }

    return SUCCESS;
  }
}
