package com.tzmb2c.web.action;

import java.io.File;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.tzmb2c.business.service.ConstParam;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.CompressPicture;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.mapper.ProductMapper;
import com.tzmb2c.web.pojo.ActivityProductPojo;
import com.tzmb2c.web.pojo.CouponPojo;
import com.tzmb2c.web.pojo.HotPushPojo;
import com.tzmb2c.web.pojo.LabelPojo;
import com.tzmb2c.web.pojo.PlatformSpecialPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.SysDictPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.TemplatePageDataPojo;
import com.tzmb2c.web.pojo.YourFavouritesPojo;
import com.tzmb2c.web.service.ActivityProductService;
import com.tzmb2c.web.service.CouponService;
import com.tzmb2c.web.service.HotPushService;
import com.tzmb2c.web.service.LabelService;
import com.tzmb2c.web.service.PlatformSpecialService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.SysDictService;
import com.tzmb2c.web.service.TemplatePageDataService;
import com.tzmb2c.web.service.YourFavouritesService;

/**
 * 
 * 专题管理相关操作
 * 
 * @author Lin&zzh
 */
public class PlatformSpecialAction extends SuperAction {
  private static final long serialVersionUID = 1L;

  @Autowired
  private PlatformSpecialService platformSpecialService;
  @Autowired
  private LabelService labelService;
  @Autowired
  private SysDictService sysDictService;
  @Autowired
  private ActivityProductService activityProductService;
  @Autowired
  private ProductMapper productMapper;
  @Autowired
  private HotPushService hotPushService;
  @Autowired
  private TemplatePageDataService templatePageDataService;
  @Autowired
  private ProductService productService;
  @Autowired
  private CouponService couponService;
  @Autowired
  private YourFavouritesService yourFavouritesService;

  private TemplatePageDataPojo templatePageDataPojo;
  private PlatformSpecialPojo platformSpecialPojo;
  private SysDictPojo sysDictPojo, sysDic, sysDictory;

  private List<PlatformSpecialPojo> platformSpecialPojoList;
  private List<LabelPojo> labelList;
  private List<SysDictPojo> ageTypeList;
  private File upfile;
  private Long titleId;
  private Long productId;
  private String[] tids;
  private Long specialId;
  private Long activityId;
  private HotPushPojo hotPushPojo;

  // 自定义专题详情json
  private String saveData;
  private String result;
  private Long dataId;
  private File files;
  private Map<String, Object> dataMap;
  private Map<String, Object> productData;
  private String filePath;
  // 商品列表
  private ProductPojo productPojo;
  private List<ProductPojo> productList;
  // 优惠劵列表
  private List<CouponPojo> couponPojos;
  private CouponPojo couponPojo;
  private Long id;
  private Integer pagetype;
  private YourFavouritesPojo yourFavourites;
  private List<YourFavouritesPojo> yourFavouritesList;
  /**
   * 用户类型标记
   */
  private Integer userFlag;


  public Integer getUserFlag() {
    return userFlag;
  }

  public void setUserFlag(Integer userFlag) {
    this.userFlag = userFlag;
  }

  public List<YourFavouritesPojo> getYourFavouritesList() {
    return yourFavouritesList;
  }

  public void setYourFavouritesList(List<YourFavouritesPojo> yourFavouritesList) {
    this.yourFavouritesList = yourFavouritesList;
  }

  public YourFavouritesPojo getYourFavourites() {
    return yourFavourites;
  }

  public void setYourFavourites(YourFavouritesPojo yourFavourites) {
    this.yourFavourites = yourFavourites;
  }

  public Integer getPagetype() {
    return pagetype;
  }

