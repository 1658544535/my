package com.tzmb2c.web.action;

import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.tzmb2c.web.pojo.ScenePojo;
import com.tzmb2c.web.pojo.SceneProductPojo;
import com.tzmb2c.web.service.SceneProductService;
import com.tzmb2c.web.service.SceneService;

public class HomePackageAction extends SuperAction {

  @Autowired
  private SceneService sceneService;
  private ScenePojo scenePojo;
  private List<ScenePojo> scenePojos;
  private Integer t;// 操作类型
  private File upfile;// 上传图片
  private String[] tids;
  private String result;
  // private double tempPrice = 0;
  // private double tempSellPrice = 0;
  private double tempPrice;
  private double tempSellPrice;
  @Autowired
  private SceneProductService sceneProductService;
  private SceneProductPojo sceneProductPojo;
  private List<SceneProductPojo> sceneProductPojos;

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  /**
   * 设置首页套餐
   * 
   * @return
   * @throws SQLException
   * @throws ParseException
   */
  public String homePackageManage() throws SQLException, ParseException {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("isdelete", "0");
    if (scenePojo != null) {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      map.put("name", scenePojo.getName().trim());
      map.put("status", scenePojo.getStatus());
      if (scenePojo.getBeginTimeStr().length() >= 10) {
        map.put("beginTimeStr", sdf.parse(scenePojo.getBeginTimeStr()));
      }
      if (scenePojo.getEndTimeStr().length() >= 10) {
        map.put("endTimeStr", sdf.parse(scenePojo.getEndTimeStr()));
      }
    }
    map.put("type", 2);
    int i = sceneService.findSceneCount(map);
    page.setRowCount(i);
    return SUCCESS;
  }

  /**
   * 首页套餐列表
   * 
   * @return
   * @throws SQLException
   */
  public String homePackageAllList() throws SQLException {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    map.put("isdelete", "0");
    if (scenePojo != null) {
      map.put("name", scenePojo.getName().trim());
      map.put("status", scenePojo.getStatus());
      if (scenePojo.getBeginTimeStr().length() >= 10) {
        map.put("beginTimeStr", scenePojo.getBeginTimeStr());
      }
      if (scenePojo.getEndTimeStr().length() >= 10) {
        map.put("endTimeStr", scenePojo.getEndTimeStr());
      }
    }
    map.put("type", 2);
    scenePojos = sceneService.findSceneList(map);
    JSONArray json = JSONArray.fromObject(scenePojos);
    page.setRowCount(scenePojos.size());
    page.setResult(json.toString());
    return SUCCESS;
  }

  /**
   * 添加/修改首页套餐
   * 
   * @return
   * @throws SQLException
   */
  public String homePackageAdd() throws SQLException {
    if (t == 2) {
      if (scenePojo != null) {
        scenePojo = sceneService.findSceneById(scenePojo.getId());
        if (scenePojo != null) {
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          if (scenePojo.getBeginTime() != null) {
            scenePojo.setBeginTimeStr(sdf.format(scenePojo.getBeginTime()));
          }
          if (scenePojo.getEndTime() != null) {
            scenePojo.setEndTimeStr(sdf.format(scenePojo.getEndTime()));
          }
          HashMap<String, Object> map = new HashMap<String, Object>();
          map.put("sceneId", scenePojo.getId());
          sceneProductPojos = sceneProductService.findSceneProductList(map);
          if (sceneProductPojos.size() != 0) {
            for (SceneProductPojo s : sceneProductPojos) {
              tempSellPrice += s.getSellingPrice();
              tempPrice += s.getDistributionPrice();
            }
          }
        }
      }
    }
    return SUCCESS;
  }

