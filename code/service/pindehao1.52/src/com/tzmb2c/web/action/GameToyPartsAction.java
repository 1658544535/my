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
import com.tzmb2c.web.pojo.ActivityTimePojo;
import com.tzmb2c.web.pojo.GameFactoryToyPojo;
import com.tzmb2c.web.pojo.GameToyPartsPojo;
import com.tzmb2c.web.pojo.ProductSkuLinkPojo;
import com.tzmb2c.web.pojo.SpecialProductPojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.service.ActivityTimeService;
import com.tzmb2c.web.service.GameToyPartsService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ProductSkuLinkService;
import com.tzmb2c.web.service.SysDictService;

public class GameToyPartsAction extends SuperAction {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @Autowired
  private ActivityTimeService activityTimeService;
  @Autowired
  private GameToyPartsService gameToyPartsService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private ProductService productService;
  @Autowired
  private ProductSkuLinkService productSkuLinkService;

  private SpecialProductPojo specialProductPojo;
  private GameToyPartsPojo gameToyPartsPojo, gameToyParts;
  private ActivityTimePojo activityTimePojo;
  private List<SysDictPojo> statusSysDictList = null;
  private Long timeId;
  private Long productId;
  private String productName;
  private double sellPrice;
  private String result;
  private String[] tids;
  private String activityTime;
  private Integer t;
  private List<ProductSkuLinkPojo> productSkuLinkPojos;
  private ProductSkuLinkPojo productSkuLinkPojo;
  private GameFactoryToyPojo gameFactoryToyPojo;
  private Integer op;
  private String typeName;
  private String activityDate;
  private List<ActivityTimePojo> activityTimeList;
  private File upfile;
  private Long toyId;

  public List<ActivityTimePojo> getactivityTimeList() {
    return activityTimeList;
  }

  public void setActivityTimeList(List<ActivityTimePojo> activityTimeList) {
    this.activityTimeList = activityTimeList;
  }

  public ProductSkuLinkPojo getProductSkuLinkPojo() {
    return productSkuLinkPojo;
  }

  public void setProductSkuLinkPojo(ProductSkuLinkPojo productSkuLinkPojo) {
    this.productSkuLinkPojo = productSkuLinkPojo;
  }

  public GameFactoryToyPojo getGameFactoryToyPojo() {
    return gameFactoryToyPojo;
  }

  public void setGameFactoryToyPojo(GameFactoryToyPojo gameFactoryToyPojo) {
    this.gameFactoryToyPojo = gameFactoryToyPojo;
  }

  public List<ProductSkuLinkPojo> getProductSkuLinkPojos() {
    return productSkuLinkPojos;
  }

  public void setProductSkuLinkPojos(List<ProductSkuLinkPojo> productSkuLinkPojos) {
    this.productSkuLinkPojos = productSkuLinkPojos;
  }

  public String getActivityTime() {
    return activityTime;
  }