  public void setPagetype(Integer pagetype) {
    this.pagetype = pagetype;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getTitleId() {
    return titleId;
  }

  public void setTitleId(Long titleId) {
    this.titleId = titleId;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public Map<String, Object> getProductData() {
    return productData;
  }

  public void setProductData(Map<String, Object> productData) {
    this.productData = productData;
  }

  public ProductPojo getProductPojo() {
    return productPojo;
  }

  public void setProductPojo(ProductPojo productPojo) {
    this.productPojo = productPojo;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public Map<String, Object> getDataMap() {
    return dataMap;
  }

  public void setDataMap(Map<String, Object> dataMap) {
    this.dataMap = dataMap;
  }

  public File getFiles() {
    return files;
  }

  public void setFiles(File files) {
    this.files = files;
  }

  public Long getDataId() {
    return dataId;
  }

  public void setDataId(Long dataId) {
    this.dataId = dataId;
  }

  public String getSaveData() {
    return saveData;
  }

  public void setSaveData(String saveData) {
    this.saveData = saveData;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public TemplatePageDataPojo getTemplatePageDataPojo() {
    return templatePageDataPojo;
  }

  public void setTemplatePageDataPojo(TemplatePageDataPojo templatePageDataPojo) {
    this.templatePageDataPojo = templatePageDataPojo;
  }

  public Long getSpecialId() {
    return specialId;
  }

  public void setSpecialId(Long specialId) {
    this.specialId = specialId;
  }

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
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


  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }


  public PlatformSpecialPojo getPlatformSpecialPojo() {
    return platformSpecialPojo;
  }

  public void setPlatformSpecialPojo(PlatformSpecialPojo platformSpecialPojo) {
    this.platformSpecialPojo = platformSpecialPojo;
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


  public SysDictPojo getSysDictory() {
    return sysDictory;
  }

  public void setSysDictory(SysDictPojo sysDictory) {
    this.sysDictory = sysDictory;
  }

  /**
   * 任务数目
   * 
   * @return
   * @throws Exception
   */
  public String platformSpecialListCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      // 上下文容器
      ActionContext ac = ActionContext.getContext();
      Map<String, Object> map = new HashMap<String, Object>();

      if (platformSpecialPojo != null) {
        map.put("title", platformSpecialPojo.getTitle().trim());
        map.put("type", platformSpecialPojo.getType());
        map.put("ageType", platformSpecialPojo.getAgeType());
        map.put("skillType", platformSpecialPojo.getSkillType());
        map.put("optionType", platformSpecialPojo.getOptionType());
        map.put("productType", platformSpecialPojo.getProductType());
        map.put("status", platformSpecialPojo.getStatus());
        map.put("specialCategories", platformSpecialPojo.getSpecialCategories());
      }
      int i = platformSpecialService.countBy(map);
      map.clear();// map使用完毕后清空
      List<LabelPojo> labelList = labelService.findLabelList(map);// 将清空后的map作为参数进行标签查询
      ac.put("labelList", labelList);// 将列表放入上下文容器中
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 任务列表
   * 
   * @return
   * @throws Exception
   */
  public String platformSpecialListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      new SysDictPojo();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());

      if (platformSpecialPojo != null) {
        if (platformSpecialPojo.getAgeType() != null) {
          platformSpecialPojo.setAgeType(platformSpecialPojo.getAgeType());
        }
        if (platformSpecialPojo.getSkillType() != null) {
          platformSpecialPojo.setSkillType(platformSpecialPojo.getSkillType());
        }
        if (platformSpecialPojo.getSecSkillType() != null) {
          platformSpecialPojo.setSecSkillType(platformSpecialPojo.getSecSkillType());
        }

        map.put("title", platformSpecialPojo.getTitle().trim());
        map.put("type", platformSpecialPojo.getType());
        map.put("ageType", platformSpecialPojo.getAgeType());
        map.put("skillType", platformSpecialPojo.getSkillType());
        map.put("optionType", platformSpecialPojo.getOptionType());
        map.put("productType", platformSpecialPojo.getProductType());
        map.put("status", platformSpecialPojo.getStatus());
        map.put("specialCategories", platformSpecialPojo.getSpecialCategories());
      }
      // 查询列表
      platformSpecialPojoList = platformSpecialService.listPage(map);

      JSONArray json = JSONArray.fromObject(platformSpecialPojoList);
      page.setRowCount(platformSpecialPojoList.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 根据ID取消审核专题
   * 
   * @return
   * @throws Exception
   */
  public String delPlatformSpecial() throws Exception {
    try {
      // 取消审核前判断该专题状态
      PlatformSpecialPojo platformSpecial =
          platformSpecialService.getById(platformSpecialPojo.getId().intValue());
      if (platformSpecial != null) {
        if (platformSpecial.getStatus() == 2) {
          FileUtil.alertMessageBySkip("该记录是审核失败状态，无法进行此操作！", "platformSpecialList.do");
        } else {
          platformSpecialPojo.setStatus(2);
          platformSpecialService.updateOne(platformSpecialPojo);
          FileUtil.alertMessageBySkip("取消审核成功！", "platformSpecialList.do");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("操作失败！", "platformSpecialList.do");
    }
    return null;
  }

  /**
   * 根据ID审核专题
   * 
   * @return
   * @throws Exception
   */
  public String updateOnePlatformSpecial() throws Exception {
    try {
      // 审核前判断该专题状态
      PlatformSpecialPojo platformSpecial =
          platformSpecialService.getById(platformSpecialPojo.getId().intValue());
      if (platformSpecial != null) {
        if (platformSpecial.getStatus() == 1) {
          FileUtil.alertMessageBySkip("该记录已是审查成功状态，无需进行审核！", "platformSpecialList.do");
        } else {
          platformSpecialPojo.setStatus(1);
          platformSpecialService.updateOne(platformSpecialPojo);
          FileUtil.alertMessageBySkip("审核成功！", "platformSpecialList.do");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("操作失败！", "platformSpecialList.do");
    }
    return null;
  }

  /**
   * 根据ID删除专题
   * 
   * @return
   * @throws Exception
   */
  public String delPlatformSpecial2() throws Exception {
    try {
      // 取消审核前判断该专题状态
      PlatformSpecialPojo platformSpecial =
          platformSpecialService.getById(platformSpecialPojo.getId().intValue());
      if (platformSpecial != null) {
        if (platformSpecial.getStatus() == 3) {
          FileUtil.alertMessageBySkip("该记录是删除状态，无法进行此操作！", "platformSpecialList.do");
        } else {
          platformSpecialPojo.setStatus(3);
          platformSpecialService.updateOne(platformSpecialPojo);
          FileUtil.alertMessageBySkip("删除成功！", "platformSpecialList.do");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("操作失败！", "platformSpecialList.do");
    }
    return null;
  }

  /**
   * 跳转至编辑任务页面
   * 
   * @return
   * @throws Exception
   */
  public String goUpdatePlatformSpecial() throws Exception {
    try {
      Map<String, Object> map = null;
      new SysDictPojo();
      new SysDictPojo();
      new SysDictPojo();
      // 获取品类
      labelList = labelService.findLabelList(map);
      // 获取年龄
      ageTypeList = sysDictService.getSysDictListByType("user_age");
      // 根据页面传过来的ID查询相关数据
      platformSpecialPojo = platformSpecialService.getById(platformSpecialPojo.getId().intValue());
      if (platformSpecialPojo != null && platformSpecialPojo.getVersion() == 1) {
        // 查询自定义专题详情页面数据
        templatePageDataPojo = getSpecialTemplatePageData(platformSpecialPojo.getId());
        if (templatePageDataPojo == null) {
          templatePageDataPojo = new TemplatePageDataPojo();
          templatePageDataPojo.setData("{}");
        }
        return SUCCESS;
      } else {
        return "oldVersion";
      }
      // //根据获取到的ageType查询相对应的POJO
      // sysDictPojo.setValue(platformSpecialPojo.getAgeType().toString());
      // sysDictPojo.setType("user_age");
      // sysDictPojo = sysDictService.getSysDictByTypeAndValue(sysDictPojo);
      // //根据获取到的skillType查询相对应的POJO
      // sysDic.setValue(platformSpecialPojo.getSkillType().toString());
      // sysDic.setType("ability");
      // sysDic = sysDictService.getSysDictByTypeAndValue(sysDictPojo);
      // //根据获取到的secSkillType查询相对应的POJO
      // sysDictory.setValue(platformSpecialPojo.getSecSkillType().toString());
      // sysDictory.setType("second_ability");
      // sysDictory = sysDictService.getSysDictByTypeAndValue(sysDictPojo);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 编辑任务
   * 
   * @return
   * @throws Throwable
   */
  public String updatePlatformSpecial() throws Throwable {
    try {

      if (platformSpecialPojo != null) {
        // 图片操作
        if (upfile != null) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/platformSpecial")
                  + File.separator;
          // FileUtil.uploadFile(file_name, uploadPath, "upfiles/platformSpecial/", upfile);
          // // 图片压缩
          // CompressPicture cp = new CompressPicture();
          // String compressPath =
          // ServletActionContext.getServletContext()
          // .getRealPath("/upfiles/platformSpecial/small") + File.separator;
          // cp.compressPic(upfile, compressPath, "upfiles/platformSpecial/small/", file_name, 300,
          // 300, true);

          // 图片压缩
          CompressPicture cp = new CompressPicture();
          cp.compressPic(upfile, uploadPath, uploadPath, file_name, 0, 0, false);
          platformSpecialPojo.setBanner(file_name);
        } else {
          platformSpecialPojo.setBanner("");
        }

        platformSpecialPojo.setAgeType(platformSpecialPojo.getAgeType());


        platformSpecialPojo.setSkillType(platformSpecialPojo.getSkillType());


        platformSpecialPojo.setSecSkillType(platformSpecialPojo.getSecSkillType());

        // 设置更新时间
        platformSpecialPojo.setUpdateDate(new Date());

        // 更新数据
        platformSpecialService.update(platformSpecialPojo);
        // FileUtil.alertMessageBySkip("编辑成功！", "platformSpecialList.do");
        this.result = platformSpecialPojo.getId().toString();
      }

    } catch (Exception e) {
      e.printStackTrace();
      this.result = null;
      // FileUtil.alertMessageBySkip("编辑失败！", "platformSpecialList.do");
    }
    return SUCCESS;
  }

  /**
   * 跳转至新增任务页面
   * 
   * @return
   */
  public String goAddPlatformSpecialList() {
    Map<String, Object> map = null;

    try {
      labelList = labelService.findLabelList(map);// 获取品类
      ageTypeList = sysDictService.getSysDictListByType("user_age");// 获取年龄
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 根据年龄查询查询有你喜欢
   * 
   * @return
   */
  public String getYourFavouritesLable() {
    if (id == null || id == 0) {
      System.out.println("id不能为空!");
    } else {
      Map<String, Object> params = new HashMap<String, Object>();
      List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
      dataMap = new HashMap<String, Object>();
      params.put("ageType", id);
      yourFavouritesList = yourFavouritesService.listPage(params);
      if (yourFavouritesList != null && yourFavouritesList.size() != 0) {
        for (YourFavouritesPojo yourFavourites : yourFavouritesList) {
          params = new HashMap<String, Object>();
          params.put("id", yourFavourites.getId());
          params.put("name", yourFavourites.getName());
          list.add(params);
        }
      }
      dataMap.put("result", list);
    }
    return SUCCESS;
  }

  /**
   * 提交新增任务
   * 
   * @return
   */
  public String addPlatformSpecialList() {
    try {
      if (platformSpecialPojo != null) {
        // 图片操作
        if (upfile != null) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/platformSpecial")
                  + File.separator;
          // FileUtil.uploadFile(file_name, uploadPath, "upfiles/platformSpecial/", upfile);
          // // 图片压缩
          // CompressPicture cp = new CompressPicture();
          // String compressPath =
          // ServletActionContext.getServletContext()
          // .getRealPath("/upfiles/platformSpecial/small") + File.separator;
          // cp.compressPic(upfile, compressPath, "upfiles/platformSpecial/small/", file_name, 300,
          // 300, true);

          // 图片压缩
          CompressPicture cp = new CompressPicture();
          cp.compressPic(upfile, uploadPath, uploadPath, file_name, 0, 0, false);
          platformSpecialPojo.setBanner(file_name);
        } else {
          platformSpecialPojo.setBanner("");
        }
        platformSpecialPojo.setAgeType(platformSpecialPojo.getAgeType());
        platformSpecialPojo.setSkillType(platformSpecialPojo.getSkillType());
        platformSpecialPojo.setSecSkillType(platformSpecialPojo.getSecSkillType());
        // 设置创建时间
        platformSpecialPojo.setCreateDate(new Date());
        // 设置更新时间
        platformSpecialPojo.setUpdateDate(new Date());
        // 将数据插入数据库
        if (platformSpecialPojo.getId() == null) {
          // 添加
          platformSpecialService.add(platformSpecialPojo);
        } else {
          // 修改
          platformSpecialService.update(platformSpecialPojo);
        }
        this.result = String.valueOf(platformSpecialPojo.getId());
      }
    } catch (Exception e) {
      this.result = null;
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 添加商品
   * 
   * @return
   */
  public String addPlatformSpecialPorduct() {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      ActivityProductPojo activityProductPojo = new ActivityProductPojo();
      if (productId != null && titleId != null && activityId != null) {
        map.put("productId", productId);
        map.put("titleId", titleId);
        map.put("types", 6);
        int n = activityProductService.getActivityPlaceCount(map);
        if (n == 0) {
          activityProductPojo.setTitleId(titleId);
          activityProductPojo.setProductId(productId);
          activityProductPojo.setType("6");
          activityProductPojo.setActivityId(0L);
          activityProductPojo.setStatus(1);
          activityProductPojo.setActivityId(activityId);
          activityProductService.insertActivityProduct(activityProductPojo);
        } else {
          FileUtil.alertMessageBySkip("此商品已在该专题添加，请重新选择！",
              "goAddPlatformSpecialPorduct.do?types=6&titleId=" + titleId);
          return null;
        }
      }
      FileUtil.alertMessageBySkip("添加商品成功！", "goSettingPlatformSpecialPorduct.do?types=6&titleId="
          + titleId);
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("添加商品失败！", "goSettingPlatformSpecialPorduct.do?types=6&titleId="
          + titleId);
    }
    return null;
  }

  /**
   * 批量添加商品
   * 
   * @return
   */
  public String addPlatformSpecialPorductAll() {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      Map<String, Object> map1 = new HashMap<String, Object>();
      ActivityProductPojo activityProductPojo = null;
      String alarm = "";
      int re = 0;
      for (String id : tids) {
        if (id != null && titleId != null) {
          map.put("productId", id);
          map.put("titleId", titleId);
          map.put("types", 6);
          int n = activityProductService.getActivityPlaceCount(map);
          if (n == 0) {
            activityProductPojo = new ActivityProductPojo();
            activityProductPojo.setTitleId(titleId);
            activityProductPojo.setProductId(Long.valueOf(id));
            activityProductPojo.setType("6");
            activityProductPojo.setActivityId(0L);
            activityProductPojo.setStatus(1);
            map1.put("timeIdIsN", 1);
            map1.put("activityType", 3);
            map1.put("productId", id);
            activityId =
                productMapper.findActivityIdByPlatformSpecialProductId(map1).getActivityId();
            activityProductPojo.setActivityId(activityId);
            activityProductService.insertActivityProduct(activityProductPojo);
          } else {
            re++;
            alarm = "其中有" + re + "件重复添加的商品，请您下次注意不要重复添加。";
          }
        }
      }
      FileUtil.alertMessageBySkip("批量添加商品成功！" + alarm,
          "goSettingPlatformSpecialPorduct.do?types=6&titleId=" + titleId);
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("批量添加商品失败！",
          "goSettingPlatformSpecialPorduct.do?types=6&titleId=" + titleId);
    }
    return null;
  }

  /**
   * 专题详情webview
   * 
   * @throws SQLException
   * @return String
   */
  public String goPlatformSpecialView() throws SQLException {
    if (specialId == null || specialId == 0) {
      System.out.println(">>>>>> specialId为空!");
    } else {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("id", specialId);
      params.put("status", 1);
      platformSpecialPojo = platformSpecialService.findSpecialByParams(params);
      if (platformSpecialPojo == null) {
        System.out.println(">>>>>> specialId=" + specialId + " 查不到!");
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
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public String setPSIsHomePage() throws Exception {
    try {
      SysLoginPojo sysLogin = UserUtil.getUser();
      if (sysLogin == null) {
        FileUtil.alertMessageBySkip("请先登录", "doLogin.do");
      } else {
        if (platformSpecialPojo.getIsHomePage() == 1 || platformSpecialPojo.getIsHomePage() == 0) {
          platformSpecialService.update(platformSpecialPojo);
          if (platformSpecialPojo.getIsHomePage() == 1 && platformSpecialPojo.getId() != null) {
            // 查询是否已存在该数据
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("specialId", platformSpecialPojo.getId());
            params.put("type", 1);
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
              hotPushPojo.setSpecialId(platformSpecialPojo.getId());
              hotPushPojo.setType(1);
              hotPushPojo.setCreateBy(sysLogin.getId());
              hotPushPojo.setCreateDate(new Date());
              hotPushPojo.setUpdateBy(sysLogin.getId());
              hotPushPojo.setUpdateDate(new Date());
              hotPushService.add(hotPushPojo);
            }
          } else {
            // 删除热门推荐
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("specialId", platformSpecialPojo.getId());
            params.put("type", 1);
            hotPushPojo = hotPushService.findByParams(params);
            if (hotPushPojo != null) {
              hotPushService.delete(hotPushPojo.getId());
            }
          }
          FileUtil.alertMessageBySkip(platformSpecialPojo.getIsHomePage() == 1 ? "上热门成功！"
              : "取消上热门成功", "platformSpecialList.do");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      if (platformSpecialPojo != null && platformSpecialPojo.getIsHomePage() != null) {
        FileUtil.alertMessageBySkip(
            platformSpecialPojo.getIsHomePage() == 0 ? "上热门失败！" : "取消上热门失败",
            "platformSpecialList.do");
      } else {
        FileUtil.alertMessageBySkip("上热门失败！", "platformSpecialList.do");
      }
    }
    return null;
  }

  /**
   * 
   * 添加自定义专题详情
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String addSpecialTemplatePageData() throws Exception {
    SysLoginPojo sysLogin = UserUtil.getUser();
    if (sysLogin == null) {
      this.result = String.valueOf(0);
    } else {
      if (saveData != null && specialId != null && pagetype != null) {
        templatePageDataPojo = new TemplatePageDataPojo();
        templatePageDataPojo.setData(saveData);
        templatePageDataPojo.setType(pagetype);
        templatePageDataPojo.setPageId(specialId);
        templatePageDataPojo.setCreateBy(sysLogin.getId());
        templatePageDataPojo.setUpdateBy(sysLogin.getId());
        templatePageDataService.add(templatePageDataPojo);
        this.result = String.valueOf(templatePageDataPojo.getId());
      } else {
        this.result = null;
      }
    }
    return SUCCESS;
  }

  /**
   * 
   * 添加自定义笔记详情
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String addSpecialTemplatePageDataTarento() throws Exception {
    SysLoginPojo sysLogin = UserUtil.getTarentoUser();
    if (sysLogin == null) {
      this.result = String.valueOf(0);
    } else {
      if (saveData != null && specialId != null && pagetype != null) {
        templatePageDataPojo = new TemplatePageDataPojo();
        templatePageDataPojo.setData(saveData);
        templatePageDataPojo.setType(pagetype);
        templatePageDataPojo.setPageId(specialId);
        templatePageDataPojo.setCreateBy(sysLogin.getId());
        templatePageDataPojo.setUpdateBy(sysLogin.getId());
        templatePageDataService.add(templatePageDataPojo);
        this.result = String.valueOf(templatePageDataPojo.getId());
      } else {
        this.result = null;
      }
    }
    return SUCCESS;
  }

  /**
   * 
   * 添加自定义笔记详情
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String addSpecialTemplatePageDataGeek() throws Exception {
    SysLoginPojo sysLogin = UserUtil.getGeekUser();
    if (sysLogin == null) {
      this.result = String.valueOf(0);
    } else {
      if (saveData != null && specialId != null && pagetype != null) {
        templatePageDataPojo = new TemplatePageDataPojo();
        templatePageDataPojo.setData(saveData);
        templatePageDataPojo.setType(pagetype);
        templatePageDataPojo.setPageId(specialId);
        templatePageDataPojo.setCreateBy(sysLogin.getId());
        templatePageDataPojo.setUpdateBy(sysLogin.getId());
        templatePageDataService.add(templatePageDataPojo);
        this.result = String.valueOf(templatePageDataPojo.getId());
      } else {
        this.result = null;
      }
    }
    return SUCCESS;
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
  public String updateSpecialTemplatePageData() throws Exception {
    SysLoginPojo sysLogin = UserUtil.getUser();
    if (sysLogin == null) {
      this.result = String.valueOf(0);
    } else {
      if (saveData != null && specialId != null && dataId != null) {
        templatePageDataPojo = new TemplatePageDataPojo();
        templatePageDataPojo.setId(dataId);
        templatePageDataPojo.setData(saveData);
        templatePageDataPojo.setPageId(specialId);
        templatePageDataPojo.setUpdateBy(sysLogin.getId());
        templatePageDataService.update(templatePageDataPojo);
        this.result = String.valueOf(templatePageDataPojo.getId());
      } else {
        // 第一次添加微页面时没保存执行添加
        if (dataId == null && specialId != null) {
          templatePageDataPojo = new TemplatePageDataPojo();
          templatePageDataPojo.setData(saveData);
          templatePageDataPojo.setType(pagetype);
          templatePageDataPojo.setPageId(specialId);
          templatePageDataPojo.setCreateBy(sysLogin.getId());
          templatePageDataPojo.setUpdateBy(sysLogin.getId());
          templatePageDataService.add(templatePageDataPojo);
          this.result = String.valueOf(templatePageDataPojo.getId());
        } else {
          this.result = null;
        }
      }
    }
    return SUCCESS;
  }

  /**
   * 
   * 修改自定义笔记详情
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String updateSpecialTemplatePageDataGeek() throws Exception {
    SysLoginPojo sysLogin = UserUtil.getGeekUser();
    if (sysLogin == null) {
      this.result = String.valueOf(0);
    } else {
      if (saveData != null && specialId != null && dataId != null) {
        templatePageDataPojo = new TemplatePageDataPojo();
        templatePageDataPojo.setId(dataId);
        templatePageDataPojo.setData(saveData);
        templatePageDataPojo.setPageId(specialId);
        templatePageDataPojo.setUpdateBy(sysLogin.getId());
        templatePageDataService.update(templatePageDataPojo);
        this.result = String.valueOf(templatePageDataPojo.getId());
      } else {
        // 第一次添加微页面时没保存执行添加
        if (dataId == null && specialId != null) {
          templatePageDataPojo = new TemplatePageDataPojo();
          templatePageDataPojo.setData(saveData);
          templatePageDataPojo.setType(pagetype);
          templatePageDataPojo.setPageId(specialId);
          templatePageDataPojo.setCreateBy(sysLogin.getId());
          templatePageDataPojo.setUpdateBy(sysLogin.getId());
          templatePageDataService.add(templatePageDataPojo);
          this.result = String.valueOf(templatePageDataPojo.getId());
        } else {
          this.result = null;
        }
      }
    }
    return SUCCESS;
  }

  /**
   * 
   * 修改自定义笔记详情
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String updateSpecialTemplatePageDataTarento() throws Exception {
    SysLoginPojo sysLogin = UserUtil.getTarentoUser();
    if (sysLogin == null) {
      this.result = String.valueOf(0);
    } else {
      if (saveData != null && specialId != null && dataId != null) {
        templatePageDataPojo = new TemplatePageDataPojo();
        templatePageDataPojo.setId(dataId);
        templatePageDataPojo.setData(saveData);
        templatePageDataPojo.setPageId(specialId);
        templatePageDataPojo.setUpdateBy(sysLogin.getId());
        templatePageDataService.update(templatePageDataPojo);
        this.result = String.valueOf(templatePageDataPojo.getId());
      } else {
        // 第一次添加微页面时没保存执行添加
        if (dataId == null && specialId != null) {
          templatePageDataPojo = new TemplatePageDataPojo();
          templatePageDataPojo.setData(saveData);
          templatePageDataPojo.setType(pagetype);
          templatePageDataPojo.setPageId(specialId);
          templatePageDataPojo.setCreateBy(sysLogin.getId());
          templatePageDataPojo.setUpdateBy(sysLogin.getId());
          templatePageDataService.add(templatePageDataPojo);
          this.result = String.valueOf(templatePageDataPojo.getId());
        } else {
          this.result = null;
        }
      }
    }
    return SUCCESS;
  }

  /**
   * 
   * 查询自定义专题详情
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public TemplatePageDataPojo getSpecialTemplatePageData(Long specialId) throws Exception {
    SysLoginPojo sysLogin = UserUtil.getUser();
    if (sysLogin == null) {
      FileUtil.alertMessageBySkip("请先登录!", "#");
    } else {
      Map<String, Object> params = new HashMap<String, Object>();
      if (specialId != null) {
        params.put("pageId", specialId);
        params.put("type", 1);
        templatePageDataPojo = templatePageDataService.findByParams(params);
      }
    }
    return templatePageDataPojo;
  }

  /**
   * 上传图片
   * 
   * @return
   * @throws Exception
   */
  public String specialImgUpfile() throws Exception {
    try {
      dataMap = new HashMap<String, Object>();
      if (files != null) {
        String file_name = StringUtil.getCurrentDateStr() + ".jpg";
        String uploadPath =
            ServletActionContext.getServletContext().getRealPath("/upfiles/platformSpecial")
                + File.separator;
        FileUtil.uploadFile(file_name, uploadPath, "upfiles/platformSpecial/", files);
        dataMap.put("src", "/upfiles/platformSpecial/" + file_name);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 删除图片
   * 
   * @return
   * @throws Exception
   */
  public String delSpecialImgUpfile() throws Exception {
    try {
      dataMap = new HashMap<String, Object>();
      if (filePath != null) {
        FileUtil.delFile(filePath);
        dataMap.put("result", true);
      }
    } catch (Exception e) {
      dataMap.put("result", false);
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 专场活动商品库
   * 
   * @return
   * @throws Exception
   * @throw
   * @return String
   */
  public String findActProBucket() throws Exception {
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> pager = new HashMap<String, Object>();
    Map<String, Object> pageData = new ListOrderedMap();
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    //创客标记
    if(userFlag != null && userFlag == 1){
      SysLoginPojo geekUser = UserUtil.getGeekUser();
      if (geekUser != null) {
        productPojo.setGeekId(geekUser.getId());
      } else {
        System.out.println("请先登录!");
      }
    }
    productPojo.setTimeIdIsN("1");
    productPojo.setActivityType("3");
    productPojo.setIsNew("1");
    productPojo.setStatus(1);
    productPojo.setProductName(productPojo.getProductName());
    productPojo.setShopName(productPojo.getShopName());
    // 查询商品总条数
    map.put("timeIdIsN", "1");
    map.put("activityType", 3);
    if (productPojo != null) {
      if (StringUtils.isNotBlank(productPojo.getShopName())) {
        map.put("shopName", productPojo.getShopName());
      }
      if (StringUtils.isNotBlank(productPojo.getProductNum())) {
        map.put("productNum", productPojo.getProductNum());
      }
      if (StringUtils.isNotBlank(productPojo.getProductName())) {
        map.put("productName", productPojo.getProductName());
      }
      if (StringUtils.isNotBlank(productPojo.getProductNo())) {
        map.put("productNo", productPojo.getProductNo());
      }
      if (productPojo.getGeekId() != null) {
        map.put("geekId", productPojo.getGeekId());
      }
      map.put("status", productPojo.getStatus());
    }
    int count = productService.getProductByActivityCount(map);
    int totalpage = count / 10;
    if (count % 10 > 0) {
      totalpage++;
    }
    pager.put("now", page.getPageNo());
    pager.put("count", totalpage);
    // 查询数据
    productList = productService.getProductByActivity(productPojo, page);
    for (ProductPojo products : productList) {
      map = new HashMap<String, Object>();
      map.put("goodId", products.getId());
      map.put("title", products.getProductName());
      map.put("img", products.getImage() == null ? "" : ConstParam.URL + "/upfiles/product"
          + File.separator + products.getImage());
      map.put("price", products.getActivePrice());
      map.put("oldPrice", products.getSellingPrice());
      map.put("activityId", products.getActivityId());
      list.add(map);
    }
    pageData.put("list", list);
    pageData.put("pager", pager);
    JSONObject json = JSONObject.fromObject(pageData);
    this.result = json.toString();
    return SUCCESS;
  }

  /**
   * 优惠卷列表
   * 
   * @return
   * @throws Exception
   */
  public String findcouponListAll() throws Exception {
    Map<String, Object> pager = new HashMap<String, Object>();
    Map<String, Object> item = new HashMap<String, Object>();
    Map<String, Object> pageData = new ListOrderedMap();
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      if (couponPojo != null) {
        map.put("name", couponPojo.getName().trim());
        map.put("type", couponPojo.getType());
        map.put("status", couponPojo.getStatus());
        if (couponPojo.getValidStimeDStr().length() >= 10) {
          Date s = sdf.parse(couponPojo.getValidStimeDStr());
          couponPojo.setValidStime(s.getTime() / 1000);
          map.put("validStime", couponPojo.getValidStime());
        }
        if (couponPojo.getValidEtimeDStr().length() >= 10) {
          Date e = sdf.parse(couponPojo.getValidEtimeDStr());
          couponPojo.setValidEtime(e.getTime() / 1000);
          map.put("validEtime", couponPojo.getValidEtime());
        }
        if (couponPojo.getCreateTimeDStr().length() >= 10) {
          Date e = sdf.parse(couponPojo.getCreateTimeDStr());
          couponPojo.setCreateTime(e.getTime() / 1000);
          map.put("createTime", couponPojo.getCreateTime());
        }
      }
      // 查询条数
      couponPojos = couponService.getcouponList(map);
      if (page == null) {
        page = new Pager();
        page.setPageNo(1);
      }
      int count = couponPojos.size();
      int totalpage = count / 10;
      if (count % 10 > 0) {
        totalpage++;
      }
      pager.put("now", page.getPageNo());
      pager.put("count", totalpage);
      // 查询列表
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      couponPojos = couponService.getcouponList(map);
      if (couponPojos != null) {
        for (CouponPojo c : couponPojos) {
          long time = c.getValidStime();
          Date s = new Date(time * 1000);
          c.setValidStimeDStr(sdf.format(s));
          long time2 = c.getValidEtime();
          Date e = new Date(time2 * 1000);
          c.setValidEtimeDStr(sdf.format(e));
          long time3 = c.getCreateTime();
          Date b = new Date(time3 * 1000);
          c.setCreateTimeDStr(sdf.format(b));
        }
      }
      for (CouponPojo couponPojo : couponPojos) {
        item = new HashMap<String, Object>();
        item.put("couponId", couponPojo.getCouponId());
        item.put("title", couponPojo.getName());
        item.put("disabled", true);
        if (couponPojo.getStatus() == 1) {
          item.put("disabled", false);
        }
        item.put("price", couponPojo.getTypeName());
        item.put("starTime", couponPojo.getValidStimeDStr());
        item.put("endTime", couponPojo.getValidEtimeDStr());
        list.add(item);
      }
      pageData.put("list", list);
      pageData.put("pager", pager);
      JSONObject json = JSONObject.fromObject(pageData);
      this.result = json.toString();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 
   * 平台专题可视化web页面
   * 
   * @return
   * @throw
   * @return String
   */
  public String getPlatformSpecialVisApi() {
    Map<String, Object> params = new HashMap<String, Object>();
    if (id == null || id == 0) {
      System.out.println("平台专题id不能为空!");
    } else {
      params.put("id", id);
      params.put("status", 1);
      platformSpecialPojo = platformSpecialService.findSpecialByParams(params);
      if (platformSpecialPojo == null) {
        System.out.println("查询不到该专题!");
      } else {
        if (platformSpecialPojo.getVersion() != null && platformSpecialPojo.getVersion() == 0) {
          return "oldSpecial";
        } else if (platformSpecialPojo.getVersion() != null
            && platformSpecialPojo.getVersion() == 1) {
          return "newSpecial";
        } else {
          System.out.println(">>>>>> 专题" + specialId + "查询版本有误!");
        }
      }
    }
    return null;
  }

}
