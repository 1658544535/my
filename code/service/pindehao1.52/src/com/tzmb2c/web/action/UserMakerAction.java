package com.tzmb2c.web.action;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.tencent.common.MD5;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.MD5Util;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.mapper.SysLoginMapper;
import com.tzmb2c.web.mapper.UserInfoMapper;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserGrowthLinePojo;
import com.tzmb2c.web.pojo.UserInfoPojo;
import com.tzmb2c.web.service.SysLoginService;
import com.tzmb2c.web.service.UserGrowthLineService;
import com.tzmb2c.web.service.UserInfoService;
import com.tzmb2c.web.service.UserMakerService;

public class UserMakerAction extends SuperAction {
  @Autowired
  private UserMakerService userMakerService;
  @Autowired
  private SysLoginMapper sysLoginMapper;
  @Autowired
  private UserInfoService userInfoService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private UserInfoMapper userInfoMapper;
  @Autowired
  private UserGrowthLineService userGrowthLineService;

  private SysLoginPojo sysLoginPojo;
  private List<SysLoginPojo> sysLoginPojoList;
  private UserInfoPojo userInfoPojo;
  private String result;
  private Long babySex;
  private String babyBirth;
  private File logo;

  public File getLogo() {
    return logo;
  }

  public void setLogo(File logo) {
    this.logo = logo;
  }

  public Long getBabySex() {
    return babySex;
  }

  public void setBabySex(Long babySex) {
    this.babySex = babySex;
  }

  public String getBabyBirth() {
    return babyBirth;
  }

  public void setBabyBirth(String babyBirth) {
    this.babyBirth = babyBirth;
  }

  public UserInfoPojo getUserInfoPojo() {
    return userInfoPojo;
  }

  public void setUserInfoPojo(UserInfoPojo userInfoPojo) {
    this.userInfoPojo = userInfoPojo;
  }

  public List<SysLoginPojo> getSysLoginPojoList() {
    return sysLoginPojoList;
  }

  public void setSysLoginPojoList(List<SysLoginPojo> sysLoginPojoList) {
    this.sysLoginPojoList = sysLoginPojoList;
  }

  public SysLoginPojo getSysLoginPojo() {
    return sysLoginPojo;
  }

