package com.tzmb2c.web.action;

import java.io.File;
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
import com.tzmb2c.web.pojo.ScenePojo;
import com.tzmb2c.web.pojo.SceneProductPojo;
import com.tzmb2c.web.service.ActivityGoodsService;
import com.tzmb2c.web.service.ActivityTimeService;
import com.tzmb2c.web.service.SceneProductService;
import com.tzmb2c.web.service.SceneService;

/**
 * 场景Action 2015-10-17
 * 
 * @author hsy
 */
public class SceneAction extends SuperAction {
  @Autowired
  private SceneService sceneService;
  @Autowired
  private SceneProductService sceneProductService;
  @Autowired
  private ActivityTimeService activityTimeService;
  @Autowired
  private ActivityGoodsService activityGoodsService;
  private ScenePojo scenePojo;
  private SceneProductPojo sceneProductPojo;
  private ActivityTimePojo activityTimePojo;
  private String result;
  private String[] tids;
  private List<ScenePojo> scenePojoList;
  private File upfile;

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

  public ScenePojo getScenePojo() {
    return scenePojo;
  }

  public void setScenePojo(ScenePojo scenePojo) {
    this.scenePojo = scenePojo;
  }

  public ActivityTimePojo getActivityTimePojo() {
    return activityTimePojo;
  }

  public void setActivityTimePojo(ActivityTimePojo activityTimePojo) {
    this.activityTimePojo = activityTimePojo;
  }

  public List<ScenePojo> getScenePojoList() {
    return scenePojoList;
  }

