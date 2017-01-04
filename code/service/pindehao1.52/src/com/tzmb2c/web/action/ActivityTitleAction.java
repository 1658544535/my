package com.tzmb2c.web.action;


import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.ActivityProductPojo;
import com.tzmb2c.web.pojo.ActivityTimePojo;
import com.tzmb2c.web.pojo.ActivityTitlePojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.ActivityGoodsService;
import com.tzmb2c.web.service.ActivityProductService;
import com.tzmb2c.web.service.ActivityTimeService;
import com.tzmb2c.web.service.ActivityTitleService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.SysLoginService;

public class ActivityTitleAction extends SuperAction {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @Autowired
  private ActivityTimeService activityTimeService;
  @Autowired
  private ActivityGoodsService activityGoodsService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private SysLoginService sysLoginService;
  @Autowired
  private ActivityTitleService activityTitleService;
  @Autowired
  private ActivityProductService activityProductService;

  private ActivityTimePojo activityTimePojo;
  private SysLoginPojo sysLogin, sysLogin1;
  private String result;
  private String[] tids;
  private String type;
  private ActivityTitlePojo activityTitlePojo;
  private ActivityProductPojo activityProductPojo;
  private List<SysDictPojo> statusSysDictList = null;
  private File upfile;
  private Long id;
  private File picfile;
  private File picturefile;
  private File photofile;

  public File getPicfile() {
    return picfile;
  }

  public void setPicfile(File picfile) {
    this.picfile = picfile;
  }

  public File getPicturefile() {
    return picturefile;
  }

  public void setPicturefile(File picturefile) {
    this.picturefile = picturefile;
  }

  public File getPhotofile() {
    return photofile;
  }

