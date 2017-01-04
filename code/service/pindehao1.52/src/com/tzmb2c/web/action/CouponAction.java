package com.tzmb2c.web.action;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.IdWorker;
import com.tzmb2c.utils.StringUtil;
import com.tzmb2c.web.pojo.CouponOrderPojo;
import com.tzmb2c.web.pojo.CouponPojo;
import com.tzmb2c.web.pojo.HongbaoLogPojo;
import com.tzmb2c.web.pojo.LotteryForwardPojo;
import com.tzmb2c.web.pojo.LotteryLogPojo;
import com.tzmb2c.web.pojo.LotteryPojo;
import com.tzmb2c.web.pojo.ProductPojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.pojo.UserCouponPojo;
import com.tzmb2c.web.service.CouponService;
import com.tzmb2c.web.service.ProductService;
import com.tzmb2c.web.service.SysLoginService;

public class CouponAction extends SuperAction {

  @Autowired
  private CouponService couponService;
  @Autowired
  private ProductService productService;
  private List<CouponPojo> couponPojos;
  private CouponPojo couponPojo;
  private String result;
  private String[] tids;
  private Integer type;
  @Autowired
  private SysLoginService sysLoginService;
  private SysLoginPojo sysLoginPojo;

  public SysLoginPojo getSysLoginPojo() {
    return sysLoginPojo;
  }

  public void setSysLoginPojo(SysLoginPojo sysLoginPojo) {
    this.sysLoginPojo = sysLoginPojo;
  }

  private List<CouponPojo> couponList;

