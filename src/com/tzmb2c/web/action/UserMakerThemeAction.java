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

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.HotPushPojo;
import com.tzmb2c.web.pojo.LabelPojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.TemplatePageDataPojo;
import com.tzmb2c.web.pojo.UserMakerThemePojo;
import com.tzmb2c.web.service.HotPushService;
import com.tzmb2c.web.service.LabelService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.TemplatePageDataService;
import com.tzmb2c.web.service.UserMakerThemeService;

/**
 * 
 * 创客专题管理相关操作
 * 
 * @author Lin
 */
public class UserMakerThemeAction extends SuperAction {
  private static final long serialVersionUID = 1L;

  @Autowired
  private UserMakerThemeService userMakerThemeService;
  @Autowired
  private LabelService labelService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private HotPushService hotPushService;
  @Autowired
  private TemplatePageDataService templatePageDataService;

  private TemplatePageDataPojo templatePageDataPojo;
  private UserMakerThemePojo userMakerThemePojo;
  private SysDictPojo sysDictPojo, sysDic;
  private HotPushPojo hotPushPojo;

  private List<UserMakerThemePojo> userMakerThemePojoList;
  private List<LabelPojo> labelList;
  private List<SysDictPojo> ageTypeList, typeList;
  private Long themeId;
  private Long userId;
  private File upfile;
  private String result;
  private String saveData;
  private Long pageId;
  private Long id;// 专题或笔记id
  private Integer type;// 模板数据类型
  private Long dataId;// 模板数据id
  private Map<String, Object> dataMap;
  private File files;
  private String filePath;



  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
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

  public Long getDataId() {
    return dataId;
  }