  public void setPhotofile(File photofile) {
    this.photofile = photofile;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public ActivityTimePojo getActivityTimePojo() {
    return activityTimePojo;
  }

  public void setActivityTimePojo(ActivityTimePojo activityTimePojo) {
    this.activityTimePojo = activityTimePojo;
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

  public SysLoginPojo getSysLogin() {
    return sysLogin;
  }

  public void setSysLogin1(SysLoginPojo sysLogin1) {
    this.sysLogin1 = sysLogin1;
  }

  public SysLoginPojo getSysLogin1() {
    return sysLogin1;
  }

  public void setSysLogin(SysLoginPojo sysLogin) {
    this.sysLogin = sysLogin;
  }

  public ActivityProductPojo getActivityProductPojo() {
    return activityProductPojo;
  }

  public void setActivityProductPojo(ActivityProductPojo activityProductPojo) {
    this.activityProductPojo = activityProductPojo;
  }

  public ActivityTitlePojo getActivityTitlePojo() {
    return activityTitlePojo;
  }

  public void setActivityTitlePojo(ActivityTitlePojo activityTitlePojo) {
    this.activityTitlePojo = activityTitlePojo;
  }

  public List<SysDictPojo> getStatusSysDictList() {
    return statusSysDictList;
  }

  public void setStatusSysDictList(List<SysDictPojo> statusSysDictList) {
    this.statusSysDictList = statusSysDictList;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  /***
   * 查找数据字典
   */
  private void getList() {
    try {
      statusSysDictList = sysDictService.getSysDictListByType("status");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String findActivityTitleList() throws SQLException {
    getList();
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (activityTitlePojo != null) {
      map.put("title", activityTitlePojo.getTitle());
      map.put("status", activityTitlePojo.getStatus());
    }
    map.put("type", type);
    ActionContext ac = ActionContext.getContext();
    ac.put(type, type);
    List<ActivityTitlePojo> list = activityTitleService.findActivityTitleList(map);
    JSONArray json = JSONArray.fromObject(list);
    page.setRowCount(activityTitleService.findActivityTitleCount(map));
    page.setResult(json.toString());
    return SUCCESS;
  }

  public String findActivityTitleCount() throws SQLException {
    getList();
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    if (activityTitlePojo != null) {
      map.put("title", activityTitlePojo.getTitle());
      map.put("status", activityTitlePojo.getStatus());
    }
    map.put("type", type);
    int i = activityTitleService.findActivityTitleCount(map);
    page.setRowCount(i);
    return SUCCESS;
  }

  public String delActivityTitle() {
    try {
      if (activityTitlePojo != null) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("remarks", activityTitlePojo.getId());
        map.put("type", type);
        activityProductService.delProductByTitleId(map);
        activityTitleService.delActivityTitle(activityTitlePojo.getId());
      }
      this.result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      this.result = "0";
    }
    return SUCCESS;
  }

  public String delActivityTitleAll() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    if (tids != null) {
      for (String tid : tids) {
        try {
          map.put("remarks", Long.valueOf(tid));
          map.put("type", type);
          activityProductService.delProductByTitleId(map);
          activityTitleService.delActivityTitle(Long.valueOf(tid));
        } catch (Exception e) {
          e.printStackTrace();
          FileUtil.alertMessageBySkip("删除失败！", "activityTitleManage.do?type=" + type);
        }
      }
      FileUtil.alertMessageBySkip("删除成功！", "activityTitleManage.do?type=" + type);
    } else {
      FileUtil.alertMessageBySkip("请先勾选！", "activityTitleManage.do?type=" + type);
    }

    return null;
  }

  public String checkActivityTitleAll() throws Exception {
    if (tids != null) {
      for (String tid : tids) {
        try {
          activityTitleService.checkActivityTitle(Long.valueOf(tid));
        } catch (Exception e) {
          e.printStackTrace();
          FileUtil.alertMessageBySkip("审核失败！", "activityTitleManage.do?type=" + type);
        }
      }
      FileUtil.alertMessageBySkip("审核成功！", "activityTitleManage.do?type=" + type);
    } else {
      FileUtil.alertMessageBySkip("请先勾选！", "activityTitleManage.do?type=" + type);
    }

    return null;
  }

  /**
   * @return 审核通过
   * @throws SQLException
   */
  public String checkActivityTitle() throws SQLException {
    try {
      activityTitleService.checkActivityTitle(activityTitlePojo.getId());
      this.result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      this.result = "0";
    }
    return SUCCESS;
  }

  /**
   * @return 取消审核
   * @throws SQLException
   */
  public String uncheckActivityTitle() throws SQLException {
    try {
      activityTitleService.uncheckActivityTitle(activityTitlePojo.getId());
      this.result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      this.result = "0";
    }
    return SUCCESS;
  }

  public String findActivityTitleById() throws Exception {
    getList();
    activityTitlePojo = activityTitleService.findActivityTitleById(activityTitlePojo.getId());
    return SUCCESS;
  }

  public void updateActivityTitle() throws Throwable {
    getList();
    if (upfile != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/notice") + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/notice/", upfile);
      activityTitlePojo.setBanner(file_name);
    }
    if (picfile != null) {
      String name_file = StringUtil.getCurrentDateStr() + ".jpg";
      String loadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/notice") + File.separator;
      FileUtil.uploadFile(name_file, loadPath, "upfiles/notice/", picfile);
      activityTitlePojo.setTitlePic(name_file);
    }
    if (picturefile != null) {
      String name_file = StringUtil.getCurrentDateStr() + ".jpg";
      String loadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/notice") + File.separator;
      FileUtil.uploadFile(name_file, loadPath, "upfiles/notice/", picturefile);
      activityTitlePojo.setTitlePicture(name_file);
    }
    if (photofile != null) {
      String name_file = StringUtil.getCurrentDateStr() + ".jpg";
      String loadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/notice") + File.separator;
      FileUtil.uploadFile(name_file, loadPath, "upfiles/notice/", photofile);
      activityTitlePojo.setTitlePhoto(name_file);
    }
    activityTitleService.updateActivityTitle(activityTitlePojo);
    FileUtil.alertMessageBySkip("修改成功！", "activityTitleManage.do?type=" + type);
  }

  public String activityTitleAdd() throws Exception {
    ActionContext ac = ActionContext.getContext();
    ac.put("type", type);
    return SUCCESS;
  }

  public String insertActivityTitle() throws Throwable {
    SysLoginPojo loginPojo = UserUtil.getUser();
    if (UserUtil.getUser() == null) {
      return "loginpage";
    } else {
      loginPojo.prePersist(loginPojo);
    }

    if (upfile != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/notice") + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/notice/", upfile);
      activityTitlePojo.setBanner(file_name);
    }
    if (picfile != null) {
      String name_file = StringUtil.getCurrentDateStr() + ".jpg";
      String loadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/notice") + File.separator;
      FileUtil.uploadFile(name_file, loadPath, "upfiles/notice/", picfile);
      activityTitlePojo.setTitlePic(name_file);
    }
    if (picturefile != null) {
      String name_file = StringUtil.getCurrentDateStr() + ".jpg";
      String loadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/notice") + File.separator;
      FileUtil.uploadFile(name_file, loadPath, "upfiles/notice/", picturefile);
      activityTitlePojo.setTitlePicture(name_file);
    }
    if (photofile != null) {
      String name_file = StringUtil.getCurrentDateStr() + ".jpg";
      String loadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/notice") + File.separator;
      FileUtil.uploadFile(name_file, loadPath, "upfiles/notice/", photofile);
      activityTitlePojo.setTitlePhoto(name_file);
    }
    sysLogin1 = sysLoginService.sysLoginFindId(loginPojo);
    // activityTimePojo = new ActivityTimePojo();
    activityTitlePojo.setCreateBy(sysLogin1.getId());
    activityTitlePojo.setStatus(0);
    activityTitlePojo.setType(type);
    activityTitleService.insertActivityTitle(activityTitlePojo);
    FileUtil.alertMessageBySkip("添加成功！", "activityTitleManage.do?type=" + type);

    return null;
  }

  /**
   * web活动页面.
   * 
   * @return
   * @throws SQLException
   */
  public String goWebView() throws SQLException {
    if (id == null || id == 0) {
      FileUtil.alertMessage("活动不存在！");
      return null;
    }
    ActionContext ac = ActionContext.getContext();
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("type", "5");
    map.put("id", id);
    activityTitlePojo = activityTitleService.getActivityTitleById(map);
    if (activityTitlePojo != null) {
      ac.put("activityTitlePojo", activityTitlePojo);
      Map<String, Object> map1 = new HashMap<String, Object>();
      map1.put("titleId", activityTitlePojo.getId());
      map1.put("type", "5");
      map1.put("productType", "1");
      map1.put("limitNo", 3);
      List<ActivityProductPojo> activityProductList =
          activityProductService.getActivityProductListWeb(map1);
      if (activityProductList != null && activityProductList.size() > 0) {
        ac.put("activityProductList", activityProductList);
      }
      Map<String, Object> map2 = new HashMap<String, Object>();
      map2.put("titleId", activityTitlePojo.getId());
      map2.put("type", "5");
      map2.put("productType", "2");
      map2.put("limitNo", 12);
      List<ActivityProductPojo> activityProductList0 =
          activityProductService.getActivityProductListWeb(map2);
      List<ActivityProductPojo> activityProductList1 = new ArrayList<ActivityProductPojo>();
      List<ActivityProductPojo> activityProductList2 = new ArrayList<ActivityProductPojo>();
      List<ActivityProductPojo> activityProductList3 = new ArrayList<ActivityProductPojo>();
      int size = activityProductList0 != null ? activityProductList0.size() : 0;

      for (int i = 0; i < 4 && i < size; i++) {
        activityProductList1.add(activityProductList0.get(i));
      }
      ac.put("activityProductList1", activityProductList1);

      for (int i = 4; i < 8 && i < size; i++) {
        activityProductList2.add(activityProductList0.get(i));
      }
      ac.put("activityProductList2", activityProductList2);

      for (int i = 8; i < 12 && i < size; i++) {
        activityProductList3.add(activityProductList0.get(i));
      }
      ac.put("activityProductList3", activityProductList3);

      return SUCCESS;
    } else {
      FileUtil.alertMessage("活动已结束，敬请关注下期活动！");
    }
    return null;
  }



}
