package com.tzmb2c.web.action;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.business.service.ConstParam;
import com.tzmb2c.business.service.SellerService;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.ActivityGoodsPojo;
import com.tzmb2c.web.pojo.ChildrenChannelHistoryPojo;
import com.tzmb2c.web.pojo.HomePageIconPojo;
import com.tzmb2c.web.pojo.SpecialProductPojo;
import com.tzmb2c.web.pojo.SpecialShowPojo;
import com.tzmb2c.web.pojo.UserBrandPojo;
import com.tzmb2c.web.service.ActivityGoodsService;
import com.tzmb2c.web.service.ChildrenChannelHistoryService;
import com.tzmb2c.web.service.HomePageIconService;
import com.tzmb2c.web.service.SpecialShowService;
import com.tzmb2c.web.service.UserBrandService;
import com.tzmb2c.web.service.VisualGoodSetingService;

public class AppApiChildrenChannelAction extends SuperAction {
  private static final long serialVersionUID = 1L;

  @Autowired
  private SellerService sellerService;
  @Autowired
  private SpecialShowService specialShowService;
  @Autowired
  private ChildrenChannelHistoryService childrenChannelHistoryService;
  @Autowired
  private HomePageIconService homePageIconService;
  @Autowired
  private ActivityGoodsService activityGoodsService;
  @Autowired
  private UserBrandService userBrandService;
  @Autowired
  private VisualGoodSetingService visualGoodSetingService;

  private Integer pageNo;
  /**
   * 商品id
   */
  private Long productId;
  /**
   * 会员id
   */
  private Long uid;
  /**
   * 活动类型
   */
  private Long activityId;
  private Long id;// 级别ID
  /**
   * 产品/店铺/专场ID
   */
  private Integer favSenId;
  /**
   * 产品/店铺/分销产品库/专场
   */
  private Integer source;// 设置排序1销量降序、11销量升序、2价格降序、22价格升序、3点击率降序、33点击率升序
  private String channelId;// 频道ID

  public String getChannelId() {
    return channelId;
  }

  public void setChannelId(String channelId) {
    this.channelId = channelId;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }

  public Integer getFavSenId() {
    return favSenId;
  }

  public void setFavSenId(Integer favSenId) {
    this.favSenId = favSenId;
  }

  public Long getUid() {
    return uid;
  }

  public Integer getPageNo() {
    return pageNo;
  }

