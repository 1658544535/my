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

import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.CompressPicture;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.mapper.UserCirclePostMapper;
import com.tzmb2c.web.pojo.ActivityTimePojo;
import com.tzmb2c.web.pojo.LabelPojo;
import com.tzmb2c.web.pojo.ProductSkuLinkPojo;
import com.tzmb2c.web.pojo.SocialCirclePojo;
import com.tzmb2c.web.pojo.SpecialProductPojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.TemplatePageDataPojo;
import com.tzmb2c.web.pojo.UserBabyPojo;
import com.tzmb2c.web.pojo.UserCalendarPojo;
import com.tzmb2c.web.pojo.UserCirclePostPojo;
import com.tzmb2c.web.pojo.UserPostHistoryPojo;
import com.tzmb2c.web.pojo.UserTaskPojo;
import com.tzmb2c.web.pojo.YourFavouritesPojo;
import com.tzmb2c.web.service.LabelService;
import com.tzmb2c.web.service.SocialCircleService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.TemplatePageDataService;
import com.tzmb2c.web.service.UserBabyService;
import com.tzmb2c.web.service.UserCalendarService;
import com.tzmb2c.web.service.UserCirclePostService;
import com.tzmb2c.web.service.UserGrowthLineService;
import com.tzmb2c.web.service.UserPostHistoryService;
import com.tzmb2c.web.service.UserTaskService;

public class UserCirclePostAction extends SuperAction {
  /**
   *
   */
  private static final long serialVersionUID = 1L;
  @Autowired
  private UserCirclePostService userCirclePostService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private LabelService labelService;
  @Autowired
  private UserTaskService userTaskService;
  @Autowired
  private UserGrowthLineService userGrowthLineService;
  @Autowired
  private UserPostHistoryService userPostHistoryService;
  @Autowired
  private UserCirclePostMapper userCirclePostMapper;
  @Autowired
  private UserCalendarService userCalendarService;
  @Autowired
  private UserBabyService userBabyService;
  @Autowired
  private TemplatePageDataService templatePageDataService;
  @Autowired
  private SocialCircleService socialCircleService;

  private SpecialProductPojo specialProductPojo;
  private UserCirclePostPojo userCirclePostPojo, userCirclePost;
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
  private LabelPojo labelPojo;
  private List<ActivityTimePojo> activityTimeList;
  private File upfile;
  private List<LabelPojo> labelPojoList;
  private List<SysLoginPojo> sysLoginPojos;
  private File upfile1;
  private File upfile2;
  private UserBabyPojo userBabyPojo;

  /**
   * 笔记ID
   */
  private Long postId;
  /**
   * 用户ID
   */
  private Long userId;
  private YourFavouritesPojo yourFavouritesPojo;
  private Map<String, Object> dataMap;
  private File files;
  private TemplatePageDataPojo templatePageDataPojo;
  private List<SocialCirclePojo> socialCirclePojoList;
  private Long id;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<SocialCirclePojo> getSocialCirclePojoList() {
    return socialCirclePojoList;
  }

  public void setSocialCirclePojoList(List<SocialCirclePojo> socialCirclePojoList) {
    this.socialCirclePojoList = socialCirclePojoList;
  }

  public TemplatePageDataPojo getTemplatePageDataPojo() {
    return templatePageDataPojo;
  }

  public void setTemplatePageDataPojo(TemplatePageDataPojo templatePageDataPojo) {
    this.templatePageDataPojo = templatePageDataPojo;
  }

  public File getFiles() {
    return files;
  }

  public void setFiles(File files) {
    this.files = files;
  }

  public Map<String, Object> getDataMap() {
    return dataMap;
  }

  public void setDataMap(Map<String, Object> dataMap) {
    this.dataMap = dataMap;
  }

  public YourFavouritesPojo getYourFavouritesPojo() {
    return yourFavouritesPojo;
  }

  public void setYourFavouritesPojo(YourFavouritesPojo yourFavouritesPojo) {
    this.yourFavouritesPojo = yourFavouritesPojo;
  }

  public UserBabyPojo getUserBabyPojo() {
    return userBabyPojo;
  }

