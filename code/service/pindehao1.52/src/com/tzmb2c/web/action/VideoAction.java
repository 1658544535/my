package com.tzmb2c.web.action;


import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.ActivityTimePojo;
import com.tzmb2c.web.pojo.DataAuditPojo;
import com.tzmb2c.web.pojo.ProductSkuLinkPojo;
import com.tzmb2c.web.pojo.SpecialProductPojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.VideoPojo;
import com.tzmb2c.web.service.ActivityTimeService;
import com.tzmb2c.web.service.DataAuditService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.ProductSkuLinkService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.VideoService;

public class VideoAction extends SuperAction {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @Autowired
  private ActivityTimeService activityTimeService;
  @Autowired
  private VideoService videoService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private ProductService productService;
  @Autowired
  private ProductSkuLinkService productSkuLinkService;
  @Autowired
  private DataAuditService dataAuditService; 

  private SpecialProductPojo specialProductPojo;
  private VideoPojo videoPojo, video;
  private ActivityTimePojo activityTimePojo;
  private List<SysDictPojo> statusSysDictList = null;
  private Long timeId;
  private Long productId;
  private String productName;
  private double sellPrice;
  private String result;
  private String[] tids;
  private String activityTime;
  private List<ProductSkuLinkPojo> productSkuLinkPojos;
  private ProductSkuLinkPojo productSkuLinkPojo;
  private List<ActivityTimePojo> activityTimeList;
  private File upfile;
  private String os;
  private String[] channelIds;
  private Long panduan;
  private Long type;

  public Long getPanduan() {
    return panduan;
  }

  public void setPanduan(Long panduan) {
    this.panduan = panduan;
  }

  public String[] getChannelIds() {
    return channelIds;
  }

  public void setChannelIds(String[] channelIds) {
    this.channelIds = channelIds;
  }

  public String getOs() {
    return os;
  }

  public void setOs(String os) {
    this.os = os;
  }


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

  public VideoPojo getVideoPojo() {
    return videoPojo;
  }

  public void setVideoPojo(VideoPojo videoPojo) {
    this.videoPojo = videoPojo;
  }

  public VideoPojo getVideo() {
    return video;
  }