  public void setDataId(Long dataId) {
    this.dataId = dataId;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String getSaveData() {
    return saveData;
  }

  public void setSaveData(String saveData) {
    this.saveData = saveData;
  }

  public Long getPageId() {
    return pageId;
  }

  public void setPageId(Long pageId) {
    this.pageId = pageId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public TemplatePageDataPojo getTemplatePageDataPojo() {
    return templatePageDataPojo;
  }

  public void setTemplatePageDataPojo(TemplatePageDataPojo templatePageDataPojo) {
    this.templatePageDataPojo = templatePageDataPojo;
  }

  public HotPushPojo getHotPushPojo() {
    return hotPushPojo;
  }

  public void setHotPushPojo(HotPushPojo hotPushPojo) {
    this.hotPushPojo = hotPushPojo;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getThemeId() {
    return themeId;
  }

  public void setThemeId(Long themeId) {
    this.themeId = themeId;
  }

  public List<LabelPojo> getLabelList() {
    return labelList;
  }

  public void setLabelList(List<LabelPojo> labelList) {
    this.labelList = labelList;
  }

  public List<SysDictPojo> getAgeTypeList() {
    return ageTypeList;
  }

  public void setAgeTypeList(List<SysDictPojo> ageTypeList) {
    this.ageTypeList = ageTypeList;
  }


  public UserMakerThemePojo getUserMakerThemePojo() {
    return userMakerThemePojo;
  }

  public void setUserMakerThemePojo(UserMakerThemePojo userMakerThemePojo) {
    this.userMakerThemePojo = userMakerThemePojo;
  }

  public SysDictPojo getSysDictPojo() {
    return sysDictPojo;
  }

  public void setSysDictPojo(SysDictPojo sysDictPojo) {
    this.sysDictPojo = sysDictPojo;
  }


  public SysDictPojo getSysDic() {
    return sysDic;
  }

  public void setSysDic(SysDictPojo sysDic) {
    this.sysDic = sysDic;
  }


  public List<SysDictPojo> getTypeList() {
    return typeList;
  }

  public void setTypeList(List<SysDictPojo> typeList) {
    this.typeList = typeList;
  }

  /**
   * 专题数目
   * 
   * @return
   * @throws Exception
   */
  public String userMakerThemeListCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      // 上下文容器
      ActionContext ac = ActionContext.getContext();
      Map<String, Object> map = new HashMap<String, Object>();

      if (userMakerThemePojo != null) {
        map.put("makerName", userMakerThemePojo.getMakerName().trim());// 用户昵称
        map.put("makerBrandName", userMakerThemePojo.getMakerBrandName().trim());// 创客品牌
        map.put("specialName", userMakerThemePojo.getSpecialName().trim());// 专题名称
        map.put("status", userMakerThemePojo.getStatus());// 审核状态
        map.put("type", userMakerThemePojo.getType());// 类型
        map.put("ageType", userMakerThemePojo.getAgeType());// 年龄标签字典ID
        map.put("skillType", userMakerThemePojo.getSkillType());// 能力标签字典ID
        map.put("optionType", userMakerThemePojo.getOptionType());// 备选ID
        map.put("productType", userMakerThemePojo.getProductType());// 商品ID
      }
      int i = userMakerThemeService.userMakerThemeCount(map);
      map.clear();// map使用完毕后清空
      List<LabelPojo> labelList = labelService.findLabelList(map);// 将清空后的map作为参数进行标签查询
      ac.put("labelList", labelList);// 将列表放入上下文容器中
      typeList = sysDictService.getSysDictListByType("user_maker_theme_type");// 获取字典中type为user_maker_theme_type的类型
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 专题列表
   * 
   * @return
   * @throws Exception
   */
  public String userMakerThemeListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      new SysDictPojo();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());

      if (userMakerThemePojo != null) {
        if (userMakerThemePojo.getAgeType() != null) {
          userMakerThemePojo.setAgeType(userMakerThemePojo.getAgeType());
        }
        if (userMakerThemePojo.getSkillType() != null) {
          userMakerThemePojo.setSkillType(userMakerThemePojo.getSkillType());
        }

        map.put("makerName", userMakerThemePojo.getMakerName().trim());// 用户昵称
        map.put("makerBrandName", userMakerThemePojo.getMakerBrandName().trim());// 创客品牌
        map.put("specialName", userMakerThemePojo.getSpecialName().trim());// 专题名称
        map.put("status", userMakerThemePojo.getStatus());// 审核状态
        map.put("type", userMakerThemePojo.getType());// 类型
        map.put("ageType", userMakerThemePojo.getAgeType());// 年龄标签字典ID
        map.put("skillType", userMakerThemePojo.getSkillType());// 能力标签字典ID
        map.put("optionType", userMakerThemePojo.getOptionType());// 备选ID
        map.put("productType", userMakerThemePojo.getProductType());// 商品ID
      }
      // 查询列表
      userMakerThemePojoList = userMakerThemeService.userMakerThemeList(map);

      JSONArray json = JSONArray.fromObject(userMakerThemePojoList);
      page.setRowCount(userMakerThemePojoList.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 更新专题状态
   * 
   * @return
   * @throws Exception
   */
  public String updateUserMakerThemeById() throws Exception {
    try {
      // 审核操作前判断该专题状态
      if (userMakerThemeService.getUserMakerThemeById(userMakerThemePojo.getId()).getStatus() == userMakerThemePojo
          .getStatus()) {
        FileUtil.alertMessageBySkip("无需进行此操作！", "userMakerThemeList.do");
      } else {
        userMakerThemeService.updateUserMakerThemeById(userMakerThemePojo);
        FileUtil.alertMessageBySkip("操作成功！", "userMakerThemeList.do");
      }
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("操作失败！", "userMakerThemeList.do");
    }
    return null;
  }

  /**
   * 跳转至编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goUpdateUserMakerTheme() throws Exception {
    try {
      Map<String, Object> map = null;

      // 获取品类
      labelList = labelService.findLabelList(map);
      // 获取年龄
      ageTypeList = sysDictService.getSysDictListByType("user_age");
      // 根据页面传过来的ID查询相关数据
      userMakerThemePojo = userMakerThemeService.getUserMakerThemeById(userMakerThemePojo.getId());

    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 专题审核
   * 
   * @return
   * @throws Throwable
   */
  public String updateUserMakerTheme() throws Throwable {
    try {

      if (userMakerThemePojo != null) {


        // 设置更新时间
        userMakerThemePojo.setUpdateDate(new Date());

        // 更新数据
        if (upfile != null) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerTheme")
                  + File.separator;
          FileUtil.uploadFile(file_name, uploadPath, "upfiles/userMakerTheme/", upfile);
          userMakerThemePojo.setBanner(file_name);
        }
        userMakerThemeService.updateUserMakerTheme(userMakerThemePojo);
        FileUtil.alertMessageBySkip("编辑成功！", "userMakerThemeList.do");
      }

    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("编辑失败！", "userMakerThemeList.do");
    }
    return null;
  }

  /**
   * 创客品牌webview
   * 
   * @throws SQLException
   * @return String
   */
  public String goMakerThemeView() throws SQLException {
    if (themeId == null || themeId == 0) {
      System.out.println(">>>>>> themeId为空!");
    }
    /*
     * else if (userId == null || userId == 0) { System.out.println(">>>>>> userId为空!"); }
     */
    else {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("id", themeId);
      params.put("status", 1);
      params.put("isDelete", 0);
      userMakerThemePojo = userMakerThemeService.getUserMakerThemeById(themeId);
      if (userMakerThemePojo == null) {
        System.out.println(">>>>>> themeId=" + themeId + " 查不到!");
      }
    }
    return SUCCESS;
  }

  /**
   * 
   * 上首页/取消上首页
   * 
   * @return
   * @throw
   * @return String
   */
  public String setUMTIsHomePage() throws Exception {
    try {
      SysLoginPojo sysLogin = UserUtil.getUser();
      if (sysLogin == null) {
        FileUtil.alertMessageBySkip("请先登录", "doLogin.do");
      } else {
        if (userMakerThemePojo.getIsHomePage() == 1 || userMakerThemePojo.getIsHomePage() == 0) {
          userMakerThemeService.updateUserMakerTheme(userMakerThemePojo);
          if (userMakerThemePojo.getIsHomePage() == 1 && userMakerThemePojo.getId() != null) {
            // 查询是否已存在该数据
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("specialId", userMakerThemePojo.getId());
            params.put("type", 2);
            hotPushPojo = hotPushService.findByParams(params);
            if (hotPushPojo != null) {
              // 修改热门推荐
              hotPushPojo.setCreateBy(sysLogin.getId());
              hotPushPojo.setCreateDate(new Date());
              hotPushPojo.setUpdateBy(sysLogin.getId());
              hotPushPojo.setUpdateDate(new Date());
              hotPushService.update(hotPushPojo);
            } else {
              // 插入热门推荐
              hotPushPojo = new HotPushPojo();
              hotPushPojo.setSpecialId(userMakerThemePojo.getId());
              hotPushPojo.setType(2);
              hotPushPojo.setCreateBy(sysLogin.getId());
              hotPushPojo.setCreateDate(new Date());
              hotPushPojo.setUpdateBy(sysLogin.getId());
              hotPushPojo.setUpdateDate(new Date());
              hotPushService.add(hotPushPojo);
            }
          } else {
            // 删除热门推荐
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("specialId", userMakerThemePojo.getId());
            params.put("type", 1);
            hotPushPojo = hotPushService.findByParams(params);
            if (hotPushPojo != null) {
              hotPushService.delete(hotPushPojo.getId());
            }
          }
          FileUtil.alertMessageBySkip(userMakerThemePojo.getIsHomePage() == 1 ? "上热门成功！"
              : "取消上热门成功", "userMakerThemeList.do");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      if (userMakerThemePojo != null && userMakerThemePojo.getIsHomePage() != null) {
        FileUtil.alertMessageBySkip(userMakerThemePojo.getIsHomePage() == 0 ? "上热门失败！" : "取消上热门失败",
            "userMakerThemeList.do");
      } else {
        FileUtil.alertMessageBySkip("上热门失败！", "userMakerThemeList.do");
      }
    }
    return null;
  }

  /**
   * 跳转专题内容修改
   * 
   * @throws SQLException
   * @return String
   */
  public String goUpdateUserMakerThemeContent() throws SQLException {
    userMakerThemePojo = userMakerThemeService.getUserMakerThemeById(userMakerThemePojo.getId());
    if (userMakerThemePojo != null && userMakerThemePojo.getVersion() == 1) {
      // 查询自定义专题详情页面数据
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("pageId", userMakerThemePojo.getId());
      params.put("type", 2);
      templatePageDataPojo = templatePageDataService.findByParams(params);
      if (templatePageDataPojo == null) {
        templatePageDataPojo = new TemplatePageDataPojo();
        templatePageDataPojo.setData("{}");
      }
      return "newTheme";
    } else {
      return "oldTheme";
    }
  }



  /**
   * 
   * 修改自定义专题详情
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String updateMakerThemeTemplatePageData() throws Exception {
    SysLoginPojo sysLogin = UserUtil.getUser();
    if (sysLogin == null) {
      result = String.valueOf(0);
    } else {
      if (saveData != null && themeId != null && dataId != null) {
        templatePageDataPojo = new TemplatePageDataPojo();
        templatePageDataPojo.setId(dataId);
        templatePageDataPojo.setData(saveData);
        templatePageDataPojo.setPageId(themeId);
        templatePageDataPojo.setUpdateBy(sysLogin.getId());
        templatePageDataPojo.setUpdateDate(new Date());
        templatePageDataService.update(templatePageDataPojo);
        result = String.valueOf(templatePageDataPojo.getId());
      } else {
        // 第一次添加微页面时没保存执行添加
        if (dataId == null && themeId != null) {
          templatePageDataPojo = new TemplatePageDataPojo();
          templatePageDataPojo.setData(saveData);
          templatePageDataPojo.setType(2);
          templatePageDataPojo.setPageId(themeId);
          templatePageDataPojo.setUpdateBy(sysLogin.getId());
          templatePageDataPojo.setUpdateDate(new Date());
          templatePageDataService.add(templatePageDataPojo);
          result = String.valueOf(templatePageDataPojo.getId());
        } else {
          result = null;
        }
      }
    }
    return SUCCESS;
  }

  /**
   * 
   * 创客专题可视化web页面
   * 
   * @return
   * @throw
   * @return String
   */
  public String getMaskerThemeVisApi() {
    if (id == null || id == 0) {
      System.out.println("专题id不能为空!");
    } else {
      userMakerThemePojo = userMakerThemeService.getUserMakerThemeById(id);
      if (userMakerThemePojo == null || userMakerThemePojo.getStatus() == 0 || userMakerThemePojo.getIsDelete() == 1) {
        System.out.println("查询不到该专题!");
      } else {
        if (userMakerThemePojo.getVersion() != null && userMakerThemePojo.getVersion() == 0) {
          return "oldMakerTheme";
        } else if (userMakerThemePojo.getVersion() != null && userMakerThemePojo.getVersion() == 1) {
          return "newMakerTheme";
        } else {
          System.out.println(">>>>>> 专题" + id + "查询版本有误!");
        }
      }
    }
    return null;
  }

  /**
   * 上传图片
   * 
   * @return
   * @throws Exception
   */
  public String themeImgUpfile() throws Exception {
    try {
      dataMap = new HashMap<String, Object>();
      if (files != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/userMakerTheme")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/userMakerTheme/", files);
        dataMap.put("src", "/upfiles/userMakerTheme/" + file_name);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

}