  public void setUserBabyPojo(UserBabyPojo userBabyPojo) {
    this.userBabyPojo = userBabyPojo;
  }

  public File getUpfile1() {
    return upfile1;
  }

  public void setUpfile1(File upfile1) {
    this.upfile1 = upfile1;
  }

  public File getUpfile2() {
    return upfile2;
  }

  public void setUpfile2(File upfile2) {
    this.upfile2 = upfile2;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getPostId() {
    return postId;
  }

  public void setPostId(Long postId) {
    this.postId = postId;
  }

  public List<SysLoginPojo> getSysLoginPojos() {
    return sysLoginPojos;
  }

  public void setSysLoginPojos(List<SysLoginPojo> sysLoginPojos) {
    this.sysLoginPojos = sysLoginPojos;
  }

  public List<LabelPojo> getLabelPojoList() {
    return labelPojoList;
  }

  public void setLabelPojoList(List<LabelPojo> labelPojoList) {
    this.labelPojoList = labelPojoList;
  }

  public List<ActivityTimePojo> getactivityTimeList() {
    return activityTimeList;
  }

  public void setActivityTimeList(List<ActivityTimePojo> activityTimeList) {
    this.activityTimeList = activityTimeList;
  }

  public LabelPojo getLabelPojo() {
    return labelPojo;
  }

  public void setLabelPojo(LabelPojo labelPojo) {
    this.labelPojo = labelPojo;
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

  public UserCirclePostPojo getUserCirclePostPojo() {
    return userCirclePostPojo;
  }

  public void setUserCirclePostPojo(UserCirclePostPojo userCirclePostPojo) {
    this.userCirclePostPojo = userCirclePostPojo;
  }

  public UserCirclePostPojo getUserCirclePost() {
    return userCirclePost;
  }

  public void setUserCirclePost(UserCirclePostPojo userCirclePost) {
    this.userCirclePost = userCirclePost;
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

  private void getDictList() {
    try {
      statusSysDictList = sysDictService.getSysDictListByType("status");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  public String findUserCirclePostList() throws SQLException {
    getDictList();
    Map<String, Object> map = new HashMap<String, Object>();
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (userCirclePostPojo != null) {
      map.put("title", userCirclePostPojo.getTitle());
      map.put("status", userCirclePostPojo.getStatus());
      map.put("ageType", userCirclePostPojo.getAgeType());
      map.put("skillType", userCirclePostPojo.getSkillType());
      map.put("secSkillType", userCirclePostPojo.getSecSkillType());
      map.put("optionType", userCirclePostPojo.getOptionType());
      map.put("productType", userCirclePostPojo.getProductType());
      map.put("createDateStartStr", userCirclePostPojo.getCreateDateStartStr());
      map.put("createDateEndStr", userCirclePostPojo.getCreateDateEndStr());
      map.put("userName", userCirclePostPojo.getUserName());

    }
    map.put("hou_tai", 1);
    List<UserCirclePostPojo> list = userCirclePostService.userCirclePostList(map);
    JSONArray json = JSONArray.fromObject(list);
    // page.setRowCount(userCirclePostService.userCirclePostCount(map));
    page.setResult(json.toString());
    return SUCCESS;
  }

  public String findUserCirclePostCount() throws SQLException {
    getDictList();
    Map<String, Object> map1 = new HashMap<String, Object>();
    labelPojoList = labelService.findLabelList(map1);
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    if (userCirclePostPojo != null) {
      map.put("title", userCirclePostPojo.getTitle());
      map.put("status", userCirclePostPojo.getStatus());
      map.put("ageType", userCirclePostPojo.getAgeType());
      map.put("skillType", userCirclePostPojo.getSkillType());
      map.put("secSkillType", userCirclePostPojo.getSecSkillType());
      map.put("optionType", userCirclePostPojo.getOptionType());
      map.put("productType", userCirclePostPojo.getProductType());
      map.put("createDateStartStr", userCirclePostPojo.getCreateDateStartStr());
      map.put("createDateEndStr", userCirclePostPojo.getCreateDateEndStr());
      map.put("userName", userCirclePostPojo.getUserName());
    }
    map.put("hou_tai", 1);
    int i = userCirclePostService.userCirclePostCount(map);
    page.setRowCount(i);
    return SUCCESS;
  }

  public String findUserCirclePostById() throws SQLException {
    getDictList();
    userCirclePostPojo = userCirclePostService.getUserCirclePostById(userCirclePostPojo.getId());
    return SUCCESS;
  }

  public String updateUserCirclePostList() throws SQLException {
    if (userCirclePostPojo != null) {
      getDictList();
      userCirclePostPojo = userCirclePostService.getUserCirclePostById(userCirclePostPojo.getId());
      Map<String, Object> map = new HashMap<String, Object>();
      labelPojoList = labelService.findLabelList(map);
      map.put("status", 1);
      socialCirclePojoList = socialCircleService.findSocialCircleList(map);
      if (userCirclePostPojo != null && userCirclePostPojo.getVersion() == 1) {
        // 查询自定义笔记详情页面数据
        Map<String, Object> params = new HashMap<String, Object>();
        if (userCirclePostPojo.getId() != null) {
          params.put("pageId", userCirclePostPojo.getId());
          params.put("type", 3);
          templatePageDataPojo = templatePageDataService.findByParams(params);
          if (templatePageDataPojo == null) {
            templatePageDataPojo = new TemplatePageDataPojo();
            templatePageDataPojo.setData("{}");
          }
        }
        return SUCCESS;
      } else {
        return "oldVersion";
      }
    }
    return SUCCESS;
  }

  public String updateUserCirclePost() throws Throwable {
    try {

      if (upfile1 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCirclePost")
                + File.separator;
        // FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCirclePost/", upfile1);

        // 图片压缩
        CompressPicture cp = new CompressPicture();
        cp.compressPic(upfile1, uploadPath, uploadPath, file_name, 0, 0, false);
        userCirclePostPojo.setBanner(file_name);
      }
      // if (upfile2 != null) {
      // String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      // String uploadPath =
      // ServletActionContext.getServletContext().getRealPath("/upfiles/userCirclePost")
      // + File.separator;
      // FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCirclePost/", upfile2);
      // userCirclePostPojo.setImage(file_name);
      // }
      userCirclePostService.updateUserCirclePost(userCirclePostPojo);
      // FileUtil.alertMessageBySkip("编辑成功！", "userCirclePost.do");
      this.result = userCirclePostPojo.getId().toString();
    } catch (Exception e) {
      e.printStackTrace();
      this.result = null;
    }
    return SUCCESS;
  }



  public String checkUserCirclePost() throws SQLException {
    try {
      userCirclePostService.checkUserCirclePost(userCirclePostPojo.getId());
      result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      result = "0";
    }
    return SUCCESS;
  }

  public String uncheckUserCirclePost() throws SQLException {
    try {
      userCirclePostService.uncheckUserCirclePost(userCirclePostPojo.getId());
      result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      result = "0";
    }
    return SUCCESS;
  }

  public String checkUserCirclePostAll() {
    StringBuffer url = new StringBuffer("userCirclePost.do");
    if (tids != null && tids.length > 0) {

      for (String tid : tids) {
        userCirclePostService.checkUserCirclePost(Long.parseLong(tid));
      }
      FileUtil.alertMessageBySkip("审核成功！", url.toString());


    } else {
      FileUtil.alertMessageBySkip("没有勾选！", url.toString());
    }
    return null;
  }

  public String delUserCirclePost() throws SQLException {
    try {
      userCirclePostService.deleteUserCirclePostById(userCirclePostPojo.getId());
      result = "1";
    } catch (Exception e) {
      e.printStackTrace();
      result = "0";
    }
    return SUCCESS;
  }

  public String delUserCirclePostAll() {
    StringBuffer url = new StringBuffer("userCirclePost.do");
    if (tids != null && tids.length > 0) {
      for (String tid : tids) {
        userCirclePostService.deleteUserCirclePostById(Long.parseLong(tid));
      }
      FileUtil.alertMessageBySkip("删除成功！", url.toString());
    } else {
      FileUtil.alertMessageBySkip("没有勾选！", url.toString());
    }
    return null;
  }

  /**
   * 帖子详情
   * 
   * @return String
   * @throws Exception
   */
  public String goPostView() throws Exception {
    if (postId == null || postId == 0) {
      System.out.println(">>>>>> postId为空!");
    }
    // if (userId == null || userId == 0) {
    // System.out.println(">>>>>> userId为空!");
    // } else {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("id", postId);
    params.put("status", 1);
    userCirclePost = userCirclePostService.findBycircleParams(params);
    if (userCirclePost == null) {
      System.out.println(">>>>>> postId=" + postId + " 查不到!");
    } else {
      // 判断连接是否可用
      boolean isMatch = SellerService.IsUrl(userCirclePost.getVideoUrl());
      if (isMatch) {
        System.out.println("连接可用");
      } else {
        userCirclePost.setVideoUrl("");
        System.out.println("连接不可用!");
      }
      // 查询是否存在浏览记录
      params.clear();
      params.put("postId", postId);
      params.put("userId", userId);
      params.put("isDelete", 0);
      int i = userPostHistoryService.countBy(params);
      if (i > 0) {
        params.put("lookNum", 1l);
        params.put("viewDate", new Date());
        params.put("updateDate", new Date());
        userPostHistoryService.addLookNum(params);
      } else {
        // 添加笔记浏览记录
        UserPostHistoryPojo userPostHistory = new UserPostHistoryPojo();
        userPostHistory.setUserId(userId);
        userPostHistory.setPostId(postId);
        userPostHistory.setIsDelete(0);
        userPostHistory.setLookNum(1l);
        userPostHistory.setViewDate(new Date());
        userPostHistory.setUpdateDate(new Date());
        userPostHistoryService.add(userPostHistory);
      }
      // 添加浏览数
      UserCirclePostPojo userCirclePostPojo = new UserCirclePostPojo();
      userCirclePostPojo.setLookNum(1l);
      userCirclePostPojo.setId(postId);
      userCirclePostService.increaseUserCirclePostNumById(userCirclePostPojo);
      if (userId != null) {
        // 查找宝宝信息
        params.clear();
        params.put("userId", userId);
        params.put("isDefault", 1);
        params.put("isDelete", 0);
        userBabyPojo = userBabyService.getByParams(params);
        if (userBabyPojo == null || userBabyPojo.getBabyBirthday() == null) {
          FileUtil.alertMessage("宝宝信息未找到！");
        } else {
          // 判断是否领取任务
          params.clear();
          params.put("userId", userId);
          params.put("taskAge", userCirclePost.getAgeType());
          params.put("ability", userCirclePost.getSkillType());
          params.put("taskTypeLink", 1);
          List<UserTaskPojo> userTaskList = userTaskService.checkBrowsePostTask(params);
          // 宝宝做任务
          if (userTaskList != null && userTaskList.size() > 0) {
            for (UserTaskPojo userTask : userTaskList) {
              if (userTask.getStatus() != 1) {
                // 修改任务为完成
                UserTaskPojo userTaskPojo = new UserTaskPojo();
                userTaskPojo.setId(userTask.getId());
                userTaskPojo.setEndTime(new Date());
                userTaskPojo.setStatus(1);
                userTaskService.update(userTaskPojo);
                // 添加积分
                params.clear();
                params.put("userId", userId);
                params.put("babyId", userBabyPojo.getId());
                params.put("score", userTask.getTaskScore());
                SellerService.calcGrowLine(Integer.parseInt(userTask.getAbility()),
                    userTask.getTaskScore(), params);
                userGrowthLineService.addUserScore(params);
              }
            }
            // 查询年龄
            double age = SellerService.getAge(userBabyPojo.getBabyBirthday());
            int ageType = 1;
            // 查询年龄对应专题
            if (age >= 0.0 && age <= 0.06) {
              ageType = 1;
            } else if (age > 0.06 && age <= 1.0) {
              ageType = 2;
            } else if (age > 1.0 && age <= 3.0) {
              ageType = 3;
            } else if (age > 3.0 && age <= 6.0) {
              ageType = 4;
            } else if (age > 6.0 && age <= 12.0) {
              ageType = 5;
            } else if (age > 12.0 && age <= 16.0) {
              ageType = 6;
            } else if (age > 16.0) {
              ageType = 6;
            }
            if (ageType > 0) {
              Date dt = new Date();
              params.clear();
              params.put("userId", userId);
              params.put("ageType", ageType);
              params.put("taskDate", StringUtil.dateToString(dt));
              int count = userTaskService.isALLTaskDone(params);
              // 没有未完成的任务
              if (count == 0) {
                UserCalendarPojo ucal = new UserCalendarPojo();
                ucal.setUserId(userId);
                ucal.setCalendar(dt);
                userCalendarService.add(ucal);
              }
            }
          }
        }
      }
    }
    // }
    return SUCCESS;
  }

  // 跳转至新增页面
  public String addUserCirclePost() throws Exception {
    getDictList();
    Map<String, Object> map = new HashMap<String, Object>();
    labelPojoList = labelService.findLabelList(map);
    map.put("status", 1);
    socialCirclePojoList = socialCircleService.findSocialCircleList(map);
    return SUCCESS;
  }

  // 新增提交
  public String insertUserCirclePost() throws Throwable {
    try {
      if (upfile1 != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCirclePost")
                + File.separator;
        // FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCirclePost/", upfile1);

        // 图片压缩
        CompressPicture cp = new CompressPicture();
        cp.compressPic(upfile1, uploadPath, uploadPath, file_name, 0, 0, false);
        userCirclePostPojo.setBanner(file_name);
      }
      // if (upfile2 != null) {
      // String file_name = StringUtil.getCurrentDateStr() + ".jpg";
      // String uploadPath =
      // ServletActionContext.getServletContext().getRealPath("/upfiles/userCirclePost")
      // + File.separator;
      // FileUtil.uploadFile(file_name, uploadPath, "upfiles/userCirclePost/", upfile2);
      // userCirclePostPojo.setImage(file_name);
      // }
      userCirclePostPojo.setLookNum(0l);
      userCirclePostPojo.setLikeNum(0l);
      userCirclePostPojo.setCommentNum(0l);
      userCirclePostPojo.setCollectNum(0l);
      userCirclePostMapper.addUserCirclePost(userCirclePostPojo);
      // FileUtil.alertMessageBySkip("添加成功！", "userCirclePost.do");
      this.result = userCirclePostPojo.getId().toString();
    } catch (Exception e) {
      e.printStackTrace();
      // FileUtil.alertMessageBySkip("添加失败！", "userCirclePost.do");
      this.result = null;
    }
    return SUCCESS;
    // return null;
  }

  /**
   * 
   * 笔记详情-上传图片
   * 
   * @return
   * @throw
   * @return String
   */
  public String postImgUpfile() {
    try {
      dataMap = new HashMap<String, Object>();
      if (files != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userCirclePost")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/platformSpecial/", files);
        dataMap.put("src", "/upfiles/userCirclePost/" + file_name);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 
   * 笔记可视化web页面
   * 
   * @return
   * @throw
   * @return String
   * @throws Exception
   */
  public String getUserCirclePostVisApi() throws Exception {
    if (id == null || id == 0) {
      System.out.println("笔记id不能为空!");
    } else {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("id", id);
      params.put("status", 1);
      // UserCirclePostPojo userCirclePostPojo = userCirclePostService.getUserCirclePostById(id);
      userCirclePost = userCirclePostService.findBycircleParams(params);
      if (userCirclePost == null || userCirclePost.getStatus() == 0) {
        System.out.println("查询不到该笔记!");
      } else {
        if (userCirclePost.getVersion() != null && userCirclePost.getVersion() == 0) {

          // 判断连接是否可用
          boolean isMatch = SellerService.IsUrl(userCirclePost.getVideoUrl());
          if (isMatch) {
            System.out.println("连接可用");
          } else {
            userCirclePost.setVideoUrl("");
            System.out.println("连接不可用!");
          }
          // 查询是否存在浏览记录
          params.clear();
          params.put("postId", id);
          params.put("userId", userId);
          params.put("isDelete", 0);
          int i = userPostHistoryService.countBy(params);
          if (i > 0) {
            params.put("lookNum", 1l);
            params.put("viewDate", new Date());
            params.put("updateDate", new Date());
            userPostHistoryService.addLookNum(params);
          } else {
            // 添加笔记浏览记录
            UserPostHistoryPojo userPostHistory = new UserPostHistoryPojo();
            userPostHistory.setUserId(userId);
            userPostHistory.setPostId(id);
            userPostHistory.setIsDelete(0);
            userPostHistory.setLookNum(1l);
            userPostHistory.setViewDate(new Date());
            userPostHistory.setUpdateDate(new Date());
            userPostHistoryService.add(userPostHistory);
          }
          // 添加浏览数
          UserCirclePostPojo userCirclePostPojo = new UserCirclePostPojo();
          userCirclePostPojo.setLookNum(1l);
          userCirclePostPojo.setId(id);
          userCirclePostService.increaseUserCirclePostNumById(userCirclePostPojo);
          if (userId != null) {
            // 查找宝宝信息
            params.clear();
            params.put("userId", userId);
            params.put("isDefault", 1);
            params.put("isDelete", 0);
            userBabyPojo = userBabyService.getByParams(params);
            if (userBabyPojo == null || userBabyPojo.getBabyBirthday() == null) {
              FileUtil.alertMessage("宝宝信息未找到！");
            } else {
              // 判断是否领取任务
              params.clear();
              params.put("userId", userId);
              params.put("taskAge", userCirclePost.getAgeType());
              params.put("ability", userCirclePost.getSkillType());
              params.put("taskTypeLink", 1);
              List<UserTaskPojo> userTaskList = userTaskService.checkBrowsePostTask(params);
              // 宝宝做任务
              if (userTaskList != null && userTaskList.size() > 0) {
                for (UserTaskPojo userTask : userTaskList) {
                  if (userTask.getStatus() != 1) {
                    // 修改任务为完成
                    UserTaskPojo userTaskPojo = new UserTaskPojo();
                    userTaskPojo.setId(userTask.getId());
                    userTaskPojo.setEndTime(new Date());
                    userTaskPojo.setStatus(1);
                    userTaskService.update(userTaskPojo);
                    // 添加积分
                    params.clear();
                    params.put("userId", userId);
                    params.put("babyId", userBabyPojo.getId());
                    params.put("score", userTask.getTaskScore());
                    SellerService.calcGrowLine(Integer.parseInt(userTask.getAbility()),
                        userTask.getTaskScore(), params);
                    userGrowthLineService.addUserScore(params);
                  }
                }
                // 查询年龄
                double age = SellerService.getAge(userBabyPojo.getBabyBirthday());
                int ageType = 1;
                // 查询年龄对应专题
                if (age >= 0.0 && age <= 0.06) {
                  ageType = 1;
                } else if (age > 0.06 && age <= 1.0) {
                  ageType = 2;
                } else if (age > 1.0 && age <= 3.0) {
                  ageType = 3;
                } else if (age > 3.0 && age <= 6.0) {
                  ageType = 4;
                } else if (age > 6.0 && age <= 12.0) {
                  ageType = 5;
                } else if (age > 12.0 && age <= 16.0) {
                  ageType = 6;
                } else if (age > 16.0) {
                  ageType = 6;
                }
                if (ageType > 0) {
                  Date dt = new Date();
                  params.clear();
                  params.put("userId", userId);
                  params.put("ageType", ageType);
                  params.put("taskDate", StringUtil.dateToString(dt));
                  int count = userTaskService.isALLTaskDone(params);
                  // 没有未完成的任务
                  if (count == 0) {
                    UserCalendarPojo ucal = new UserCalendarPojo();
                    ucal.setUserId(userId);
                    ucal.setCalendar(dt);
                    userCalendarService.add(ucal);
                  }
                }
              }
            }
          }

          return "oldPost";
        } else if (userCirclePost.getVersion() != null && userCirclePost.getVersion() == 1) {

          // 查询是否存在浏览记录
          params.clear();
          params.put("postId", id);
          params.put("userId", userId);
          params.put("isDelete", 0);
          int i = userPostHistoryService.countBy(params);
          if (i > 0) {
            params.put("lookNum", 1l);
            params.put("viewDate", new Date());
            params.put("updateDate", new Date());
            userPostHistoryService.addLookNum(params);
          } else {
            // 添加笔记浏览记录
            UserPostHistoryPojo userPostHistory = new UserPostHistoryPojo();
            userPostHistory.setUserId(userId);
            userPostHistory.setPostId(id);
            userPostHistory.setIsDelete(0);
            userPostHistory.setLookNum(1l);
            userPostHistory.setViewDate(new Date());
            userPostHistory.setUpdateDate(new Date());
            userPostHistoryService.add(userPostHistory);
          }
          // 添加浏览数
          UserCirclePostPojo userCirclePostPojo = new UserCirclePostPojo();
          userCirclePostPojo.setLookNum(1l);
          userCirclePostPojo.setId(id);
          userCirclePostService.increaseUserCirclePostNumById(userCirclePostPojo);
          if (userId != null) {
            // 查找宝宝信息
            params.clear();
            params.put("userId", userId);
            params.put("isDefault", 1);
            params.put("isDelete", 0);
            userBabyPojo = userBabyService.getByParams(params);
            if (userBabyPojo == null || userBabyPojo.getBabyBirthday() == null) {
              FileUtil.alertMessage("宝宝信息未找到！");
            } else {
              // 判断是否领取任务
              params.clear();
              params.put("userId", userId);
              params.put("taskAge", userCirclePost.getAgeType());
              params.put("ability", userCirclePost.getSkillType());
              params.put("taskTypeLink", 1);
              List<UserTaskPojo> userTaskList = userTaskService.checkBrowsePostTask(params);
              // 宝宝做任务
              if (userTaskList != null && userTaskList.size() > 0) {
                for (UserTaskPojo userTask : userTaskList) {
                  if (userTask.getStatus() != 1) {
                    // 修改任务为完成
                    UserTaskPojo userTaskPojo = new UserTaskPojo();
                    userTaskPojo.setId(userTask.getId());
                    userTaskPojo.setEndTime(new Date());
                    userTaskPojo.setStatus(1);
                    userTaskService.update(userTaskPojo);
                    // 添加积分
                    params.clear();
                    params.put("userId", userId);
                    params.put("babyId", userBabyPojo.getId());
                    params.put("score", userTask.getTaskScore());
                    SellerService.calcGrowLine(Integer.parseInt(userTask.getAbility()),
                        userTask.getTaskScore(), params);
                    userGrowthLineService.addUserScore(params);
                  }
                }
                // 查询年龄
                double age = SellerService.getAge(userBabyPojo.getBabyBirthday());
                int ageType = 1;
                // 查询年龄对应专题
                if (age >= 0.0 && age <= 0.06) {
                  ageType = 1;
                } else if (age > 0.06 && age <= 1.0) {
                  ageType = 2;
                } else if (age > 1.0 && age <= 3.0) {
                  ageType = 3;
                } else if (age > 3.0 && age <= 6.0) {
                  ageType = 4;
                } else if (age > 6.0 && age <= 12.0) {
                  ageType = 5;
                } else if (age > 12.0 && age <= 16.0) {
                  ageType = 6;
                } else if (age > 16.0) {
                  ageType = 6;
                }
                if (ageType > 0) {
                  Date dt = new Date();
                  params.clear();
                  params.put("userId", userId);
                  params.put("ageType", ageType);
                  params.put("taskDate", StringUtil.dateToString(dt));
                  int count = userTaskService.isALLTaskDone(params);
                  // 没有未完成的任务
                  if (count == 0) {
                    UserCalendarPojo ucal = new UserCalendarPojo();
                    ucal.setUserId(userId);
                    ucal.setCalendar(dt);
                    userCalendarService.add(ucal);
                  }
                }
              }
            }
          }

          return "newPost";
        } else {
          System.out.println(">>>>>> 笔记" + id + "查询版本有误!");
        }
      }
    }
    return null;
  }
}
