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
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.ActivityTimePojo;
import com.tzmb2c.web.pojo.ChildrenChannelPojo;
import com.tzmb2c.web.pojo.ProductTypePojo;
import com.tzmb2c.web.pojo.SpecialProductPojo;
import com.tzmb2c.web.pojo.SpecialShowPojo;
import com.tzmb2c.web.pojo.UserBrandPojo;
import com.tzmb2c.web.service.ActivityGoodsService;
import com.tzmb2c.web.service.ActivityTimeService;
import com.tzmb2c.web.service.ChildrenChannelService;
import com.tzmb2c.web.service.ProductTypeService;
import com.tzmb2c.web.service.SpecialProductService;
import com.tzmb2c.web.service.SpecialShowService;
import com.tzmb2c.web.service.SyntheticalDictService;
import com.tzmb2c.web.service.UserBrandService;

/**
 * 专场special_show 2015-11-24
 * 
 * @author by zhuzehuang
 */
public class SpecialShowAction extends SuperAction {
  @Autowired
  private SpecialShowService specialShowService;
  @Autowired
  private SpecialProductService specialProductService;
  @Autowired
  private ActivityTimeService activityTimeService;
  @Autowired
  private UserBrandService userBrandService;
  @Autowired
  private ProductTypeService productTypeService;
  @Autowired
  private SyntheticalDictService syntheticalDictService;
  @Autowired
  private ActivityGoodsService activityGoodsService;
  @Autowired
  private ChildrenChannelService childrenChannelService;
  @Autowired
  private SellerService sellerService;
  private SpecialShowPojo specialShowPojo;
  private SpecialProductPojo specialProductPojo;
  private ProductTypePojo productTypePojo;
  private ActivityTimePojo activityTimePojo;
  private String result;
  private String[] tids;
  private List<SpecialShowPojo> specialShowPojoList;
  private List<SpecialProductPojo> specialProductPojoList;
  private File upfile;
  private Long t;
  private Long activityId;
  private Long end;
  /**
   * channelIds:多选频道ID
   */
  private String[] channelIds;
  private String channelIdsc;

  public String getChannelIdsc() {
    return channelIdsc;
  }

  public void setChannelIdsc(String channelIdsc) {
    this.channelIdsc = channelIdsc;
  }

  public String[] getChannelIds() {
    return channelIds;
  }

  public void setChannelIds(String[] channelIds) {
    this.channelIds = channelIds;
  }

  public Long getEnd() {
    return end;
  }

  public void setEnd(Long end) {
    this.end = end;
  }

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
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

  public ActivityTimePojo getActivityTimePojo() {
    return activityTimePojo;
  }

  public void setActivityTimePojo(ActivityTimePojo activityTimePojo) {
    this.activityTimePojo = activityTimePojo;
  }

  public File getUpfile() {
    return upfile;
  }

  public void setUpfile(File upfile) {
    this.upfile = upfile;
  }

  public SpecialShowPojo getSpecialShowPojo() {
    return specialShowPojo;
  }

  public void setSpecialShowPojo(SpecialShowPojo specialShowPojo) {
    this.specialShowPojo = specialShowPojo;
  }

  public SpecialProductPojo getSpecialProductPojo() {
    return specialProductPojo;
  }

  public void setSpecialProductPojo(SpecialProductPojo specialProductPojo) {
    this.specialProductPojo = specialProductPojo;
  }

  public List<SpecialShowPojo> getSpecialShowPojoList() {
    return specialShowPojoList;
  }

  public void setSpecialShowPojoList(List<SpecialShowPojo> specialShowPojoList) {
    this.specialShowPojoList = specialShowPojoList;
  }

  public List<SpecialProductPojo> getSpecialProductPojoList() {
    return specialProductPojoList;
  }

  public void setSpecialProductPojoList(List<SpecialProductPojo> specialProductPojoList) {
    this.specialProductPojoList = specialProductPojoList;
  }

  public Long getT() {
    return t;
  }

  public void setT(Long t) {
    this.t = t;
  }

  public ProductTypePojo getProductTypePojo() {
    return productTypePojo;
  }

