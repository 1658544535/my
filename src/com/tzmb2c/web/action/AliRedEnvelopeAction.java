/*
 * 文 件 名: AliRedEnvelopeAction.java 创 建 人: admin 创建时间: 2017-01-06
 */
package com.tzmb2c.web.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.utils.UserUtil;
import com.tzmb2c.web.pojo.AliRedEnvelopePojo;
import com.tzmb2c.web.pojo.SysLoginPojo;
import com.tzmb2c.web.service.AliRedEnvelopeService;

public class AliRedEnvelopeAction extends SuperAction {
  @Autowired
  private AliRedEnvelopeService aliRedEnvelopeService;
  private AliRedEnvelopePojo aliRedEnvelopePojo;
  private Long id;
  private String[] tids;
  private String result;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public AliRedEnvelopePojo getAliRedEnvelopePojo() {
    return aliRedEnvelopePojo;
  }

  public void setAliRedEnvelopePojo(AliRedEnvelopePojo aliRedEnvelopePojo) {
    this.aliRedEnvelopePojo = aliRedEnvelopePojo;
  }

  /**
   * 查询全部条数
   */
  public String goAliRedEnvelope() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    try {
      int i = aliRedEnvelopeService.countBy(null);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部条数
   */
  public String aliRedEnvelopeRowCnt() throws Exception {
    if (page == null) {
      page = new Pager();
    }
    Map<String, Object> map = new HashMap<String, Object>();
    try {
      if (aliRedEnvelopePojo != null) {
        map.put("inviteCode", aliRedEnvelopePojo.getInviteCode());
        map.put("isUsed", aliRedEnvelopePojo.getIsUsed());
        map.put("attendId", aliRedEnvelopePojo.getAttendId());
      }
      int i = aliRedEnvelopeService.countBy(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部记录
   */
  public String aliRedEnvelopeList() throws Exception {
    if (page == null) {
      page = new Pager();
      page.setPageNo(1);
    }
    page.setPageSize(10);
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("pageSize", page.getPageSize());
    map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
    if (aliRedEnvelopePojo != null) {
      map.put("inviteCode", aliRedEnvelopePojo.getInviteCode());
      map.put("isUsed", aliRedEnvelopePojo.getIsUsed());
      map.put("attendId", aliRedEnvelopePojo.getAttendId());
      map.put("orderBy", "create_date desc");
    }
    List<AliRedEnvelopePojo> aliRedEnvelopeList = null;
    try {
      aliRedEnvelopeList = aliRedEnvelopeService.listPage(map);
      JSONArray json = JSONArray.fromObject(aliRedEnvelopeList);
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
  public String goAddAliRedEnvelope() throws Exception {
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
    if (user != null && aliRedEnvelopePojo != null) {
      aliRedEnvelopePojo.setCreateBy(user.getId());
      aliRedEnvelopePojo.setCreateDate(new Date());
      aliRedEnvelopePojo.setUpdateBy(user.getId());
      aliRedEnvelopePojo.setUpdateDate(new Date());
      try {
        aliRedEnvelopeService.add(aliRedEnvelopePojo);
        FileUtil.alertMessageBySkip("新增成功！", "goAliRedEnvelope.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("新增失败！", "goAddAliRedEnvelope.do");
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goAliRedEnvelope.do");
    }
    return null;
  }

  /**
   * 编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goEditAliRedEnvelope() throws Exception {
    if (id != null && id > 0) {
      aliRedEnvelopePojo = aliRedEnvelopeService.getById(id);
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
    if (user != null && aliRedEnvelopePojo != null) {
      aliRedEnvelopePojo.setUpdateBy(user.getId());
      aliRedEnvelopePojo.setUpdateDate(new Date());
      try {
        aliRedEnvelopeService.update(aliRedEnvelopePojo);
        FileUtil.alertMessageBySkip("提交成功！", "goAliRedEnvelope.do");
      } catch (Exception e) {
        e.printStackTrace();
        FileUtil.alertMessageBySkip("提交失败！",
            "goEditAliRedEnvelope.do?id=" + aliRedEnvelopePojo.getId());
      }
    } else {
      FileUtil.alertMessageBySkip("操作失败！", "goAliRedEnvelope.do");
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
    result = "0";
    if (user != null && id != null && id > 0) {
      aliRedEnvelopePojo = new AliRedEnvelopePojo();
      aliRedEnvelopePojo.setId(id);
      aliRedEnvelopePojo.setUpdateBy(user.getId());
      aliRedEnvelopePojo.setUpdateDate(new Date());
      try {
        aliRedEnvelopeService.update(aliRedEnvelopePojo);
        result = "1";
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
    result = "0";
    if (user != null && id != null && id > 0) {
      aliRedEnvelopePojo = new AliRedEnvelopePojo();
      aliRedEnvelopePojo.setId(id);
      aliRedEnvelopePojo.setUpdateBy(user.getId());
      aliRedEnvelopePojo.setUpdateDate(new Date());
      try {
        aliRedEnvelopeService.update(aliRedEnvelopePojo);
        result = "1";
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
    result = "0";
    if (user != null && id != null && id > 0) {
      try {
        aliRedEnvelopeService.delete(id);
        result = "1";
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
    result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      aliRedEnvelopePojo = new AliRedEnvelopePojo();
      for (String tid : tids) {
        aliRedEnvelopePojo.setId(Long.valueOf(tid));
        aliRedEnvelopePojo.setUpdateBy(user.getId());
        aliRedEnvelopePojo.setUpdateDate(new Date());
        try {
          aliRedEnvelopeService.update(aliRedEnvelopePojo);
        } catch (Exception e) {
          part = true;
          e.printStackTrace();
        }
      }
      if (part) {
        result = "2";
      } else {
        result = "1";
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
    result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      aliRedEnvelopePojo = new AliRedEnvelopePojo();
      for (String tid : tids) {
        aliRedEnvelopePojo.setId(Long.valueOf(tid));
        aliRedEnvelopePojo.setUpdateBy(user.getId());
        aliRedEnvelopePojo.setUpdateDate(new Date());
        try {
          aliRedEnvelopeService.update(aliRedEnvelopePojo);
        } catch (Exception e) {
          part = true;
          e.printStackTrace();
        }
      }
      if (part) {
        result = "2";
      } else {
        result = "1";
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
    result = "0";
    if (user != null && tids != null && tids.length > 0) {
      boolean part = false;
      for (String tid : tids) {
        try {
          aliRedEnvelopeService.delete(Long.valueOf(tid));
        } catch (Exception e) {
          part = true;
          e.printStackTrace();
        }
      }
      if (part) {
        result = "2";
      } else {
        result = "1";
      }
    }
    return SUCCESS;
  }
}