  public void setActivityTime(String activityTime) {
    this.activityTime = activityTime;
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



  public List<SysDictPojo> getStatusSysDictList() {
    return statusSysDictList;
  }

  public void setStatusSysDictList(List<SysDictPojo> statusSysDictList) {
    this.statusSysDictList = statusSysDictList;
  }

  public GameToyPartsPojo getGameToyPartsPojo() {
    return gameToyPartsPojo;
  }

  public void setGameToyPartsPojo(GameToyPartsPojo gameToyPartsPojo) {
    this.gameToyPartsPojo = gameToyPartsPojo;
  }

  public GameToyPartsPojo getGameToyParts() {
    return gameToyParts;
  }

  public void setGameToyParts(GameToyPartsPojo gameToyParts) {
    this.gameToyParts = gameToyParts;
  }

  public SpecialProductPojo getSpecialProductPojo() {
    return specialProductPojo;
  }

  public void setSpecialProductPojo(SpecialProductPojo specialProductPojo) {
    this.specialProductPojo = specialProductPojo;
  }

  public ActivityTimePojo getActivityTimePojo() {
    return activityTimePojo;
  }

  public void setActivityTimePojo(ActivityTimePojo activityTimePojo) {
    this.activityTimePojo = activityTimePojo;
  }

  public Long getTimeId() {
    return timeId;
  }

  public void setTimeId(Long timeId) {
    this.timeId = timeId;
  }

  public Long getToyId() {
    return toyId;
  }

  public void setToyId(Long toyId) {
    this.toyId = toyId;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public double getSellPrice() {
    return sellPrice;
  }

  public void setSellPrice(double sellPrice) {
    this.sellPrice = sellPrice;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  private void getDictList() {
    try {
      statusSysDictList = sysDictService.getSysDictListByType("status");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String findGameToyPartsList() throws SQLException {
    getDictList();
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    map.put("toyId", gameToyPartsPojo.getToyId());
    List<GameToyPartsPojo> list = gameToyPartsService.findGameToyPartsList(map);
    JSONArray json = JSONArray.fromObject(list);
    page.setRowCount(gameToyPartsService.findGameToyPartsCount(map));
    page.setResult(json.toString());
    return SUCCESS;

  }

  public String findGameToyPartsCount() throws SQLException {
    getDictList();
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("toyId", gameFactoryToyPojo.getId());
    int i = gameToyPartsService.findGameToyPartsCount(map);
    page.setRowCount(i);
    return SUCCESS;
  }

  public String findGameToyPartsById() throws SQLException {
    getDictList();
    gameToyPartsPojo = gameToyPartsService.findGameToyPartsById(gameToyPartsPojo.getId());
    return SUCCESS;
  }

  public String updateGameToyPartsList() throws SQLException {
    getDictList();
    gameToyPartsPojo = gameToyPartsService.findGameToyPartsById(gameToyPartsPojo.getId());
    return SUCCESS;
  }

  public void updateGameToyParts() throws Throwable {
    getDictList();
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("name", gameToyPartsPojo.getName());
    map.put("panduan", 1);
    map.put("toyId", toyId);
    List<GameToyPartsPojo> list = gameToyPartsService.findGameToyPartsList(map);
    if (list != null && list.size() != 0 && list.get(0).getId() != gameToyPartsPojo.getId()) {
      FileUtil.alertMessageBySkip("该名称已存在！", "updateGameToyPartsList.do?toyId=" + toyId
          + "&gameToyPartsPojo.id=" + gameToyPartsPojo.getId());
    } else {
      if (upfile != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/gameToyParts")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/gameToyParts/", upfile);
        gameToyPartsPojo.setImages(file_name);
      }
      gameToyPartsService.updateGameToyParts(gameToyPartsPojo);
      FileUtil.alertMessageBySkip("修改成功！", "gameToyParts.do?gameFactoryToyPojo.id=" + toyId);
    }


  }

  public String checkGameToyParts() throws SQLException {
    try {
      gameToyPartsService.checkGameToyParts(gameToyPartsPojo.getId());
      this.result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      this.result = "0";
    }
    return SUCCESS;
  }

  public String uncheckGameToyParts() throws SQLException {
    try {
      gameToyPartsService.uncheckGameToyParts(gameToyPartsPojo.getId());
      this.result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      this.result = "0";
    }
    return SUCCESS;
  }

  public String checkGameToyPartsAll() {
    StringBuffer url = new StringBuffer("gameToyParts.do?gameFactoryToyPojo.id=" + toyId);
    if (tids != null && tids.length > 0) {
      try {
        for (String tid : tids) {
          gameToyPartsService.checkGameToyParts(Long.parseLong(tid));
        }
        FileUtil.alertMessageBySkip("审核成功！", url.toString());
      } catch (SQLException e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("审核失败！", url.toString());
      }
    } else {
      FileUtil.alertMessageBySkip("没有勾选！", url.toString());
    }
    return null;
  }

  public String delGameToyParts() throws SQLException {
    try {
      gameToyPartsService.delGameToyParts(gameToyPartsPojo.getId());
      this.result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      this.result = "0";
    }
    return SUCCESS;
  }

  public String delGameToyPartsAll() {
    StringBuffer url = new StringBuffer("gameToyParts.do?gameFactoryToyPojo.id=" + toyId);
    if (tids != null && tids.length > 0) {
      try {
        for (String tid : tids) {
          gameToyPartsService.delGameToyParts(Long.parseLong(tid));
        }
        FileUtil.alertMessageBySkip("删除成功！", url.toString());
      } catch (SQLException e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("删除失败！", url.toString());
      }
    } else {
      FileUtil.alertMessageBySkip("没有勾选！", url.toString());
    }
    return null;
  }

  // 跳转至新增玩具配件页面
  public String addGameToyParts() throws Exception {
    return SUCCESS;
  }

  // 新增玩具配件提交
  public String insertGameToyParts() throws Throwable {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("name", gameToyPartsPojo.getName());
    map.put("panduan", 1);
    map.put("toyId", toyId);
    List<GameToyPartsPojo> list = gameToyPartsService.findGameToyPartsList(map);
    if (list != null && list.size() != 0) {
      FileUtil.alertMessageBySkip("该名称已存在！", "addGameToyParts.do?toyId=" + toyId);
      return null;
    }
    if (upfile != null) {
      String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      String uploadPath =
          ServletActionContext.getServletContext().getRealPath("/upfiles/gameToyParts")
              + File.separator;
      FileUtil.uploadFile(file_name, uploadPath, "upfiles/gameToyParts/", upfile);
      gameToyPartsPojo.setImages(file_name);
    }
    gameToyPartsPojo.setToyId(toyId);
    gameToyPartsService.insertGameToyParts(gameToyPartsPojo);
    FileUtil.alertMessageBySkip("添加成功！", "gameToyParts.do?gameFactoryToyPojo.id=" + toyId);

    return null;
  }


  public Integer getT() {
    return t;
  }

  public void setT(Integer t) {
    this.t = t;
  }

  public Integer getOp() {
    return op;
  }

  public void setOp(Integer op) {
    this.op = op;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public String getActivityDate() {
    return activityDate;
  }

  public void setActivityDate(String activityDate) {
    this.activityDate = activityDate;
  }

}