  public void setPageNo(Integer pageNo) {
    this.pageNo = pageNo;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public Long getActivityId() {
    return activityId;
  }

  public void setActivityId(Long activityId) {
    this.activityId = activityId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getSource() {
    return source;
  }

  public void setSource(Integer source) {
    this.source = source;
  }

  /**
   * 儿童频道专场列表
   * 
   * @return
   * @throws SQLException
   */
  public String childrenChannelSpecialListApi() throws SQLException {
    String msg = "";
    boolean success = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Object> result = new ArrayList<Object>();

    // if (channelId == null || "".equals(channelId)) {
    if (id == null || id == 0) {
      msg = "频道ID不能为空哦！~";
    } else {
      // 专场所属频道
      Map<String, Object> option = new HashMap<String, Object>();
      String channelId = id.toString();
      option.put("channelId", channelId);
      option.put("status", 4);
      // 未删除
      option.put("isdelete", "0");
      option.put("currentTimeStr", StringUtil.dateString(new Date()));
      // 分页
      int pageSize = 10;
      if (pageNo == null || pageNo == 0) {
        option.put("pageNo", 0);
        option.put("pageSize", pageSize);
      } else {
        option.put("pageNo", (pageNo - 1) * pageSize);
        option.put("pageSize", pageSize);
      }
      List<SpecialShowPojo> list = specialShowService.findSpecialShowList(option);
      if (list.size() != 0) {
        Map<String, Object> item = null;
        Calendar cal = Calendar.getInstance();
        Date end = null;
        for (SpecialShowPojo s : list) {
          item = new HashMap<String, Object>();
          // item.put("name", s.getName());
          item.put("name", s.getTitle());
          item.put("banner",
              ConstParam.URL + "/upfiles/specialShow" + File.separator + s.getBanner());
          item.put("activityId", s.getActivityId() == null ? "" : s.getActivityId());
          item.put("beginTime", s.getBeginTimeStr());
          // 特殊处理 + 8H
          cal.setTime(s.getEndTime());
          cal.add(Calendar.HOUR, 8);
          end = cal.getTime();
          item.put("endTime", StringUtil.dateString(end));
          item.put("endTime2", s.getEndTimeStr());
          int discountType = s.getDiscountType() == null ? 0 : s.getDiscountType();
          item.put("discountType", discountType);
          item.put("discountText",
              sellerService.transJsonToDiscountStr(discountType, s.getDiscountContext()));
          item.put("discount", s.getDiscount() == null || s.getDiscount() == 0
              || s.getDiscount() == 0.0 ? "" : s.getDiscount() + "折起");
          item.put("currentTime", StringUtil.dateString(new Date()));
          result.add(item);
        }
        success = true;
      } else {
        msg = "暂时还没有该频道的专场哦！~";
      }

      // 少儿频道浏览记录
      option.clear();
      option.put("type", 1);
      if (uid == null || uid == 0) {
        uid = 0l;
      }
      option.put("userId", uid);
      option.put("businessId", id);
      option.put("createDate", StringUtil.dateToString(new Date()));
      List<ChildrenChannelHistoryPojo> historys =
          childrenChannelHistoryService.findChildrenChannelHistoryList(option);
      ChildrenChannelHistoryPojo childrenChannelHistory = null;
      if (historys.size() != 0) {
        childrenChannelHistory = new ChildrenChannelHistoryPojo();
        childrenChannelHistory.setId(historys.get(0).getId());

        childrenChannelHistory.setHid(historys.get(0).getHid() + 1);
        childrenChannelHistoryService.updateChildrenChannelHistory(childrenChannelHistory);
      } else {
        childrenChannelHistory = new ChildrenChannelHistoryPojo();
        childrenChannelHistory.setUserId(uid);
        childrenChannelHistory.setType(1);

        childrenChannelHistory.setBusinessId(id);
        childrenChannelHistory.setHid(1);
        childrenChannelHistory.setActivityId(0l);
        childrenChannelHistoryService.insertChildrenChannelHistory(childrenChannelHistory);
      }
    }

    map.put("result", result);
    map.put("error_msg", msg);
    map.put("success", success);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 首页图标接口
   * 
   * @return
   * @throws Exception
   */
  public String homePageIcon() throws Exception {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> option = new HashMap<String, Object>();
    option.put("status", 1);
    option.put("pageNo", 4);
    option.put("pageSize", 5);
    List<HomePageIconPojo> homePageIconPojo = homePageIconService.findHomePageIconList(option);
    List<Object> list = new ArrayList<Object>();
    Map<String, Object> result = null;
    if (homePageIconPojo.size() > 0) {
      for (HomePageIconPojo h : homePageIconPojo) {
        result = new HashMap<String, Object>();
        result.put("image", ConstParam.URL + "/upfiles/icon/" + h.getImage());
        result.put("title", h.getTitle() == null ? "" : h.getTitle());
        result.put("sorting", h.getSorting());
        list.add(result);
      }
      b = true;
    } else {
      msg = "未找到设置信息!";
    }
    map.put("result", list);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * 专场产品列表
   * 
   * @return
   * @throws SQLException
   * @throws ParseException
   */
  public String getSpecialProductListApi() throws SQLException {
    String msg = "";
    boolean b = false;
    Map<String, Object> map = new HashMap<String, Object>();
    Map<String, Object> result = new HashMap<String, Object>();
    if (activityId == null || activityId == 0) {
      msg = "活动ID不能为空哦~";
    } else {
      Map<String, Object> option = new HashMap<String, Object>();
      // option.put("id", id);
      option.put("activityId", activityId);
      option.put("status", 4);
      // 未删除
      option.put("isdelete", "0");
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      option.put("currentTimeStr", sdf.format(new Date()));

      List<SpecialShowPojo> specialShowPojos = new ArrayList<SpecialShowPojo>();
      specialShowPojos = specialShowService.findSpecialShowList(option);
      Date end = null;
      Calendar cal = Calendar.getInstance();
      if (specialShowPojos.size() > 0) {
        SpecialShowPojo specialShowPojo = specialShowPojos.get(0);
        result.put("banner", ConstParam.URL + "/upfiles/specialShow" + File.separator
            + specialShowPojo.getBanner());
        result.put("introduction", specialShowPojo.getIntroduction());

        UserBrandPojo userBrand =
            userBrandService.findUserBrandById(specialShowPojo.getUserBrandId());
        if (userBrand != null) {
          result.put("introduction",
              userBrand.getBrandDisc() == null ? "" : userBrand.getBrandDisc());
        }
        int discountType =
            specialShowPojo.getDiscountType() == null ? 0 : specialShowPojo.getDiscountType();
        result.put("discountType", discountType);
        result.put("discountText", sellerService.transJsonToDiscountStr(discountType,
            specialShowPojo.getDiscountContext()));
        result.put("beginTime", specialShowPojo.getBeginTimeStr());
        // result.put("endTime", specialShowPojo.getEndTimeStr());
        // 特殊处理 + 8H
        cal.setTime(specialShowPojo.getEndTime());
        cal.add(Calendar.HOUR, 8);
        end = cal.getTime();
        result.put("endTime", sdf.format(end));
        result.put("endTime2", specialShowPojo.getEndTimeStr());
        result.put("currentTime", StringUtil.dateString(new Date()));

        option.clear();
        List<Object> list = new ArrayList<Object>();
        Map<String, Object> item = new HashMap<String, Object>();

        option.put("timeId", activityId);
        option.put("status", 1);
        option.put("proStatus", 1);
        if (source != null && source != 0) {
          option.put("sort", source);
        }
        // 分页
        int pageSize = 10;
        if (pageNo == null || pageNo == 0) {
          option.put("pageNo", 0);
          option.put("pageSize", pageSize);
        } else {
          option.put("pageNo", (pageNo - 1) * pageSize);
          option.put("pageSize", pageSize);
        }

        List<ActivityGoodsPojo> activityGoodsPojos =
            activityGoodsService.findActivityGoodsList(option);
        DecimalFormat df = new DecimalFormat("#.##");
        if (activityGoodsPojos.size() > 0) {
          for (ActivityGoodsPojo a : activityGoodsPojos) {
            item = new HashMap<String, Object>();
            item.put("productId", a.getProductId());
            item.put("image",
                ConstParam.URL + "/upfiles/product" + File.separator + a.getProductImage());
            item.put("banner",
                ConstParam.URL + "/upfiles/product" + File.separator + a.getProductImage());
            item.put("productName", a.getProductName());
            item.put("activePrice", df.format(a.getActivePrice()));
            item.put("activityId", a.getTimeId());
            item.put("sellPrice", df.format(a.getSellPrice()));
            list.add(item);
          }
        }
        result.put("productList", list);
        b = true;
      } else {
        msg = "不好意思，未找到活动信息!";
        b = false;
      }

      // 少儿频道专场浏览记录
      option.clear();
      option.put("type", 2);
      if (uid == null || uid == 0) {
        uid = 0l;
      }
      option.put("userId", uid);
      option.put("businessId", activityId);
      option.put("createDate", StringUtil.dateToString(new Date()));
      List<ChildrenChannelHistoryPojo> historys =
          childrenChannelHistoryService.findChildrenChannelHistoryList(option);
      ChildrenChannelHistoryPojo childrenChannelHistory = null;
      if (historys.size() != 0) {
        childrenChannelHistory = new ChildrenChannelHistoryPojo();
        childrenChannelHistory.setId(historys.get(0).getId());

        childrenChannelHistory.setHid(historys.get(0).getHid() + 1);
        childrenChannelHistoryService.updateChildrenChannelHistory(childrenChannelHistory);
      } else {
        childrenChannelHistory = new ChildrenChannelHistoryPojo();
        childrenChannelHistory.setUserId(uid);
        childrenChannelHistory.setType(2);

        childrenChannelHistory.setBusinessId(activityId);
        childrenChannelHistory.setHid(1);
        childrenChannelHistory.setActivityId(activityId);
        childrenChannelHistoryService.insertChildrenChannelHistory(childrenChannelHistory);
      }
    }
    map.put("result", result);
    map.put("error_msg", msg);
    map.put("success", b);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * tv视频列表
   * 
   * @return
   * @throws SQLException
   */
  public String tvVideoListApi() throws SQLException {
    String msg = "";
    boolean success = false;
    Map<String, Object> map = new HashMap<String, Object>();
    List<Object> result = new ArrayList<Object>();

    Map<String, Object> option = new HashMap<String, Object>();
    option.put("videoUrl", "videoUrl");
    option.put("videoUrlImage", "videoUrlImage");
    option.put("proStatus", 1);
    option.put("status", 1);
    // option.put("specialStatus", 4);
    // 分页
    int pageSize = 10;
    if (pageNo == null || pageNo == 0) {
      option.put("pageNo", 0);
      option.put("pageSize", pageSize);
    } else {
      option.put("pageNo", (pageNo - 1) * pageSize);
      option.put("pageSize", pageSize);
    }
    List<SpecialProductPojo> list = visualGoodSetingService.findVisualGoodSetingList(option);
    if (list.size() != 0) {
      Map<String, Object> item = null;
      for (SpecialProductPojo s : list) {
        item = new HashMap<String, Object>();
        item.put("url", s.getVideoUrl());
        item.put("image",
            ConstParam.URL + "/upfiles/product" + File.separator + s.getVideoUrlImage());
        item.put("productId", s.getProductId() == null ? 0 : s.getProductId());
        item.put("productName", s.getProductName() == null ? "" : s.getProductName());
        item.put("activityId", s.getActivityId() == null ? 0 : s.getActivityId());
        result.add(item);
      }
      success = true;
    } else {
      msg = "暂时还没有tv视频哦！~";
    }

    map.put("result", result);
    map.put("error_msg", msg);
    map.put("success", success);
    JSONObject json = JSONObject.fromObject(map);
    ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    try {
      ServletActionContext.getResponse().getWriter().write(json.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * tv视频浏览记录
   * 
   * @return
   * @throws SQLException
   */
  public void tvVideoHistoryApi() throws SQLException {
    // String msg = "";
    // boolean success = false;
    // Map<String, Object> map = new HashMap<String, Object>();
    // List<Object> result = new ArrayList<Object>();

    // tv视频浏览记录
    Map<String, Object> option = new HashMap<String, Object>();
    option.put("type", 4);
    if (uid == null || uid == 0) {
      uid = 0l;
    }
    option.put("userId", uid);
    if (activityId == null || activityId == 0) {
      activityId = 0l;
    }
    option.put("activityId", activityId);
    if (productId == null || productId == 0) {
      productId = 0l;
    }
    option.put("businessId", productId);
    option.put("createDate", StringUtil.dateToString(new Date()));
    List<ChildrenChannelHistoryPojo> historys =
        childrenChannelHistoryService.findChildrenChannelHistoryList(option);
    ChildrenChannelHistoryPojo childrenChannelHistory = null;
    if (historys.size() != 0) {
      childrenChannelHistory = new ChildrenChannelHistoryPojo();
      childrenChannelHistory.setId(historys.get(0).getId());

      childrenChannelHistory.setHid(historys.get(0).getHid() + 1);
      childrenChannelHistoryService.updateChildrenChannelHistory(childrenChannelHistory);
    } else {
      childrenChannelHistory = new ChildrenChannelHistoryPojo();
      childrenChannelHistory.setUserId(uid);
      childrenChannelHistory.setType(4);

      childrenChannelHistory.setBusinessId(productId);
      childrenChannelHistory.setHid(1);
      childrenChannelHistory.setActivityId(activityId);
      childrenChannelHistoryService.insertChildrenChannelHistory(childrenChannelHistory);
    }

    // map.put("result", result);
    // map.put("error_msg", msg);
    // map.put("success", success);
    // JSONObject json = JSONObject.fromObject(map);
    // ServletActionContext.getResponse().setContentType("text/html; charset=utf-8");
    // try {
    // ServletActionContext.getResponse().getWriter().write(json.toString());
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // return null;
  }
}
