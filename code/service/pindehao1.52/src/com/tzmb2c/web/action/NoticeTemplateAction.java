/*
 * 文 件 名: NoticeTemplateAction.java 创 建 人: admin 创建时间: 2016-12-01
 */
package com.tzmb2c.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.NoticeTemplatePojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.NoticeTemplateService;

public class NoticeTemplateAction extends SuperAction {
  @Autowired
  private NoticeTemplateService noticeTemplateService;
  private NoticeTemplatePojo noticeTemplatePojo;
  private Long id;
  private String[] tids;
  private String result;
  private String title;
  private String img;
  private String name;
  private String price;
  private String pname;
  private String address;
  private String orderNo;
  private String drawDate;
  private String drawStatus;
  private String express;
  private String expressNo;
  private String num;
  private String refundItem;
  private String refundReason;
  private String refundDate;
  private Integer type;
  private String linkType;
  private String info;
  private String remark;
  private String lname;
  private String param;
  private String paramValue;
  private String refundPrice;

  public String getRefundPrice() {
    return refundPrice;
  }

  public void setRefundPrice(String refundPrice) {
    this.refundPrice = refundPrice;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getParamValue() {
    return paramValue;
  }

  public void setParamValue(String paramValue) {
    this.paramValue = paramValue;
  }

  public String getNum() {
    return num;
  }

  public void setNum(String num) {
    this.num = num;
  }

  public String getLinkType() {
    return linkType;
  }

  public void setLinkType(String linkType) {
    this.linkType = linkType;
  }

  public String[] getTids() {
    return this.tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public String getResult() {
    return this.result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getPname() {
    return pname;
  }

  public void setPname(String pname) {
    this.pname = pname;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getOrderNo() {
    return orderNo;
  }

  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo;
  }

  public String getDrawDate() {
    return drawDate;
  }

  public void setDrawDate(String drawDate) {
    this.drawDate = drawDate;
  }

  public String getDrawStatus() {
    return drawStatus;
  }

  public void setDrawStatus(String drawStatus) {
    this.drawStatus = drawStatus;
  }

  public String getExpress() {
    return express;
  }

  public void setExpress(String express) {
    this.express = express;
  }

  public String getExpressNo() {
    return expressNo;
  }

  public void setExpressNo(String expressNo) {
    this.expressNo = expressNo;
  }

  public String getRefundItem() {
    return refundItem;
  }

  public void setRefundItem(String refundItem) {
    this.refundItem = refundItem;
  }

  public String getRefundReason() {
    return refundReason;
  }

  public void setRefundReason(String refundReason) {
    this.refundReason = refundReason;
  }

  public String getRefundDate() {
    return refundDate;
  }

  public void setRefundDate(String refundDate) {
    this.refundDate = refundDate;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getLname() {
    return lname;
  }

  public void setLname(String lname) {
    this.lname = lname;
  }

  public String getParam() {
    return param;
  }

  public void setParam(String param) {
    this.param = param;
  }

  public NoticeTemplatePojo getNoticeTemplatePojo() {
    return noticeTemplatePojo;
  }

  public void setNoticeTemplatePojo(NoticeTemplatePojo noticeTemplatePojo) {
    this.noticeTemplatePojo = noticeTemplatePojo;
  }

  /**
   * 查询全部条数
   */
  public String goNoticeTemplate() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    try {
      int i = noticeTemplateService.countBy(null);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部条数
   */
  public String noticeTemplateRowCnt() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      int i = noticeTemplateService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部记录
   */
  public String noticeTemplateList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    List<NoticeTemplatePojo> noticeTemplateList = null;
    try {
      noticeTemplateList = noticeTemplateService.listPage(map);
      JSONArray json = JSONArray.fromObject(noticeTemplateList);
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }

    return SUCCESS;
  }

  /**
   * 跳转新增页面
   * 
   * @return
   * @throws Exception
   */
  public String goAddNoticeTemplate() throws Exception {
    return SUCCESS;
  }

  /**
   * 新增提交
   * 
   * @return
   * @throws Throwable
   */
  public String add() throws Throwable {
    SysLoginPojo user = UserUtil.getUser();
    if (user != null && noticeTemplatePojo != null) {
      noticeTemplatePojo.setCreateBy(user.getId());
      noticeTemplatePojo.setCreateDate(new Date());
      noticeTemplatePojo.setUpdateBy(user.getId());
      noticeTemplatePojo.setUpdateDate(new Date());
      try {
        noticeTemplateService.add(noticeTemplatePojo);
        FileUtil.alertMessageBySkip("新增成功！", "goNoticeTemplate.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("新增失败！", "goAddNoticeTemplate.do");
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goNoticeTemplate.do");
    }
    return null;
  }

  /**
   * 编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goEditNoticeTemplate() throws Exception {
    if (id != null && id > 0) {
      noticeTemplatePojo = noticeTemplateService.getById(id);
      JSONObject jsonObject = JSONObject.fromObject(noticeTemplatePojo.getTemplate());
      // 读取template里面所有字段的值
      title = jsonObject.getString("title");
      img = jsonObject.getString("img");
      name = jsonObject.getString("name");

      if (noticeTemplatePojo.getType() != 6) {
        price = jsonObject.get("price") == null ? "" : jsonObject.getString("price");
      }
      if (noticeTemplatePojo.getType() != 3 && noticeTemplatePojo.getType() != 7) {
        pname = jsonObject.get("pname") == null ? "" : jsonObject.getString("pname");
      }
      if (noticeTemplatePojo.getType() == 2 || noticeTemplatePojo.getType() == 9) {
        address = jsonObject.get("address") == null ? "" : jsonObject.getString("address");
      }
      if (noticeTemplatePojo.getType() == 2 || noticeTemplatePojo.getType() == 3
          || noticeTemplatePojo.getType() == 7 || noticeTemplatePojo.getType() == 8
          || noticeTemplatePojo.getType() == 9) {
        orderNo = jsonObject.get("orderNo") == null ? "" : jsonObject.getString("orderNo");
      }
      if (noticeTemplatePojo.getType() == 4 || noticeTemplatePojo.getType() == 5) {
        drawDate = jsonObject.get("drawDate") == null ? "" : jsonObject.getString("drawDate");
        drawStatus = jsonObject.get("drawStatus") == null ? "" : jsonObject.getString("drawStatus");
      }
      if (noticeTemplatePojo.getType() == 6) {
        express = jsonObject.get("express") == null ? "" : jsonObject.getString("express");
        expressNo = jsonObject.get("expressNo") == null ? "" : jsonObject.getString("expressNo");
        num = jsonObject.get("num") == null ? "" : jsonObject.getString("num");
      }
      if (noticeTemplatePojo.getType() == 7 || noticeTemplatePojo.getType() == 8) {
        refundItem = jsonObject.get("refundItem") == null ? "" : jsonObject.getString("refundItem");
        refundReason =
            jsonObject.get("refundReason") == null ? "" : jsonObject.getString("refundReason");
        refundDate = jsonObject.get("refundDate") == null ? "" : jsonObject.getString("refundDate");
        refundPrice =
            jsonObject.get("refundPrice") == null ? "" : jsonObject.getString("refundPrice");
      }
      linkType = jsonObject.getString("type");
      info = jsonObject.getString("info");
      remark = jsonObject.getString("remark");
      lname = jsonObject.getString("lname");
      paramValue = jsonObject.getString("param");

    }
    return SUCCESS;
  }

  /**
   * 编辑提交
   * 
   * @return
   * @throws Throwable
   */
  public String update() throws Throwable {
    SysLoginPojo user = UserUtil.getUser();
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("type", type);
    params.put("isDelete", 0);
    params.put("status", 1);
    List<NoticeTemplatePojo> noticeTemplatePojos = noticeTemplateService.listPage(params);
    if (noticeTemplatePojos != null && noticeTemplatePojos.size() > 0) {
      NoticeTemplatePojo noticeTemplatePojo = noticeTemplatePojos.get(0);
      if (noticeTemplatePojo.getTemplate() != null && !"".equals(noticeTemplatePojo.getTemplate())
          && noticeTemplatePojo.getTemplate().length() > 2) {

        JSONObject jsonObject = JSONObject.fromObject(noticeTemplatePojo.getTemplate());

        jsonObject.put("info", info);
        jsonObject.put("remark", remark);
        jsonObject.put("lname", lname);
        jsonObject.put("param", param);
        if (linkType != null) {
          jsonObject.put("type", linkType);
        }

        if (user != null && noticeTemplatePojo != null) {
          noticeTemplatePojo.setUpdateBy(user.getId());
          noticeTemplatePojo.setUpdateDate(new Date());
          noticeTemplatePojo.setTemplate(jsonObject.toString());
          try {
            noticeTemplateService.update(noticeTemplatePojo);
            FileUtil.alertMessageBySkip("提交成功！", "goNoticeTemplate.do");
          } catch (Exception e) {
            e.printStackTrace();
            FileUtil.alertMessageBySkip("提交失败！",
                "goEditNoticeTemplate.do?id=" + noticeTemplatePojo.getId());
          }
        } else {
          FileUtil.alertMessageBySkip("操作失败！", "goNoticeTemplate.do");
        }
      }
    }
    return null;
  }

  /**
   * 根据id审核
   * 
   * @return
   */
  public String check() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    this.result = "0";
    if (user != null && id != null && id > 0) {
      noticeTemplatePojo = new NoticeTemplatePojo();
      noticeTemplatePojo.setId(id);
      noticeTemplatePojo.setStatus(1);
      noticeTemplatePojo.setUpdateBy(user.getId());
      noticeTemplatePojo.setUpdateDate(new Date());
      try {
        noticeTemplateService.update(noticeTemplatePojo);
        this.result = "1";
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return SUCCESS;
  }

  /**
   * 根据id取审
   * 
   * @return
   */
  public String uncheck() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    this.result = "0";
    if (user != null && id != null && id > 0) {
      noticeTemplatePojo = new NoticeTemplatePojo();
      noticeTemplatePojo.setId(id);
      noticeTemplatePojo.setStatus(0);
      noticeTemplatePojo.setUpdateBy(user.getId());
      noticeTemplatePojo.setUpdateDate(new Date());
      try {
        noticeTemplateService.update(noticeTemplatePojo);
        this.result = "1";
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return SUCCESS;
  }

  /**
   * 根据id删除
   * 
   * @return
   */
  public String delete() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    this.result = "0";
    if (user != null && id != null && id > 0) {
      try {
        noticeTemplateService.delete(id);
        this.result = "1";
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return SUCCESS;
  }

  /**
   * 审核选中
   * 
   * @return
   */
  public String checkAll() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    this.result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      noticeTemplatePojo = new NoticeTemplatePojo();
      noticeTemplatePojo.setStatus(1);
      for (String tid : tids) {
        noticeTemplatePojo.setId(Long.valueOf(tid));
        noticeTemplatePojo.setUpdateBy(user.getId());
        noticeTemplatePojo.setUpdateDate(new Date());
        try {
          noticeTemplateService.update(noticeTemplatePojo);
        } catch (Exception e) {
          part = true;
          e.printStackTrace();
        }
      }
      if (part) {
        this.result = "2";
      } else {
        this.result = "1";
      }
    }
    return SUCCESS;
  }

  /**
   * 选中取消审核
   * 
   * @return
   */
  public String uncheckAll() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    this.result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      noticeTemplatePojo = new NoticeTemplatePojo();
      noticeTemplatePojo.setStatus(0);
      for (String tid : tids) {
        noticeTemplatePojo.setId(Long.valueOf(tid));
        noticeTemplatePojo.setUpdateBy(user.getId());
        noticeTemplatePojo.setUpdateDate(new Date());
        try {
          noticeTemplateService.update(noticeTemplatePojo);
        } catch (Exception e) {
          part = true;
          e.printStackTrace();
        }
      }
      if (part) {
        this.result = "2";
      } else {
        this.result = "1";
      }
    }
    return SUCCESS;
  }

  /**
   * 删除选中
   * 
   * @return
   */
  public String deleteAll() throws Exception {
    SysLoginPojo user = UserUtil.getUser();
    this.result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      for (String tid : tids) {
        try {
          noticeTemplateService.delete(Long.valueOf(tid));
        } catch (Exception e) {
          part = true;
          e.printStackTrace();
        }
      }
      if (part) {
        this.result = "2";
      } else {
        this.result = "1";
      }
    }
    return SUCCESS;
  }
}
