package com.tzmb2c.web.action;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.ActivityGoodsPojo;
import com.tzmb2c.web.pojo.ScenePojo;
import com.tzmb2c.web.pojo.SceneProductPojo;
import com.tzmb2c.web.service.ActivityGoodsService;
import com.tzmb2c.web.service.SceneProductService;
import com.tzmb2c.web.service.SceneService;

/**
 * 场景产品Action 2015-10-17
 * 
 * @author hsy
 */
public class SceneProductAction extends SuperAction {
  @Autowired
  private SceneProductService sceneProductService;
  @Autowired
  private SceneService sceneService;
  @Autowired
  private ActivityGoodsService activityGoodsService;
  private SceneProductPojo sceneProductPojo;
  private ScenePojo scenePojo;
  private ActivityGoodsPojo activityGoodsPojo;
  private String result;
  private String[] tids;
  private List<SceneProductPojo> sceneProductPojoList;
  private List<ScenePojo> sceneList;
  private File upfile;
  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public SceneProductPojo getSceneProductPojo() {
    return sceneProductPojo;
  }

  public void setSceneProductPojo(SceneProductPojo sceneProductPojo) {
    this.sceneProductPojo = sceneProductPojo;
  }

  public ScenePojo getScenePojo() {
    return scenePojo;
  }

  public void setScenePojo(ScenePojo scenePojo) {
    this.scenePojo = scenePojo;
  }

  public List<SceneProductPojo> getSceneProductPojoList() {
    return sceneProductPojoList;
  }

  public void setSceneProductPojoList(List<SceneProductPojo> sceneProductPojoList) {
    this.sceneProductPojoList = sceneProductPojoList;
  }

  public List<ScenePojo> getSceneList() {
    return sceneList;
  }

