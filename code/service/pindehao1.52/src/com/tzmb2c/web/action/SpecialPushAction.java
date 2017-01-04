package com.tzmb2c.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import maowu.framework.utils.web.SuperAction;
import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.tzmb2c.common.Pager;
import com.tzmb2c.utils.FileUtil;
import com.tzmb2c.web.pojo.SpecialPushPojo;
import com.tzmb2c.web.service.SpecialPushService;
import com.tzmb2c.web.service.SpecialShowService;

public class SpecialPushAction extends SuperAction {
  @Autowired
  private SpecialShowService specialShowService;
  @Autowired
  private SpecialPushService specialPushService;
  private SpecialPushPojo specialPushPojo;
  private List<SpecialPushPojo> specialPushPojoList;
  private String[] tids;

  public String[] getTids() {
    return tids;
  }

  public void setTids(String[] tids) {
    this.tids = tids;
  }

  public SpecialPushPojo getSpecialPushPojo() {
    return specialPushPojo;
  }

  public void setSpecialPushPojo(SpecialPushPojo specialPushPojo) {
    this.specialPushPojo = specialPushPojo;
  }

  public List<SpecialPushPojo> getSpecialPushPojoList() {
    return specialPushPojoList;
  }

  public void setSpecialPushPojoList(List<SpecialPushPojo> specialPushPojoList) {
    this.specialPushPojoList = specialPushPojoList;
  }

  /**
   * 查询全部条数
   */
  public String specialPushListCount() throws Exception {
    try {
      if (page == null) {
        page = new Pager();
      }
      Map<String, Object> map = new HashMap<String, Object>();
      if (specialPushPojo != null) {
        map.put("specialName", specialPushPojo.getSpecialName());
        map.put("status", specialPushPojo.getStatus());
      }
      int i = specialPushService.findSpecialPushCount(map);
      page.setRowCount(i);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 查询全部内容
   */
  public String specialPushListAll() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      if (page == null) {
        page = new Pager();
      }
      map.put("pageSize", 10);
      map.put("pageNo", (page.getPageNo() - 1) * page.getPageSize());
      if (specialPushPojo != null) {
        map.put("specialName", specialPushPojo.getSpecialName());
        map.put("status", specialPushPojo.getStatus());
      }
      specialPushPojoList = specialPushService.findSpecialPushList(map);
      JSONArray json = JSONArray.fromObject(specialPushPojoList);
      page.setRowCount(specialPushPojoList.size());
      page.setResult(json.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return SUCCESS;
  }

  /**
   * 根据id删除
   * 
   * @return
   */
  public String delSpecialPush() throws Exception {
    try {
      specialPushService.delSpecialPush(specialPushPojo.getId());
      FileUtil.alertMessageBySkip("删除成功！", "specialPushList.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("删除失败！", "specialPushList.do");
    }
    return null;
  }

  /**
   * 根据id批量删除
   * 
   * @return
   */
  public String delSpecialPushAll() {
    try {
      for (String id : tids) {
        specialPushService.delSpecialPush(Long.valueOf(id));
      }
      FileUtil.alertMessageBySkip("全部删除成功！", "specialPushList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("全部删除失败！", "specialPushList.do");
    }
    return null;
  }

  /**
   * 根据id审核
   * 
   * @return
   */
  public String checkSpecialPush() throws Exception {
    try {
      specialPushService.checkSpecialPush(specialPushPojo.getId());
      FileUtil.alertMessageBySkip("审核成功！", "specialPushList.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("审核失败！", "specialPushList.do");
    }
    return null;
  }

  /**
   * 根据id取审
   * 
   * @return
   */
  public String uncheckSpecialPush() throws Exception {
    try {
      specialPushService.uncheckSpecialPush(specialPushPojo.getId());
      FileUtil.alertMessageBySkip("取审成功！", "specialPushList.do");
    } catch (Exception e) {
      FileUtil.alertMessageBySkip("取审失败！", "specialPushList.do");
    }
    return null;
  }

  /**
   * 根据id批量通过审核
   * 
   * @return
   */
  public String checkSpecialPushAll() {
    try {
      for (String id : tids) {
        specialPushService.checkSpecialPush(Long.valueOf(id));
      }
      FileUtil.alertMessageBySkip("全部通过审核成功！", "specialPushList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("全部通过审核失败！", "specialPushList.do");
    }
    return null;
  }

  /**
   * 跳转编辑页面
   * 
   * @return
   * @throws Exception
   */
  public String goUpdateSpecialPush() throws Exception {
    specialPushPojo = specialPushService.findSpecialPushById(specialPushPojo.getId());
    return SUCCESS;
  }

  /**
   * 编辑提交
   * 
   * @return
   * @throws Exception
   */
  public String updateSpecialPush() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("specialId", specialPushPojo.getSpecialId());
      specialPushPojoList = specialPushService.findSpecialPushList(map);
      if (specialPushPojoList.size() != 0) {
        FileUtil.alertMessageBySkip("此专场已被推送，请重新输入！", "specialPushList.do");
        return null;
      } else if (specialShowService.findSpecialShowById(specialPushPojo.getSpecialId()) == null) {
        FileUtil.alertMessageBySkip("查无此专场，请重新输入！", "specialPushList.do");
        return null;
      } else if (specialShowService.findSpecialShowById(specialPushPojo.getSpecialId()).getStatus() != 4) {
        FileUtil.alertMessageBySkip("此专场尚未审核以及排期，请重新输入！", "specialPushList.do");
        return null;
      }
      specialPushService.updateSpecialPush(specialPushPojo);
      FileUtil.alertMessageBySkip("提交成功！", "specialPushList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("提交失败！", "specialPushList.do");
    }
    return null;
  }

  /**
   * 跳转新增页面
   * 
   * @return
   * @throws Exception
   */
  public String addSpecialPush() throws Exception {
    return SUCCESS;
  }

  /**
   * 新增提交
   * 
   * @return
   * @throws Exception
   */
  public String addSpecialPushOk() throws Exception {
    try {
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("specialId", specialPushPojo.getSpecialId());
      specialPushPojoList = specialPushService.findSpecialPushList(map);
      if (specialPushPojoList.size() != 0) {
        FileUtil.alertMessageBySkip("此专场已被推送，请重新输入！", "specialPushList.do");
        return null;
      } else if (specialShowService.findSpecialShowById(specialPushPojo.getSpecialId()) == null) {
        FileUtil.alertMessageBySkip("查无此专场，请重新输入！", "specialPushList.do");
        return null;
      } else if (specialShowService.findSpecialShowById(specialPushPojo.getSpecialId()).getStatus() != 4) {
        FileUtil.alertMessageBySkip("此专场尚未审核以及排期，请重新输入！", "specialPushList.do");
        return null;
      }
      specialPushService.insertSpecialPush(specialPushPojo);
      FileUtil.alertMessageBySkip("新增成功！", "specialPushList.do");
    } catch (Exception e) {
      e.printStackTrace();
      FileUtil.alertMessageBySkip("新增失败！", "specialPushList.do");
    }
    return null;
  }
}