  /**
   * 添加/修改首页套餐提交
   * 
   * @return
   */
  public String homePackageAddOk() {
    if (t == 1) {
      try {
        if (scenePojo != null) {
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          if (scenePojo.getBeginTimeStr().length() >= 19) {
            scenePojo.setBeginTime(sdf.parse(scenePojo.getBeginTimeStr()));
          }
          if (scenePojo.getEndTimeStr().length() >= 19) {
            scenePojo.setEndTime(sdf.parse(scenePojo.getEndTimeStr()));
          }
          if (upfile != null) {
            String file_name = StringUtil.getCurrentDateStr() + ".jpg";
            String uploadPath =
                ServletActionContext.getServletContext().getRealPath("/upfiles/homePackage")
                    + File.separator;
            FileUtil.uploadFile(file_name, uploadPath, "upfiles/homePackage/", upfile);
            scenePojo.setImage(file_name);
          }
          scenePojo.setType(2);
          scenePojo.setPreview(0);
          sceneService.insertScene(scenePojo);
          FileUtil.alertMessageBySkip("添加成功！", "homePackageManage.do");
        }
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("添加失败！", "homePackageManage.do");
      }
    } else {
      try {
        if (scenePojo != null) {
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          if (scenePojo.getBeginTimeStr().length() >= 19) {
            scenePojo.setBeginTime(sdf.parse(scenePojo.getBeginTimeStr()));
          }
          if (scenePojo.getEndTimeStr().length() >= 19) {
            scenePojo.setEndTime(sdf.parse(scenePojo.getEndTimeStr()));
          }
          if (upfile != null) {
            String file_name = StringUtil.getCurrentDateStr() + ".jpg";
            String uploadPath =
                ServletActionContext.getServletContext().getRealPath("/upfiles/homePackage")
                    + File.separator;
            FileUtil.uploadFile(file_name, uploadPath, "upfiles/homePackage/", upfile);
            scenePojo.setImage(file_name);
          }
          // scenePojo.setType(2);

          sceneService.updateSceneById(scenePojo);
          FileUtil.alertMessageBySkip("编辑成功！", "homePackageManage.do");
        }
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("编辑失败！", "homePackageManage.do");
      }
    }
    return null;
  }

  /**
   * 批量删除场首页套餐
   * 
   * @return
   */
  public String deletePackageAll() {
    try {
      for (String id : tids) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("sceneId", id);
        sceneProductPojos = sceneProductService.findSceneProductList(map);
        if (sceneProductPojos.size() != 0) {
          for (SceneProductPojo s : sceneProductPojos) {
            sceneProductService.delSceneProductById(s.getId());
          }
        }
        sceneService.delSceneById(Long.valueOf(id));
      }
      FileUtil.alertMessageBySkip("删除成功！", "homePackageManage.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("删除失败！", "homePackageManage.do");
    }
    return null;
  }

  /**
   * 删除首页套餐
   * 
   * @return
   */
  public String deletePackage() {
    try {
      if (scenePojo != null) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("sceneId", scenePojo.getId());
        sceneProductPojos = sceneProductService.findSceneProductList(map);
        if (sceneProductPojos.size() != 0) {
          for (SceneProductPojo s : sceneProductPojos) {
            sceneProductService.delSceneProductById(s.getId());
          }
        }
        sceneService.delSceneById(scenePojo.getId());
      }
      setResult("1");
    } catch (Exception e) {
      e.printStackTrace();
      setResult("0");
    }
    return SUCCESS;
  }

  /**
   * 批量审核场首页套餐
   * 
   * @return
   */
  public String checkPackageAll() {
    try {
      for (String id : tids) {
        sceneService.checkSceneById(Long.valueOf(id));
      }
      FileUtil.alertMessageBySkip("审核成功！", "homePackageManage.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("审核失败！", "homePackageManage.do");
    }
    return null;
  }

  /**
   * 取消审核首页套餐
   * 
   * @return
   */
  public String checkPackage() {
    try {
      if (scenePojo != null) {
        sceneService.checkSceneById(scenePojo.getId());
      }
      setResult("1");
    } catch (Exception e) {
      e.printStackTrace();
      setResult("0");
    }
    return SUCCESS;
  }

  /**
   * 审核首页套餐
   * 
   * @return
   */
  public String uncheckPackage() {
    try {
      if (scenePojo != null) {
        sceneService.uncheckSceneById(scenePojo.getId());
      }
      setResult("1");
    } catch (Exception e) {
      e.printStackTrace();
      setResult("0");
    }
    return SUCCESS;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public ScenePojo getScenePojo() {
    return scenePojo;
  }

  public void setScenePojo(ScenePojo scenePojo) {
    this.scenePojo = scenePojo;
  }

  public Integer getT() {
    return t;
  }

  public void setT(Integer t) {
    this.t = t;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public double getTempPrice() {
    return tempPrice;
  }

  public void setTempPrice(double tempPrice) {
    this.tempPrice = tempPrice;
  }

  public double getTempSellPrice() {
    return tempSellPrice;
  }

  public void setTempSellPrice(double tempSellPrice) {
    this.tempSellPrice = tempSellPrice;
  }

  public SceneProductPojo getSceneProductPojo() {
    return sceneProductPojo;
  }

  public void setSceneProductPojo(SceneProductPojo sceneProductPojo) {
    this.sceneProductPojo = sceneProductPojo;
  }
}