  public void setScenePojoList(List<ScenePojo> scenePojoList) {
    this.scenePojoList = scenePojoList;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  /**
   * 场景表总数
   * 
   * @return
   * @throws Exception
   */
  public String sceneListCount() throws Exception {

    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("isdelete", "0");
      if (scenePojo != null) {
        map.put("name", scenePojo.getName().trim());
        map.put("beginTimeStr", scenePojo.getBeginTimeStr());
        map.put("endTimeStr", scenePojo.getEndTimeStr());
        map.put("status", scenePojo.getStatus());
      }
      map.put("type", 1);
      int i = sceneService.findSceneCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  /**
   * 场景列表
   * 
   * @return
   * @throws Exception
   */
  public String sceneListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      map.put("isdelete", "0");
      if (scenePojo != null) {
        map.put("name", scenePojo.getName().trim());
        map.put("beginTimeStr", scenePojo.getBeginTimeStr());
        map.put("endTimeStr", scenePojo.getEndTimeStr());
        map.put("status", scenePojo.getStatus());
      }
      map.put("type", 1);
      scenePojoList = sceneService.findSceneList(map);
      JSONArray json = JSONArray.fromObject(scenePojoList);
      page.setRowCount(scenePojoList.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  //
  // /**
  // * 删除场景
  // * @return
  // */
  // public String deleteScene(){
  // try {
  // if (scenePojo != null) {
  // sceneService.delSceneById(scenePojo.getId());
  // }
  // setResult("1");
  // } catch (Exception e) {
  // // TODO: handle exception
  // setResult("0");
  // }
  // return SUCCESS;
  // }
  /**
   * 根据id删除场景页面
   * 
   * @return
   */
  public String delSceneById() throws Exception {
    try {
      /*
       * Map<String, Object> map = new HashMap<String, Object>();
       * map.put("sceneId",scenePojo.getId());
       * sceneProductPojoList=sceneProductService.findSceneProductList(map); for(SceneProductPojo
       * SceneProduct:sceneProductPojoList){
       * activityGoodsService.delActivityGoods(SceneProduct.getGoodId()); }
       */
      /* sceneProductService.delSceneProductBySceneId(scenePojo.getId()); */
      scenePojo = sceneService.findSceneById(scenePojo.getId());
      sceneService.delSceneById(scenePojo.getId());
      // 删除活动产品设置
      /* activityGoodsService.delActivityGoodsByTimeId(scenePojo.getActivityId()); */
      activityTimeService.delActivityTime(scenePojo.getActivityId());
      FileUtil.alertMessageBySkip("删除成功！", "sceneList.do");

    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("删除失败！", "sceneList.do");
    }
    return null;
  }

  /**
   * 根据id批量通过审核场景
   * 
   * @return
   */
  public String delSceneAllById() {
    try {
      // Map<String, Object> map = new HashMap<String, Object>();
      for (String id : tids) {
        /*
         * map.put("sceneId",Long.valueOf(id));
         * sceneProductPojoList=sceneProductService.findSceneProductList(map); for(SceneProductPojo
         * SceneProduct:sceneProductPojoList){
         * activityGoodsService.delActivityGoods(SceneProduct.getGoodId()); }
         */
        /* sceneProductService.delSceneProductBySceneId(Long.valueOf(id)); */
        scenePojo = sceneService.findSceneById(Long.valueOf(id));
        sceneService.delSceneById(Long.valueOf(id));
        // 删除活动产品设置
        /* activityGoodsService.delActivityGoodsByTimeId(scenePojo.getActivityId()); */
        activityTimeService.delActivityTime(scenePojo.getActivityId());
      }
      FileUtil.alertMessageBySkip("全部删除成功！", "sceneList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("全部删除失败！", "sceneList.do");
    }
    return null;
  }

  /**
   * 跳转至编辑场景页面
   * 
   * @return
   * @throws Exception
   */
  public String goUpdateSceneById() throws Exception {
    try {
      scenePojo = sceneService.findSceneById(scenePojo.getId());
      FileUtil.alertMessageBySkip("跳转成功！", "sceneList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("跳转失败！", "sceneList.do");
    }
    return SUCCESS;
  }

  /**
   * 编辑场景
   * 
   * @return
   * @throws Throwable
   */
  public String updateSceneById() throws Throwable {
    try {
      activityTimePojo = new ActivityTimePojo();
      if (scenePojo != null) {
        if (scenePojo.getBeginTimeStr() != null && scenePojo.getEndTimeStr() != null) {
          Date endTime = new Date();
          endTime = StringUtil.stringDate(scenePojo.getEndTimeStr());
          activityTimePojo.setEndTime(scenePojo.getEndTimeStr());
          scenePojo.setEndTime(endTime);
          Date beginTime = new Date();
          beginTime = StringUtil.stringDate(scenePojo.getBeginTimeStr());
          activityTimePojo.setBeginTime(scenePojo.getBeginTimeStr());
          scenePojo.setBeginTime(beginTime);
        }
        if (upfile != null) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/scene")
                  + File.separator;
          FileUtil.uploadFile(file_name, uploadPath, "upfiles/scene/", upfile);
          scenePojo.setImage(file_name);
        }
      }
      Date time = new Date();
      scenePojo.setUpdateDate(time);
      activityTimePojo.setUpdateDate(time);
      sceneService.updateSceneById(scenePojo);
      scenePojo = sceneService.findSceneById(scenePojo.getId());
      activityTimePojo.setId(scenePojo.getActivityId());
      activityTimePojo.setTitle(scenePojo.getName());
      activityTimeService.updateActivityTime(activityTimePojo);
      FileUtil.alertMessageBySkip("提交成功！", "sceneList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("提交失败！", "sceneList.do");
    }
    return null;
  }

  /**
   * 通过审核场景
   * 
   * @return
   */
  public String checkSceneById() throws SQLException {
    try {
      sceneService.checkSceneById(scenePojo.getId());
      FileUtil.alertMessageBySkip("通过审核成功！", "sceneList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("通过审核失败！", "sceneList.do");
    }
    return null;
  }

  /**
   * 取消审核场景
   * 
   * @return
   */
  public String uncheckSceneById() throws SQLException {
    try {
      sceneService.uncheckSceneById(scenePojo.getId());
      FileUtil.alertMessageBySkip("取消审核成功！", "sceneList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("取消审核失败！", "sceneList.do");
    }
    return null;
  }

  /**
   * 根据id批量通过审核场景
   * 
   * @return
   */
  public String checkSceneAllById() {
    try {
      for (String id : tids) {
        sceneService.checkSceneById(Long.valueOf(id));
      }
      FileUtil.alertMessageBySkip("全部通过审核成功！", "sceneList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("全部通过审核失败！", "sceneList.do");
    }
    return null;
  }

  /**
   * 跳转至新增场景页面
   * 
   * @return
   */
  public String goAddSceneList() {
    return SUCCESS;
  }

  /**
   * 提交新增场景
   * 
   * @return
   * @throws Throwable
   */
  public String addSceneListOk() throws Throwable {
    activityTimePojo = new ActivityTimePojo();
    try {
      if (scenePojo != null) {
        if (scenePojo.getBeginTimeStr() != null && scenePojo.getEndTimeStr() != null) {
          Date endTime = new Date();
          endTime = StringUtil.stringDate(scenePojo.getEndTimeStr());
          activityTimePojo.setEndTime(scenePojo.getEndTimeStr());
          scenePojo.setEndTime(endTime);
          Date beginTime = new Date();
          beginTime = StringUtil.stringDate(scenePojo.getBeginTimeStr());
          activityTimePojo.setBeginTime(scenePojo.getBeginTimeStr());
          scenePojo.setBeginTime(beginTime);
        }
        if (upfile != null) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/scene")
                  + File.separator;
          FileUtil.uploadFile(file_name, uploadPath, "upfiles/scene/", upfile);
          scenePojo.setImage(file_name);
        }
        Date time = new Date();
        activityTimePojo.setTitle(scenePojo.getName());
        // 2-场景
        activityTimePojo.setType(2);
        activityTimePojo.setCreateDate(time);
        activityTimePojo.setUpdateDate(time);
        // 1-APP 2-微商城
        activityTimePojo.setChannel(1);
        activityTimePojo.setCreateBy(1L);
        activityTimeService.insertActivityTime(activityTimePojo);
        Long t = activityTimePojo.getId();
        scenePojo.setActivityId(t);
        scenePojo.setType(1);
        scenePojo.setCreateDate(time);
        scenePojo.setUpdateDate(time);
        sceneService.insertScene(scenePojo);
        FileUtil.alertMessageBySkip("新增场景成功！", "sceneList.do");
      }
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("新增场景失败！", "sceneList.do");
    }
    return null;
  }

  public SceneProductPojo getSceneProductPojo() {
    return sceneProductPojo;
  }

  public void setSceneProductPojo(SceneProductPojo sceneProductPojo) {
    this.sceneProductPojo = sceneProductPojo;
  }
}
