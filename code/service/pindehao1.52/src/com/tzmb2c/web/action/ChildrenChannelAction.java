package com.tzmb2c.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
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
import com.tzmb2c.web.pojo.ActivityTimePojo;
import com.tzmb2c.web.pojo.ChildrenChannelPojo;
import com.tzmb2c.web.service.ActivityGoodsService;
import com.tzmb2c.web.service.ActivityTimeService;
import com.tzmb2c.web.service.ChildrenChannelService;
import com.tzmb2c.web.service.SceneProductService;

/**
 * 场景Action 2015-10-17
 * 
 * @author hsy
 */
public class ChildrenChannelAction extends SuperAction {
  @Autowired
  private ChildrenChannelService childrenChannelService;
  @Autowired
  private SceneProductService sceneProductService;
  @Autowired
  private ActivityTimeService activityTimeService;
  @Autowired
  private ActivityGoodsService activityGoodsService;
  private ChildrenChannelPojo childrenChannelPojo;
  private ActivityTimePojo activityTimePojo;
  private String result;
  private String[] tids;
  private List<ChildrenChannelPojo> childrenChannelPojoList;
  private File upfile;
  private Long id;

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

  public ChildrenChannelPojo getChildrenChannelPojo() {
    return childrenChannelPojo;
  }

  public void setChildrenChannelPojo(ChildrenChannelPojo childrenChannelPojo) {
    this.childrenChannelPojo = childrenChannelPojo;
  }

  public ActivityTimePojo getActivityTimePojo() {
    return activityTimePojo;
  }

  public void setActivityTimePojo(ActivityTimePojo activityTimePojo) {
    this.activityTimePojo = activityTimePojo;
  }

  public List<ChildrenChannelPojo> getChildrenChannelPojoList() {
    return childrenChannelPojoList;
  }

  public void setChildrenChannelPojoList(List<ChildrenChannelPojo> childrenChannelPojoList) {
    this.childrenChannelPojoList = childrenChannelPojoList;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  /**
   * 少儿频道web页面
   **/
  public String goChildChannelWeb() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("countWeb", 6);
    List<ChildrenChannelPojo> childrenChannelList =
        childrenChannelService.findChildrenChannelWeb(map);
    ActionContext ac = ActionContext.getContext();
    ac.put("childrenChannelList", childrenChannelList);
    return SUCCESS;
  }

  /**
   * 频道表总数
   * 
   * @return
   * @throws Exception
   */
  public String findChildrenChannelCount() throws Exception {

    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (childrenChannelPojo != null) {
        map.put("name", childrenChannelPojo.getName());
        map.put("status", childrenChannelPojo.getStatus());
      }

      int i = childrenChannelService.findChildrenChannelCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  /**
   * 频道列表
   * 
   * @return
   * @throws Exception
   */
  public String findChildrenChannelList() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());

      if (childrenChannelPojo != null) {
        map.put("name", childrenChannelPojo.getName());
        map.put("status", childrenChannelPojo.getStatus());
      }

      childrenChannelPojoList = childrenChannelService.findChildrenChannelList(map);
      JSONArray json = JSONArray.fromObject(childrenChannelPojoList);
      page.setRowCount(childrenChannelPojoList.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 根据id删除场景页面
   * 
   * @return
   */
  public String delChildrenChannelById() throws Exception {
    try {
      childrenChannelService.delChildrenChannelById(childrenChannelPojo.getId());
      FileUtil.alertMessageBySkip("删除成功！", "childrenChannelList.do");

    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("删除失败！", "childrenChannelList.do");
    }
    return null;
  }

  /**
   * 通过审核场景
   * 
   * @return
   */
  public String checkChildrenChannelById() throws SQLException {
    try {
      childrenChannelService.checkChildrenChannelById(childrenChannelPojo.getId());
      FileUtil.alertMessageBySkip("通过审核成功！", "childrenChannelList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("通过审核失败！", "childrenChannelList.do");
    }
    return null;
  }

  /**
   * 取消审核场景
   * 
   * @return
   */
  public String uncheckChildrenChannelById() throws SQLException {
    try {
      childrenChannelService.uncheckChildrenChannelById(childrenChannelPojo.getId());
      FileUtil.alertMessageBySkip("取消审核成功！", "childrenChannelList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("取消审核失败！", "childrenChannelList.do");
    }
    return null;
  }

  /**
   * 根据id批量通过审核场景
   * 
   * @return
   */
  public String delChildrenChannelAllById() {
    try {
      for (String id : tids) {
        childrenChannelService.delChildrenChannelById(Long.valueOf(id));
      }
      FileUtil.alertMessageBySkip("全部删除成功！", "childrenChannelList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("全部删除失败！", "childrenChannelList.do");
    }
    return null;
  }

  /**
   * 根据id批量通过审核场景
   * 
   * @return
   */
  public String checkChildrenChannelAllById() {
    try {
      for (String id : tids) {
        childrenChannelService.checkChildrenChannelById(Long.valueOf(id));
      }
      FileUtil.alertMessageBySkip("全部通过审核成功！", "childrenChannelList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("全部通过审核失败！", "childrenChannelList.do");
    }
    return null;
  }

  /**
   * 跳转至新增频道页面
   * 
   * @return
   */
  public String goAddChildrenChannelList() {
    return SUCCESS;
  }

  /**
   * 新增频道
   * 
   * @return
   * @throws Throwable
   */
  public void addChildrenChannelListOk() throws Throwable {
    try {
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/childrenChannel")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/childrenChannel/", upfile);
        childrenChannelPojo.setPicture(file_name);
      }
      childrenChannelService.insertChildrenChannel(childrenChannelPojo);
      FileUtil.alertMessageBySkip("添加成功！", "childrenChannelList.do");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 跳转至编辑频道页面
   * 
   * @return
   * @throws Exception
   */
  public String findChildrenChannelById() {
    try {
      childrenChannelPojo = childrenChannelService.findChildrenChannelById(id);

    } catch (Exception e) {
      e.printStackTrace();

    }
    return SUCCESS;

  }

  /**
   * 编辑频道
   * 
   * @return
   * @throws Throwable
   */
  public void updateChildrenChannelById() throws Throwable {
    try {
      if (upfile != null) {
        new FileInputStream(upfile);
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/childrenChannel")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/childrenChannel/", upfile);
        childrenChannelPojo.setPicture(file_name);
      }
      childrenChannelService.updateChildrenChannelById(childrenChannelPojo);
      FileUtil.alertMessageBySkip("修改成功！", "childrenChannelList.do");
    } catch (Exception e) {
      e.printStackTrace();

    }
  }
}
