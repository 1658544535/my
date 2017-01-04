package com.tzmb2c.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.Date;
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
import com.tzmb2c.web.pojo.ActivityTimePojo;
import com.tzmb2c.web.pojo.CategorySettingPojo;
import com.tzmb2c.web.service.ActivityGoodsService;
import com.tzmb2c.web.service.ActivityTimeService;
import com.tzmb2c.web.service.CategorySettingService;
import com.tzmb2c.web.service.SceneProductService;

/**
 * 场景Action 2015-10-17
 * 
 * @author hsy
 */
public class CategorySettingAction extends SuperAction {
  @Autowired
  private CategorySettingService categorySettingService;
  @Autowired
  private SceneProductService sceneProductService;
  @Autowired
  private ActivityTimeService activityTimeService;
  @Autowired
  private ActivityGoodsService activityGoodsService;
  private CategorySettingPojo categorySettingPojo;
  private ActivityTimePojo activityTimePojo;
  private String result;
  private String[] tids;
  private List<CategorySettingPojo> categorySettingPojoList;
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

  public CategorySettingPojo getCategorySettingPojo() {
    return categorySettingPojo;
  }

  public void setCategorySettingPojo(CategorySettingPojo categorySettingPojo) {
    this.categorySettingPojo = categorySettingPojo;
  }

  public ActivityTimePojo getActivityTimePojo() {
    return activityTimePojo;
  }

  public void setActivityTimePojo(ActivityTimePojo activityTimePojo) {
    this.activityTimePojo = activityTimePojo;
  }

  public List<CategorySettingPojo> getCategorySettingPojoList() {
    return categorySettingPojoList;
  }

  public void setCategorySettingPojoList(List<CategorySettingPojo> categorySettingPojoList) {
    this.categorySettingPojoList = categorySettingPojoList;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }


  /**
   * 总数
   * 
   * @return
   * @throws Exception
   */
  public String findCategorySettingCount() throws Exception {

    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (categorySettingPojo != null) {
        map.put("title", categorySettingPojo.getTitle());
        map.put("status", categorySettingPojo.getStatus());
      }

      int i = categorySettingService.countBy(map);
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
  public String findCategorySettingList() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());

      if (categorySettingPojo != null) {
        map.put("title", categorySettingPojo.getTitle());
        map.put("status", categorySettingPojo.getStatus());
      }
      map.put("orderBy", "sorting desc, create_date desc");
      categorySettingPojoList = categorySettingService.listPage(map);
      JSONArray json = JSONArray.fromObject(categorySettingPojoList);
      page.setRowCount(categorySettingPojoList.size());
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
  public String delCategorySettingById() throws Exception {
    try {
      categorySettingService.delete(categorySettingPojo.getId());
      FileUtil.alertMessageBySkip("删除成功！", "categorySettingList.do");

    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("删除失败！", "categorySettingList.do");
    }
    return null;
  }

  /**
   * 通过审核场景
   * 
   * @return
   */
  public String checkCategorySettingById() throws SQLException {
    try {
      categorySettingPojo.setStatus(1);
      categorySettingService.update(categorySettingPojo);
      FileUtil.alertMessageBySkip("通过审核成功！", "categorySettingList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("通过审核失败！", "categorySettingList.do");
    }
    return null;
  }

  /**
   * 取消审核场景
   * 
   * @return
   */
  public String uncheckCategorySettingById() throws SQLException {
    try {
      categorySettingPojo.setStatus(0);
      categorySettingService.update(categorySettingPojo);
      FileUtil.alertMessageBySkip("取消审核成功！", "categorySettingList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("取消审核失败！", "categorySettingList.do");
    }
    return null;
  }

  /**
   * 根据id批量通过审核场景
   * 
   * @return
   */
  public String delCategorySettingAllById() {
    try {
      for (String id : tids) {
        categorySettingService.delete(Long.valueOf(id));
      }
      FileUtil.alertMessageBySkip("全部删除成功！", "categorySettingList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("全部删除失败！", "categorySettingList.do");
    }
    return null;
  }

  /**
   * 根据id批量通过审核场景
   * 
   * @return
   */
  public String checkCategorySettingAllById() {
    try {
      CategorySettingPojo categorySettingPojo = new CategorySettingPojo();
      for (String id : tids) {
        categorySettingPojo.setId(Long.valueOf(id));
        categorySettingPojo.setStatus(1);
        categorySettingService.update(categorySettingPojo);
      }
      FileUtil.alertMessageBySkip("全部通过审核成功！", "categorySettingList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("全部通过审核失败！", "categorySettingList.do");
    }
    return null;
  }

  /**
   * 跳转至新增频道页面
   * 
   * @return
   */
  public String goAddCategorySettingList() {
    return SUCCESS;
  }

  /**
   * 新增频道
   * 
   * @return
   * @throws Throwable
   */
  public void addCategorySettingListOk() throws Throwable {
    try {
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/categorySetting")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/categorySetting/", upfile);
        categorySettingPojo.setBanner(file_name);
      }
      categorySettingPojo.setCreateDate(new Date());
      categorySettingPojo.setUpdateDate(new Date());
      categorySettingService.add(categorySettingPojo);
      FileUtil.alertMessageBySkip("添加成功！", "categorySettingList.do");
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
  public String findCategorySettingById() {
    try {
      categorySettingPojo = categorySettingService.getById(id);

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
  public void updateCategorySettingById() throws Throwable {
    try {
      if (upfile != null) {
        new FileInputStream(upfile);
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/categorySetting")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/categorySetting/", upfile);
        categorySettingPojo.setBanner(file_name);
      }
      categorySettingService.update(categorySettingPojo);
      FileUtil.alertMessageBySkip("修改成功！", "categorySettingList.do");
    } catch (Exception e) {
      e.printStackTrace();

    }
  }



}
