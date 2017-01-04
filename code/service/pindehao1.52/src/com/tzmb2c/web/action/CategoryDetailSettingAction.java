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
import com.tzmb2c.web.pojo.ActivityTimePojo;
import com.tzmb2c.web.pojo.CategoryDetailSettingPojo;
import com.tzmb2c.web.service.ActivityGoodsService;
import com.tzmb2c.web.service.ActivityTimeService;
import com.tzmb2c.web.service.CategoryDetailSettingService;
import com.tzmb2c.web.service.SceneProductService;

/**
 * 场景Action 2015-10-17
 * 
 * @author hsy
 */
public class CategoryDetailSettingAction extends SuperAction {
  @Autowired
  private CategoryDetailSettingService categoryDetailSettingService;
  @Autowired
  private SceneProductService sceneProductService;
  @Autowired
  private ActivityTimeService activityTimeService;
  @Autowired
  private ActivityGoodsService activityGoodsService;
  private CategoryDetailSettingPojo categoryDetailSettingPojo;
  private ActivityTimePojo activityTimePojo;
  private String result;
  private String[] tids;
  private List<CategoryDetailSettingPojo> categoryDetailSettingPojoList;
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

  public CategoryDetailSettingPojo getCategoryDetailSettingPojo() {
    return categoryDetailSettingPojo;
  }

  public void setCategoryDetailSettingPojo(CategoryDetailSettingPojo categoryDetailSettingPojo) {
    this.categoryDetailSettingPojo = categoryDetailSettingPojo;
  }

  public ActivityTimePojo getActivityTimePojo() {
    return activityTimePojo;
  }

  public void setActivityTimePojo(ActivityTimePojo activityTimePojo) {
    this.activityTimePojo = activityTimePojo;
  }

  public List<CategoryDetailSettingPojo> getCategoryDetailSettingPojoList() {
    return categoryDetailSettingPojoList;
  }

  public void setCategoryDetailSettingPojoList(
      List<CategoryDetailSettingPojo> categoryDetailSettingPojoList) {
    this.categoryDetailSettingPojoList = categoryDetailSettingPojoList;
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
  public String findCategoryDetailSettingCount() throws Exception {

    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (categoryDetailSettingPojo != null) {
        map.put("categoryId", categoryDetailSettingPojo.getCategoryId());
      }

      int i = categoryDetailSettingService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  /**
   * 列表
   * 
   * @return
   * @throws Exception
   */
  public String findCategoryDetailSettingList() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());

      if (categoryDetailSettingPojo != null) {
        map.put("categoryId", categoryDetailSettingPojo.getCategoryId());
      }
      map.put("orderBy", "s.sorting desc, s.create_date desc");
      categoryDetailSettingPojoList = categoryDetailSettingService.listPage(map);
      JSONArray json = JSONArray.fromObject(categoryDetailSettingPojoList);
      page.setRowCount(categoryDetailSettingPojoList.size());
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
  public String delCategoryDetailSettingById() throws Exception {
    try {
      categoryDetailSettingService.delete(categoryDetailSettingPojo.getId());
      FileUtil.alertMessageBySkip("删除成功！",
          "categoryDetailSettingList.do?categoryDetailSettingPojo.categoryId="
              + categoryDetailSettingPojo.getCategoryId());

    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("删除失败！",
          "categoryDetailSettingList.do?categoryDetailSettingPojo.categoryId="
              + categoryDetailSettingPojo.getCategoryId());
    }
    return null;
  }



  /**
   * 根据id批量通过审核场景
   * 
   * @return
   */
  public String delCategoryDetailSettingAllById() {
    try {
      for (String id : tids) {
        categoryDetailSettingService.delete(Long.valueOf(id));
      }
      FileUtil.alertMessageBySkip("全部删除成功！", "categoryDetailSettingList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("全部删除失败！", "categoryDetailSettingList.do");
    }
    return null;
  }


  /**
   * 跳转至新增页面
   * 
   * @return
   */
  public String goAddCategoryDetailSettingList() {
    return SUCCESS;
  }

  /**
   * 新增
   * 
   * @return
   * @throws Throwable
   */
  public String addCategoryDetailSettingListOk() throws Throwable {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("categoryId", categoryDetailSettingPojo.getCategoryId());
      map.put("typeId", categoryDetailSettingPojo.getTypeId());
      int count = categoryDetailSettingService.countBy(map);
      if (count > 0) {
        FileUtil.alertMessageBySkip("该分类已添加！", "goProductTypeThree.do");
        return null;
      }
      categoryDetailSettingPojo.setSorting(0);
      categoryDetailSettingPojo.setCreateDate(new Date());
      categoryDetailSettingPojo.setUpdateDate(new Date());
      categoryDetailSettingService.add(categoryDetailSettingPojo);
      FileUtil.alertMessageBySkip("添加成功！",
          "categoryDetailSettingList.do?categoryDetailSettingPojo.categoryId="
              + categoryDetailSettingPojo.getCategoryId());
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("添加失败！", "goProductTypeThree.do");
    }
    return result;
  }


  /**
   * 跳转至编辑频道页面
   * 
   * @return
   * @throws Exception
   */
  public String findCategoryDetailSettingById() {
    try {
      categoryDetailSettingPojo = categoryDetailSettingService.getById(id);

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
  public void updateCategoryDetailSettingById() throws Throwable {
    try {
      categoryDetailSettingService.update(categoryDetailSettingPojo);
      FileUtil.alertMessageBySkip("修改成功！",
          "categoryDetailSettingList.do?categoryDetailSettingPojo.categoryId="
              + categoryDetailSettingPojo.getCategoryId());
    } catch (Exception e) {
      e.printStackTrace();

    }
  }



}