  public void setVideo(VideoPojo video) {
    this.video = video;
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
  

  public Long getType() {
    return type;
  }

  public void setType(Long type) {
    this.type = type;
  }

  private void getDictList() {
    try {
      statusSysDictList = sysDictService.getSysDictListByType("status");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String findVideoList() throws SQLException {
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (videoPojo != null) {
      map.put("label", videoPojo.getLabel());
      map.put("status", videoPojo.getStatus());
      map.put("age", videoPojo.getAge());
      map.put("skill", videoPojo.getSkill());
      map.put("type", videoPojo.getType());
    }
    map.put("os", os);
    List<VideoPojo> list = videoService.findVideoList(map);
    JSONArray json = JSONArray.fromObject(list);
    page.setRowCount(videoService.findVideoCount(map));
    page.setResult(json.toString());
    return SUCCESS;
  }

  public String findVideoCount() throws SQLException {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    if (videoPojo != null) {
      map.put("label", videoPojo.getLabel());
      map.put("source", videoPojo.getSource());
      map.put("status", videoPojo.getStatus());
      map.put("age", videoPojo.getAge());
      map.put("skill", videoPojo.getSkill());
      map.put("skill", videoPojo.getSkill());
      map.put("type", videoPojo.getType());
    }
    map.put("os", os);
    int i = videoService.findVideoCount(map);
    page.setRowCount(i);
    if ("2".equals(os)) {
      // 已审核视频库
      return "pass";
    }
    if ("4".equals(os)) {
      // 待编辑视频库
      return "waitEdit";
    }
    return SUCCESS;
  }

  public String findVideoCount2() throws SQLException {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    if (videoPojo != null) {
      map.put("label", videoPojo.getLabel());
      map.put("source", videoPojo.getSource());
      map.put("status", videoPojo.getStatus());
      map.put("age", videoPojo.getAge());
      map.put("skill", videoPojo.getSkill());
      map.put("skill", videoPojo.getSkill());
      map.put("type", videoPojo.getType());
    }
    map.put("os", os);
    int i = videoService.findVideoCount(map);
    page.setRowCount(i);
    return SUCCESS;
  }

  public String findVideoById() throws SQLException {
    getDictList();
    videoPojo = videoService.findVideoById(videoPojo.getId());
    return SUCCESS;
  }

  public String updateVideoList() throws SQLException {
    getDictList();
    videoPojo = videoService.findVideoById(videoPojo.getId());
    return SUCCESS;
  }



  public void updateVideo() throws Throwable {
    // try {
    // // 多选类型，以":"分隔（编辑）
    // if (channelIds != null && channelIds.length > 0) {
    // videoPojo.setType(StringUtils.join(channelIds, "，"));
    // } else {
    // videoPojo.setType("");
    // }
    // videoService.updateVideo(videoPojo);
    // videoService.checkVideo(videoPojo.getId());
    // if (panduan == 1) {
    // FileUtil.alertMessageBySkip("审核成功！", "video.do?os=2");
    // } else {
    // FileUtil.alertMessageBySkip("修改成功！", "video.do?os=2");
    // }
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    try {
      // 获取视频类型，多选类型，以":"分隔（编辑）
      if (channelIds != null && channelIds.length > 0) {
        videoPojo.setType(StringUtils.join(channelIds, "，"));
      } else {
        videoPojo.setType("");
      }
      //将数据更新进数据库
      videoService.updateVideo(videoPojo);
      
      
      //页面跳转逻辑
      //panduan这个变量的值是从页面传递过来的
      //待审核视频进行审核时:panduan == 1，页面跳转到待审核页面
      //审核成功视频进行编辑时：panduan == 2，页面跳转到审核成功页面
      //待编辑视频进行编辑时：panduan == 3，页面跳转到待编辑页面
      if (panduan == 1) {
        if(type == 1){
          videoService.checkVideo(videoPojo.getId());
          checkVideo(videoPojo);
          FileUtil.alertMessageBySkip("审核成功！", "video.do?os=1");
        }else if(type == 2){
          videoService.editVideo(videoPojo.getId());
          setVideoWaitEdit(videoPojo);
          FileUtil.alertMessageBySkip("设置为待编辑成功！", "video.do?os=1");
        }
       
      } else if (panduan == 2) {
        FileUtil.alertMessageBySkip("修改成功！", "video.do?os=2");
      } else if (panduan == 3) {
        FileUtil.alertMessageBySkip("修改成功！", "video.do?os=4");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * 此方法的作用是设置视频为待编辑状态 修改数据库视频记录，将status修改为3 审核状态（0-未审核、1-审核通过、2-审核不通过、3-待编辑）
   * 
   * @throws SQLException
   * @return void
   */
  public void setVideoWaitEdit(VideoPojo videoPojo) throws SQLException {

    if (videoPojo != null) {
//      videoService.updateVideo(videoPojo);
      //构建一个DataAuditPojo，用来保存视频审核记录信息
      DataAuditPojo dataAudit = new DataAuditPojo();
      //设置type=1（1：视频）
      dataAudit.setType("1");
      //设置tableName
      dataAudit.setTableName("video");
      //设置业务表对应的id
      dataAudit.setTableLinkId(videoPojo.getId().intValue());
      //获取当前用户id，并记录
      SysLoginPojo sysLoginPojo = UserUtil.getUser();
      dataAudit.setOperationUserId(sysLoginPojo.getId().intValue());
      //设置业务类型（1->审核成功；2->审核失败;3->待编辑;4->删除）
      dataAudit.setOperationType("3");
      //设置时间（格式：2016-06-28 17:18:00）
      dataAudit.setOperationData(new Date());
      //设置时间（格式：2016-06-28）
      dataAudit.setSimpleData(StringUtil.dateToString(new Date()));
      //将数据插入视频记录表
      dataAuditService.add(dataAudit);
      // 设置视频为待编辑成功，页面跳转
      FileUtil.alertMessageBySkip("操作成功！", "video.do?os=1");
    } else {
      // 设置视频为待编辑失败，页面跳转
      FileUtil.alertMessageBySkip("操作失败！", "video.do?os=1");
    }
  }



  public String checkVideo(VideoPojo videoPojo) throws SQLException {
    try {
//      videoService.checkVideo(videoPojo.getId());
      //构建一个DataAuditPojo，用来保存视频审核记录信息
      DataAuditPojo dataAudit = new DataAuditPojo();
      //设置type=1（1：视频）
      dataAudit.setType("1");
      //设置tableName
      dataAudit.setTableName("video");
      //设置业务表对应的id
      dataAudit.setTableLinkId(videoPojo.getId().intValue());
      //获取当前用户id，并记录
      SysLoginPojo sysLoginPojo = UserUtil.getUser();
      dataAudit.setOperationUserId(sysLoginPojo.getId().intValue());
      //设置业务类型（1->审核成功；2->审核失败;3->待编辑;4->删除）
      dataAudit.setOperationType("1");
      //设置时间（格式：2016-06-28 17:18:00）
      dataAudit.setOperationData(new Date());
      //设置时间（格式：2016-06-28）
      dataAudit.setSimpleData(StringUtil.dateToString(new Date()));
      //将数据插入视频记录表
      dataAuditService.add(dataAudit);
      result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      result = "0";
    }
    return SUCCESS;
  }

  public String uncheckVideo() throws SQLException {
    try {
      videoService.uncheckVideo(videoPojo.getId());
      //构建一个DataAuditPojo，用来保存视频审核记录信息
      DataAuditPojo dataAudit = new DataAuditPojo();
      //设置type=1（1：视频）
      dataAudit.setType("1");
      //设置tableName
      dataAudit.setTableName("video");
      //设置业务表对应的id
      dataAudit.setTableLinkId(videoPojo.getId().intValue());
      //获取当前用户id，并记录
      SysLoginPojo sysLoginPojo = UserUtil.getUser();
      dataAudit.setOperationUserId(sysLoginPojo.getId().intValue());
      //设置业务类型（1->审核成功；2->审核失败;3->待编辑;4->删除）
      dataAudit.setOperationType("2");
      //设置时间（格式：2016-06-28 17:18:00）
      dataAudit.setOperationData(new Date());
      //设置时间（格式：2016-06-28）
      dataAudit.setSimpleData(StringUtil.dateToString(new Date()));
      //将数据插入视频记录表
      dataAuditService.add(dataAudit);
      result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      result = "0";
    }
    return SUCCESS;
  }

  public String delVideo() throws SQLException {
    try {
      //videoService.delVideo(videoPojo.getId());
      videoPojo.setIsDelete(1L);
      videoService.updateVideo(videoPojo);
    //构建一个DataAuditPojo，用来保存视频审核记录信息
      DataAuditPojo dataAudit = new DataAuditPojo();
      //设置type=1（1：视频）
      dataAudit.setType("1");
      //设置tableName
      dataAudit.setTableName("video");
      //设置业务表对应的id
      dataAudit.setTableLinkId(videoPojo.getId().intValue());
      //获取当前用户id，并记录
      SysLoginPojo sysLoginPojo = UserUtil.getUser();
      dataAudit.setOperationUserId(sysLoginPojo.getId().intValue());
      //设置业务类型（1->审核成功；2->审核失败;3->待编辑;4->删除）
      dataAudit.setOperationType("4");
      //设置时间（格式：2016-06-28 17:18:00）
      dataAudit.setOperationData(new Date());
      //设置时间（格式：2016-06-28）
      dataAudit.setSimpleData(StringUtil.dateToString(new Date()));
      //将数据插入视频记录表
      dataAuditService.add(dataAudit);
      result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      result = "0";
    }
    return SUCCESS;
  }

  // 跳转至新增视频页面
  public String addVideo() throws Exception {
    return SUCCESS;
  }

  // 新增视频提交
  public String insertVideo() throws Throwable {
    Map<String, Object> map = new HashMap<String, Object>();
    if (videoPojo != null) {
      map.put("url", videoPojo.getUrl());
    }
    List<VideoPojo> list = videoService.findVideoList(map);
    if (list.size() == 0) {
      if (channelIds != null && channelIds.length > 0) {
        videoPojo.setType(StringUtils.join(channelIds, "，"));
      } else {
        videoPojo.setType("");
      }
      videoService.insertVideo(videoPojo);
      FileUtil.alertMessageBySkip("添加成功！", "video.do?os=2");
    } else {
      FileUtil.alertMessageBySkip("视频库中已存在该视频链接！", "addVideo.do");
    }
    return null;
  }

  /*
   * public String checkVideoAll() { StringBuffer url = new StringBuffer("video.do"); if (tids !=
   * null && tids.length > 0) { try { for (String tid : tids) {
   * videoService.checkVideo(Long.parseLong(tid)); } FileUtil.alertMessageBySkip("审核成功！",
   * url.toString()); } catch (SQLException e) { e.printStackTrace();
   * FileUtil.alertMessageBySkip("审核失败！", url.toString()); } } else {
   * FileUtil.alertMessageBySkip("没有勾选！", url.toString()); } return null; }
   * 
   * public String delVideo() throws SQLException { try { videoService.delVideo(videoPojo.getId());
   * this.result = "1"; } catch (Exception e) { e.printStackTrace(); this.result = "0"; } return
   * SUCCESS; }
   * 
   * public String delVideoAll() { StringBuffer url = new StringBuffer("video.do"); if (tids != null
   * && tids.length > 0) { try { for (String tid : tids) {
   * videoService.delVideo(Long.parseLong(tid)); } FileUtil.alertMessageBySkip("删除成功！",
   * url.toString()); } catch (SQLException e) { e.printStackTrace();
   * FileUtil.alertMessageBySkip("删除失败！", url.toString()); } } else {
   * FileUtil.alertMessageBySkip("没有勾选！", url.toString()); } return null; }
   * 
   * // 跳转至新增玩具页面 public String addVideo() throws Exception { return SUCCESS; }
   * 
   * // 新增玩具提交 public String insertVideo() throws Throwable { Map<String, Object> map = new
   * HashMap<String, Object>(); map.put("name", videoPojo.getName()); map.put("panduan", 1);
   * List<VideoPojo> list = videoService.findVideoList(map); if (list != null && list.size() != 0) {
   * FileUtil.alertMessageBySkip("该名称已存在！", "addVideo.do"); return null; } if (upfile != null) {
   * String file_name = StringUtil.getCurrentDateStr() + ".jpg"; String uploadPath =
   * ServletActionContext.getServletContext().getRealPath("/upfiles/video") + File.separator;
   * FileUtil.uploadFile(file_name, uploadPath, "upfiles/video/", upfile);
   * videoPojo.setImages(file_name); } videoService.insertVideo(videoPojo);
   * FileUtil.alertMessageBySkip("添加成功！", "video.do");
   * 
   * return null; }
   * 
   * 
   * public Integer getT() { return t; }
   * 
   * public void setT(Integer t) { this.t = t; }
   * 
   * public Integer getOp() { return op; }
   * 
   * public void setOp(Integer op) { this.op = op; }
   * 
   * public String getTypeName() { return typeName; }
   * 
   * public void setTypeName(String typeName) { this.typeName = typeName; }
   * 
   * public String getActivityDate() { return activityDate; }
   * 
   * public void setActivityDate(String activityDate) { this.activityDate = activityDate; }
   * 
   * public String getUpfileFileName() { return upfileFileName; }
   * 
   * public void setUpfileFileName(String upfileFileName) { this.upfileFileName = upfileFileName; }
   */
}