  public void setProductTypePojo(ProductTypePojo productTypePojo) {
    this.productTypePojo = productTypePojo;
  }

  /**
   * 专场数目
   * 
   * @return
   * @throws Exception
   */
  public String specialShowListCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      // 更新已结束专场状态
      specialShowService.updateSpecialShowEnd();
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("isdelete", "0");
      if (getEnd() != null) {
        map.put("end", getEnd());
      }
      if (specialShowPojo != null) {
        map.put("title", specialShowPojo.getTitle().trim());
        map.put("beginTimeStr", specialShowPojo.getBeginTimeStr());
        map.put("endTimeStr", specialShowPojo.getEndTimeStr());
        map.put("status", specialShowPojo.getStatus());
        map.put("id", specialShowPojo.getId());
        map.put("channelId", specialShowPojo.getChannelId());
      }
      int i = specialShowService.findSpecialShowCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 专场列表
   * 
   * @return
   * @throws Exception
   */
  public String specialShowListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      map.put("isdelete", "0");
      map.put("houtai", 1);
      if (getEnd() != null) {
        map.put("end", getEnd());
      }
      if (specialShowPojo != null) {
        map.put("title", specialShowPojo.getTitle().trim());
        map.put("beginTimeStr", specialShowPojo.getBeginTimeStr());
        map.put("endTimeStr", specialShowPojo.getEndTimeStr());
        map.put("status", specialShowPojo.getStatus());
        map.put("id", specialShowPojo.getId());
        map.put("channelId", specialShowPojo.getChannelId());
      }
      specialShowPojoList = specialShowService.findSpecialShowList(map);
      for (SpecialShowPojo specialShow : specialShowPojoList) {
        specialShow.setAgeRangeName(syntheticalDictService.getfindByIdSyntheticalDict(
            specialShow.getAgeRange()).getName());
        if (specialShow.getDiscountType() != null && specialShow.getDiscountType() != 0
            && specialShow.getDiscountContext() != null
            && !"".equals(specialShow.getDiscountContext())) {
          specialShow.setSpecificDiscount(sellerService.transJsonToDiscountStr(
              specialShow.getDiscountType(), specialShow.getDiscountContext()));
        } else {
          specialShow.setSpecificDiscount("无优惠");
        }
        if (specialShow.getDiscount() != null && specialShow.getDiscount() != 0.0) {
          specialShow.setDiscountStr(specialShow.getDiscount() + "折");
        } else {
          specialShow.setDiscountStr("无折扣");
        }
        /*
         * if(specialShow.getChannelName() == null ){ specialShow.setChannelName("无所属频道"); }
         */
      }
      JSONArray json = JSONArray.fromObject(specialShowPojoList);
      page.setRowCount(specialShowPojoList.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 根据id删除专场
   * 
   * @return
   */
  public String delSpecialShow() throws Exception {
    try {
      /*
       * Map<String, Object> map = new HashMap<String, Object>();
       * map.put("sceneId",scenePojo.getId());
       * sceneProductPojoList=sceneProductService.findSceneProductList(map); for(SceneProductPojo
       * SceneProduct:sceneProductPojoList){
       * activityGoodsService.delActivityGoods(SceneProduct.getGoodId()); }
       */
      /* specialProductService.delSpecialProductBySpecialId(specialShowPojo.getId()); */
      specialShowPojo = specialShowService.findSpecialShowById(specialShowPojo.getId());
      specialShowService.delSpecialShow(specialShowPojo.getId());
      // 删除专场产品设置
      /* activityGoodsService.delActivityGoodsByTimeId(specialShowPojo.getActivityId()); */
      activityTimeService.delActivityTime(specialShowPojo.getActivityId());
      FileUtil.alertMessageBySkip("删除成功！", "specialShowList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("删除失败！", "specialShowList.do");
    }
    return null;
  }

  /**
   * 根据id批量删除专场
   * 
   * @return
   */
  public String delSpecialShowAll() {
    try {
      // Map<String, Object> map = new HashMap<String, Object>();
      if (tids == null || "".equals(tids)) {
        FileUtil.alertMessageBySkip("请勾选需要删除的专场！", "specialShowList.do");
        return null;
      } else {
        for (String id : tids) {
          /*
           * map.put("sceneId",Long.valueOf(id));
           * sceneProductPojoList=sceneProductService.findSceneProductList(map);
           * for(SceneProductPojo SceneProduct:sceneProductPojoList){
           * activityGoodsService.delActivityGoods(SceneProduct.getGoodId()); }
           */
          /* specialProductService.delSpecialProductBySpecialId(Long.valueOf(id)); */
          specialShowPojo = specialShowService.findSpecialShowById(Long.valueOf(id));
          specialShowService.delSpecialShow(Long.valueOf(id));
          // 删除活动产品设置
          /* activityGoodsService.delActivityGoodsByTimeId(specialShowPojo.getActivityId()); */
          activityTimeService.delActivityTime(specialShowPojo.getActivityId());
        }
        FileUtil.alertMessageBySkip("全部删除成功！", "specialShowList.do");
      }
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("全部删除失败！", "specialShowList.do");
    }
    return null;
  }

  /**
   * 跳转至编辑专场页面
   * 
   * @return
   * @throws Exception
   */
  public String goUpdateSpecialShow() throws Exception {
    try {
      t = specialShowPojo.getT();
      specialShowPojo = specialShowService.findSpecialShowById(specialShowPojo.getId());
      if (t != 1) {
        ProductTypePojo productTypePojo = new ProductTypePojo();
        productTypePojo.setId(Long.valueOf(specialShowPojo.getMainCategory1()));
        specialShowPojo.setMainCategory1Name(productTypeService.findProductType(productTypePojo)
            .getName());
        productTypePojo.setId(Long.valueOf(specialShowPojo.getMainCategory2()));
        specialShowPojo.setMainCategory2Name(productTypeService.findProductType(productTypePojo)
            .getName());
        specialShowPojo.setUserBrandIdName(userBrandService.findUserBrandById(
            specialShowPojo.getUserBrandId()).getBrandName());
        specialShowPojo.setAgeRangeName(syntheticalDictService.getfindByIdSyntheticalDict(
            specialShowPojo.getAgeRange()).getName());
        String[] str = null;
        String ss = "";
        if (specialShowPojo.getChannelId() != null && !"".equals(specialShowPojo.getChannelId())) {
          str =
              specialShowPojo.getChannelId().substring(1, specialShowPojo.getChannelId().length())
                  .split(":");
          for (String s : str) {
            ChildrenChannelPojo childrenChannelPojo =
                childrenChannelService.findChildrenChannelById(Long.parseLong(s));
            if (childrenChannelPojo != null && childrenChannelPojo.getName() != null
                && !"".equals(childrenChannelPojo.getName())) {
              ss += childrenChannelPojo.getName() + ",";
            }
          }
          if (ss.length() > 1) {
            ss = ss.substring(0, ss.length() - 1);
          }
          specialShowPojo.setChannelName(ss);
        } else {
          specialShowPojo.setChannelName("无所属频道");
        }
      }
      if (specialShowPojo.getDiscountType() != null && specialShowPojo.getDiscountType() != 0
          && specialShowPojo.getDiscountContext() != null
          && !"".equals(specialShowPojo.getDiscountContext())) {
        specialShowPojo.setSpecificDiscount(sellerService.transJsonToDiscountStr(
            specialShowPojo.getDiscountType(), specialShowPojo.getDiscountContext()));
      } else {
        specialShowPojo.setSpecificDiscount("无优惠");
      }
      specialShowPojo.setT(t);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 编辑专场
   * 
   * @return
   * @throws Throwable
   */
  public String updateSpecialShow() throws Throwable {
    try {
      activityTimePojo = new ActivityTimePojo();
      if (specialShowPojo != null) {
        if (specialShowPojo.getBeginTimeStr() != null && specialShowPojo.getEndTimeStr() != null) {
          Date endTime = new Date();
          endTime = StringUtil.stringDate(specialShowPojo.getEndTimeStr());
          activityTimePojo.setEndTime(specialShowPojo.getEndTimeStr());
          specialShowPojo.setEndTime(endTime);
          Date beginTime = new Date();
          beginTime = StringUtil.stringDate(specialShowPojo.getBeginTimeStr());
          activityTimePojo.setBeginTime(specialShowPojo.getBeginTimeStr());
          specialShowPojo.setBeginTime(beginTime);
        }
        if (upfile != null) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/specialShow")
                  + File.separator;
          FileUtil.uploadFile(file_name, uploadPath, "upfiles/specialShow/", upfile);
          specialShowPojo.setBanner(file_name);
        }
      }
      Date time = new Date();
      specialShowPojo.setUpdateDate(time);
      activityTimePojo.setUpdateDate(time);
      t = specialShowPojo.getT();
      specialShowPojo.setName(userBrandService.findUserBrandById(specialShowPojo.getUserBrandId())
          .getBrandName() + "专场特卖");
      specialShowPojo.setUserId(userBrandService
          .findUserBrandById(specialShowPojo.getUserBrandId()).getUserId());
      if (specialShowPojo.getT() == 1) {
        {
          if (specialShowPojo.getDiscountTypec() != -1) {
            specialShowPojo.setDiscountType(specialShowPojo.getDiscountTypec());
          }
        }
        {
          if (specialShowPojo.getDiscountTypec() != -1 && specialShowPojo.getDiscountType() == 0) {
            specialShowPojo.setDiscountContext("");
          }
          if (specialShowPojo.getDiscountTypec() != -1 && specialShowPojo.getDiscountType() == 1) {
            if (specialShowPojo.getOm1() != null && specialShowPojo.getM1() != null
                & specialShowPojo.getOm3() == null && specialShowPojo.getM3() == null) {
              specialShowPojo.setDiscountContext("[{ \"om\" :\"" + specialShowPojo.getOm1()
                  + "\" ,\"m\": \"" + specialShowPojo.getM1() + "\"}]");
            } else if (specialShowPojo.getOm1() != null && specialShowPojo.getM1() != null
                & specialShowPojo.getOm3() != null && specialShowPojo.getM3() != null) {
              specialShowPojo.setDiscountContext("[{ \"om\" :\"" + specialShowPojo.getOm1()
                  + "\" ,\"m\": \"" + specialShowPojo.getM1() + "\"},{ \"om\" :\""
                  + specialShowPojo.getOm3() + "\" ,\"m\": \"" + specialShowPojo.getM3() + "\"}]");
            } else {
              specialShowPojo.setDiscountContext("");
            }
          }
          if (specialShowPojo.getDiscountTypec() != -1 && specialShowPojo.getDiscountType() == 2) {
            if (specialShowPojo.getOm2() != null && specialShowPojo.getM2() != null
                & specialShowPojo.getOm4() == null && specialShowPojo.getM4() == null) {
              specialShowPojo.setDiscountContext("[{ \"om\" :\"" + specialShowPojo.getOm2()
                  + "\" ,\"m\": \"" + specialShowPojo.getM2() + "\"}]");
            } else if (specialShowPojo.getOm2() != null && specialShowPojo.getM2() != null
                & specialShowPojo.getOm4() != null && specialShowPojo.getM4() != null) {
              specialShowPojo.setDiscountContext("[{ \"om\" :\"" + specialShowPojo.getOm2()
                  + "\" ,\"m\": \"" + specialShowPojo.getM2() + "\"},{ \"om\" :\""
                  + specialShowPojo.getOm4() + "\" ,\"m\": \"" + specialShowPojo.getM4() + "\"}]");
            } else {
              specialShowPojo.setDiscountContext("");
            }
          }
        }
      }
      // 多选频道，以":"分隔（编辑）
      if (channelIds != null && channelIds.length > 0) {
        specialShowPojo.setChannelId(":" + StringUtils.join(channelIds, ":") + ":");
      } else {
        specialShowPojo.setChannelId("");
      }
      // 排期
      if (channelIdsc != null) {
        specialShowPojo.setChannelId(channelIdsc);
      }
      specialShowService.updateSpecialShow(specialShowPojo);
      specialShowPojo.setT(t);
      if (specialShowPojo.getT() == 2 && specialShowPojo.getBeginTimeStr() != null
          && specialShowPojo.getEndTimeStr() != null) {
        specialShowService.schedulingSpecialShow(specialShowPojo.getId());
      }
      specialShowPojo = specialShowService.findSpecialShowById(specialShowPojo.getId());
      activityTimePojo.setType(3);
      activityTimePojo.setId(specialShowPojo.getActivityId());
      activityTimePojo.setTitle(specialShowPojo.getName());
      activityTimeService.updateActivityTime(activityTimePojo);
      FileUtil.alertMessageBySkip("提交成功！", "specialShowList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("提交失败！", "specialShowList.do");
    }
    return null;
  }

  /**
   * 通过审核专场
   * 
   * @return
   */
  public String checkSpecialShow() throws SQLException {
    try {
      specialShowService.checkSpecialShow(specialShowPojo.getId());
      FileUtil.alertMessageBySkip("通过审核成功！", "specialShowList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("通过审核失败！", "specialShowList.do");
    }
    return null;
  }

  /**
   * 取消审核专场
   * 
   * @return
   */
  public String uncheckSpecialShow() throws SQLException {
    try {
      specialShowService.uncheckSpecialShow(specialShowPojo.getId());
      FileUtil.alertMessageBySkip("取消审核成功！", "specialShowList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("取消审核失败！", "specialShowList.do");
    }
    return null;
  }

  /**
   * 退回修改专场
   * 
   * @return
   */
  public String modifySpecialShow() throws SQLException {
    try {
      specialShowService.modifySpecialShow(specialShowPojo.getId());
      specialShowPojo = specialShowService.findSpecialShowById(specialShowPojo.getId());
      if (specialShowPojo != null) {
        specialProductService.uncheckSpecialProductByActivityId(specialShowPojo.getActivityId());
      }
      FileUtil.alertMessageBySkip("退回修改成功！", "specialShowList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("退回修改失败！", "specialShowList.do");
    }
    return null;
  }

  /**
   * 根据id批量通过审核专场
   * 
   * @return
   */
  public String checkSpecialShowAll() {
    try {
      if (tids == null || "".equals(tids)) {
        FileUtil.alertMessageBySkip("请勾选需要审核的专场！", "specialShowList.do");
        return null;
      } else {
        for (String id : tids) {
          specialShowService.checkSpecialShow(Long.valueOf(id));
        }
        FileUtil.alertMessageBySkip("批量审核成功！", "specialShowList.do");
      }
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("批量审核失败！", "specialShowList.do");
    }
    return null;
  }

  /**
   * 根据id批量通过审核专场及其商品
   * 
   * @return
   */
  public String checkSpecialShowProductAll() {
    SpecialShowPojo specialShowPojo = null;
    try {
      if (tids == null || "".equals(tids)) {
        FileUtil.alertMessageBySkip("请勾选需要审核的专场！", "specialShowList.do");
        return null;
      } else {
        for (String id : tids) {
          specialShowPojo = specialShowService.findSpecialShowById(Long.valueOf(id));
          if (specialShowPojo != null) {
            activityId = specialShowPojo.getActivityId();
            specialProductService.checkSpecialProductByActivityId(activityId);
            specialShowService.checkSpecialShow(Long.valueOf(id));
          }
        }
        FileUtil.alertMessageBySkip("批量审核成功！", "specialShowList.do");
      }
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("批量审核失败！", "specialShowList.do");
    }
    return null;
  }

  /**
   * 跳转至新增专场页面
   * 
   * @return
   */
  public String goAddSpecialShowList() {
    return SUCCESS;
  }

  /**
   * 提交新增专场
   * 
   * @return
   */
  public String addSpecialShowListOk() {
    activityTimePojo = new ActivityTimePojo();
    try {
      if (specialShowPojo != null) {
        /*
         * if (specialShowPojo.getBeginTimeStr()!=null && specialShowPojo.getEndTimeStr()!=null) {
         * Date endTime=new Date(); endTime= StringUtil.stringDate(specialShowPojo.getEndTimeStr());
         * activityTimePojo.setEndTime(specialShowPojo.getEndTimeStr());
         * specialShowPojo.setEndTime(endTime); Date beginTime=new Date(); beginTime =
         * StringUtil.stringDate(specialShowPojo.getBeginTimeStr());
         * activityTimePojo.setBeginTime(specialShowPojo.getBeginTimeStr());
         * specialShowPojo.setBeginTime(beginTime); }
         */
        if (upfile != null) {
          String file_name = StringUtil.getCurrentDateStr() + ".jpg";
          String uploadPath =
              ServletActionContext.getServletContext().getRealPath("/upfiles/specialShow")
                  + File.separator;
          FileUtil.uploadFile(file_name, uploadPath, "upfiles/specialShow/", upfile);
          specialShowPojo.setBanner(file_name);
        }
        UserBrandPojo userBrand =
            userBrandService.findUserBrandById(specialShowPojo.getUserBrandId());
        String specialName = userBrand == null ? "专场特卖" : userBrand.getBrandName() + "专场特卖";
        activityTimePojo.setTitle(specialName);
        activityTimePojo.setChannel(1);
        // 1-APP 2-微商城
        activityTimePojo.setCreateBy(1L);
        activityTimePojo.setType(3);
        activityTimeService.insertActivityTime(activityTimePojo);
        Long t = activityTimePojo.getId();
        specialShowPojo.setActivityId(t);
        specialShowPojo.setName(specialName);
        specialShowPojo.setUserId(userBrandService.findUserBrandById(
            specialShowPojo.getUserBrandId()).getUserId());
        {
          if (specialShowPojo.getDiscountType() == 0) {
            specialShowPojo.setDiscountContext("");
          }
          if (specialShowPojo.getDiscountType() == 1) {
            if (specialShowPojo.getOm1() != null && specialShowPojo.getM1() != null
                & specialShowPojo.getOm3() == null && specialShowPojo.getM3() == null) {
              specialShowPojo.setDiscountContext("[{ \"om\" :\"" + specialShowPojo.getOm1()
                  + "\" ,\"m\": \"" + specialShowPojo.getM1() + "\"}]");
            } else if (specialShowPojo.getOm1() != null && specialShowPojo.getM1() != null
                & specialShowPojo.getOm3() != null && specialShowPojo.getM3() != null) {
              specialShowPojo.setDiscountContext("[{ \"om\" :\"" + specialShowPojo.getOm1()
                  + "\" ,\"m\": \"" + specialShowPojo.getM1() + "\"},{ \"om\" :\""
                  + specialShowPojo.getOm3() + "\" ,\"m\": \"" + specialShowPojo.getM3() + "\"}]");
            } else {
              specialShowPojo.setDiscountContext("");
            }
          }
          if (specialShowPojo.getDiscountType() == 2) {
            if (specialShowPojo.getOm2() != null && specialShowPojo.getM2() != null
                & specialShowPojo.getOm4() == null && specialShowPojo.getM4() == null) {
              specialShowPojo.setDiscountContext("[{ \"om\" :\"" + specialShowPojo.getOm2()
                  + "\" ,\"m\": \"" + specialShowPojo.getM2() + "\"}]");
            } else if (specialShowPojo.getOm2() != null && specialShowPojo.getM2() != null
                & specialShowPojo.getOm4() != null && specialShowPojo.getM4() != null) {
              specialShowPojo.setDiscountContext("[{ \"om\" :\"" + specialShowPojo.getOm2()
                  + "\" ,\"m\": \"" + specialShowPojo.getM2() + "\"},{ \"om\" :\""
                  + specialShowPojo.getOm4() + "\" ,\"m\": \"" + specialShowPojo.getM4() + "\"}]");
            } else {
              specialShowPojo.setDiscountContext("");
            }
          }
        }

        // 多选频道，以":"分隔
        if (channelIds != null && channelIds.length > 0) {
          specialShowPojo.setChannelId(":" + StringUtils.join(channelIds, ":") + ":");
        } else {
          specialShowPojo.setChannelId("");
        }

        specialShowService.insertSpecialShow(specialShowPojo);
        FileUtil.alertMessageBySkip("新增专场成功！", "specialShowList.do");
      }
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("新增专场失败！", "specialShowList.do");
    }
    return null;
  }
}