  public void setSceneList(List<ScenePojo> sceneList) {
    this.sceneList = sceneList;
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

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  /**
   * 该场景产品条数目
   * 
   * @return
   * @throws Exception
   */
  public String sceneProductListCount() throws Exception {

    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (sceneProductPojo != null) {
        map.put("title", sceneProductPojo.getTitle());
        map.put("sceneStock", sceneProductPojo.getSceneStock());
        map.put("sceneId", sceneProductPojo.getSceneId());
      }
      int i = sceneProductService.findSceneProductCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 该场景产品列表
   * 
   * @return
   * @throws Exception
   */
  public String sceneProductListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (sceneProductPojo != null) {
        map.put("title", sceneProductPojo.getTitle());
        map.put("sceneStock", sceneProductPojo.getSceneStock());
        map.put("sceneId", sceneProductPojo.getSceneId());
      }
      sceneProductPojoList = sceneProductService.findSceneProductList(map);
      JSONArray json = JSONArray.fromObject(sceneProductPojoList);
      page.setRowCount(sceneProductPojoList.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 场景活动详情页面无视频.
   * 
   * @return
   * @throws SQLException
   */
  public String goSceneProductWeb() throws SQLException {
    ActionContext ac = ActionContext.getContext();
    List<SceneProductPojo> sceneProducts1 = new ArrayList<SceneProductPojo>();
    List<SceneProductPojo> sceneProducts0 = new ArrayList<SceneProductPojo>();
    if (id == null || id == 0L) {
      return SUCCESS;
    }
    scenePojo = sceneService.findSceneById(id);
    if (scenePojo.getStatus() == 0) {
      return null;
    }
    if (scenePojo != null) {
      if (!"".equals(scenePojo.getIntroduction())) {// 去掉视频iframe便签
        boolean a = scenePojo.getIntroduction().contains("<iframe");
        boolean b = scenePojo.getIntroduction().contains("</iframe>");
        if (a && b) {
          System.out.println(scenePojo.getIntroduction());
          StringBuffer introduction = new StringBuffer(scenePojo.getIntroduction());
          int start = introduction.indexOf("<iframe");
          int end = introduction.lastIndexOf("</iframe>") + 9;
          introduction.delete(start, end);
          System.out.println(introduction);
          String introductions = introduction.toString();
          scenePojo.setIntroduction(introductions);
        }
      }
      Map<String, Object> map1 = new HashMap<String, Object>();
      map1.put("sceneId", scenePojo.getId());
      map1.put("status", 1);
      map1.put("isIntroduce", 1);
      sceneProducts1 = sceneProductService.findSceneProductList(map1);
      if (sceneProducts1 != null && sceneProducts1.size() > 0) {
        ac.put("sceneProductPojos1", sceneProducts1);
      }
      /*
       * for(SceneProductPojo u:sceneProducts1){ ac.put("title"+u.getSorting(), u.getTitle());
       * ac.put("image"+u.getSorting(), u.getImage()); ac.put("ProductImage"+u.getSorting(),
       * u.getImage()); ac.put("introduction"+u.getSorting(), u.getIntroduction());
       * ac.put("productId"+u.getSorting(), u.getProductId()); ac.put("productName"+u.getSorting(),
       * u.getProductName()); ac.put("sellPrice"+u.getSorting(), u.getSellPrice());
       * ac.put("scenePrice"+u.getSorting(), u.getScenePrice()); }
       */
      Map<String, Object> map0 = new HashMap<String, Object>();
      map0.put("sceneId", scenePojo.getId());
      map0.put("status", 1);
      map0.put("isIntroduce", 0);
      sceneProducts0 = sceneProductService.findSceneProductList(map0);
      if (sceneProducts0 != null && sceneProducts0.size() > 0) {
        ac.put("sceneProductPojos0", sceneProducts0);
      }

    } else {
      // FileUtil.alertMessageBySkip("场景活动已结束", "goIndexWeb.do");
      // return null;
    }
    return SUCCESS;
  }

  /**
   * 场景活动详情页面带视频.
   * 
   * @return
   * @throws SQLException
   */
  public String goSceneProductWebVideo() throws SQLException {
    ActionContext ac = ActionContext.getContext();
    List<SceneProductPojo> sceneProducts1 = new ArrayList<SceneProductPojo>();
    List<SceneProductPojo> sceneProducts0 = new ArrayList<SceneProductPojo>();
    if (id == null || id == 0L) {
      return SUCCESS;
    }
    scenePojo = sceneService.findSceneById(id);
    if (scenePojo.getStatus() == 0) {
      return null;
    }
    if (scenePojo != null) {
      if (!"".equals(scenePojo.getIntroduction())) {
        boolean a = scenePojo.getIntroduction().contains("<iframe");
        boolean b = scenePojo.getIntroduction().contains("embed/");// 优酷视频的通用地址
        boolean c = scenePojo.getIntroduction().contains("sid/");// 优酷视频的flash地址
        if (a && b) {
          // 去掉视频iframe视频广告
          System.out.println(scenePojo.getIntroduction());
          StringBuffer introduction = new StringBuffer(scenePojo.getIntroduction());
          int start = introduction.indexOf("embed/") + 6;
          int end = introduction.indexOf("==\"");
          String introductions = introduction.substring(start, end);
          System.out.println(introductions);
          scenePojo.setVideo(introductions);
          String iframeStr = "<p id='youkuplayer' ></p>";
          StringBuffer introduction2 = new StringBuffer(scenePojo.getIntroduction());
          start = introduction2.indexOf("<iframe");
          end = introduction2.lastIndexOf("</iframe>") + 9;
          introduction2 = introduction2.replace(start, end, iframeStr);
          String introductions2 = introduction2.toString();
          System.out.println(introduction2);
          scenePojo.setIntroduction(introductions2);
        }
        if (a && c) {
          // 去掉视频iframe视频广告
          System.out.println(scenePojo.getIntroduction());
          StringBuffer introduction = new StringBuffer(scenePojo.getIntroduction());
          int start = introduction.indexOf("sid/") + 4;
          int end = introduction.lastIndexOf("==/v.swf");
          String introductions = introduction.substring(start, end);
          System.out.println(introductions);
          scenePojo.setVideo(introductions);
          String iframeStr = "<p id='youkuplayer' ></p>";
          StringBuffer introduction2 = new StringBuffer(scenePojo.getIntroduction());
          start = introduction2.indexOf("<iframe");
          end = introduction2.lastIndexOf("</iframe>") + 9;
          introduction2 = introduction2.replace(start, end, iframeStr);
          String introductions2 = introduction2.toString();
          System.out.println(introduction2);
          scenePojo.setIntroduction(introductions2);
        }
      }
      Map<String, Object> map1 = new HashMap<String, Object>();
      map1.put("sceneId", scenePojo.getId());
      map1.put("status", 1);
      map1.put("isIntroduce", 1);
      sceneProducts1 = sceneProductService.findSceneProductList(map1);
      if (sceneProducts1 != null && sceneProducts1.size() > 0) {
        ac.put("sceneProductPojos1", sceneProducts1);
      }
      Map<String, Object> map0 = new HashMap<String, Object>();
      map0.put("sceneId", scenePojo.getId());
      map0.put("status", 1);
      map0.put("isIntroduce", 0);
      sceneProducts0 = sceneProductService.findSceneProductList(map0);
      if (sceneProducts0 != null && sceneProducts0.size() > 0) {
        ac.put("sceneProductPojos0", sceneProducts0);
      }

    }
    return SUCCESS;
  }

  /**
   * 根据id删除场景产品
   * 
   * @return
   */
  public String delSceneProductById() throws Exception {

    try {
      sceneProductPojo = sceneProductService.findSceneProductById(sceneProductPojo.getId());
      sceneProductService.delSceneProductById(sceneProductPojo.getId());
      activityGoodsService.delActivityGoods(sceneProductPojo.getGoodId());
      FileUtil.alertMessageBySkip("删除成功！", "sceneProductList.do?sceneProductPojo.sceneId="
          + sceneProductPojo.getSceneId());
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("删除失败！", "sceneProductList.do?sceneProductPojo.sceneId="
          + sceneProductPojo.getSceneId());
    }
    return null;
  }

  /**
   * 根据id批量删除场景产品
   * 
   * @return
   */
  public String delSceneProductAllById() {
    try {
      for (String id : tids) {
        sceneProductPojo = sceneProductService.findSceneProductById(Long.valueOf(id));
        sceneProductService.delSceneProductById(Long.valueOf(id));
        activityGoodsService.delActivityGoods(sceneProductPojo.getGoodId());
      }
      FileUtil.alertMessageBySkip("全部删除成功！", "sceneProductList.do?sceneProductPojo.sceneId="
          + sceneProductPojo.getSceneId());
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("全部删除失败！", "sceneProductList.do?sceneProductPojo.sceneId="
          + sceneProductPojo.getSceneId());
    }
    return null;
  }

  /**
   * 跳转至编辑场景产品页面
   * 
   * @return
   * @throws Exception
   */
  public String goUpdateSceneProductById() throws Exception {
    sceneProductPojo = sceneProductService.findSceneProductById(sceneProductPojo.getId());
    return SUCCESS;
  }

  /**
   * 编辑场景产品
   * 
   * @return
   * @throws Throwable
   */
  public String updateSceneProductById() throws Throwable {
    try {
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/scene") + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/scene/", upfile);
        sceneProductPojo.setImage(file_name);
      }
      activityGoodsPojo = new ActivityGoodsPojo();
      Date time = new Date();
      activityGoodsPojo.setSorting(sceneProductPojo.getSorting());
      activityGoodsPojo.setSellPrice(sceneProductPojo.getSellPrice());
      activityGoodsPojo.setActivePrice(sceneProductPojo.getScenePrice());
      activityGoodsPojo.setStatus(sceneProductPojo.getStatus());
      activityGoodsPojo.setUpdateDate(time);
      activityGoodsPojo.setActivityStock(sceneProductPojo.getSceneStock());
      activityGoodsPojo.setActivityNum(sceneProductPojo.getSceneNum());
      sceneProductService.updateSceneProductById(sceneProductPojo);
      sceneProductPojo = sceneProductService.findSceneProductById(sceneProductPojo.getId());
      activityGoodsPojo.setId(sceneProductPojo.getGoodId());
      activityGoodsService.updateActivityGoods(activityGoodsPojo);
      FileUtil.alertMessageBySkip("提交成功！", "sceneProductList.do?sceneProductPojo.sceneId="
          + sceneProductPojo.getSceneId());
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("提交失败！", "sceneProductList.do?sceneProductPojo.sceneId="
          + sceneProductPojo.getSceneId());
    }
    return null;
  }

  /**
   * 通过审核场景产品
   * 
   * @return
   */
  public String checkSceneProductById() throws SQLException {
    try {
      sceneProductPojo = sceneProductService.findSceneProductById(sceneProductPojo.getId());
      activityGoodsService.checkActivityGoods(sceneProductPojo.getGoodId());
      // sceneProductService.checkSceneProductById(sceneProductPojo.getId());
      FileUtil.alertMessageBySkip("通过审核成功！", "sceneProductList.do?sceneProductPojo.sceneId="
          + sceneProductPojo.getSceneId());
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("通过审核失败！", "sceneProductList.do?sceneProductPojo.sceneId="
          + sceneProductPojo.getSceneId());
    }
    return null;
  }

  /**
   * 取消审核场景产品
   * 
   * @return
   */
  public String uncheckSceneProductById() throws SQLException {
    try {
      sceneProductPojo = sceneProductService.findSceneProductById(sceneProductPojo.getId());
      activityGoodsService.uncheckActivityGoods(sceneProductPojo.getGoodId());
      // sceneProductService.uncheckSceneProductById(sceneProductPojo.getId());
      FileUtil.alertMessageBySkip("取消审核成功！", "sceneProductList.do?sceneProductPojo.sceneId="
          + sceneProductPojo.getSceneId());
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("取消审核失败！", "sceneProductList.do?sceneProductPojo.sceneId="
          + sceneProductPojo.getSceneId());
    }
    return null;
  }

  /**
   * 根据id批量通过审核场景产品
   * 
   * @return
   */
  public String checkSceneProductAllById() {
    try {
      for (String id : tids) {
        sceneProductPojo = sceneProductService.findSceneProductById(Long.valueOf(id));
        activityGoodsService.checkActivityGoods(sceneProductPojo.getGoodId());
        // sceneProductService.checkSceneProductById(Long.valueOf(id));
      }
      FileUtil.alertMessageBySkip("全部审核通过成功！", "sceneProductList.do?sceneProductPojo.sceneId="
          + sceneProductPojo.getSceneId());

    } catch (Exception e) {
      FileUtil.alertMessageBySkip("全部审核通过失败！", "sceneProductList.do?sceneProductPojo.sceneId="
          + sceneProductPojo.getSceneId());
    }
    return null;
  }

  /**
   * 跳转至新增场景产品页面
   * 
   * @return
   */
  public String goAddSceneProductList() {
    return SUCCESS;
  }

  /**
   * 提交新增场景产品
   * 
   * @return
   */
  public String addSceneProductListOk() {
    try {
      if (sceneProductPojo != null) {
        Map<String, Object> param = new HashMap<String, Object>();
        JSONObject.fromObject(param);
        sceneProductService.insertSceneProduct(sceneProductPojo);
        FileUtil.alertMessageBySkip("新增场景产品成功！", "sceneProductList.do?sceneProductPojo.sceneId="
            + sceneProductPojo.getSceneId());
      }
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("新增场景产品失败！", "sceneProductList.do?sceneProductPojo.sceneId="
          + sceneProductPojo.getSceneId());
    }
    return null;
  }
}
