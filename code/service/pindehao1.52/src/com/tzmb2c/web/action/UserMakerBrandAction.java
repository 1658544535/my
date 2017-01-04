package com.tzmb2c.web.action;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.UserBrandPojo;
import com.tzmb2c.web.pojo.UserMakerBrandPojo;
import com.tzmb2c.web.service.UserBrandService;
import com.tzmb2c.web.service.UserMakerBrandService;

public class UserMakerBrandAction extends SuperAction {
  @Autowired
  private UserMakerBrandService userMakerBrandService;
  @Autowired
  UserBrandService userBrandService;
  private UserMakerBrandPojo userMakerBrandPojo;
  private List<UserMakerBrandPojo> userMakerBrandPojoList;
  private Long makerBrandId;
  private Long userId;
  private File upfile;
  private File upfile1;

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public File getUpfile1() {
    return upfile1;
  }

  public void setUpfile1(File upfile1) {
    this.upfile1 = upfile1;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getMakerBrandId() {
    return makerBrandId;
  }

  public void setMakerBrandId(Long makerBrandId) {
    this.makerBrandId = makerBrandId;
  }

  public UserMakerBrandPojo getUserMakerBrandPojo() {
    return userMakerBrandPojo;
  }

  public void setUserMakerBrandPojo(UserMakerBrandPojo userMakerBrandPojo) {
    this.userMakerBrandPojo = userMakerBrandPojo;
  }

  public List<UserMakerBrandPojo> getUserMakerBrandPojoList() {
    return userMakerBrandPojoList;
  }

  public void setUserMakerBrandPojoList(List<UserMakerBrandPojo> userMakerBrandPojoList) {
    this.userMakerBrandPojoList = userMakerBrandPojoList;
  }

  /**
   * 查询全部条数
   */
  public String userMakerBrandListCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (userMakerBrandPojo != null) {
        map.put("userName", userMakerBrandPojo.getUserName());
        map.put("brandNames", userMakerBrandPojo.getBrandName());
        map.put("status", userMakerBrandPojo.getStatus());
        map.put("brandType", userMakerBrandPojo.getBrandType());
      }
      int i = userMakerBrandService.userMakerBrandCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }



  /**
   * 查询全部内容
   */
  public String userMakerBrandListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (userMakerBrandPojo != null) {
        map.put("userName", userMakerBrandPojo.getUserName());
        map.put("brandNames", userMakerBrandPojo.getBrandName());
        map.put("status", userMakerBrandPojo.getStatus());
        map.put("brandType", userMakerBrandPojo.getBrandType());
      }
      userMakerBrandPojoList = userMakerBrandService.userMakerBrandList(map);
      JSONArray json = JSONArray.fromObject(userMakerBrandPojoList);
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 跳转审核页面
   * 
   * @return
   */

  public String goCheckUserMakerBrand() throws Exception {
    try {
      if (userMakerBrandPojo != null) {
        userMakerBrandPojo =
            userMakerBrandService.getUserMakerBrandById(userMakerBrandPojo.getId());
      }
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("未能查询到该创客的品牌信息，请咨询后台技术人员！", "userMakerBrand.do");
    }
    return SUCCESS;
  }

  /**
   * 根据id审核成功
   * 
   * @return
   */

  public String checkUserMakerBrand() throws Exception {
    try {
      userMakerBrandService.checkUserMakerBrand(userMakerBrandPojo.getId());
      FileUtil.alertMessageBySkip("审核成功！", "userMakerBrand.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("审核未能成功！", "userMakerBrand.do");
    }
    return null;
  }

  /**
   * 根据id审核失败
   * 
   * @return
   */

  public String uncheckUserMakerBrand() throws Exception {
    try {
      userMakerBrandService.uncheckUserMakerBrand(userMakerBrandPojo.getId());
      FileUtil.alertMessageBySkip("审核失败！", "userMakerBrand.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("审核未能失败！", "userMakerBrand.do");
    }
    return null;
  }

  /**
   * 修改品牌类型
   * 
   * @return
   */

  public String changeTypeUserMakerBrand1() throws Exception {
    try {
      userMakerBrandService.changeTypeUserMakerBrand1(userMakerBrandPojo.getId());
      FileUtil.alertMessageBySkip("修改品牌类型成功！", "userMakerBrand.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("修改品牌类型失败！", "userMakerBrand.do");
    }
    return null;
  }

  /**
   * 修改品牌类型
   * 
   * @return
   */

  public String changeTypeUserMakerBrand2() throws Exception {
    try {
      userMakerBrandService.changeTypeUserMakerBrand2(userMakerBrandPojo.getId());
      FileUtil.alertMessageBySkip("修改品牌类型成功！", "userMakerBrand.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("修改品牌类型失败！", "userMakerBrand.do");
    }
    return null;
  }

  /**
   * 创客品牌webview
   * 
   * @throws SQLException
   * @return String
   */
  public String goMakerBrandView() throws SQLException {
    if (makerBrandId == null || makerBrandId == 0) {
      System.out.println(">>>>>> makerBrandId为空!");
    }
    /*
     * else if (userId == null || userId == 0) { System.out.println(">>>>>> userId为空!"); }
     */
    else {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("id", makerBrandId);
      params.put("status", 1);
      userMakerBrandPojo = userMakerBrandService.findMakerBrandByParams(params);
      if (userMakerBrandPojo == null) {
        System.out.println(">>>>>> makerBrandId=" + makerBrandId + " 查不到!");
      }
    }
    return SUCCESS;
  }

  /**
   * 跳转编辑图文说明页
   * 
   * @return
   */
  public String goUpdateUserMakerContent() throws Exception {
    if (userMakerBrandPojo != null) {
      userMakerBrandPojo = userMakerBrandService.getUserMakerBrandById(userMakerBrandPojo.getId());
    }
    return SUCCESS;
  }

  public String updateUserMakerBrand() throws Exception {
    try {
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerBrand")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userMakerBrand/", upfile);
        userMakerBrandPojo.setLogo(file_name);
      }
      if (upfile1 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerBrand")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userMakerBrand/", upfile1);
        userMakerBrandPojo.setRegistrationCertificate(file_name);
      }

      userMakerBrandService.updateUserMakerBrand(userMakerBrandPojo);
      UserBrandPojo userBrandPojo = new UserBrandPojo();
      if (userMakerBrandPojo.getStatus() == 1) {
        userBrandPojo.setStatus(1);
      } else {
        userBrandPojo.setStatus(0);
      }
      userBrandPojo.setId(userMakerBrandPojo.getBrandId());
      userBrandService.updateUserBrand(userBrandPojo);
      FileUtil.alertMessageBySkip("修改成功！", "userMakerBrand.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("修改失败！", "goUpdateUserMakerContent.do?userMakerBrandPojo.id="
          + userMakerBrandPojo.getId());
    }
    return null;
  }

}