  public void setSysLoginPojo(SysLoginPojo sysLoginPojo) {
    this.sysLoginPojo = sysLoginPojo;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  /**
   * 查询全部条数
   */
  public String userMakerListCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (sysLoginPojo != null) {
        map.put("name", sysLoginPojo.getName());
        map.put("loginname", sysLoginPojo.getLoginname());
        map.put("status", sysLoginPojo.getStatus());
      }
      int i = userMakerService.findUserMakerCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部内容
   */
  public String userMakerListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (sysLoginPojo != null) {
        map.put("name", sysLoginPojo.getName());
        map.put("loginname", sysLoginPojo.getLoginname());
        map.put("status", sysLoginPojo.getStatus());
      }
      sysLoginPojoList = userMakerService.findUserMakerList(map);
      JSONArray json = JSONArray.fromObject(sysLoginPojoList);
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 根据id删除
   * 
   * @return
   */
  public String delUserMaker() throws Exception {
    try {
      userMakerService.delUserMaker(sysLoginPojo.getId());
      FileUtil.alertMessageBySkip("删除成功！", "userMaker.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("删除失败！", "userMaker.do");
    }
    return null;
  }

  /**
   * 跳转编辑标签页面
   * 
   * @return
   * @throws Throwable
   */
  public String goUpdateUserMaker() throws Exception {
    if (sysLoginPojo != null) {
      sysLoginPojo = userMakerService.findUserMakerById(sysLoginPojo.getId());
    }
    return SUCCESS;
  }

  /**
   * 编辑标签
   * 
   * @return
   * @throws Throwable
   */
  public String updateUserMaker() throws Throwable {
    try {
      if (sysLoginPojo != null) {
        int i = sysLoginMapper.getCountByLoginName(sysLoginPojo.getLoginname());
        if (i > 0) {
          FileUtil.alertMessageBySkip("不可以添加相同的账号，请重新编辑！", "userMaker.do");
          return null;
        }
      }
      sysLoginPojo.setPassword(MD5.MD5Encode(sysLoginPojo.getPassword()));
      userMakerService.updateUserMaker(sysLoginPojo);
      FileUtil.alertMessageBySkip("编辑成功！", "userMaker.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("编辑失败！", "userMaker.do");
    }
    return null;
  }

  /**
   * 跳转新增页面
   * 
   * @return
   * @throws Exception
   */

  public String goAddUserMaker() throws Exception {
    return SUCCESS;
  }

  /**
   * 新增标签
   * 
   * @return
   * @throws Throwable
   */
  public String addUserMaker() throws Throwable {
    try {
      if (sysLoginPojo != null) {
        int i = sysLoginMapper.getCountByLoginName(sysLoginPojo.getLoginname());
        if (i > 0) {
          FileUtil.alertMessageBySkip("不可以添加相同的账号，请重新添加！", "goAddUserMaker.do");
          return null;
        }
      }
      sysLoginPojo.setPassword(MD5Util.MD5(sysLoginPojo.getPassword()));
      sysLoginPojo.setExternalSignCode(sysLoginService.genExternalSignCode(sysLoginPojo
          .getLoginname()));
      sysLoginPojo
          .setInvitationCode(sysLoginService.genInvitationCode(sysLoginPojo.getLoginname()));
      userMakerService.insertUserMaker(sysLoginPojo);
      UserInfoPojo userInfoPojo = new UserInfoPojo();
      userInfoPojo.setUserId(sysLoginPojo.getId());
      userInfoPojo.setSex(1);
      userInfoPojo.setChannel(0);
      userInfoPojo.setStatus(1);
      userInfoPojo.setBabySex(Integer.valueOf(Long.toString(babySex)));
      userInfoPojo.setBabyBirthday(babyBirth);
      Date now = new Date();
      userInfoPojo.setUpdateDate(now);
      userInfoPojo.setCreateDate(now);
      userInfoService.insertUserInfo(userInfoPojo);
      UserGrowthLinePojo userGrowthLinePojo = new UserGrowthLinePojo();
      userGrowthLinePojo.setUserId(sysLoginPojo.getId());
      userGrowthLineService.addUserGrowthLine(userGrowthLinePojo);
      FileUtil.alertMessageBySkip("新增成功！", "userMaker.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("新增失败！", "userMaker.do");
    }
    return null;
  }

  /**
   * 根据id审核
   * 
   * @return
   */

  public String checkUserMaker() throws Exception {
    try {
      userInfoPojo = userInfoMapper.getfindByUserIdUserInfo(sysLoginPojo.getId());
      if (userInfoPojo == null) {
        FileUtil.alertMessageBySkip("未查询到该用户的信息，请向后台技术人员咨询！", "userMaker.do");
        return null;
      } else {
        userInfoService.checkUserInfo(userInfoPojo.getId());
      }
      userMakerService.checkUserMaker(sysLoginPojo.getId());
      FileUtil.alertMessageBySkip("此条审核成功！", "userMaker.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("此条审核未能成功！", "userMaker.do");
    }
    return null;
  }

  /**
   * 根据id取审
   * 
   * @return
   */

  public String uncheckUserMaker() throws Exception {
    try {
      userInfoPojo = userInfoMapper.getfindByUserIdUserInfo(sysLoginPojo.getId());
      if (userInfoPojo == null) {
        FileUtil.alertMessageBySkip("未查询到该用户的信息，请向后台技术人员咨询！", "userMaker.do");
        return null;
      } else {
        userInfoService.uncheckUserInfo(userInfoPojo.getId());
      }
      userMakerService.uncheckUserMaker(sysLoginPojo.getId());
      FileUtil.alertMessageBySkip("此条取消审核！", "userMaker.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("此条审核未能失败！", "userMaker.do");
    }
    return null;
  }

  /**
   * 重置密码
   * 
   * @return
   */
  public String resetPassword() throws Exception {
    try {
      sysLoginPojo.setPassword(MD5Util.MD5("123456"));
      userMakerService.updateUserMaker(sysLoginPojo);
      setResult("1");
    } catch (Exception e) {
      setResult("0");
    }
    return SUCCESS;
  }

  /**
   * 信息修改
   * 
   * @return
   */
  public String goEditMaker() throws Exception {
    if (sysLoginPojo != null && sysLoginPojo.getId() != null && sysLoginPojo.getId() > 0) {
      sysLoginPojo = sysLoginService.findSysLoginById(sysLoginPojo.getId());
    }
    return SUCCESS;
  }

  /**
   * 信息修改提交
   * 
   * @return
   */
  public String editMaker() throws Exception {
    if (sysLoginPojo != null && sysLoginPojo.getId() != null && sysLoginPojo.getId() > 0) {
      if (logo != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userlogo")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userlogo/", logo);
        sysLoginPojo.setImage(file_name);
        sysLoginService.updateSysLogin(sysLoginPojo);
        FileUtil.alertMessageBySkip("修改成功!", "userMaker.do");
      } else {
        FileUtil.alertMessageBySkip("头像未修改!", "goEditMaker.do");
      }
    } else {
      FileUtil.alertMessageBySkip("修改失败!", "userMaker.do?sysLoginPojo.id="
          + sysLoginPojo.getId());
    }
    return null;
  }
}