  /**
   * 优惠卷数量
   * 
   * @return
   * @throws Exception
   */
  public String couponListCount() throws Exception {

    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (couponPojo != null) {
        map.put("name", couponPojo.getName().trim());
        map.put("type", couponPojo.getType());
        map.put("status", couponPojo.getStatus());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (couponPojo.getValidStimeDStr().length() >= 10) {
          Date s = sdf.parse(couponPojo.getValidStimeDStr() + " 00:00:00");
          couponPojo.setValidStime(s.getTime() / 1000);
          map.put("validStime", couponPojo.getValidStime());
        }
        if (couponPojo.getValidEtimeDStr().length() >= 10) {
          Date e = sdf.parse(couponPojo.getValidEtimeDStr() + " 00:00:00");
          couponPojo.setValidEtime(e.getTime() / 1000);
          map.put("validEtime", couponPojo.getValidEtime());
        }
        if (couponPojo.getCreateTimeDStr().length() >= 10) {
          Date e = sdf.parse(couponPojo.getCreateTimeDStr() + " 00:00:00");
          couponPojo.setCreateTime(e.getTime() / 1000);
          map.put("createTime", couponPojo.getCreateTime());
        }
      }
      int i = couponService.getCouponCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  /**
   * 优惠卷列表
   * 
   * @return
   * @throws Exception
   */
  public String couponListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      if (couponPojo != null) {
        map.put("name", couponPojo.getName().trim());
        map.put("type", couponPojo.getType());
        map.put("status", couponPojo.getStatus());
        if (couponPojo.getValidStimeDStr().length() >= 10) {
          Date s = sdf.parse(couponPojo.getValidStimeDStr() + " 00:00:00");
          couponPojo.setValidStime(s.getTime() / 1000);
          map.put("validStime", couponPojo.getValidStime());
        }
        if (couponPojo.getValidEtimeDStr().length() >= 10) {
          Date e = sdf.parse(couponPojo.getValidEtimeDStr() + " 00:00:00");
          couponPojo.setValidEtime(e.getTime() / 1000);
          map.put("validEtime", couponPojo.getValidEtime());
        }
        if (couponPojo.getCreateTimeDStr().length() >= 10) {
          Date e = sdf.parse(couponPojo.getCreateTimeDStr() + " 00:00:00");
          couponPojo.setCreateTime(e.getTime() / 1000);
          map.put("createTime", couponPojo.getCreateTime());
        }
      }
      couponPojos = couponService.getcouponList(map);
      ProductPojo productparam = new ProductPojo();
      ProductPojo product = null;
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
          c.setProductName("无");
          if (c.getProductId() != null && c.getProductId() != 0) {
            productparam.setId(c.getProductId());
            product = productService.findProduct(productparam);
            if (product != null) {
              c.setProductName(product.getProductName());
            }
          }
        }
      }
      JSONArray json = JSONArray.fromObject(couponPojos);
      // page.setRowCount(couponPojos.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  public List<CouponPojo> getCouponList() {
    return couponList;
  }

  public void setCouponList(List<CouponPojo> couponList) {
    this.couponList = couponList;
  }

  public List<CouponPojo> getCouponPojos() {
    return couponPojos;
  }

  public void setCouponPojos(List<CouponPojo> couponPojos) {
    this.couponPojos = couponPojos;
  }

  public CouponPojo getCouponPojo() {
    return couponPojo;
  }

  public void setCouponPojo(CouponPojo couponPojo) {
    this.couponPojo = couponPojo;
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

  /**
   * 根据id删除优惠卷
   * 
   * @return
   * @throws Exception
   */
  public String deletecouponById() throws Exception {
    this.result = "0";
    try {
      couponService.deletecouponById(couponPojo.getCouponId());
      this.result = "1";
      // FileUtil.alertMessageBySkip("删除成功！", "couponList.do");

    } catch (Exception e) {
      e.printStackTrace();
      this.result = "0";
      // FileUtil.alertMessageBySkip("删除失败！", "couponList.do");
    }

    return SUCCESS;
  }

  /**
   * 根据id批量删除优惠卷
   * 
   * @return
   */
  public String deletecouponAllById() {

    try {
      for (String id : tids) {
        couponService.deletecouponById(Long.valueOf(id));
      }
      FileUtil.alertMessageBySkip("删除成功！", "couponList.do");

    } catch (Exception e) {
      FileUtil.alertMessageBySkip("删除失败！", "couponList.do");
    }
    return null;
  }

  /**
   * 根据id启用优惠卷
   * 
   * @return
   * @throws Exception
   */
  public String checkcouponById() throws Exception {
    this.result = "0";
    try {
      couponService.checkcouponById(couponPojo.getCouponId());
      this.result = "1";
      // FileUtil.alertMessageBySkip("启用成功！", "couponList.do");

    } catch (Exception e) {
      e.printStackTrace();
      this.result = "0";
      // FileUtil.alertMessageBySkip("启用失败！", "couponList.do");
    }

    return SUCCESS;
  }

  /**
   * 根据id禁用优惠卷
   * 
   * @return
   * @throws Exception
   */
  public String uncheckcouponById() throws Exception {
    this.result = "0";
    try {
      couponService.uncheckcouponById(couponPojo.getCouponId());
      this.result = "1";
      // FileUtil.alertMessageBySkip("禁用成功！", "couponList.do");

    } catch (Exception e) {
      e.printStackTrace();
      this.result = "0";
      // FileUtil.alertMessageBySkip("禁用失败！", "couponList.do");
    }

    return SUCCESS;
  }

  /**
   * 根据id批量启用优惠卷
   * 
   * @return
   */
  public String checkcouponAllById() {

    try {
      for (String id : tids) {
        couponService.checkcouponById(Long.valueOf(id));
      }
      FileUtil.alertMessageBySkip("启用成功！", "couponList.do");

    } catch (Exception e) {
      FileUtil.alertMessageBySkip("启用失败！", "couponList.do");
    }
    return null;
  }

  /**
   * 新增优惠卷
   * 
   * @return
   */
  public String addcoupon() {

    return SUCCESS;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  /**
   * 提交新增的优惠卷
   * 
   * @return
   */
  public String addcouponOk() {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try {
      if (couponPojo != null) {
        Date s = sdf.parse(couponPojo.getValidStimeDStr());
        Date e = sdf.parse(couponPojo.getValidEtimeDStr());
        couponPojo.setValidStime(s.getTime() / 1000);
        couponPojo.setValidEtime(e.getTime() / 1000);
        couponPojo.setCreateTime(new Date().getTime() / 1000);
        Map<String, Object> param = new HashMap<String, Object>();
        if (couponPojo.getType() == 0) {
          param.put("pid", couponPojo.getPid());
          param.put("n", couponPojo.getN());
        } else if (couponPojo.getType() == 1) {
          param.put("om", couponPojo.getOm());
          param.put("m", couponPojo.getM());
        } else if (couponPojo.getType() == 2) {
          param.put("m", couponPojo.getM2());
        }
        // 非指定商品，ID为0
        if (couponPojo.getIsProduct() != 1) {
          couponPojo.setProductId(0l);
        }
        JSONObject json = JSONObject.fromObject(param);
        couponPojo.setContent(json.toString());
        couponService.addcoupon(couponPojo);
        FileUtil.alertMessageBySkip("新增成功！", "couponList.do");
      }
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("新增失败！", "couponList.do");
    }
    return null;
  }

  /**
   * 修改的优惠卷
   * 
   * @return
   */
  public String updatecoupon() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try {
      if (couponPojo != null && couponPojo.getCouponId() != null) {
        couponPojo = couponService.getcouponById(couponPojo.getCouponId());
        if (couponPojo != null) {

          org.json.JSONObject json = new org.json.JSONObject(couponPojo.getContent());
          if (couponPojo.getType().intValue() == 0) {
            couponPojo.setPid(json.get("pid").toString());
            couponPojo.setN(json.get("n").toString());
          } else if (couponPojo.getType().intValue() == 1) {
            couponPojo.setOm(json.get("om").toString());
            couponPojo.setM(json.get("m").toString());
          } else if (couponPojo.getType().intValue() == 2) {
            couponPojo.setM2(json.get("m").toString());
          }

          long time = couponPojo.getValidStime();
          Date s = new Date(time * 1000);
          couponPojo.setValidStimeDStr(sdf.format(s));
          long time2 = couponPojo.getValidEtime();
          Date e = new Date(time2 * 1000);
          couponPojo.setValidEtimeDStr(sdf.format(e));
        }
      }
      type = 1;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 提交修改的优惠卷
   * 
   * @return
   */
  public String updatecouponOk() {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try {

      if (couponPojo != null && couponPojo.getCouponId() != null) {
        Date s = sdf.parse(couponPojo.getValidStimeDStr());
        Date e = sdf.parse(couponPojo.getValidEtimeDStr());
        couponPojo.setValidStime(s.getTime() / 1000);
        couponPojo.setValidEtime(e.getTime() / 1000);
        // couponPojo.setCreateTime((int) (new Date().getTime()/1000));
        Map<String, Object> param = new HashMap<String, Object>();
        if (couponPojo.getType() == 0) {
          param.put("pid", couponPojo.getPid());
          param.put("n", couponPojo.getN());
        } else if (couponPojo.getType() == 1) {
          param.put("om", couponPojo.getOm());
          param.put("m", couponPojo.getM());
        } else if (couponPojo.getType() == 2) {
          param.put("m", couponPojo.getM2());
        }
        // 非指定商品，ID为0
        if (couponPojo.getIsProduct() != 1) {
          couponPojo.setProductId(0l);
        }

        JSONObject json = JSONObject.fromObject(param);
        couponPojo.setContent(json.toString());
        couponService.updatecouponById(couponPojo);
        FileUtil.alertMessageBySkip("修改成功！", "couponList.do");
      }
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("修改失败！", "couponList.do");
    }
    return null;
  }

  /**
   * 验证优惠卷名称是否存在
   * 
   * @return
   */
  public String validCouponName() {

    try {
      result = "0";
      if (couponPojo != null) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", couponPojo.getName());
        int count = couponService.getCouponCount(map);
        if (count == 0) {
          result = "1";
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      result = "0";
    }
    return SUCCESS;
  }

  private UserCouponPojo userCouponPojo;
  private List<UserCouponPojo> userCouponPojos;

  /**
   * 用户优惠卷数量
   * 
   * @return
   * @throws Exception
   */
  public String userCouponListCount() throws Exception {

    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      if (userCouponPojo != null) {
        map.put("couponNo", userCouponPojo.getCouponNo().trim());
        map.put("couponName", userCouponPojo.getCouponName().trim());
        map.put("type", userCouponPojo.getType());
        map.put("status", userCouponPojo.getStatus());
        map.put("used", userCouponPojo.getUsed());
        if (userCouponPojo.getGenTimeDStr().length() >= 10) {
          Date g = sdf.parse(userCouponPojo.getGenTimeDStr() + " 00:00:00");
          userCouponPojo.setGenTime(g.getTime() / 1000);
          map.put("genTime", userCouponPojo.getGenTime());
        }
        if (userCouponPojo.getUseTimeDStr().length() >= 10) {
          Date u = sdf.parse(userCouponPojo.getUseTimeDStr() + " 00:00:00");
          userCouponPojo.setUseTime(u.getTime() / 1000);
          map.put("genTime", userCouponPojo.getUseTime());
        }
      }
      page.setRowCount(couponService.getuserCouponCount(map));
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  /**
   * 用户优惠卷列表
   * 
   * @return
   * @throws Exception
   */
  public String userCouponListAll() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (userCouponPojo != null) {
      map.put("couponNo", userCouponPojo.getCouponNo().trim());
      map.put("couponName", userCouponPojo.getCouponName().trim());
      map.put("type", userCouponPojo.getType());
      map.put("status", userCouponPojo.getStatus());
      map.put("used", userCouponPojo.getUsed());
      map.put("orderBy", "u.gen_time desc");
      if (userCouponPojo.getGenTimeDStr().length() >= 10) {
        Date g = sdf.parse(userCouponPojo.getGenTimeDStr() + " 00:00:00");
        userCouponPojo.setGenTime(g.getTime() / 1000);
        map.put("genTime", userCouponPojo.getGenTime());
      }
      if (userCouponPojo.getUseTimeDStr().length() >= 10) {
        Date u = sdf.parse(userCouponPojo.getUseTimeDStr() + " 00:00:00");
        userCouponPojo.setUseTime(u.getTime() / 1000);
        map.put("useTime", userCouponPojo.getUseTime());
      }
    }
    userCouponPojos = couponService.getuserCouponList(map);
    if (userCouponPojos != null) {
      for (UserCouponPojo u : userCouponPojos) {
        long time = u.getGenTime();
        Date g = new Date(time * 1000);
        u.setGenTimeDStr(sdf.format(g));
        long time2 = u.getUseTime();
        if (time2 != 0) {
          Date us = new Date(time2 * 1000);
          u.setUseTimeDStr(sdf.format(us));
        }
        long time3 = u.getValidstime();
        Date s = new Date(time3 * 1000);
        u.setValidstimeStr(sdf.format(s));
        long time4 = u.getValidetime();
        Date e = new Date(time4 * 1000);
        u.setValidetimeStr(sdf.format(e));
      }

    }
    JSONArray json = JSONArray.fromObject(userCouponPojos);
    page.setRowCount(userCouponPojos.size());
    page.setResult(json.toString());
    return SUCCESS;
  }

  /**
   * 新增优惠券页面
   * 
   * @return
   * @throws Exception
   */
  public String goAddUserCoupon() throws Exception {
    Map<String, Object> param = new HashMap<String, Object>();
    param.put("status", 1);
    couponList = couponService.getcouponList(param);
    return SUCCESS;
  }

  /**
   * 新增优惠券
   * 
   * @return
   * @throws Exception
   */
  public String insertUserCoupon() throws Exception {
    if (userCouponPojo != null) {
      // 判断是否指定商品
      if (userCouponPojo.getCouponId() != null) {
        couponPojo = couponService.getcouponById(userCouponPojo.getCouponId());
        if (couponPojo != null && couponPojo.getIsProduct() == 1
            && couponPojo.getProductId() != null) {
          userCouponPojo.setProductId(couponPojo.getProductId());
        }
      }
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      long gentime = sdf.parse(StringUtil.dateString(new Date())).getTime();
      // 优惠券码规则
      // IdWorker idWorker = new IdWorker(0,0);
      // userCouponPojo.setCouponNo(String.valueOf(idWorker.nextId()));
      userCouponPojo.setCouponNo(String.valueOf(gentime / 1000) + StringUtil.genRandomStr(5));
      Date sd = sdf.parse(userCouponPojo.getValidstimeStr());
      Date ed = sdf.parse(userCouponPojo.getValidetimeStr());
      userCouponPojo.setValidstime(sd.getTime() / 1000);
      userCouponPojo.setValidetime(ed.getTime() / 1000);
      // 用户
      userCouponPojo.setUserId(0L);
      // userCouponPojo.setStatus(0);
      userCouponPojo.setGenTime(gentime / 1000);
      userCouponPojo.setUsed(0);
      userCouponPojo.setUseTime(0L);
      userCouponPojo.setSource(0);

      String msg = "";
      if (sysLoginPojo != null && !"".equals(sysLoginPojo.getLoginname())) {
        sysLoginPojo = sysLoginService.findSysLoginByLoginname(sysLoginPojo);
        if (sysLoginPojo != null) {
          userCouponPojo.setUserId(sysLoginPojo.getId());
          userCouponPojo.setSource(6);
          // List<SysLoginPojo> sysLoginPojos = new ArrayList<SysLoginPojo>();
          // sysLoginPojos = sysLoginService.sysLoginAllList(sysLoginPojo, null, null);
          // if (sysLoginPojos.size() != 0) {
          // userCoupon.setUserId(sysLoginPojos.get(0).getId());
        } else {
          // FileUtil.alertMessageBySkip("该用户帐号不存在！", "userCouponList.do");
          // userCoupon.setUserId((long)0);
          // userCoupon.setSource(0);
          msg = "该用户帐号不存在！";
        }
      }
      try {
        couponService.addUserCoupon(userCouponPojo);
        FileUtil.alertMessageBySkip(msg + "新增成功！", "userCouponList.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("新增失败！", "userCouponList.do");
      }
    } else {
      FileUtil.alertMessageBySkip("新增失败！", "userCouponList.do");
    }
    return null;
  }

  //-------------------------批量新增优惠券开始-----------------------------------//
  /**
   * 进入批量新增优惠券页面
   * 
   * @return
   * @throws Exception
   */
  public String goAddUserCouponBatch() throws Exception {
    Map<String, Object> param = new HashMap<String, Object>();
    param.put("status", 1);
    couponList = couponService.getcouponList(param);
    return SUCCESS;
  }

  /**
   * 新增优惠券
   * 
   * @return
   * @throws Exception
   */
  public String insertUserCouponBatch() throws Exception {
    if (userCouponPojo != null) {
      // 判断是否指定商品
      if (userCouponPojo.getCouponId() != null) {
        couponPojo = couponService.getcouponById(userCouponPojo.getCouponId());
        if (couponPojo != null && couponPojo.getIsProduct() == 1
            && couponPojo.getProductId() != null) {
          userCouponPojo.setProductId(couponPojo.getProductId());
        }
      }

      try {
        for (int i = 1; i <= userCouponPojo.getNum(); i++) {
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          long gentime = sdf.parse(StringUtil.dateString(new Date())).getTime();
          // 优惠券码规则：时间秒+5位数字随机
          //userCouponPojo.setCouponNo(String.valueOf(gentime / 1000) + StringUtil.genRandomStr(5));
          IdWorker idWorker = new IdWorker(0, 0);
          userCouponPojo.setCouponNo(String.valueOf(idWorker.nextId()));
          Date sd = sdf.parse(userCouponPojo.getValidstimeStr());
          Date ed = sdf.parse(userCouponPojo.getValidetimeStr());
          userCouponPojo.setValidstime(sd.getTime() / 1000);
          userCouponPojo.setValidetime(ed.getTime() / 1000);
          // 用户
          userCouponPojo.setUserId(0L);
          // userCouponPojo.setStatus(0);
          userCouponPojo.setGenTime(gentime / 1000);
          userCouponPojo.setUsed(0);
          userCouponPojo.setUseTime(0L);
          userCouponPojo.setSource(0);
          couponService.addUserCoupon(userCouponPojo);
        }
        FileUtil.alertMessageBySkip("新增成功！", "userCouponList.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("新增失败！", "userCouponList.do");
      }
    } else {
      FileUtil.alertMessageBySkip("新增失败！", "userCouponList.do");
    }
    return null;
  }

  //---------------------------------批量新增优惠券结束-----------------------------//

  /**
   * 编辑优惠券页面
   * 
   * @return
   * @throws Exception
   */
  public String goUpdateUserCoupon() throws Exception {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Map<String, Object> param = new HashMap<String, Object>();
    param.put("status", 1);
    couponList = couponService.getcouponList(param);
    param.clear();
    param.put("couponNo", userCouponPojo.getCouponNo());
    userCouponPojo = couponService.getUserCouponByNo(param);
    if (userCouponPojo != null) {
      long time3 = userCouponPojo.getValidstime();
      Date s = new Date(time3 * 1000);
      userCouponPojo.setValidstimeStr(sdf.format(s));
      long time4 = userCouponPojo.getValidetime();
      Date e = new Date(time4 * 1000);
      userCouponPojo.setValidetimeStr(sdf.format(e));

      sysLoginPojo = sysLoginService.findSysLoginById(userCouponPojo.getUserId());
      if (sysLoginPojo == null) {
        userCouponPojo.setLoginname("");
      } else {
        userCouponPojo.setLoginname(sysLoginPojo.getLoginname());
      }
    }

    return SUCCESS;
  }

  /**
   * 编辑优惠券
   * 
   * @return
   * @throws Exception
   */
  public String updateUserCoupon() throws Exception {
    if (userCouponPojo != null) {
      Map<String, Object> param = new HashMap<String, Object>();
      param.put("couponNo", userCouponPojo.getCouponNo());
      UserCouponPojo userCoupon = couponService.getUserCouponByNo(param);
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      long gentime = sdf.parse(StringUtil.dateString(new Date())).getTime();

      Date sd = sdf.parse(userCouponPojo.getValidstimeStr());
      Date ed = sdf.parse(userCouponPojo.getValidetimeStr());
      userCoupon.setValidstime(sd.getTime() / 1000);
      userCoupon.setValidetime(ed.getTime() / 1000);
      userCoupon.setCouponId(userCouponPojo.getCouponId());
      userCoupon.setStatus(userCouponPojo.getStatus());
      userCoupon.setGenTime(gentime / 1000);

      if (!"".equals(userCouponPojo.getLoginname())) {
        sysLoginPojo = sysLoginService.getSysLoginByLoginName(userCouponPojo.getLoginname());
        if (sysLoginPojo != null) {
          userCoupon.setUserId(sysLoginPojo.getId());
          userCoupon.setSource(6);
          // List<SysLoginPojo> sysLoginPojos = new ArrayList<SysLoginPojo>();
          // sysLoginPojos = sysLoginService.sysLoginAllList(sysLoginPojo, null, null);
          // if (sysLoginPojos.size() != 0) {
          // userCoupon.setUserId(sysLoginPojos.get(0).getId());
        } else {
          // FileUtil.alertMessageBySkip("该用户帐号不存在！", "userCouponList.do");
          // userCoupon.setUserId((long)0);
          // userCoupon.setSource(0);
          FileUtil.alertMessageBySkip("用户名不存在！请输入正确用户名。",
              "goUpdateUserCoupon.do?userCouponPojo.couponNo=" + userCouponPojo.getCouponNo());
        }
      }
      try {
        couponService.updateUserCoupon(userCoupon);
        FileUtil.alertMessageBySkip("提交成功！", "userCouponList.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("提交失败！", "userCouponList.do");
      }
    } else {
      FileUtil.alertMessageBySkip("提交失败！", "userCouponList.do");
    }
    return null;
  }

  /**
   * @return 审核通过
   * @throws SQLException
   */
  public String checkUserCoupon() throws SQLException {
    if (userCouponPojo != null) {
      userCouponPojo.setStatus(1);
      try {
        couponService.checkUserCoupon(userCouponPojo);
        this.result = "1";
      } catch (Exception e) {
        e.printStackTrace();
        this.result = "0";
      }
    } else {
      this.result = "0";
    }

    return SUCCESS;
  }

  /**
   * @return 审核通过
   * @throws SQLException
   */
  public String uncheckUserCoupon() throws SQLException {
    if (userCouponPojo != null) {
      userCouponPojo.setStatus(0);
      try {
        couponService.checkUserCoupon(userCouponPojo);
        this.result = "1";
      } catch (Exception e) {
        e.printStackTrace();
        this.result = "0";
      }
    } else {
      this.result = "0";
    }

    return SUCCESS;
  }

  /**
   * @return 删除优惠券
   * @throws SQLException
   */
  public String delUserCoupon() throws SQLException {
    if (userCouponPojo != null) {
      try {
        couponService.delUserCouponByNo(userCouponPojo.getCouponNo());
        this.result = "1";
      } catch (Exception e) {
        e.printStackTrace();
        this.result = "0";
      }
    } else {
      this.result = "0";
    }

    return SUCCESS;
  }

  public UserCouponPojo getUserCouponPojo() {
    return userCouponPojo;
  }

  public void setUserCouponPojo(UserCouponPojo userCouponPojo) {
    this.userCouponPojo = userCouponPojo;
  }

  public List<UserCouponPojo> getUserCouponPojos() {
    return userCouponPojos;
  }

  public void setUserCouponPojos(List<UserCouponPojo> userCouponPojos) {
    this.userCouponPojos = userCouponPojos;
  }

  private LotteryLogPojo lotteryLogPojo;
  private List<LotteryLogPojo> lotteryLogPojos;

  public LotteryLogPojo getLotteryLogPojo() {
    return lotteryLogPojo;
  }

  public void setLotteryLogPojo(LotteryLogPojo lotteryLogPojo) {
    this.lotteryLogPojo = lotteryLogPojo;
  }

  public List<LotteryLogPojo> getLotteryLogPojos() {
    return lotteryLogPojos;
  }

  public void setLotteryLogPojos(List<LotteryLogPojo> lotteryLogPojos) {
    this.lotteryLogPojos = lotteryLogPojos;
  }

  /**
   * 抽奖记录
   * 
   * @return
   * @throws Exception
   */
  public String lotteryLogListCount() throws Exception {

    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      if (lotteryLogPojo != null) {
        map.put("prize", lotteryLogPojo.getPrize().trim());
        map.put("prizeType", lotteryLogPojo.getPrizeType());
        map.put("prizeVal", lotteryLogPojo.getPrizeVal().trim());
        if (lotteryLogPojo.getTimeDStr().length() >= 10) {
          Date t = sdf.parse(lotteryLogPojo.getTimeDStr() + " 00:00:00");
          lotteryLogPojo.setTime(t.getTime() / 1000);
          map.put("time", lotteryLogPojo.getTime());
        }
      }
      page.setRowCount(couponService.getLotteryLogList(map).size());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  /**
   * 抽奖记录列表
   * 
   * @return
   * @throws Exception
   */
  public String lotteryLogListAll() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (lotteryLogPojo != null) {
      map.put("prize", lotteryLogPojo.getPrize().trim());
      map.put("prizeType", lotteryLogPojo.getPrizeType());
      map.put("prizeVal", lotteryLogPojo.getPrizeVal().trim());
      if (lotteryLogPojo.getTimeDStr().length() >= 10) {
        Date t = sdf.parse(lotteryLogPojo.getTimeDStr() + " 00:00:00");
        lotteryLogPojo.setTime(t.getTime() / 1000);
        map.put("time", lotteryLogPojo.getTime());
      }
    }
    lotteryLogPojos = couponService.getLotteryLogList(map);
    if (lotteryLogPojos != null) {
      for (LotteryLogPojo l : lotteryLogPojos) {
        long time = l.getTime();
        Date t = new Date(time * 1000);
        l.setTimeDStr(sdf.format(t));
      }
    }
    JSONArray json = JSONArray.fromObject(lotteryLogPojos);
    page.setRowCount(lotteryLogPojos.size());
    page.setResult(json.toString());
    return SUCCESS;
  }

  private LotteryForwardPojo lotteryForwardPojo;
  private List<LotteryForwardPojo> lotteryForwardPojos;

  /**
   * 转发抽奖记录
   * 
   * @return
   * @throws Exception
   */
  public String lotteryForwardListCount() throws Exception {

    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      if (lotteryForwardPojo != null) {
        map.put("userName", lotteryForwardPojo.getUserName().trim());
        if (lotteryForwardPojo.getTimeDStr().length() >= 10) {
          Date t = sdf.parse(lotteryForwardPojo.getTimeDStr() + " 00:00:00");
          lotteryForwardPojo.setTime((int) (t.getTime() / 1000));
          map.put("time", lotteryForwardPojo.getTime());
        }
      }
      page.setRowCount(couponService.getLotteryForwardList(map).size());
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }

    return SUCCESS;
  }

  /**
   * 转发抽奖记录列表
   * 
   * @return
   * @throws Exception
   */
  public String lotteryForwardListAll() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (lotteryForwardPojo != null) {
      map.put("userName", lotteryForwardPojo.getUserName().trim());
      if (lotteryForwardPojo.getTimeDStr().length() >= 10) {
        Date t = sdf.parse(lotteryForwardPojo.getTimeDStr() + " 00:00:00");
        lotteryForwardPojo.setTime((int) (t.getTime() / 1000));
        map.put("time", lotteryForwardPojo.getTime());
      }
    }
    lotteryForwardPojos = couponService.getLotteryForwardList(map);
    if (lotteryForwardPojos != null) {
      for (LotteryForwardPojo l : lotteryForwardPojos) {
        long time = l.getTime();
        Date t = new Date(time * 1000);
        l.setTimeDStr(sdf.format(t));
      }
    }
    JSONArray json = JSONArray.fromObject(lotteryForwardPojos);
    page.setRowCount(lotteryForwardPojos.size());
    page.setResult(json.toString());
    return SUCCESS;
  }

  public LotteryForwardPojo getLotteryForwardPojo() {
    return lotteryForwardPojo;
  }

  public void setLotteryForwardPojo(LotteryForwardPojo lotteryForwardPojo) {
    this.lotteryForwardPojo = lotteryForwardPojo;
  }

  public List<LotteryForwardPojo> getLotteryForwardPojos() {
    return lotteryForwardPojos;
  }

  public void setLotteryForwardPojos(List<LotteryForwardPojo> lotteryForwardPojos) {
    this.lotteryForwardPojos = lotteryForwardPojos;
  }

  private CouponOrderPojo couponOrderPojo;
  private List<CouponOrderPojo> couponOrderPojos;

  public CouponOrderPojo getCouponOrderPojo() {
    return couponOrderPojo;
  }

  public void setCouponOrderPojo(CouponOrderPojo couponOrderPojo) {
    this.couponOrderPojo = couponOrderPojo;
  }

  public List<CouponOrderPojo> getCouponOrderPojos() {
    return couponOrderPojos;
  }

  public void setCouponOrderPojos(List<CouponOrderPojo> couponOrderPojos) {
    this.couponOrderPojos = couponOrderPojos;
  }

  /**
   * 优惠券订单关联记录
   * 
   * @return
   * @throws Exception
   */
  public String couponOrderListCount() throws Exception {

    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      if (couponOrderPojo != null) {
        map.put("orderNo", couponOrderPojo.getOrderNo().trim());
        map.put("couponNo", couponOrderPojo.getCouponNo().trim());
        if (couponOrderPojo.getRelTimeDStr().length() >= 10) {
          Date r = sdf.parse(couponOrderPojo.getRelTimeDStr() + " 00:00:00");
          couponOrderPojo.setRelTime(r.getTime() / 1000);
          map.put("relTime", couponOrderPojo.getRelTime());
        }
      }
      page.setRowCount(couponService.getcouponOrderList(map).size());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  /**
   * 优惠券订单关联列表
   * 
   * @return
   * @throws Exception
   */
  public String couponOrderListAll() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (couponOrderPojo != null) {
      map.put("orderNo", couponOrderPojo.getOrderNo().trim());
      map.put("couponNo", couponOrderPojo.getCouponNo().trim());
      if (couponOrderPojo.getRelTimeDStr().length() >= 10) {
        Date r = sdf.parse(couponOrderPojo.getRelTimeDStr() + " 00:00:00");
        couponOrderPojo.setRelTime(r.getTime() / 1000);
        map.put("relTime", couponOrderPojo.getRelTime());
      }
    }
    couponOrderPojos = couponService.getcouponOrderList(map);
    if (couponOrderPojos != null) {
      for (CouponOrderPojo c : couponOrderPojos) {
        long time = c.getRelTime();
        Date t = new Date(time * 1000);
        c.setRelTimeDStr(sdf.format(t));
      }
    }
    JSONArray json = JSONArray.fromObject(couponOrderPojos);
    page.setRowCount(couponOrderPojos.size());
    page.setResult(json.toString());
    return SUCCESS;
  }

  private LotteryPojo lotteryPojo;
  private List<LotteryPojo> lotteryPojos;

  public LotteryPojo getLotteryPojo() {
    return lotteryPojo;
  }

  public void setLotteryPojo(LotteryPojo lotteryPojo) {
    this.lotteryPojo = lotteryPojo;
  }

  public List<LotteryPojo> getLotteryPojos() {
    return lotteryPojos;
  }

  public void setLotteryPojos(List<LotteryPojo> lotteryPojos) {
    this.lotteryPojos = lotteryPojos;
  }

  private HongbaoLogPojo hongbaoLogPojo;
  private List<HongbaoLogPojo> hongbaoLogPojos;

  public HongbaoLogPojo getHongbaoLogPojo() {
    return hongbaoLogPojo;
  }

  public void setHongbaoLogPojo(HongbaoLogPojo hongbaoLogPojo) {
    this.hongbaoLogPojo = hongbaoLogPojo;
  }

  public List<HongbaoLogPojo> getHongbaoLogPojos() {
    return hongbaoLogPojos;
  }

  public void setHongbaoLogPojos(List<HongbaoLogPojo> hongbaoLogPojos) {
    this.hongbaoLogPojos = hongbaoLogPojos;
  }

  /**
   * 抽奖
   * 
   * @return
   * @throws Exception
   */
  public String lotteryListCount() throws Exception {

    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      if (lotteryPojo != null) {
        map.put("subject", lotteryPojo.getSubject().trim());
        map.put("status", lotteryPojo.getStatus());
        if (lotteryPojo.getStartTimeDStr().length() >= 10) {
          Date s = sdf.parse(lotteryPojo.getStartTimeDStr() + " 00:00:00");
          lotteryPojo.setStartTime(s.getTime() / 1000);
          map.put("startTime", lotteryPojo.getStartTime());
        }
        if (lotteryPojo.getEndTimeDStr().length() >= 10) {
          Date e = sdf.parse(lotteryPojo.getEndTimeDStr() + " 00:00:00");
          lotteryPojo.setEndTime(e.getTime() / 1000);
          map.put("endTime", lotteryPojo.getEndTime());
        }
      }
      page.setRowCount(couponService.getLotteryList(map).size());
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }

    return SUCCESS;
  }

  /**
   * 抽奖列表
   * 
   * @return
   * @throws Exception
   */
  public String lotteryListAll() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (lotteryPojo != null) {
      map.put("subject", lotteryPojo.getSubject().trim());
      map.put("status", lotteryPojo.getStatus());
      if (lotteryPojo.getStartTimeDStr().length() >= 10) {
        Date s = sdf.parse(lotteryPojo.getStartTimeDStr() + " 00:00:00");
        lotteryPojo.setStartTime(s.getTime() / 1000);
        map.put("startTime", lotteryPojo.getStartTime());
      }
      if (lotteryPojo.getEndTimeDStr().length() >= 10) {
        Date e = sdf.parse(lotteryPojo.getEndTimeDStr() + " 00:00:00");
        lotteryPojo.setEndTime(e.getTime() / 1000);
        map.put("endTime", lotteryPojo.getEndTime());
      }
    }
    lotteryPojos = couponService.getLotteryList(map);
    if (lotteryPojos != null) {
      for (LotteryPojo l : lotteryPojos) {
        long time = l.getStartTime();
        Date s = new Date(time * 1000);
        l.setStartTimeDStr(sdf.format(s));
        long time2 = l.getEndTime();
        Date e = new Date(time2 * 1000);
        l.setEndTimeDStr(sdf.format(e));
      }
    }
    JSONArray json = JSONArray.fromObject(lotteryPojos);
    page.setRowCount(lotteryPojos.size());
    page.setResult(json.toString());
    return SUCCESS;
  }

  /**
   * 红包记录
   * 
   * @return
   * @throws Exception
   */
  public String hongbaoLogListCount() throws Exception {

    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      if (hongbaoLogPojo != null) {
        map.put("userName", hongbaoLogPojo.getUserName().trim());
        if (hongbaoLogPojo.getLogTimeDStr().length() >= 10) {
          Date l = sdf.parse(hongbaoLogPojo.getLogTimeDStr() + " 00:00:00");
          hongbaoLogPojo.setLogTime(l.getTime() / 1000);
          map.put("logTime", hongbaoLogPojo.getLogTime());
        }
        String ms = hongbaoLogPojo.getMoneyStr().trim();
        if (ms.length() != 0) {
          Pattern pattern =
              Pattern.compile("[-+]{0,1}\\d+\\.\\d*|[-+]{0,1}\\d*\\.\\d+|[-+]{0,1}\\d+");
          Matcher isNum = pattern.matcher(ms);
          if (isNum.matches()) {
            hongbaoLogPojo.setMoney(Float.valueOf(ms));
            map.put("money", hongbaoLogPojo.getMoney() * 100);
          }
        }
        map.put("remark", hongbaoLogPojo.getRemark().trim());
      }
      page.setRowCount(couponService.getHongbaoLogList(map).size());
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }

    return SUCCESS;
  }

  /**
   * 红包记录列表
   * 
   * @return
   * @throws Exception
   */
  public String hongbaoLogListAll() throws Exception {
    Map<String, Object> map = new HashMap<String, Object>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    if (page == null) {
      page = new Pager();
    }
    map.put("pageSize", 10);
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (hongbaoLogPojo != null) {
      map.put("userName", hongbaoLogPojo.getUserName().trim());
      if (hongbaoLogPojo.getLogTimeDStr().length() >= 10) {
        Date l = sdf.parse(hongbaoLogPojo.getLogTimeDStr() + " 00:00:00");
        hongbaoLogPojo.setLogTime(l.getTime() / 1000);
        map.put("logTime", hongbaoLogPojo.getLogTime());
      }
      String ms = hongbaoLogPojo.getMoneyStr().trim();
      if (ms.length() != 0) {
        Pattern pattern =
            Pattern.compile("[-+]{0,1}\\d+\\.\\d*|[-+]{0,1}\\d*\\.\\d+|[-+]{0,1}\\d+");
        Matcher isNum = pattern.matcher(ms);
        if (isNum.matches()) {
          hongbaoLogPojo.setMoney(Float.valueOf(ms));
          map.put("money", hongbaoLogPojo.getMoney() * 100);
        }
      }
      map.put("remark", hongbaoLogPojo.getRemark().trim());
    }
    hongbaoLogPojos = couponService.getHongbaoLogList(map);
    if (hongbaoLogPojos != null) {
      for (HongbaoLogPojo h : hongbaoLogPojos) {
        long time = h.getLogTime();
        Date l = new Date(time * 1000);
        h.setLogTimeDStr(sdf.format(l));
        if (h.getMoney() != null) {
          h.setMoney(h.getMoney() / 100);
        }
      }
    }
    JSONArray json = JSONArray.fromObject(hongbaoLogPojos);
    page.setRowCount(hongbaoLogPojos.size());
    page.setResult(json.toString());
    return SUCCESS;
  }


}
