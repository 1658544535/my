/*
 * 文 件 名: FocusSettingAction.java 创 建 人: admin 创建时间: 2016-09-21
 */
package com.tzmb2c.web.action;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.FocusSettingPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.FocusSettingService;

public class FocusSettingAction extends SuperAction {
  @Autowired
  private FocusSettingService focusSettingService;
  private FocusSettingPojo focusSettingPojo;
  private Long id;
  private String[] tids;
  private String result;
  private File upfile;
  private String upfileFileName;
  private int os;
  private Integer activityFocusType;

  public String getUpfileFileName() {
    return upfileFileName;
  }

  public void setUpfileFileName(String upfileFileName) {
    this.upfileFileName = upfileFileName;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
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

  public FocusSettingPojo getFocusSettingPojo() {
    return focusSettingPojo;
  }

  public void setFocusSettingPojo(FocusSettingPojo focusSettingPojo) {
    this.focusSettingPojo = focusSettingPojo;
  }

  /**
   * 查询全部条数
   */
  public String goFocusSetting() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("type", 1);
      if (focusSettingPojo != null) {
        map.put("title", focusSettingPojo.getTitle());
        map.put("status", focusSettingPojo.getStatus());
      }
      int i = focusSettingService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部记录
   */
  public String focusSettingList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    map.put("type", 1);
    map.put("orderBy", "sorting");
    if (focusSettingPojo != null) {
      map.put("title", focusSettingPojo.getTitle());
      map.put("status", focusSettingPojo.getStatus());
    }
    List<FocusSettingPojo> focusSettingList = null;
    try {
      focusSettingList = focusSettingService.listPage(map);
      JSONArray json = JSONArray.fromObject(focusSettingList);
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  /**
   * 查询活动图片全部条数
   */
  public String goActivityFocusSetting() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("activityFocusSetting", 1);// 用来判断区分是活动顶部图片还是首页顶部图片
      int i = focusSettingService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询活动图片全部记录
   */
  public String activityFocusSettingList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    map.put("activityFocusSetting", 1);// 用来判断区分是活动顶部图片还是首页顶部图片
    List<FocusSettingPojo> focusSettingList = null;
    try {
      focusSettingList = focusSettingService.listPage(map);
      JSONArray json = JSONArray.fromObject(focusSettingList);
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
  public String goAddFocusSetting() throws Exception {
    return SUCCESS;
  }

  /**
   * 跳转活动顶部图片新增页面
   * 
   * @return
   * @throws Exception
   */
  public String goAddActivityFocusSetting() throws Exception {
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
    if (user != null && focusSettingPojo != null) {
      focusSettingPojo.setCreateBy(user.getId());
      focusSettingPojo.setCreateDate(new Date());
      focusSettingPojo.setUpdateBy(user.getId());
      focusSettingPojo.setUpdateDate(new Date());
      // os=1即是活动顶部图片添加的标志
      if (os == 1) {
        focusSettingPojo.setType(activityFocusType);
      } else {
        focusSettingPojo.setType(1);
      }
      // 图片上传
      if (upfile != null) {
        String file_name = getUpfileFileName();
        String image =
            FileUtil.uploadFile(upfile, 0, file_name, "upfiles/focusbanner", false, 300, 300, true,
                true, "");
        focusSettingPojo.setBanner(file_name);
        if (image == null) {
          if (os == 1) {
            FileUtil.alertMessageBySkip("新增失败！", "goAddActivityFocusSetting.do");
          } else {
            FileUtil.alertMessageBySkip("新增失败！", "goAddFocusSetting.do");
          }
          return null;
        } else {
          focusSettingPojo.setBanner(image);
        }
      }
      try {
        focusSettingService.add(focusSettingPojo);
        if (os == 1) {
          FileUtil.alertMessageBySkip("新增成功！", "goActivityFocusSetting.do");
        } else {
          FileUtil.alertMessageBySkip("新增成功！", "goFocusSetting.do");
        }
      } catch (Exception e) {
        e.printStackTrace();
        if (os == 1) {
          FileUtil.alertMessageBySkip("新增失败！", "goAddActivityFocusSetting.do");
        } else {
          FileUtil.alertMessageBySkip("新增失败！", "goAddFocusSetting.do");
        }


      }
    } else {
      if (os == 1) {
        FileUtil.alertMessageBySkip("操作失败！", "goActivityFocusSetting.do");
      } else {
        FileUtil.alertMessageBySkip("操作失败！", "goFocusSetting.do");
      }

    }
    return null;
  }

  /**
   * 编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goEditFocusSetting() throws Exception {
    if (id != null && id > 0) {
      focusSettingPojo = focusSettingService.getById(id);
    }
    return SUCCESS;
  }

  /**
   * 活动编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goEditActivityFocusSetting() throws Exception {
    if (id != null && id > 0) {
      focusSettingPojo = focusSettingService.getById(id);
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
    if (user != null && focusSettingPojo != null) {
      focusSettingPojo.setUpdateBy(user.getId());
      focusSettingPojo.setUpdateDate(new Date());
      if (upfile != null) {
        String file_name = getUpfileFileName();
        String image =
            FileUtil.uploadFile(upfile, 0, file_name, "upfiles/focusbanner", false, 300, 300, true,
                true, "");
        focusSettingPojo.setBanner(file_name);
        if (image == null) {
          if (os == 1) {
            FileUtil.alertMessageBySkip("提交失败！", "goEditActivityFocusSetting.do?id="
                + focusSettingPojo.getId());
          } else {
            FileUtil.alertMessageBySkip("提交失败！",
                "goEditFocusSetting.do?id=" + focusSettingPojo.getId());
          }

          return null;
        } else {
          focusSettingPojo.setBanner(image);
        }
      }
      if (os == 1) {
        focusSettingPojo.setType(activityFocusType);
      }
      try {
        focusSettingService.update(focusSettingPojo);
        if (os == 1) {
          FileUtil.alertMessageBySkip("提交成功！", "goActivityFocusSetting.do");
        } else {
          FileUtil.alertMessageBySkip("提交成功！", "goFocusSetting.do");
        }
      } catch (Exception e) {
        e.printStackTrace();
        if (os == 1) {
          FileUtil.alertMessageBySkip("提交失败！", "goEditActivityFocusSetting.do?id="
              + focusSettingPojo.getId());
        } else {
          FileUtil.alertMessageBySkip("提交失败！",
              "goEditFocusSetting.do?id=" + focusSettingPojo.getId());
        }

      }
    } else {
      if (os == 1) {
        FileUtil.alertMessageBySkip("操作失败！", "goActivityFocusSetting.do");
      } else {
        FileUtil.alertMessageBySkip("操作失败！", "goFocusSetting.do");
      }
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
    result = "0";
    if (user != null && id != null && id > 0) {
      focusSettingPojo = new FocusSettingPojo();
      focusSettingPojo.setId(id);
      focusSettingPojo.setStatus(1);
      focusSettingPojo.setUpdateBy(user.getId());
      focusSettingPojo.setUpdateDate(new Date());
      try {
        focusSettingService.update(focusSettingPojo);
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
      focusSettingPojo = new FocusSettingPojo();
      focusSettingPojo.setId(id);
      focusSettingPojo.setStatus(0);
      focusSettingPojo.setUpdateBy(user.getId());
      focusSettingPojo.setUpdateDate(new Date());
      try {
        focusSettingService.update(focusSettingPojo);
        result = "1";
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return SUCCESS;
  }

  /**
   * 根据id删除
   * 
   * @return
   */
  public String delete() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    result = "0";
    if (user != null && id != null && id > 0) {
      try {
        focusSettingService.delete(id);
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
      focusSettingPojo = new FocusSettingPojo();
      focusSettingPojo.setStatus(1);
      for (String tid : tids) {
        focusSettingPojo.setId(Long.valueOf(tid));
        focusSettingPojo.setUpdateBy(user.getId());
        focusSettingPojo.setUpdateDate(new Date());
        try {
          focusSettingService.update(focusSettingPojo);
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
      focusSettingPojo = new FocusSettingPojo();
      focusSettingPojo.setStatus(0);
      for (String tid : tids) {
        focusSettingPojo.setId(Long.valueOf(tid));
        focusSettingPojo.setUpdateBy(user.getId());
        focusSettingPojo.setUpdateDate(new Date());
        try {
          focusSettingService.update(focusSettingPojo);
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
    result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      for (String tid : tids) {
        try {
          focusSettingService.delete(Long.valueOf(tid));
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

  public int getOs() {
    return os;
  }

  public void setOs(int os) {
    this.os = os;
  }

  public Integer getActivityFocusType() {
    return activityFocusType;
  }

  public void setActivityFocusType(Integer activityFocusType) {
    this.activityFocusType = activityFocusType;
  }
}
